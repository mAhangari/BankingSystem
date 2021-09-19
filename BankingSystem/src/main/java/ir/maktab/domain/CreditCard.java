package ir.maktab.domain;

import ir.maktab.base.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard extends BaseEntity<Long> {

    private static final String CVV2 = "cvv2";
    private static final String CARD_NUMBER = "card_number";

    private Boolean isActive;

    @Column(name = CARD_NUMBER, length = 16, unique = true)
    @Size(min = 16, max = 16)
    private String cardNumber;

    private String secondPassword;

    @Column(name = CVV2, length = 4)
    private String cvv2;

    private Date expireDate;

    private String balance;

}
