package pers.jiangyinzuo.carbon.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import pers.jiangyinzuo.carbon.domain.dto.PropCountDTO;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
@Mapper
public interface PropMapper {
    /**
     * 获取用户道具
     * @param userId 用户ID
     * @return 用户道具列表
     */
    List<pers.jiangyinzuo.carbon.domain.entity.Prop> getProps(Long userId);

    /**
     * 根据道具ID和用户ID获取某一道具信息
     * @param userId 用户ID
     * @param propId 道具ID
     * @return 道具实体类
     */
    pers.jiangyinzuo.carbon.domain.entity.Prop getPropById(Long userId, Long propId);

    /**
     * 减少道具数量
     * @param userId 用户ID
     * @param propId 道具ID
     * @param decr 减少道具的数量
     */
    void decrPropsCount(Long userId, Long propId, Integer decr);

    /**
     * 增加道具数量
     * @param userId 用户ID
     * @param props 道具DTO列表
     */
    void addPropsCount(Long userId, List<PropCountDTO.Prop> props);
}
