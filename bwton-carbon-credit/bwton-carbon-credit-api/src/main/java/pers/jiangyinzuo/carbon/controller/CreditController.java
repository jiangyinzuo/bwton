package pers.jiangyinzuo.carbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.jiangyinzuo.carbon.domain.dto.DropPickingDTO;
import pers.jiangyinzuo.carbon.http.HttpResponse;
import pers.jiangyinzuo.carbon.domain.validation.annotation.ID;
import pers.jiangyinzuo.carbon.domain.vo.LeaderBoardVO;
import pers.jiangyinzuo.carbon.service.CreditService;
import pers.jiangyinzuo.carbon.service.FriendService;
import pers.jiangyinzuo.carbon.util.HttpHeaderUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Jiang Yinzuo
 */
@RestController
public class CreditController {

    private CreditService creditService;
    private FriendService friendService;

    private static final Set<String> LEADERBOARD_MODE = Set.of("total", "week");

    @Autowired
    public void setLeaderBoardService(CreditService creditService, FriendService friendService) {
        this.creditService = creditService;
        this.friendService = friendService;
    }

    @GetMapping("/leaderboard/{mode}")
    public ResponseEntity<Object> getLeaderBoard(
            @RequestHeader("Authorization") String authToken,
            @Validated @ID @RequestParam Long userId,
            @PathVariable String mode) {
        if (!LEADERBOARD_MODE.contains(mode)) {
            return HttpResponse.NOT_FOUND;
        }
        if (!userId.equals(HttpHeaderUtil.getUserId(authToken))) {
            return HttpResponse.FORBIDDEN;
        }
        LeaderBoardVO vo = creditService.getLeaderBoard(userId, mode);
        return HttpResponse.ok(vo);
    }

    @GetMapping("/creditDrop")
    public ResponseEntity<Object> getCreditDrop(
            @RequestHeader("Authorization") String authToken,
            @Validated @ID @RequestParam Long userId) {
        Long uid = HttpHeaderUtil.getUserId(authToken);
        if (!(uid.equals(userId) || friendService.isFriend(uid, userId))) {
            return HttpResponse.FORBIDDEN;
        }
        List<String> drops = creditService.getCreditDrops(userId);
        Map<String, Object> data = new HashMap<>(1);
        data.put("drops", drops);
        return HttpResponse.ok(data);
    }

    @PostMapping("/creditDrop")
    public ResponseEntity<Object> pickCreditDrop(@Validated @RequestBody DropPickingDTO dropPickingDTO) {
        if (dropPickingDTO.isOutOfDate()) {
            return HttpResponse.badRequest("小水滴已过期");
        }

        // 帮自己摘小水滴
        if (dropPickingDTO.isSelf()) {

        } else if (friendService.isFriend(dropPickingDTO.getPickedUserId(), dropPickingDTO.getPickerUserId())) {
            // 帮好友摘

        } else { // 既不是自己也不是好友, 权限不够
            return HttpResponse.FORBIDDEN;
        }

        return HttpResponse.OK;
    }
}
