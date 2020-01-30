package pers.jiangyinzuo.carbon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jiangyinzuo.carbon.dao.cache.CreditCache;
import pers.jiangyinzuo.carbon.domain.entity.User;
import pers.jiangyinzuo.carbon.domain.vo.LeaderBoardVO;
import pers.jiangyinzuo.carbon.service.FriendService;
import pers.jiangyinzuo.carbon.service.LeaderBoardService;
import pers.jiangyinzuo.carbon.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Jiang Yinzuo
 */
@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {

    private FriendService friendService;
    private CreditCache creditCache;
    private UserService userService;

    @Autowired
    public LeaderBoardServiceImpl(FriendService friendService, CreditCache creditCache, UserService userService) {
        this.friendService = friendService;
        this.creditCache = creditCache;
        this.userService = userService;
    }

    @Override
    public LeaderBoardVO getTotalLeaderBoard(Long userId) throws InterruptedException, ExecutionException {
        Set<Long> userIds = friendService.getFriendIds(userId);

        // 加用户自己
        userIds.add(userId);

        Future<List<User>> usersFuture = userService.getUsersAsync(userIds);

        List<Object> cacheCreditsResult = creditCache.getTotalCredits(userIds);
        List<Long> expiredFriendIds = new ArrayList<>();
        int i = 1;
        for (Long id : userIds) {
            if (cacheCreditsResult.get(i) == null) {
                expiredFriendIds.add(id);
            }
            i += 2;
        }
        List<User> users;
        LeaderBoardVO vo = new LeaderBoardVO();
        // 没有过期键
        if (expiredFriendIds.isEmpty()) {
            while (!usersFuture.isDone()) {
                Thread.sleep(10);
            }
            users = usersFuture.get();
            i = 0;
            for (User user : users) {
                vo.addUser(user, (long)cacheCreditsResult.get(i) + (long)cacheCreditsResult.get(i+1));
                i += 2;
            }
        }

        return null;
    }
}
