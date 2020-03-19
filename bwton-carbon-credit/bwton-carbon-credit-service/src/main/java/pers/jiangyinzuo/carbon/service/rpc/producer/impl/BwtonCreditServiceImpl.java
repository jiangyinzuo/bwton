package pers.jiangyinzuo.carbon.service.rpc.producer.impl;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pers.jiangyinzuo.carbon.dao.cache.CreditCache;
import pers.jiangyinzuo.carbon.domain.entity.CreditDrop;
import pers.jiangyinzuo.carbon.rpc.producer.BwtonCreditService;

import java.util.Random;

/**
 * @author Jiang Yinzuo
 */
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        interfaceClass = BwtonCreditService.class
)
@org.springframework.stereotype.Service
public class BwtonCreditServiceImpl implements BwtonCreditService {

    private CreditCache creditCache;
    private static Random random = new Random(System.currentTimeMillis());

    /**
     * 碳积分计算公式
     *
     * @param distance 用户绿色出行路程, 单位公里
     * @return 用户本次生成的积分小水滴的碳积分值
     */
    private static long calcCreditValue(Double distance) {
        return (long) (distance * 20) + random.nextInt(5);
    }

    @Autowired
    public BwtonCreditServiceImpl(CreditCache creditCache) {
        this.creditCache = creditCache;
    }

    @Override
    public void addCreditDrop(Long userId, Double distance) {
        if (creditCache.getCreditDropsSize(userId) <= CreditDrop.MAXIMUM_CREDIT_DROP_COUNT) {
            long credit = calcCreditValue(distance);
            creditCache.addCreditDropAsync(userId, credit, CreditDrop.MATURE_SPAN_MILLIS);
        }
    }
}
