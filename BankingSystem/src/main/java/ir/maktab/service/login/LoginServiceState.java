package ir.maktab.service.login;

import ir.maktab.domain.User;
import ir.maktab.service.ExceptionHandling.AccountLoginLimitReachedException;
import ir.maktab.service.ExceptionHandling.AccountRevokedException;
import ir.maktab.service.menu.BaseMenu;
import ir.maktab.service.menu.ProfileMenu;
import ir.maktab.service.menu.ProfileMenuFactory;
import ir.maktab.util.LoginServiceContext;

public abstract class LoginServiceState {
    public final void login(LoginServiceContext context, User account,
                            String password) {
        if (account.passwordMatches(password)) {
            if (account.isLoggedIn())
                throw new AccountLoginLimitReachedException();
            if (account.isRevoked())
                throw new AccountRevokedException();
            account.setLoggedIn(true);
            context.setState(new AwaitingFirstLoginAttempt());
            BaseMenu.singlePrintMessage(BaseMenu.WELCOME);
            ProfileMenu<User> profile = ProfileMenuFactory.getProfileMenu(account);
            if (profile != null)
                profile.dashboard(account);
            account.setLoggedIn(false);
        } else
            handleIncorrectPassword(context, account, password);
    }

    public abstract void handleIncorrectPassword(LoginServiceContext context,
                                                 User account, String password);
}