package pers.jiangyinzuo.carbon.service.impl;

import io.lettuce.core.LettuceFutures;
import io.lettuce.core.RedisFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jiangyinzuo.carbon.common.StringUtil;
import pers.jiangyinzuo.carbon.dao.cache.QuestCache;
import pers.jiangyinzuo.carbon.dao.cache.SignInCache;
import pers.jiangyinzuo.carbon.domain.entity.QuestProgress;
import pers.jiangyinzuo.carbon.domain.vo.SignInVO;
import pers.jiangyinzuo.carbon.service.PropService;
import pers.jiangyinzuo.carbon.service.QuestService;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Jiang Yinzuo
 */
@Service
public class QuestServiceImpl implements QuestService {

    private QuestCache questCache;
    private SignInCache signInCache;
    private PropService propService;

    @Autowired
    public QuestServiceImpl(QuestCache questCache, SignInCache signInCache, PropService propService) {
        this.questCache = questCache;
        this.signInCache = signInCache;
        this.propService = propService;
    }

    @Override
    public QuestProgress getQuestProgress(Long userId) {
        return questCache.getQuestProgress(userId);
    }

    @Override
    public SignInVO signIn(Long userId, int signInDate) throws InterruptedException, ExecutionException, TimeoutException {
        int today = LocalDate.now().getDayOfMonth();
        RedisFuture<String> calendarFuture = signInCache.getSignInCalendarAsync(userId);

        // 0即今日签到
        if (signInDate == 0) {
            signInDate = today;
        }

        String calendar = calendarFuture.get(5, TimeUnit.SECONDS);

        int bitCalendar = calendar == null ? 0 : Integer.parseInt(calendar);

        // signInDate这一天已经签过到了
        if (((bitCalendar >> (signInDate - 1)) & 1) == 1) {
            return SignInVO.haveSignedIn();
        }

        // 更新签到日历
        bitCalendar += (1 << (signInDate - 1));

        // 当天签到
        if (today == signInDate) {
            signInCache.updateSignInCalendarAsync(userId, bitCalendar);
            RedisFuture<String> continueSignInDaysFuture = signInCache.getContinueSignInDaysAsync(userId);
            int continueDaysBeforeSignIn = StringUtil.toInteger(continueSignInDaysFuture.get(5, TimeUnit.SECONDS));
            signInCache.addContinueDaysAsync(userId, 1L);

            if (signInDate == 1) {
                signInCache.updateLastContinueDaysAsync(userId, continueDaysBeforeSignIn);
            }

            SignInVO signInVO = SignInVO.signInSuccess(bitCalendar, continueDaysBeforeSignIn + 1, today);
            if (!signInVO.getRewards().getProps().isEmpty()) {
                propService.addPropsGift(userId, signInVO.getRewards());
            }

            return signInVO;
        }
        // 补签
        List<String> reSignInDays = signInCache.getReSignInDaysAsync(userId).get(5, TimeUnit.SECONDS);

        // 补签次数未超过3次
        assert reSignInDays != null;
        if (reSignInDays.size() >= 3) {
            return SignInVO.limitUseOfResignCard();
        }

        // 补签卡数量不足
        if (propService.getResignInCard(userId).getPropCount() <= 0) {
            return SignInVO.useOutOfResignCard();
        }
        RedisFuture<String> continueSignInDaysFuture = signInCache.getContinueSignInDaysAsync(userId);
        RedisFuture<String> lastContinueDaysFuture = signInCache.getLastContinueSignInDaysAsync(userId);
        // 补签卡数量大于0, 消耗一张补签卡
        propService.decrResignInCard(userId);

        LettuceFutures.awaitAll(5, TimeUnit.SECONDS, continueSignInDaysFuture, lastContinueDaysFuture);

        int continueDaysBeforeReSignIn = StringUtil.toInteger(continueSignInDaysFuture.get());

        if (signInDate == 1) {
            signInCache.updateLastContinueDaysAsync(userId, continueDaysBeforeReSignIn);
        }

        SignInVO signInVO = SignInVO.resignInSuccess(bitCalendar, continueDaysBeforeReSignIn, signInDate);

        if (!signInVO.getRewards().getProps().isEmpty()) {
            propService.addPropsGift(userId, signInVO.getRewards());
        }
        return signInVO;
    }
}
