package pers.jiangyinzuo.carbon.controller;

import io.lettuce.core.ScoredValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.jiangyinzuo.carbon.domain.CREDIT_RECORD_MODE;
import pers.jiangyinzuo.carbon.domain.entity.CreditDrop;
import pers.jiangyinzuo.carbon.domain.validation.annotation.ID;
import pers.jiangyinzuo.carbon.domain.vo.LeaderBoardVO;
import pers.jiangyinzuo.carbon.http.CustomRequestException;
import pers.jiangyinzuo.carbon.http.HttpResponse;
import pers.jiangyinzuo.carbon.rpc.producer.BwtonCreditService;
import pers.jiangyinzuo.carbon.service.CreditService;
import pers.jiangyinzuo.carbon.service.FriendService;
import pers.jiangyinzuo.carbon.util.HttpHeaderUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
@RestController
public class CreditController {

    private CreditService creditService;
    private FriendService friendService;
    private BwtonCreditService bwtonCreditService;

    @Autowired
    public void setLeaderBoardService(CreditService creditService, FriendService friendService, BwtonCreditService bwtonCreditService) {
        this.creditService = creditService;
        this.friendService = friendService;
        this.bwtonCreditService = bwtonCreditService;
    }

    /**
     * 获取好友排行榜
     * @param authToken
     * @param userId
     * @param mode
     * @return
     */
    @GetMapping("/leaderboard/{mode}")
    public ResponseEntity<Object> getLeaderBoard(
            @RequestHeader("Authorization") String authToken,
            @Validated @ID @RequestParam Long userId,
            @PathVariable String mode) {
        CREDIT_RECORD_MODE creditRecordMode;
        try {
            creditRecordMode = CREDIT_RECORD_MODE.valueOf(mode);
        } catch (IllegalArgumentException e) {
            return HttpResponse.NOT_FOUND;
        }

        if (!userId.equals(HttpHeaderUtil.getUserId(authToken))) {
            return HttpResponse.FORBIDDEN;
        }
        LeaderBoardVO vo = creditService.getLeaderBoard(userId, creditRecordMode);
        return HttpResponse.ok(vo);
    }

    /**
     * 获取用户积分小水滴
     *
     * @param authToken 用户验证Token
     * @param userId    用户ID
     * @return
     */
    @GetMapping("/creditDrop")
    public ResponseEntity<Object> getCreditDrop(
            @RequestHeader("Authorization") String authToken,
            @Validated @ID @RequestParam Long userId) {
        Long uid = HttpHeaderUtil.getUserId(authToken);

        // 该用户既不是小水滴本人也不是好友，没有查看权限
        if (!(uid.equals(userId) || friendService.isFriend(uid, userId))) {
            return HttpResponse.FORBIDDEN;
        }

        List<ScoredValue<String>> drops = creditService.getCreditDrops(userId);
        Map<String, Object> data = new HashMap<>(1);
        data.put("drops", drops);
        return HttpResponse.ok(data);
    }

    /**
     * 采摘积分小水滴
     *
     * @param creditDrop
     * @return
     */
    @PostMapping("/creditDrop")
    public ResponseEntity<Object> pickCreditDrop(@Validated @RequestBody CreditDrop creditDrop) throws CustomRequestException {
        if (creditDrop.isOutOfDate()) {
            return HttpResponse.badRequest("小水滴已过期");
        }

        if (creditDrop.isSelf() || friendService.isFriend(creditDrop.getPickedUserId(), creditDrop.getPickerUserId())) {
            if(creditService.pickCreditDrop(creditDrop)) {
                return HttpResponse.ok(creditDrop.isSelf() ? "采摘成功" : "帮助成功");
            }
            return HttpResponse.ok("小水滴消失了");
        } else { // 既不是自己也不是好友, 权限不够
            return HttpResponse.FORBIDDEN;
        }
    }
}
