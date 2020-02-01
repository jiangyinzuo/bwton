package pers.jiangyinzuo.carbon.service.impl;

import lombok.extern.log4j.Log4j2;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Jiang Yinzuo
 */
@Service
@Log4j2
public class UserServiceImpl implements UserService {

    private UserCache userCache;
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserCache userCache, UserMapper userMapper) {
        this.userCache = userCache;
        this.userMapper = userMapper;
    }

    @Override
    public Future<List<Map<String, String>>> getUsersAsync(Set<Long> userIds) throws ExecutionException, InterruptedException {

        List<Map<String, String>> users = userCache.getUsers(userIds);

        int i = 0;
        List<Long> expiredIds = new ArrayList<>();
        for (Long id : userIds) {
            if (users.get(i++).isEmpty()) {
                expiredIds.add(id);
            }
        }

        List<Map<String, String>> result = new ArrayList<>();

        // 没有用户key过期，直接返回
        if (expiredIds.isEmpty()) {
            return new AsyncResult<>(users);
        } else {
            List<User> usersFromDb = userMapper.getUsers(expiredIds);
            userCache.setUsersAsync(usersFromDb);
            i = 0;
            for (Map<String, String> user : users) {
                if (user.isEmpty()) {
                    result.add(usersFromDb.get(i++).getHash());
                } else {
                    result.add(user);
                }
            }
        }
        return new AsyncResult<>(result);
    }
}