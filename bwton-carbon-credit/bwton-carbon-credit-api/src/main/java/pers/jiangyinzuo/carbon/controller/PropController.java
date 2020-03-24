package pers.jiangyinzuo.carbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.jiangyinzuo.carbon.domain.entity.Prop;
import pers.jiangyinzuo.carbon.domain.validation.annotation.ID;
import pers.jiangyinzuo.carbon.http.HttpResponseUtil;
import pers.jiangyinzuo.carbon.service.PropService;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
@RestController
public class PropController {

    private PropService propService;

    @Autowired
    public PropController(PropService propService) {
        this.propService = propService;
    }

    @GetMapping("/prop")
    public ResponseEntity<Object> getProps(@Validated @RequestParam @ID Long userId) {
        List<Prop> props =  propService.getProps(userId);
        return HttpResponseUtil.ok(props);
    }
}
