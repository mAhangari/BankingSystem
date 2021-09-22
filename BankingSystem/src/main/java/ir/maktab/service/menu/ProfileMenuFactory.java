package ir.maktab.service.menu;

import ir.maktab.domain.User;
import ir.maktab.domain.enumeration.UserType;
import java.util.HashMap;
import java.util.Map;

public class ProfileMenuFactory {

    public static final Map<UserType, ProfileMenu<User>> profileMenus = new HashMap<>() {
        {
            put(UserType.Customer, new CustomerProfileMenu<>());
            put(UserType.Employee, new EmployeeProfileMenu<>());
            put(UserType.Boss, new BossProfileMenu<>());
        }
    };

    public static ProfileMenu<User> getProfileMenu(User user) {

        return profileMenus.get(user.getUserType());
    }

}
