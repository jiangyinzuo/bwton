package pers.jiangyinzuo.carbon.service;

import pers.jiangyinzuo.carbon.domain.dto.PropCountDTO;
import pers.jiangyinzuo.carbon.domain.entity.Prop;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface PropService {

    /**
     * 获取用户的所有道具
     * @param userId 用户ID
     * @return 道具列表
     */
    List<Prop> getProps(Long userId);

    /**
     * 获取用户补签卡
     * @param userId 用户ID
     * @return Prop实体类
     */
    Prop getResignInCard(Long userId);

    /**
     * 用户使用一张补签卡
     * @param userId 用户ID
     */
    void decrResignInCard(Long userId);

    void addPropsGift(Long userId, PropCountDTO propCountDTO);
}
