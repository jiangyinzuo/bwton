package pers.jiangyinzuo.carbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.jiangyinzuo.carbon.domain.entity.Prop;
import pers.jiangyinzuo.carbon.domain.validation.annotation.ID;
import pers.jiangyinzuo.carbon.http.HttpResponseUtil;
import pers.jiangyinzuo.carbon.service.PropService;
import pers.jiangyinzuo.carbon.util.HttpHeaderUtil;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

import static pers.jiangyinzuo.carbon.domain.entity.Prop.PROP_ID.COVER;
import static pers.jiangyinzuo.carbon.domain.entity.Prop.PROP_ID.SPEEDER;

/**
 * @author Jiang Yinzuo
 */
@RestController
@Validated
public class PropController {

    private PropService propService;

    @Autowired
    public PropController(PropService propService) {
        this.propService = propService;
    }

    @GetMapping("/prop")
    public ResponseEntity<Object> getProps(@Validated @RequestParam @ID Long userId) {
        List<Prop> props = propService.getProps(userId);
        return HttpResponseUtil.ok(props);

    }

    /**
     * 使用道具
     *
     * @param propId   道具ID
     * @param useCount 使用个数
     * @return JSON
     */
    @PostMapping("/prop/use")
    public ResponseEntity<Object> useProps(
            @RequestHeader("Authorization") String authToken,
            @NotNull @Min(1) Long propId,
            @NotNull @Min(1) Integer useCount) {
        Long userId = HttpHeaderUtil.getUserId(authToken);
        Prop prop = propService.getPropById(userId, propId);
        if (prop == null || prop.getPropCount().equals(0L)) {
            return HttpResponseUtil.ok(1, "道具数量不足");
        }

        if (prop.getPropId().equals(SPEEDER.id)) {
            propService.speedDrops(userId);
        } else if (prop.getPropId().equals(COVER.id)) {
            boolean isCovered = propService.coverDrops(userId);
            if (!isCovered) {
                return HttpResponseUtil.ok(2, "正在保护状态，不能叠加");
            }
        }
        return HttpResponseUtil.OK;
    }
}
