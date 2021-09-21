package ir.maktab.service.menu;

import ir.maktab.domain.IUser;
import ir.maktab.domain.enumeration.UserType;
import java.util.HashMap;
import java.util.Map;

public class ProfileMenuFactory {

    private static final Map<UserType, ProfileMenu<? extends IUser>> profileMenus = new HashMap<>() {
        {
            put(UserType.Customer, new CustomerProfileMenu());
            put(UserType.Employee, new EmployeeProfileMenu());
            put(UserType.Boss, new BossProfileMenu());
        }
    };

    public static ProfileMenu<? extends IUser> getProfileMenu(IUser user) {

        return profileMenus.get(user.getUserType());
    }

}
