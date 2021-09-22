package ir.maktab.service.menu;

import ir.maktab.domain.IUser;

public interface ProfileMenu<E extends IUser> {

    void dashboard(E entity);
}
