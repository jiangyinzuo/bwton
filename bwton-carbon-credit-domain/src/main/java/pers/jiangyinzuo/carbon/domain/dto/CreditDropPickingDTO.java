package pers.jiangyinzuo.carbon.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pers.jiangyinzuo.carbon.domain.validation.annotation.DropValue;
import pers.jiangyinzuo.carbon.domain.validation.annotation.ID;

/**
 * @author Jiang Yinzuo
 */
@Data
public class CreditDropPickingDTO {

    @ID
    private Long userId;

    @ID
    private Long pickedUserId;

    @DropValue
    private String dropValue;

    @JsonIgnore
    private Long credit;

    @JsonIgnore
    private Long matureTime;

    public CreditDropPickingDTO() {
    }

    public CreditDropPickingDTO(Long userId, Long pickedUserId, String dropValue) {

        this.userId = userId;
        this.pickedUserId = pickedUserId;
        this.dropValue = dropValue;

        String[] dropParam = dropValue.split(".", 2);
        this.credit = Long.parseLong(dropParam[1]);
        this.matureTime = Long.parseLong(dropParam[0]);
    }
}
