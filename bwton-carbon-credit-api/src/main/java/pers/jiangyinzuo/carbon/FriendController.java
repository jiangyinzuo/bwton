package pers.jiangyinzuo.carbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import pers.jiangyinzuo.carbon.domain.bo.FriendshipBO;
import pers.jiangyinzuo.carbon.http.HttpResponseBody;
import pers.jiangyinzuo.carbon.service.FriendService;
import pers.jiangyinzuo.carbon.util.HttpHeaderUtil;

/**
 * @author Jiang Yinzuo
 */
@RestController
public class FriendController {

    private FriendService friendService;

    @Autowired
    public void setFriendService(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping("/friend")
    public HttpResponseBody<Object> addFriend(
            @RequestHeader("Authorization") String authToken,
            @Validated @RequestBody FriendshipBO friendshipBO
    ) {
        Long userId = HttpHeaderUtil.getUserId(authToken);
        if (friendshipBO.getUserId().equals(userId) && friendService.addFriend(friendshipBO)) {
            return new HttpResponseBody<>(0, "ok", null);
        } else {
            return new HttpResponseBody<>(-1, "添加失败", null);
        }
    }
}
