package ir.maktab.domain;

import ir.maktab.base.domain.BaseEntity;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity<Long> {

    private static final String CUSTOMER_ID = "customer_id";
    private static final String BANK_BRANCH_ID = "bank_branch_id";
    private static final String CREDIT_CARD_ID = "credit_card_id";
    private static final String ACCOUNT_NUMBER = "account_number";

    @Column(name = ACCOUNT_NUMBER)
    @Size(min = 10, max = 10)
    private Long accountNumber;

    @ManyToOne
    @JoinColumn(name = CUSTOMER_ID)
    private Customer customer;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private CreditCard creditCard;

    @ManyToOne
    @JoinColumn(name = BANK_BRANCH_ID)
    private BankBranch bankBranch;

    private String balance;

    @Override
    public String toString(){
        return "account number: '" + accountNumber + "' " +
               "Bank Branch: '" + bankBranch.getBranchName() + "' " +
               "Balance: '" + balance + "'";
    }

}
