package ir.maktab.service;

import ir.maktab.base.service.BaseEntityService;
import ir.maktab.domain.User;

public interface BaseUserService<E extends User, UT, PT> extends BaseEntityService<E, Long> {

    E findUserByUsername(UT username);

    Boolean existsByUsernameAndPassword(UT username, PT password);

    Boolean existsByUsername(UT username);
}
