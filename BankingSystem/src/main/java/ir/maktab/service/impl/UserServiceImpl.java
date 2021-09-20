package ir.maktab.service.impl;

import ir.maktab.domain.Customer;
import ir.maktab.domain.User;
import ir.maktab.repository.BaseUserRepository;
import ir.maktab.service.UserService;
import ir.maktab.service.menu.BaseMenu;
import ir.maktab.service.menu.ProfileMenu;
import ir.maktab.service.menu.ProfileMenuFactory;
import java.util.Scanner;

public class UserServiceImpl extends BaseUserServiceImpl<User, String, String> implements UserService {

    Scanner input = new Scanner(System.in);

    public UserServiceImpl(BaseUserRepository<User, String, String> repository) {
        super(repository);
    }

    public void login() {
        BaseMenu.singleSetMessage("Insert your Username");
        var username = input.next();

        BaseMenu.singleSetMessage("Insert your Password");
        var password = input.next();

        if(existsByUsernameAndPassword(username, password)) {
            BaseMenu.singlePrintMessage(BaseMenu.WELCOME);
            User user = findByUsername(username);
            ProfileMenu<? extends User> profile = ProfileMenuFactory.getProfileMenu(user);
            profile.dashboard(user);
        }
        else {
            BaseMenu.singlePrintMessage("Username or Password Incorrect!!");
        }
    }

}
