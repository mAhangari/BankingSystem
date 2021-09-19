package ir.maktab.domain;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Boss extends User {

    @OneToMany(mappedBy = "boss")
    private Set<Employee> employees = new HashSet<>();

    @OneToOne(mappedBy = "boss")
    private BankBranch bankBranch;
}
