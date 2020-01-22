package pers.jiangyinzuo.carbon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jiangyinzuo.carbon.common.security.Sha256Util;
import pers.jiangyinzuo.carbon.dao.cache.TokenCache;
import pers.jiangyinzuo.carbon.service.TokenService;

/**
 * @author Jiang Yinzuo
 */
@Service
public class TokenServiceImpl implements TokenService {

    private TokenCache tokenCache;

    @Autowired
    public void setTokenCache(TokenCache tokenCache) {
        this.tokenCache = tokenCache;
    }

    @Override
    public boolean validateToken(Long userId, String token) {
        return tokenCache.validateToken(userId, token);
    }

    @Override
    public String refreshToken(Long userId) {
        String newToken = Sha256Util.genToken();
        tokenCache.setToken(userId, newToken);
        return newToken;
    }


}
