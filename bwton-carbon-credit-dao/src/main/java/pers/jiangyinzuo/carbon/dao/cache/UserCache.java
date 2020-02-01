package pers.jiangyinzuo.carbon.dao.cache;

import io.lettuce.core.RedisFuture;
import org.springframework.scheduling.annotation.Async;
import pers.jiangyinzuo.carbon.domain.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * @author Jiang Yinzuo
 */
public interface UserCache {
    /**
     * 异步批量设置用户信息key
     * @param users 用户列表
     */
    @Async
    void setUsersAsync(List<User> users);

    List<Map<String, String>> getUsers(Set<Long> userIds);
}
