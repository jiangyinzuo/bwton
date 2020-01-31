package pers.jiangyinzuo.carbon.dao.cache;

import java.util.Set;

/**
 * @author Jiang Yinzuo
 */
public interface FriendCache {

    /**
     * 令缓存中的好友失效
     * @param userIds 用户ID
     */
    void delUserFriKey(Long ...userIds);

    /**
     * 获取好友们的ID
     * @param userId 用户ID
     * @return 好友ID列表
     */
    Set<Object> getFriendsId(Long userId);

    void setFriendsId(Long userId, Set<Long> friendsId);
}
