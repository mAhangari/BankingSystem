package ir.maktab.base.repository.impl;

import ir.maktab.base.domain.BaseEntity;
import ir.maktab.base.repository.BaseEntityRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseEntityRepositoryImpl<E extends BaseEntity<ID>, ID extends Serializable>
			implements BaseEntityRepository<E, ID> {
	
	private final EntityManagerFactory emf;
	protected EntityManager em;
	
	public BaseEntityRepositoryImpl(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public abstract Class<E> getEntityClass();

	@Override
	public E save(E e) {
		if(e.getId() == null) {
			em.persist(e);
		}else {
			em.merge(e);
		}
		return e;
	}

	@Override
	public List<E> findAllById(Collection<ID> ids) {
		
		List<E> elements = new ArrayList<>();
		for(ID id: ids) {
			elements.add(findById(id));
		}
		return elements;
	}
	
	@Override
	public List<E> findAll() {
		return em.createQuery("FROM " + getEntityClass().getSimpleName(), getEntityClass()).getResultList();
	}

	@Override
	public void delete(E e) {
		//em.remove(e);
		em.createQuery(
				"UPDATE " + getEntityClass().getSimpleName() + 
				" AZ a SET a.isDeleted = 1 WHERE a.id =: id")
		.setParameter("id", e.getId()).executeUpdate();
	}

	@Override
	public E findById(ID id) {	
		return em.find(getEntityClass(), id);
	}

	@Override
	public Boolean existsById(ID id) {
		return em.createQuery(
				"SELECT COUNT(e.id) FROM " + getEntityClass().getSimpleName() +
				" AZ e WHERE e.id =: id AND e.isDeleted = 0 AND isActive = 1"
				, Long.class
				).setParameter("id", id).getSingleResult() == 1L;
	}
	
	@Override
	public EntityManager getEntityManager() {
		em = emf.createEntityManager();
		return em;
	}
}
