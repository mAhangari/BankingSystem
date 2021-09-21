package ir.maktab.service.impl;

import ir.maktab.base.service.impl.BaseEntityServiceImpl;
import ir.maktab.domain.User;
import ir.maktab.repository.BaseUserRepository;
import ir.maktab.service.BaseUserService;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class BaseUserServiceImpl<E extends User, UT, PT>
        extends BaseEntityServiceImpl<E, Long, BaseUserRepository<E, UT, PT>>
        implements BaseUserService<E, UT, PT> {

    public BaseUserServiceImpl(BaseUserRepository<E, UT, PT> repository) {
        super(repository);
    }

    @Override
    public Boolean existsByUsername(UT username) {
        EntityManager em = repository.getEntityManager();
        try {
            return repository.existsByUsername(username);
        } catch (NoResultException e) {
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public E findUserByUsername(UT username) {
        EntityManager em = repository.getEntityManager();
        try {
            return repository.findUserByUsername(username);
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Boolean existsByUsernameAndPassword(UT username, PT password) {
        EntityManager em = repository.getEntityManager();
        try {
            return repository.existsByUsernameAndPassword(username, password);
        } catch (NoResultException e) {
            return false;
        } finally {
            em.close();
        }
    }

}
