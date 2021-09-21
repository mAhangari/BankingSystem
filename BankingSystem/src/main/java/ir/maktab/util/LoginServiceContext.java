package ir.maktab.util;

import ir.maktab.service.login.LoginServiceState;

public abstract class LoginServiceContext {

    private LoginServiceState state;

    public LoginServiceContext(LoginServiceState state) {
        this.state = state;
    }

    public LoginServiceState getState() {
        return state;
    }

    public void setState(LoginServiceState state) {
        this.state = state;
    }

}
