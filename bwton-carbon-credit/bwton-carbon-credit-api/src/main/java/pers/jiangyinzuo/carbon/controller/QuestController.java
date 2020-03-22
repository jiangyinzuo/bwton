package pers.jiangyinzuo.carbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.jiangyinzuo.carbon.domain.validation.annotation.ID;
import pers.jiangyinzuo.carbon.http.HttpResponse;
import pers.jiangyinzuo.carbon.service.QuestService;

/**
 * @author Jiang Yinzuo
 */
@RestController
public class QuestController {

    private QuestService questService;

    @Autowired
    public QuestController(QuestService questService) {
        this.questService = questService;
    }

    @GetMapping("/quest")
    public ResponseEntity<Object> getQuestProgress(@Validated @RequestParam @ID Long userId) {
        return HttpResponse.ok(questService.getQuestProgress(userId));
    }

    /**
     * 用户签到
     * @return
     */
    @PostMapping("/signIn")
    public ResponseEntity<Object> signIn() {
        // TODO
    }
}
