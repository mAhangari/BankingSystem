package ir.maktab.repository.impl;

import ir.maktab.base.repository.impl.BaseEntityRepositoryImpl;
import ir.maktab.domain.User;
import ir.maktab.repository.BaseUserRepository;
//import javax.persistence.EntityGraph;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class BaseUserRepositoryImpl<E extends User, UT, PT> extends BaseEntityRepositoryImpl<E, Long>
                    implements BaseUserRepository<E, UT, PT> {


    public BaseUserRepositoryImpl(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public Boolean existsByUsername(UT username) {
        return em.createQuery(
                        "SELECT COUNT(e.id) FROM " + getEntityClass().getSimpleName() +
                                " AS e WHERE e.username =: username AND e.isDeleted = false",
                        Long.class)
                .setParameter("username", username)
                .getSingleResult() == 1L;
    }

    @Override
    public E findUserByUsername(UT username) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<? extends E> cq = cb.createQuery(getEntityClass());

        Root<? extends E> root = cq.from(getEntityClass());
        cq.where(cb.and(cb.equal(root.get("username"), username), cb.equal(root.get("isDeleted"), 0)));

        TypedQuery<? extends E> query = em.createQuery(cq);

        /*
        EntityGraph<?> entityGraph = em.getEntityGraph(Customer.FETCH_TWEET_AND_COMMENT);
                typedQuery.setHint(
                        LOAD_GRAPH, entityGraph
                );
        */

        return query.getSingleResult();
    }

    @Override
    public Boolean existsByUsernameAndPassword(UT username, PT password) {
        return em.createQuery(
                        "SELECT COUNT(e.id) FROM " + getEntityClass().getSimpleName() +
                                " AS e WHERE e.username =: username AND e.password =: password"
                                + " AND e.isDeleted = 0 AND e.isActive = 1"
                        , Long.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult() == 1L;
    }
}
