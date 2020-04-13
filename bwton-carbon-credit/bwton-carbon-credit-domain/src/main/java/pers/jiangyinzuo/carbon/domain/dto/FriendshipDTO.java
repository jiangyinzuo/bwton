package pers.jiangyinzuo.carbon.domain.dto;

import pers.jiangyinzuo.carbon.domain.validation.annotation.ID;

/**
 * @author Jiang Yinzuo
 */
public record FriendshipDTO(@ID Long userId, @ID Long friendId) {
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
