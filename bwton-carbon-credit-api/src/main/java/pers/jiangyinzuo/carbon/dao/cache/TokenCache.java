package pers.jiangyinzuo.carbon.dao.cache;

/**
 * @author Jiang Yinzuo
 */
public interface TokenCache {

    /**
     * 保存用户签名
     * @param userId 用户ID
     * @param signature 签名
     */
    void setSignature(String userId, String signature);

    /**
     * 根据用户ID获取签名
     * @param userId 用户ID
     * @return 用户签名，不存在则返回null
     */
    String getSignature(String userId);
}
