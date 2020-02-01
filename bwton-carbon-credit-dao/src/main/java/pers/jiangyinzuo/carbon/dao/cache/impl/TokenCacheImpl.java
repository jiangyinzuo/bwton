package pers.jiangyinzuo.carbon.dao.cache.impl;

import io.lettuce.core.SetArgs;
import org.springframework.stereotype.Repository;
import pers.jiangyinzuo.carbon.dao.cache.AbstractCache;
import pers.jiangyinzuo.carbon.dao.cache.TokenCache;

import static pers.jiangyinzuo.carbon.dao.cache.KeyBuilder.userSignatureKey;

/**
 * @author Jiang Yinzuo
 */
@Repository
public class TokenCacheImpl extends AbstractCache implements TokenCache {

    @Override
    public void setSignature(String userId, String signature) {
        redisConnection.sync().set(
                userSignatureKey(userId),
                signature,
                SetArgs.Builder.ex(864000)
        );
    }

    @Override
    public String getSignature(String userId) {
        return redisConnection.sync().get(userSignatureKey(userId));
    }
}
