package pers.jiangyinzuo.carbon.dao.cache;

/**
 * @author Jiang Yinzuo
 */
public interface TokenCache {
    /**
     * 验证token有效性
     * @param userId 用户ID
     * @param token 用户token
     * @return 有效返回true，无效返回false
     */
    boolean validateToken(Long userId, String token);

    /**
     * 为用户设置token
     * @param userId 用户ID
     * @param token 用户token
     */
    void setToken(Long userId, String token);
}
