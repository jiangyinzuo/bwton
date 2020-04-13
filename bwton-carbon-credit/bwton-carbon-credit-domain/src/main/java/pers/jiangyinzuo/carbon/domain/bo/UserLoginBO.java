package pers.jiangyinzuo.carbon.domain.bo;

/**
 * @author Jiang Yinzuo
 */
public record UserLoginBO(
        Long userId,
        String telephone,
        byte[]salt,
        String cipher
) {}
