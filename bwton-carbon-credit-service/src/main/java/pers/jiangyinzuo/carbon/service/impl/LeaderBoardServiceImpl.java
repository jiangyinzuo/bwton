package pers.jiangyinzuo.carbon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jiangyinzuo.carbon.domain.vo.LeaderBoardVO;
import pers.jiangyinzuo.carbon.service.FriendService;
import pers.jiangyinzuo.carbon.service.LeaderBoardService;

import java.util.Set;

/**
 * @author Jiang Yinzuo
 */
@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {

    private FriendService friendService;

    @Autowired
    public LeaderBoardServiceImpl(FriendService friendService) {
        this.friendService = friendService;
    }

    @Override
    public LeaderBoardVO getLeaderBoardInfo(Long userId) {
        Set<Long> friends = friendService.getFriendsId(userId);
        return null;
    }
}
