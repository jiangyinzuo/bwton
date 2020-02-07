package pers.jiangyinzuo.carbon.service;

import pers.jiangyinzuo.carbon.domain.entity.UserProps;

public interface PropService {
    UserProps getProps(Long userId);
}
