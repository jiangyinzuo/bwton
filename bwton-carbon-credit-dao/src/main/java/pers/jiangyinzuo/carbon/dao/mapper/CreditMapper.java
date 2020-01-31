package pers.jiangyinzuo.carbon.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
@Mapper
public interface CreditMapper {
    /**
     * 向数据库读取用户的历史总碳积分
     * @param userIds 用户ID列表
     * @return 历史总碳积分列表
     */
    List<Long> getHistoryTotalCredits(List<Long> userIds);
}
