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

    public Map<String, String> getHash() {
        Map<String, String> hash = new HashMap<>(2);
        hash.put("userId", userId.toString());
        hash.put("nickname", nickname);
        hash.put("badge", badge.toString());
        return hash;
    }
}
