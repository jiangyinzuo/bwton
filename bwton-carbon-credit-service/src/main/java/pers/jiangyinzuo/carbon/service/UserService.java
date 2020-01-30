package pers.jiangyinzuo.carbon.service;

import org.springframework.scheduling.annotation.Async;
import pers.jiangyinzuo.carbon.domain.entity.User;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

public interface UserService {
    @Async
    Future<List<User>> getUsersAsync(Set<Long> userIds);
}
