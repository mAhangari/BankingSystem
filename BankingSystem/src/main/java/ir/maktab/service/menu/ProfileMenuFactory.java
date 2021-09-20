package ir.maktab.service.menu;

import ir.maktab.domain.User;
import ir.maktab.domain.enumeration.UserType;
import java.util.HashMap;
import java.util.Map;

public class ProfileMenuFactory {

    private static final Map<UserType, ProfileMenu<? extends User>> profileMenus = new HashMap<>() {
        {
            put(UserType.Customer, new CustomerProfileMenu());
            put(UserType.Employee, new EmployeeProfileMenu());
            put(UserType.Boss, new BossProfileMenu());
        }
    };

    public static ProfileMenu<? extends User> getProfileMenu(User user) {

        return profileMenus.get(user.getUserType());
    }

}
