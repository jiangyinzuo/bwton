package pers.jiangyinzuo.carbon.dao.cache;

import pers.jiangyinzuo.carbon.domain.dto.CreditDTO;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface FriendCache {

    int FRIENDS_EXPIRE_TIME = 259200;

    /**
     * 向缓存中添加好友
     * @param userId1 用户1ID
     * @param userId2 用户2ID
     * @return 添加成功返回true，失败返回false
     */
    boolean addFriend(String userId1, String userId2);

    List<CreditDTO> getFriendsCredit(Long userId);
}
