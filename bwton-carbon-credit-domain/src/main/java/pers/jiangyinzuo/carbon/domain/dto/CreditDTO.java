package pers.jiangyinzuo.carbon.domain.dto;

import lombok.Data;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Objects;

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

    public static CreditDTO createCreditDTO(ZSetOperations.TypedTuple<Number> tuple) {
        return new CreditDTO(Objects.requireNonNull(tuple.getValue()), tuple.getScore());
    }
}
