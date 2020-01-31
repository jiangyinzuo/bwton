package pers.jiangyinzuo.carbon.service;


/**
 * @author Jiang Yinzuo
 */
public interface TokenService {
    /**
     * 验证base64 token的有效性
     * @param base64Token 用户token
     * @return 有效返回true，无效返回false
     */
    boolean authenticate(String base64Token);

    /**
     * 刷新base64 token
     * @param base64Token 用户ID
     * @return 新的base64 token
     */
    String refreshBase64Token(String base64Token);

    /**
     * 生成base64 token
     * @param userId 用户ID
     * @return base64 token
     */
    String genBase64Token(String userId);
}
