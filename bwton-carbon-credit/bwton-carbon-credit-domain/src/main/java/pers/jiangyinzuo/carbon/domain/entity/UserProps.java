package pers.jiangyinzuo.carbon.domain.entity;

import lombok.Data;

/**
 * 用户拥有的道具数量
 *
 * @author Jiang Yinzuo
 */
@Data
public class UserProps {
    /**
     * 加速器
     */
    private Long speeder;

    /**
     * 积分保护罩
     */
    private Long cover;
    private Long lottery;

    /**
     * 补签卡
     */
    private Long reissueCard;
}
