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
     * 根据ID获取道具
     * @param userId 用户ID
     * @return
     */
    Prop getPropById(Long userId, Long propId);

    /**
     * 获取用户补签卡
     * @param userId 用户ID
     * @return Prop实体类
     */
    Prop getResignInCard(Long userId);

    /**
     * 加速积分小水滴成熟
     * @param userId 用户ID
     */
    void speedDrops(Long userId);

    void addPropsGift(Long userId, PropCountDTO propCountDTO);

    /**
     * 保护积分小水滴
     * @param userId 用户ID
     * @return 是：使用成功；否：已经有保护了，不能叠加
     */
    boolean coverDrops(Long userId);
}
