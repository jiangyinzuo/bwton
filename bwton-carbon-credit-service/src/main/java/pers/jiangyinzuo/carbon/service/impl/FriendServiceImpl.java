package pers.jiangyinzuo.carbon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
    public FriendServiceImpl(FriendMapper friendMapper, FriendCache friendCache) {
        this.friendMapper = friendMapper;
        this.friendCache = friendCache;
    }

    @Override
    public void addFriend(FriendshipDTO friendshipDTO) {
        Long minUserId = friendshipDTO.getMinId();
        Long maxUserId = friendshipDTO.getMaxId();
        friendMapper.addFriends(minUserId, maxUserId);
        friendCache.delUserFriKey(minUserId, maxUserId);
    }

    @Override
    public Set<Long> getFriendIds(Long userId) {
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
