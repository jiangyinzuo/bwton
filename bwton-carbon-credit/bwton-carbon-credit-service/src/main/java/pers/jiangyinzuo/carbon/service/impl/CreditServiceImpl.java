package pers.jiangyinzuo.carbon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jiangyinzuo.carbon.dao.cache.CreditCache;
import pers.jiangyinzuo.carbon.domain.CREDIT_RECORD_MODE;
import pers.jiangyinzuo.carbon.domain.dto.PickCreditDropDTO;
import pers.jiangyinzuo.carbon.domain.entity.User;
import pers.jiangyinzuo.carbon.domain.vo.LeaderBoardVO;
import pers.jiangyinzuo.carbon.domain.vo.PickedRecordVO;
import pers.jiangyinzuo.carbon.service.CreditService;
import pers.jiangyinzuo.carbon.service.FriendService;

import java.util.ArrayList;
import java.util.List;

import static pers.jiangyinzuo.carbon.domain.vo.PickedRecordVO.createRecords;

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
            userIdList.add(user.userId());
        }

        List<Long> creditList = creditCache.getUsersCredits(userIdList, mode);

        return LeaderBoardVO.newLeaderBoardVO(userList, creditList);
    }

    @Override
    public List<String> getCreditDrops(Long userId) {
        return creditCache.getCreditDrops(userId);
    }

    @Override
    public Long getUserCredit(Long userId, CREDIT_RECORD_MODE mode) {
        return creditCache.getUserCredit(userId, mode);
    }

    @Override
    public boolean pickCreditDrop(PickCreditDropDTO pickCreditDropDTO) {

        boolean hasDrop = creditCache.removeCreditDrop(pickCreditDropDTO);

        // 积分小水滴不存在
        if (!hasDrop) {
            return false;
        }

        creditCache.addCreditsAsync(pickCreditDropDTO.getGainerUserId(), pickCreditDropDTO.getValue());
        return true;
    }

    @Override
    public List<PickedRecordVO> getPickedRecord(Long queriedUserId) {

        // Redis中的原始数据
        // 每条记录格式为 `{采摘用户ID}["+", "-"]{碳积分数值}`
        List<String> rawResult = creditCache.getRawPickedRecord(queriedUserId);
        return createRecords(queriedUserId, rawResult);
    }

    @Override
    public long getCoveredTime(Long userId) {
        long coveredTime = creditCache.getCoveredTime(userId);
        return coveredTime == -2 ? 0 : coveredTime;
    }
}
