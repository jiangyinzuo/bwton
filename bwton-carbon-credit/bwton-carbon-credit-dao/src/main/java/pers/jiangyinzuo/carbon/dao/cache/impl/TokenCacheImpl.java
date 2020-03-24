package pers.jiangyinzuo.carbon.dao.cache.impl;

import io.lettuce.core.SetArgs;
import org.springframework.stereotype.Repository;
import pers.jiangyinzuo.carbon.dao.cache.BaseCache;
import pers.jiangyinzuo.carbon.dao.cache.TokenCache;

import static pers.jiangyinzuo.carbon.dao.cache.RedisKeyBuilder.userSignatureKey;

/**
 * @author Jiang Yinzuo
 */
@Repository
public class TokenCacheImpl extends BaseCache implements TokenCache {

    @Override
    public void setSignature(String userId, String signature) {
        conn.async().set(
                userSignatureKey(userId),
                signature,
                SetArgs.Builder.ex(864000)
        );
    }

    @Override
    public String getSignature(String userId) {
        return conn.sync().get(userSignatureKey(userId));
    }
}
