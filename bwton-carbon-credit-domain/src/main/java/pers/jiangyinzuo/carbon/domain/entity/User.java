package pers.jiangyinzuo.carbon.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
@Data
@AllArgsConstructor
public class User {
    Long userId;
    String nickname;
    Long badge;

    public User() {
        // used for deserialize from Object value
    }

    public Map<byte[], byte[]> getHash() {
        Map<byte[], byte[]> hash = new HashMap<>(2);
        hash.put("userId".getBytes(), userId.toString().getBytes());
        hash.put("nickname".getBytes(), nickname.getBytes());
        hash.put("badge".getBytes(), badge.toString().getBytes());
        return hash;
    }
}
