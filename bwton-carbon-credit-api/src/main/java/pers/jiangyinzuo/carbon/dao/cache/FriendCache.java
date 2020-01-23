package pers.jiangyinzuo.carbon.dao.cache;

/**
 * @author Jiang Yinzuo
 */
public interface FriendCache {

    /**
     * 添加好友
     * @param userId1 用户1ID
     * @param userId2 用户2ID
     * @return 添加成功返回true，失败返回false
     */
    boolean addFriend(String userId1, String userId2);
}
