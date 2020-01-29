package pers.jiangyinzuo.carbon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jiangyinzuo.carbon.common.security.Sha256Util;
import pers.jiangyinzuo.carbon.dao.cache.TokenCache;
import pers.jiangyinzuo.carbon.service.TokenService;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

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
    public boolean authenticate(String base64Token) {
        String token = new String(Base64.getUrlDecoder().decode(base64Token), StandardCharsets.UTF_8);
        String[] pair = token.split(":",2);
        if (pair.length != 2) {
            return false;
        }
        String signature = tokenCache.getSignature(pair[0]);
        return signature != null && signature.equals(Sha256Util.genSignature(pair[1]));
    }

    @Override
    public String refreshBase64Token(String base64Token) {
        String token = new String(Base64.getUrlDecoder().decode(base64Token), StandardCharsets.UTF_8);
        String userId = token.split(":")[0];
        return genBase64Token(userId);
    }

    @Override
    public String genBase64Token(String userId) {
        String credential = Sha256Util.genCredential();
        String signature = Sha256Util.genSignature(credential);
        tokenCache.setSignature(userId, signature);
        return Base64.getUrlEncoder().encodeToString((userId + ":" + credential).getBytes());
    }

}
