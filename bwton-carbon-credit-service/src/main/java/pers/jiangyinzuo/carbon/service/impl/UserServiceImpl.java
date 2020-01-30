package pers.jiangyinzuo.carbon.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import pers.jiangyinzuo.carbon.dao.cache.UserCache;
import pers.jiangyinzuo.carbon.dao.mapper.UserMapper;
import pers.jiangyinzuo.carbon.domain.entity.User;
import pers.jiangyinzuo.carbon.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * @author Jiang Yinzuo
 */
@Service
public class UserServiceImpl implements UserService {

    private UserCache userCache;
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserCache userCache, UserMapper userMapper) {
        this.userCache = userCache;
        this.userMapper = userMapper;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Future<List<User>> getUsersAsync(Set<Long> userIds) {
        List<Object> users = userCache.getUsers(userIds);
        int i = 0;
        List<Long> expiredIds = new ArrayList<>();
        for (Long id : userIds) {
            if (((Map<String, Object>)users.get(i++)).isEmpty()) {
                expiredIds.add(id);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> result = new ArrayList<>();

        // 没有用户key过期，直接返回
        if (expiredIds.isEmpty()) {
            for (Object user : users) {
                result.add(objectMapper.convertValue(user, User.class));
            }
        } else {
            List<Object> usersFromDb = userMapper.getUsers(expiredIds);
            userCache.setUsersAsync(usersFromDb);
            i = 0;
            for (Object user : users) {
                if (((Map<String, Object>)user).isEmpty()) {
                    result.add((User) usersFromDb.get(i++));
                } else {
                    result.add(objectMapper.convertValue(user, User.class));
                }
            }
        }
        return new AsyncResult<>(result);
    }
}
