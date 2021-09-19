package ir.maktab.base.domain;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity<ID extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ID id;
	
	@Column(name = "is_Delet", columnDefinition = "TINYINT(1)")
    private Boolean isDeleted = false;
    
	public BaseEntity() {
	}
	
    public BaseEntity(Boolean isDeleted, ID id) {
		this.setDeleted(isDeleted);
        this.setId(id);
	}

	public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
