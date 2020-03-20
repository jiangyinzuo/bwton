package pers.jiangyinzuo.carbon.service.impl;

import io.lettuce.core.ScoredValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jiangyinzuo.carbon.dao.cache.CreditCache;
import pers.jiangyinzuo.carbon.domain.CREDIT_RECORD_MODE;
import pers.jiangyinzuo.carbon.domain.entity.CreditDrop;
import pers.jiangyinzuo.carbon.domain.entity.PickedRecord;
import pers.jiangyinzuo.carbon.domain.entity.User;
import pers.jiangyinzuo.carbon.domain.vo.LeaderBoardVO;
import pers.jiangyinzuo.carbon.http.CustomRequestException;
import pers.jiangyinzuo.carbon.service.CreditService;
import pers.jiangyinzuo.carbon.service.FriendService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
@Service
public class CreditServiceImpl implements CreditService {

    private FriendService friendService;
    private CreditCache creditCache;

    @Autowired
    public CreditServiceImpl(FriendService friendService, CreditCache creditCache) {
        this.friendService = friendService;
        this.creditCache = creditCache;
    }

    @Override
    public LeaderBoardVO getLeaderBoard(Long userId, CREDIT_RECORD_MODE mode) {

        List<User> userList = friendService.getUserAndFriends(userId);

        List<Long> userIdList = new ArrayList<>();
        for (User user : userList) {
            userIdList.add(user.getUserId());
        }

        List<Long> creditList = creditCache.getUsersCredits(userIdList, mode);

        return LeaderBoardVO.newLeaderBoardVO(userList, creditList);
    }

    @Override
    public List<ScoredValue<String>> getCreditDrops(Long userId) {
        return creditCache.getCreditDrops(userId);
    }

    @Override
    public Long getUserCredit(Long userId, CREDIT_RECORD_MODE mode) {
        return creditCache.getUserCredit(userId, mode);
    }

    @Override
    public boolean pickCreditDrop(CreditDrop creditDrop) throws CustomRequestException {

        boolean hasDrop = creditCache.removeCreditDrop(creditDrop);

        // 积分小水滴不存在
        if (!hasDrop) {
            return false;
        }

        creditCache.addCreditsAsync(creditDrop.getGainerUserId(), creditDrop.getValue());
        return true;
    }

    @Override
    public List<PickedRecord> getPickedRecord(Long userId) {
        return null;
    }
}
