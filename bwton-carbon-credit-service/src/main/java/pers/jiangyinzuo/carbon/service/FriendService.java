package pers.jiangyinzuo.carbon.service;

import pers.jiangyinzuo.carbon.domain.dto.FriendshipDTO;
import pers.jiangyinzuo.carbon.domain.entity.User;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface FriendService {
    /**
     * 加好友
     * @param friendshipDTO 好友关系业务对象
     */
    void addFriend(FriendshipDTO friendshipDTO);

    /**
     * 获取碳积分排行榜上的用户
     * @param userId 用户ID
     * @return 排行榜用户列表
     */
    List<User> getUserAndFriends(Long userId);
}
