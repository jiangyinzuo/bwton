package pers.jiangyinzuo.carbon.service;


/**
 * @author Jiang Yinzuo
 */
public interface TokenService {
    /**
     * 验证token的有效性
     * @param userId 用户ID
     * @param token 用户token
     * @return 有效返回true，无效返回false
     */
    boolean validateToken(Long userId, String token);

    /**
     * 刷新token
     * @param userId 用户ID
     * @return 新的token
     */
    String refreshToken(Long userId);
}
