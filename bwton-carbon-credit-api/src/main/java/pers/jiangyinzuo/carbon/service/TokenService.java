package pers.jiangyinzuo.carbon.service;


/**
 * @author Jiang Yinzuo
 */
public interface TokenService {
    /**
     * 验证token的有效性
     * @param token 用户token
     * @return 有效返回true，无效返回false
     */
    boolean validate(String token);
}
