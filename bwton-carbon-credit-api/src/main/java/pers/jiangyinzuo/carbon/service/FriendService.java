package pers.jiangyinzuo.carbon.service;

import pers.jiangyinzuo.carbon.domain.dto.CreditDTO;
import pers.jiangyinzuo.carbon.domain.dto.FriendshipDTO;

import java.util.List;

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

    /**
     * 获取好友碳积分排行榜
     * @param userId 用户ID
     * @return 由用户ID和碳积分组成的数组
     */
    List<CreditDTO> getCreditLeaderBoard(Long userId);
}
