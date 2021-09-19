package ir.maktab.domain;

import ir.maktab.base.domain.BaseEntity;
import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BankBranch extends BaseEntity<Long> {

    public static final String BOSS_ID = "boss_id";

    private String branchName;

    private String branchCode;

    @OneToMany(mappedBy = "bankBranch")
    private Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy = "bankBranch")
    private Set<Employee> employees = new HashSet<>();

    @OneToOne
    @JoinColumn(name = BOSS_ID)
    private Boss boss;
}
