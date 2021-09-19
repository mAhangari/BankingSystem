package ir.maktab.repository.impl;

import ir.maktab.domain.User;
import ir.maktab.repository.UserRepository;
import javax.persistence.EntityManagerFactory;

public class UserRepositoryImpl extends BaseUserRepositoryImpl<User, String, String>
                    implements UserRepository {
    public UserRepositoryImpl(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }
}
