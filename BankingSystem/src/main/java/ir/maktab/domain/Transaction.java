package ir.maktab.domain;

import ir.maktab.base.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends BaseEntity<Long> {

    private static final String CUSTOMER_ID = "customer_id";
    private static final String TRANSACTION_TIME = "transaction_time";

    private String description;

    @ManyToOne
    @JoinColumn(name = CUSTOMER_ID)
    private Customer customer;

    @Column(name = TRANSACTION_TIME)
    private LocalDate transactionTime;


}
