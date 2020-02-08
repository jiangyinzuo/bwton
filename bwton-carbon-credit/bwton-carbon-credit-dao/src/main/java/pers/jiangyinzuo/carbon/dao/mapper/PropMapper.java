package pers.jiangyinzuo.carbon.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import pers.jiangyinzuo.carbon.domain.entity.UserProps;

/**
 * @author Jiang Yinzuo
 */
@Mapper
public interface PropMapper {
    /**
     * 获取用户道具
     * @param userId 用户ID
     * @return 用户道具实体类
     */
    UserProps getProps(Long userId);
}
