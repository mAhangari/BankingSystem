package ir.maktab.service.impl;

import ir.maktab.domain.User;
import ir.maktab.repository.BaseUserRepository;
import ir.maktab.service.UserService;

public class UserServiceImpl extends BaseUserServiceImpl<User, String, String> implements UserService {

    public UserServiceImpl(BaseUserRepository<User, String, String> repository) {
        super(repository);
    }

}
