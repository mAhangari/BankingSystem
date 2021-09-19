package ir.maktab.service.menu;

import ir.maktab.domain.User;
import ir.maktab.domain.enumeration.UserType;
import java.util.HashMap;
import java.util.Map;

public final class ProfileMenuFactory {

    private static final Map<UserType, ProfileMenu<? extends User>> profileMenus = new HashMap<>() {
        {
            put(UserType.Customer, new CustomerProfileMenu());
            put(UserType.Employee, new EmployeeProfileMenu());
            put(UserType.Boss, new BossProfileMenu());
        }
    };
    private static ProfileMenu<? extends User> profileMenu;

    private ProfileMenuFactory() {
    }

    public static synchronized ProfileMenu<? extends User> getProfileMenu(User user) {
        if (profileMenu == null)
            profileMenu = profileMenus.get(user.getUserType());

        return profileMenu;
    }

}
