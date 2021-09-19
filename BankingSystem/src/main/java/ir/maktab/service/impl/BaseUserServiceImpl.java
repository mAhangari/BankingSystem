package ir.maktab.service.impl;

import ir.maktab.base.service.impl.BaseEntityServiceImpl;
import ir.maktab.domain.User;
import ir.maktab.repository.BaseUserRepository;
import ir.maktab.service.BaseUserService;

public class BaseUserServiceImpl<E extends User, UT, PT>
        extends BaseEntityServiceImpl<E, Long, BaseUserRepository<E, UT, PT>>
            implements BaseUserService<E, UT, PT> {

    public BaseUserServiceImpl(BaseUserRepository<E, UT, PT> repository) {
        super(repository);
    }

    @Override
    public Boolean existsByUsername(UT username) {
        return repository.existsByUsername(username);
    }

    @Override
    public E findByUsername(UT username) {
        return repository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsernameAndPassword(UT username, PT password) {
        return repository.existsByUsernameAndPassword(username, password);
    }

}
