package pers.jiangyinzuo.carbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.jiangyinzuo.carbon.domain.validation.annotation.ID;
import pers.jiangyinzuo.carbon.domain.vo.SignInVO;
import pers.jiangyinzuo.carbon.http.HttpResponseUtil;
import pers.jiangyinzuo.carbon.service.QuestService;
import pers.jiangyinzuo.carbon.util.HttpHeaderUtil;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @author Jiang Yinzuo
 */
@RestController
@Validated
public class QuestController {

    private QuestService questService;

    @Autowired
    public QuestController(QuestService questService) {
        this.questService = questService;
    }

    @GetMapping("/quest")
    public ResponseEntity<Object> getQuestProgress(
            @Validated @RequestParam @ID Long userId) {
        return HttpResponseUtil.ok(questService.getQuestProgress(userId));
    }

    /**
     * 用户签到
     *
     * @return
     */
    @PostMapping("/signIn")
    public ResponseEntity<Object> signIn(
            @RequestHeader("Authorization")
                    String authToken,
            @NotNull
            @Max(value = 31, message = "签到日期非法")
            @Min(value = 0, message = "签到日期非法")
                    Integer signInDate) throws InterruptedException, ExecutionException, TimeoutException {
        Long userId = HttpHeaderUtil.getUserId(authToken);
        SignInVO signInVO = questService.signIn(userId, signInDate);
        return HttpResponseUtil.ok(signInVO);
    }
}
