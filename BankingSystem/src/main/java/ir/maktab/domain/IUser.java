package ir.maktab.domain;

import ir.maktab.domain.enumeration.UserType;

public interface IUser {

    boolean passwordMatches(String password);

    boolean isLoggedIn();

    void setLoggedIn(boolean value);

    boolean isRevoked();

    void setRevoked(boolean value);

    String getUsername();

    UserType getUserType();
}