package pers.jiangyinzuo.carbon.domain.bo;

import lombok.Data;
import pers.jiangyinzuo.carbon.controller.validation.annotation.ID;

/**
 * @author Jiang Yinzuo
 */
@Data
public class FriendshipBO {

    @ID
    Long userId;

    @ID
    Long friendId;

    public boolean isValid() {
        return userId != null && !userId.equals(friendId);
    }

    public Long getMinId() {
        return Long.min(userId, friendId);
    }

    public Long getMaxId() {
        return Long.max(userId, friendId);
    }
}
