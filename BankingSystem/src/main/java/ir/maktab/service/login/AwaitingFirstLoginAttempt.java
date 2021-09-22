package ir.maktab.service.login;

import ir.maktab.domain.User;
import ir.maktab.util.LoginServiceContext;

public class AwaitingFirstLoginAttempt extends LoginServiceState {
    @Override
    public void handleIncorrectPassword(LoginServiceContext context, User account,
                                        String password) {
        context.setState(new AfterFirstFailedLoginAttempt(account.getUsername()));
    }
}