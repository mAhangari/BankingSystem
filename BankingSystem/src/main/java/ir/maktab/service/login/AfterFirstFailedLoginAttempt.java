package ir.maktab.service.login;

import ir.maktab.domain.User;
import ir.maktab.util.LoginServiceContext;

public class AfterFirstFailedLoginAttempt extends LoginServiceState {
    private String previousAccountId;

    public AfterFirstFailedLoginAttempt(String previousAccountId) {
        this.previousAccountId = previousAccountId;
    }

    @Override
    public void handleIncorrectPassword(LoginServiceContext context, User account,
                                        String password) {
        if (previousAccountId.equals(account.getUsername()))
            context.setState(new AfterSecondFailedLoginAttempt(account.getUsername()));
        else
            previousAccountId = account.getUsername();
    }
}