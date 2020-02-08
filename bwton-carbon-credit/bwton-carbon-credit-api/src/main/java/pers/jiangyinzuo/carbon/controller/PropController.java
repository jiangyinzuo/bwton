package pers.jiangyinzuo.carbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.jiangyinzuo.carbon.domain.entity.UserProps;
import pers.jiangyinzuo.carbon.domain.validation.annotation.ID;
import pers.jiangyinzuo.carbon.http.HttpResponse;
import pers.jiangyinzuo.carbon.service.PropService;

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
        UserProps userProps = propService.getProps(userId);
        return HttpResponse.ok(userProps);
    }
}
