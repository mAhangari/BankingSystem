package ir.maktab.service.menu;

import ir.maktab.domain.User;

public interface ProfileMenu<E extends User> {

    void dashboard(User entity);
}
