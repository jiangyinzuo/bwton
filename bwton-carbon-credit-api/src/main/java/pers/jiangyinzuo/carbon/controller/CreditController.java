package pers.jiangyinzuo.carbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.jiangyinzuo.carbon.validation.annotation.ID;
import pers.jiangyinzuo.carbon.domain.vo.LeaderBoardVO;
import pers.jiangyinzuo.carbon.common.http.HttpResponseBody;
import pers.jiangyinzuo.carbon.service.LeaderboardService;

import java.util.concurrent.ExecutionException;

/**
 * @author Jiang Yinzuo
 */
@RestController
public class CreditController {

    private LeaderboardService leaderBoardService;

    @Autowired
    public void setLeaderBoardService(LeaderboardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
    }

    @GetMapping("/leaderboard/total/")
    public HttpResponseBody<LeaderBoardVO> getLeaderBoard(@Validated @ID @RequestParam Long userId) throws ExecutionException, InterruptedException {
        LeaderBoardVO vo = leaderBoardService.getTotalLeaderBoard(userId);
        return new HttpResponseBody<>(0, "ok", vo);
    }
}
