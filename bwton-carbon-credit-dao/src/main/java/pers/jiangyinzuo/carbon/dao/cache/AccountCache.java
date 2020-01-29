package pers.jiangyinzuo.carbon.dao.cache;

/**
 * @author Jiang Yinzuo
 */
public interface AccountCache {
    void increaseAccountTotal();

    long getAccountTotal();
}
