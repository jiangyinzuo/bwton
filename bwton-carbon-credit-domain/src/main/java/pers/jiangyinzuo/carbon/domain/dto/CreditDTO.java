package pers.jiangyinzuo.carbon.domain.dto;

import lombok.Data;

/**
 * @author Jiang Yinzuo
 */
@Data
public class CreditDTO {
    Long userId;
    Double credit;

    public CreditDTO(Number userId, Double credit) {
        this.userId = userId.longValue();
        this.credit = credit;
    }

    public static CreditDTO createCreditDTO(Number userId) {
        return new CreditDTO(userId, 0D);
    }
}
