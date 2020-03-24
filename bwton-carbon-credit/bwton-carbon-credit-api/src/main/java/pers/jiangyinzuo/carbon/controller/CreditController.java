package pers.jiangyinzuo.carbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.jiangyinzuo.carbon.domain.CREDIT_RECORD_MODE;
import pers.jiangyinzuo.carbon.domain.dto.PickCreditDropDTO;
import pers.jiangyinzuo.carbon.domain.validation.annotation.ID;
import pers.jiangyinzuo.carbon.domain.vo.LeaderBoardVO;
import pers.jiangyinzuo.carbon.domain.vo.PickedRecordVO;
import pers.jiangyinzuo.carbon.http.CustomRequestException;
import pers.jiangyinzuo.carbon.http.HttpResponseUtil;
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

    @Autowired
    public void setLeaderBoardService(CreditService creditService, FriendService friendService) {
        this.creditService = creditService;
        this.friendService = friendService;
    }

    /**
     * 获取好友排行榜
     *
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
            return HttpResponseUtil.NOT_FOUND;
        }

        if (!userId.equals(HttpHeaderUtil.getUserId(authToken))) {
            return HttpResponseUtil.FORBIDDEN;
        }
        LeaderBoardVO vo = creditService.getLeaderBoard(userId, creditRecordMode);
        return HttpResponseUtil.ok(vo);
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
            return HttpResponseUtil.FORBIDDEN;
        }

        List<String> drops = creditService.getCreditDrops(userId);
        Map<String, Object> data = new HashMap<>(1);
        data.put("drops", drops);
        return HttpResponseUtil.ok(data);
    }

    /**
     * 采摘积分小水滴
     *
     * @param pickCreditDropDTO
     * @return
     */
    @PostMapping("/creditDrop")
    public ResponseEntity<Object> pickCreditDrop(@Validated @RequestBody PickCreditDropDTO pickCreditDropDTO) throws CustomRequestException {
        if (pickCreditDropDTO.isOutOfDate()) {
            return HttpResponseUtil.badRequest("小水滴已过期");
        }

        if (pickCreditDropDTO.isSelf() || friendService.isFriend(pickCreditDropDTO.getPickedUserId(), pickCreditDropDTO.getPickerUserId())) {
            if (creditService.pickCreditDrop(pickCreditDropDTO)) {
                return HttpResponseUtil.ok(pickCreditDropDTO.isSelf() ? "采摘成功" : "帮助成功");
            }
            return HttpResponseUtil.ok("小水滴消失了");
        } else { // 既不是自己也不是好友, 权限不够
            return HttpResponseUtil.FORBIDDEN;
        }
    }

    /**
     * 获取用户被采摘碳积分的记录
     *
     * @param authToken 用户验证Token
     * @param queriedUserId    用户ID
     * @return
     */
    @GetMapping("/creditDrop/record")
    public ResponseEntity<Object> getPickedRecord(
            @RequestHeader("Authorization") String authToken,
            @Validated @ID @RequestParam Long queriedUserId) {

        Long userId = HttpHeaderUtil.getUserId(authToken);

        // 该用户既不是小水滴本人也不是好友，没有查看权限
        if (!(userId.equals(queriedUserId) || friendService.isFriend(userId, queriedUserId))) {
            return HttpResponseUtil.FORBIDDEN;
        }

        List<PickedRecordVO> pickedRecord = creditService.getPickedRecord(queriedUserId);
        return HttpResponseUtil.ok(pickedRecord);
    }
}
