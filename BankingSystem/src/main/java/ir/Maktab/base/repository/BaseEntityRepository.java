package ir.Maktab.base.repository;

import ir.Maktab.base.domain.BaseEntity;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseEntityRepository<E extends BaseEntity<ID>, ID extends Serializable> {

    E save(E e);

    List<E> findAllById(Collection<ID> ids);

    List<E> findAll();

	void delete(E e);
    
    E findById(ID id);

    Boolean existsById(ID id);

	EntityManager getEntityManager();
    
}
