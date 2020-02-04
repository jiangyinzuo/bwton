package pers.jiangyinzuo.carbon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jiangyinzuo.carbon.dao.cache.CreditCache;
import pers.jiangyinzuo.carbon.domain.entity.User;
import pers.jiangyinzuo.carbon.domain.vo.LeaderBoardVO;
import pers.jiangyinzuo.carbon.service.FriendService;
import pers.jiangyinzuo.carbon.service.LeaderboardService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
@Service
public class LeaderboardServiceImpl implements LeaderboardService {

    private FriendService friendService;
    private CreditCache creditCache;

    @Autowired
    public LeaderboardServiceImpl(FriendService friendService, CreditCache creditCache) {
        this.friendService = friendService;
        this.creditCache = creditCache;
    }

    @Override
    public LeaderBoardVO getLeaderBoard(Long userId, Mode mode) {

        List<User> userList = friendService.getUserAndFriends(userId);

        List<Long> userIdList = new ArrayList<>();
        for (User user : userList) {
            userIdList.add(user.getUserId());
        }

        List<Long> creditList = creditCache.getCredits(userIdList, "total");

        return LeaderBoardVO.create(userList, creditList);
    }
}
