package pers.jiangyinzuo.carbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.jiangyinzuo.carbon.controller.validation.annotation.ID;
import pers.jiangyinzuo.carbon.domain.dto.CreditDTO;
import pers.jiangyinzuo.carbon.domain.vo.LeaderBoardVO;
import pers.jiangyinzuo.carbon.http.HttpResponseBody;
import pers.jiangyinzuo.carbon.service.LeaderBoardService;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
@RestController
public class CreditController {

    private LeaderBoardService leaderBoardService;

    @Autowired
    public void setLeaderBoardService(LeaderBoardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
    }

    @GetMapping("/leaderboard/total/")
    public HttpResponseBody<LeaderBoardVO> getLeaderBoardInfo(@Validated @ID @RequestParam Long userId) {
        // TODO: implement leaderBoardService
        return new HttpResponseBody<>(0, "ok", null);
    }
}
