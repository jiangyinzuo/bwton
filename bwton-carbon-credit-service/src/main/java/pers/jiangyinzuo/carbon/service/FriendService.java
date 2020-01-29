package pers.jiangyinzuo.carbon.service;

import pers.jiangyinzuo.carbon.domain.dto.FriendshipDTO;

import java.util.Set;

/**
 * @author Jiang Yinzuo
 */
public interface FriendService {
    /**
     * 加好友
     * @param friendshipDTO 好友关系业务对象
     * @return 添加好友是否成功
     */
    boolean addFriend(FriendshipDTO friendshipDTO);

    Set<Long> getFriendsId(Long userId);
}
