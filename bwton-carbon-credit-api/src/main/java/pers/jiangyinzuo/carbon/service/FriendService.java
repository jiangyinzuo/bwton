package pers.jiangyinzuo.carbon.service;

import pers.jiangyinzuo.carbon.domain.bo.FriendshipBO;

/**
 * @author Jiang Yinzuo
 */
public interface FriendService {
    /**
     * 加好友
     * @param friendshipBO 好友关系业务对象
     * @return 添加好友是否成功
     */
    boolean addFriend(FriendshipBO friendshipBO);
}
