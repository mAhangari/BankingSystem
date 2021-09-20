package ir.maktab.domain;

import ir.maktab.base.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
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
    private static final String FIRST_PASSWORD = "first_password";
    private static final String SECOND_PASSWORD = "second_password";
    private static final String ACCOUNT_ID = "account_id";

    private Boolean isActive;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = ACCOUNT_ID, unique = true, nullable = false)
    private Account account;

    @Column(name = CARD_NUMBER, length = 16, unique = true)
    @Size(min = 16, max = 16)
    private Long cardNumber;

    @Column(name = FIRST_PASSWORD, length = 4)
    @Size(min = 4, max = 4)
    private int firstPassword;

    @Column(name = SECOND_PASSWORD)
    @Size(min = 5, max = 10)
    private String secondPassword;

    @Column(name = CVV2, length = 4)
    private String cvv2;

    private Date expireDate;

}
