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
     */
    void addFriend(FriendshipDTO friendshipDTO);

    Set<Long> getFriendIds(Long userId);
}