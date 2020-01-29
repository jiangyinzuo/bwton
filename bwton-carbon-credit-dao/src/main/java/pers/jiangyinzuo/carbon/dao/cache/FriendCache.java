package pers.jiangyinzuo.carbon.dao.cache;

import java.util.Set;

/**
 * @author Jiang Yinzuo
 */
public interface FriendCache {



    /**
     * 向缓存中添加好友
     * @param userId1 用户1ID
     * @param userId2 用户2ID
     * @return 添加成功返回true，失败返回false
     */
    boolean addFriend(Long userId1, Long userId2);

    /**
     * 获取好友们的ID
     * @param userId 用户ID
     * @return 好友ID列表
     */
    Set<Object> getFriendsId(Long userId);

    void setFriendsId(Long userId, Set<Long> friendsId);
}
