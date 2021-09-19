package ir.maktab.domain;

import ir.maktab.base.domain.BaseEntity;
import lombok.*;
import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity<Long> {

    private static final String CUSTOMER_ID = "customer_id";
    private static final String BANK_BRANCH_ID = "bank_branch_id";

    @ManyToOne
    @JoinColumn(name = CUSTOMER_ID)
    private Customer customer;

    @Embedded
    private CreditCard creditCard;

    @ManyToOne
    @JoinColumn(name = BANK_BRANCH_ID)
    private BankBranch bankBranch;

}
