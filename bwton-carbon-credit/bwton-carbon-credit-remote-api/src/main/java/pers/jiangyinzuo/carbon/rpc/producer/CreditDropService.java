package pers.jiangyinzuo.carbon.rpc.producer;

/**
 * @author Jiang Yinzuo
 */
public interface CreditDropService {
    /**
     * 用户绿色出行后，根据出行路程生成未成熟的积分小水滴
     *
     * @param userId 用户ID
     * @param distance 用户绿色出行路程, 单位公里
     */
    void addCreditDrop(Long userId, Double distance);
}
