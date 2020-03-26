package pers.jiangyinzuo.carbon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.jiangyinzuo.carbon.dao.cache.CreditCache;
import pers.jiangyinzuo.carbon.dao.mapper.PropMapper;
import pers.jiangyinzuo.carbon.domain.dto.PropCountDTO;
import pers.jiangyinzuo.carbon.domain.entity.Prop;
import pers.jiangyinzuo.carbon.http.CustomRequestException;
import pers.jiangyinzuo.carbon.service.PropService;

import java.util.List;

import static pers.jiangyinzuo.carbon.domain.entity.Prop.PROP_ID.RE_SIGN_IN_CARD;
import static pers.jiangyinzuo.carbon.domain.entity.Prop.PROP_ID.SPEEDER;

/**
 * @author Jiang Yinzuo
 */
@Service
public class PropServiceImpl implements PropService {

    private PropMapper propMapper;
    private CreditCache creditCache;

    @Autowired
    public PropServiceImpl(PropMapper propMapper, CreditCache creditCache) {
        this.propMapper = propMapper;
        this.creditCache = creditCache;
    }

    @Override
    public List<Prop> getProps(Long userId) {
        return propMapper.getProps(userId);
    }

    @Override
    public Prop getPropById(Long userId, Long propId) {
        return propMapper.getPropById(userId, propId);
    }

    @Override
    public Prop getResignInCard(Long userId) {
        return propMapper.getPropById(userId, RE_SIGN_IN_CARD.id);
    }

    @Transactional(timeout = 10, rollbackFor = CustomRequestException.class)
    @Override
    public void speedDrops(Long userId) {
        propMapper.decrPropsCount(userId, SPEEDER.id, 1);
        creditCache.updateMatureTimeAsync(userId);
    }

    @Override
    public void addPropsGift(Long userId, PropCountDTO propCountDTO) {
        propMapper.addPropsCount(userId, propCountDTO.getProps());
    }

    @Override
    public boolean coverDrops(Long userId) {
        return creditCache.setCover(userId);
    }
}
