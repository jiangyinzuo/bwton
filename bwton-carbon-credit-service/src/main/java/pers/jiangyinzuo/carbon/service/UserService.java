package pers.jiangyinzuo.carbon.service;

import org.springframework.scheduling.annotation.Async;
import pers.jiangyinzuo.carbon.domain.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Jiang Yinzuo
 */
public interface UserService {
    @Async
    Future<List<Map<String, String>>> getUsersAsync(Set<Long> userIds) throws ExecutionException, InterruptedException;
}
