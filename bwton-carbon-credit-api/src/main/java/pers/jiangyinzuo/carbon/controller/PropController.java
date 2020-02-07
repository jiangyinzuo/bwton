package pers.jiangyinzuo.carbon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pers.jiangyinzuo.carbon.domain.validation.annotation.ID;
import pers.jiangyinzuo.carbon.http.HttpResponse;

/**
 * @author Jiang Yinzuo
 */
@RestController
public class PropController {

    @GetMapping("/prop")
    public ResponseEntity<Object> getProps(@Validated @PathVariable @ID Long userId) {
        return HttpResponse.OK;
    }
}
