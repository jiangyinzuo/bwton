package pers.jiangyinzuo.carbon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jiangyinzuo.carbon.dao.mapper.FriendMapper;
import pers.jiangyinzuo.carbon.dao.mapper.UserMapper;
import pers.jiangyinzuo.carbon.domain.dto.FriendshipDTO;
import pers.jiangyinzuo.carbon.domain.entity.User;
import pers.jiangyinzuo.carbon.service.FriendService;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
@Service
public class FriendServiceImpl implements FriendService {

    private FriendMapper friendMapper;
    private UserMapper userMapper;

    @Autowired
    public FriendServiceImpl(FriendMapper friendMapper, UserMapper userMapper) {
        this.friendMapper = friendMapper;
        this.userMapper = userMapper;
    }

    @Override
    public void addFriend(FriendshipDTO friendshipDTO) {
        if (Boolean.TRUE.equals(userMapper.exists(friendshipDTO.getFriendId()))) {
            Long minUserId = friendshipDTO.getMinId();
            Long maxUserId = friendshipDTO.getMaxId();
            friendMapper.addFriends(minUserId, maxUserId);
        }
    }

    @Override
    public List<User> getUserAndFriends(Long userId) {
        return friendMapper.getUserAndFriends(userId);
    }
}
