package ir.maktab.domain;

import lombok.*;
import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee extends User {

    public static final String BANK_BRANCH_ID = "bank_branch_id";
    public static final String BOSS_ID = "boss_id";

    @ManyToOne
    @JoinColumn(name = BANK_BRANCH_ID)
    private BankBranch bankBranch;

    @ManyToOne
    @JoinColumn(name = BOSS_ID)
    private Boss boss;

    public Employee(String username, String password, String firstName, String lastName){
        setUsername(username);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
    }
}
