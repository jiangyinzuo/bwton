package pers.jiangyinzuo.carbon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import pers.jiangyinzuo.carbon.dao.cache.FriendCache;
import pers.jiangyinzuo.carbon.dao.mapper.FriendMapper;
import pers.jiangyinzuo.carbon.domain.dto.CreditDTO;
import pers.jiangyinzuo.carbon.domain.dto.FriendshipDTO;
import pers.jiangyinzuo.carbon.service.FriendService;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
@Service
public class FriendServiceImpl implements FriendService {

    private FriendMapper friendMapper;
    private FriendCache friendCache;

    @Autowired
    public void setFriendMapper(FriendMapper friendMapper) {
        this.friendMapper = friendMapper;
    }

    @Autowired
    public void setFriendCache(FriendCache friendCache) {
        this.friendCache = friendCache;
    }

    @Override
    public boolean addFriend(FriendshipDTO friendshipDTO) {
        if (!friendshipDTO.isValid()) {
            return false;
        }
        Long minUserId = friendshipDTO.getMinId();
        Long maxUserId = friendshipDTO.getMaxId();
        try {
            friendMapper.addFriends(minUserId, maxUserId);
        } catch (DuplicateKeyException e) {
            return false;
        }
        return friendCache.addFriend(minUserId.toString(), maxUserId.toString());
    }

    @Override
    public List<CreditDTO> getCreditLeaderBoard(Long userId) {
        return friendCache.getFriendsCredit(userId);
    }
}
