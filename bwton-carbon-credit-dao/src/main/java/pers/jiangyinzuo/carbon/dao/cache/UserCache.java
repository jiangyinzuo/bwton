package pers.jiangyinzuo.carbon.dao.cache;

import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Set;

/**
 * @author Jiang Yinzuo
 */
public interface UserCache {
    /**
     * 异步批量设置用户信息key
     * @param users 用户列表
     */
    @Async
    void setUsersAsync(List<Object> users);

    List<Object> getUsers(Set<Long> userIds);
}
