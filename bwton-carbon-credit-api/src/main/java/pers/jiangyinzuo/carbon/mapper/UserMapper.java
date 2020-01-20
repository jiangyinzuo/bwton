package pers.jiangyinzuo.carbon.mapper;

import pers.jiangyinzuo.carbon.domain.dto.UserAccountDTO;

/**
 * @author Jiang Yinzuo
 */
public interface UserMapper {

    UserAccountDTO getUserAccountByUserId(Long userId);
}
