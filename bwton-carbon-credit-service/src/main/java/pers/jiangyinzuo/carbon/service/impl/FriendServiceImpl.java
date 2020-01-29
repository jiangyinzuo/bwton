package pers.jiangyinzuo.carbon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import pers.jiangyinzuo.carbon.dao.cache.FriendCache;
import pers.jiangyinzuo.carbon.dao.mapper.FriendMapper;
import pers.jiangyinzuo.carbon.domain.dto.FriendshipDTO;
import pers.jiangyinzuo.carbon.service.FriendService;

import java.util.HashSet;
import java.util.Set;

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
        return friendCache.addFriend(minUserId, maxUserId);
    }

    @Override
    public Set<Long> getFriendsId(Long userId) {
        Set<Object> friendsSet = friendCache.getFriendsId(userId);
        Set<Long> result = new HashSet<>();
        // 缓存命中，直接返回
        if (friendsSet != null) {
            for (Object friendId : friendsSet) {
                result.add(((Number) friendId).longValue());
            }
        } else { // 缓存失效，查数据库，更新缓存
            result = friendMapper.getFriendsId(userId);
            friendCache.setFriendsId(userId, result);
        }
        return result;
    }
}
