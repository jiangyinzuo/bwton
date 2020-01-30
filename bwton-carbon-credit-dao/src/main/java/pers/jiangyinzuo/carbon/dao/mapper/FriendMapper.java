package pers.jiangyinzuo.carbon.dao.mapper;

import pers.jiangyinzuo.carbon.domain.entity.User;

import java.util.List;
import java.util.Set;

/**
 * @author Jiang Yinzuo
 */
public interface FriendMapper {

    /**
     * 添加好友，用户1ID需小于等于用户2ID
     * @param user1Id 用户1ID
     * @param user2Id 用户2ID
     */
    void addFriends(Long user1Id, Long user2Id);

    /**
     * 获取好友列表
     * @param userId 用户ID
     * @return 好友列表
     */
    Set<Long> getFriendsId(Long userId);

    /**
     * 获取好友基本信息
     * @param userId 用户ID
     * @return 好友列表
     */
    List<User> getFriendsByUserId(Long userId);
}
