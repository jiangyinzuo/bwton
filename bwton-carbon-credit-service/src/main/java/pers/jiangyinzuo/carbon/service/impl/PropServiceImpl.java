package pers.jiangyinzuo.carbon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jiangyinzuo.carbon.dao.mapper.PropMapper;
import pers.jiangyinzuo.carbon.domain.entity.UserProps;
import pers.jiangyinzuo.carbon.service.PropService;

@Service
public class PropServiceImpl implements PropService {

    private PropMapper propMapper;

    @Autowired
    public PropServiceImpl(PropMapper propMapper) {
        this.propMapper = propMapper;
    }

    @Override
    public UserProps getProps(Long userId) {
        return propMapper.getProps(userId);
    }
}
