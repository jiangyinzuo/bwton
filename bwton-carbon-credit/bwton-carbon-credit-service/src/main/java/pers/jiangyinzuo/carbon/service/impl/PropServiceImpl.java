package pers.jiangyinzuo.carbon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jiangyinzuo.carbon.dao.mapper.PropMapper;
import pers.jiangyinzuo.carbon.domain.dto.PropCountDTO;
import pers.jiangyinzuo.carbon.domain.entity.Prop;
import pers.jiangyinzuo.carbon.service.PropService;

import java.util.List;

import static pers.jiangyinzuo.carbon.domain.entity.Prop.PROP_ID.RE_SIGN_IN_CARD;

/**
 * @author Jiang Yinzuo
 */
@Service
public class PropServiceImpl implements PropService {

    private PropMapper propMapper;

    @Autowired
    public PropServiceImpl(PropMapper propMapper) {
        this.propMapper = propMapper;
    }

    @Override
    public List<Prop> getProps(Long userId) {
        return propMapper.getProps(userId);
    }

    @Override
    public Prop getResignInCard(Long userId) {
        return propMapper.getPropById(userId, RE_SIGN_IN_CARD.id);
    }

    @Override
    public void decrResignInCard(Long userId) {
        propMapper.decrPropsCount(userId, RE_SIGN_IN_CARD.id, 1);
    }

    @Override
    public void addPropsGift(Long userId, PropCountDTO propCountDTO) {
        propMapper.addPropsCount(userId, propCountDTO.getProps());
    }
}
