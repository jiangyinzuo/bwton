package pers.jiangyinzuo.carbon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jiangyinzuo.carbon.dao.cache.CreditCache;
import pers.jiangyinzuo.carbon.dao.mapper.CreditMapper;
import pers.jiangyinzuo.carbon.domain.dto.LeaderBoardUserDTO;
import pers.jiangyinzuo.carbon.domain.entity.User;
import pers.jiangyinzuo.carbon.domain.vo.LeaderBoardVO;
import pers.jiangyinzuo.carbon.service.FriendService;
import pers.jiangyinzuo.carbon.service.LeaderboardService;
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
public class LeaderboardServiceImpl implements LeaderboardService {

    private FriendService friendService;
    private CreditCache creditCache;
    private UserService userService;
    private CreditMapper creditMapper;

    @Autowired
    public LeaderboardServiceImpl(FriendService friendService, CreditCache creditCache, UserService userService, CreditMapper creditMapper) {
        this.friendService = friendService;
        this.creditCache = creditCache;
        this.userService = userService;
        this.creditMapper = creditMapper;
    }

    @Override
    public LeaderBoardVO getTotalLeaderBoard(Long userId) throws InterruptedException, ExecutionException {

        // 获取用户好友ID
        Set<Long> userIds = friendService.getFriendIds(userId);

        // 加用户自己
        userIds.add(userId);

        // 异步获取用户信息
        Future<List<User>> usersFuture = userService.getUsersAsync(userIds);

        // 从缓存中获取用户碳积分
        List<Object> cacheCreditsResult = creditCache.getTotalCredits(userIds);

        // 碳积分缓存失效的用户ID
        List<Long> expiredUserIds = new ArrayList<>();

        int i = 1;
        for (Long id : userIds) {
            if (cacheCreditsResult.get(i) == null) {
                expiredUserIds.add(id);
            }
            i += 2;
        }

        // 用户信息
        List<User> users;

        // 返回值对象
        LeaderBoardVO vo = new LeaderBoardVO();

        // 获取users
        while (!usersFuture.isDone()) {
            Thread.sleep(10);
        }
        users = usersFuture.get();
        
        // 没有过期的用户碳积分缓存
        if (expiredUserIds.isEmpty()) {
            i = 0;
            for (User user : users) {
                vo.addUser(user, (long)cacheCreditsResult.get(i) + (long)cacheCreditsResult.get(i+1));
                if (userId.equals(user.getUserId())) {
                    vo.setUser(new LeaderBoardUserDTO(user, (long)cacheCreditsResult.get(i) + (long)cacheCreditsResult.get(i+1)));
                }
                i += 2;
            }
        } else { // 部分用户碳积分缓存过期，向数据库查询碳积分
            List<Long> dbCreditsResult = creditMapper.getHistoryTotalCredits(expiredUserIds);

            i = 0;
            int j = 0;
            long creditToday;
            for (User user : users) {
                creditToday = cacheCreditsResult.get(i) == null ? 0L : (Long)cacheCreditsResult.get(i);
                // 碳积分缓存失效
                if (cacheCreditsResult.get(i+1) == null) {
                    vo.addUser(user, creditToday + dbCreditsResult.get(j));
                    if (userId.equals(user.getUserId())) {
                        vo.setUser(new LeaderBoardUserDTO(user, creditToday + dbCreditsResult.get(j)));
                    }
                } else { // 碳积分缓存未失效
                    vo.addUser(user, creditToday + (long)cacheCreditsResult.get(i));
                    if (userId.equals(user.getUserId())) {
                        vo.setUser(new LeaderBoardUserDTO(user, creditToday + (long)cacheCreditsResult.get(i)));
                    }
                }
                i += 2;
                j++;
            }
        }
        return vo;
    }
}
