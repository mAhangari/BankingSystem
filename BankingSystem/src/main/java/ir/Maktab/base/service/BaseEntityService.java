package ir.Maktab.base.service;

import ir.Maktab.base.domain.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseEntityService<E extends BaseEntity<ID>, ID extends Serializable> {
	
	E save(E e);

    List<E> findAllById(Collection<ID> ids);

    List<E> findAll();

    void delete(E e);

    E findById(ID id);

    Boolean existsById(ID id);
    
}
