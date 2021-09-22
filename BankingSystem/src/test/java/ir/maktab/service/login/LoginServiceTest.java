package ir.maktab.service.login;

import static org.junit.jupiter.api.Assertions.*;
import ir.maktab.domain.User;
import ir.maktab.service.ExceptionHandling.AccountLoginLimitReachedException;
import ir.maktab.service.ExceptionHandling.AccountNotFoundException;
import ir.maktab.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LoginServiceTest {

    private User account;
    private UserService accountService;
    private LoginService service;

    @BeforeEach
    public void init() {
        account = mock(User.class);
        when(account.getUsername()).thenReturn("brett");
        accountService = mock(UserService.class);
        when(accountService.findUserByUsername(anyString())).thenReturn(account);
        service = new LoginService(accountService);
    }

    private void willPasswordMatch(boolean value) {
        when(account.passwordMatches(anyString())).thenReturn(value);
    }

    @Test
    public void itShouldSetAccountToLoggedInWhenPasswordMatches() {
        willPasswordMatch(true);
        service.login("brett", "password");
        verify(account, times(1)).setLoggedIn(true);
    }

    @Test
    public void itShouldSetAccountToRevokedAfterThreeFailedLoginAttempts() {
        willPasswordMatch(false);

        for (int i = 0; i < 3; ++i)
            service.login("brett", "password");

        verify(account, times(1)).setRevoked(true);
    }

    @Test
    public void itShouldNotSetAccountLoggedInIfPasswordDoesNotMatch() {
        willPasswordMatch(false);
        service.login("brett", "password");
        verify(account, never()).setLoggedIn(true);
    }

    @Test
    public void itShouldNotRevokeSecondAccountAfterTwoFailedAttemptsFirstAccount() {
        willPasswordMatch(false);

        User secondAccount = mock(User.class);
        when(secondAccount.passwordMatches(anyString())).thenReturn(false);
        when(accountService.findUserByUsername("schuchert")).thenReturn(secondAccount);

        service.login("brett", "password");
        service.login("brett", "password");
        service.login("schuchert", "password");

        verify(secondAccount, never()).setRevoked(true);
    }

    @Test
    public void itShouldNowAllowConcurrentLogins() {
        willPasswordMatch(true);
        when(account.isLoggedIn()).thenReturn(true);
        assertThrows(AccountLoginLimitReachedException.class, () -> service.login("brett", "password"));
    }

    @Test
    public void itShouldThrowExceptionIfAccountNotFound() {
        when(accountService.findUserByUsername("schuchert")).thenReturn(null);
         assertThrows(AccountNotFoundException.class,  ()-> service.login("schuchert", "password") );

    }

    @Test
    public void ItShouldNotBePossibleToLogIntoRevokedAccount() {
        willPasswordMatch(true);
        when(account.isRevoked()).thenReturn(true);
        when(accountService.findUserByUsername("brett")).thenReturn(null);
        assertThrows(AccountNotFoundException.class, () -> service.login("brett", "password"));
    }

    @Test
    public void itShouldResetBackToInitialStateAfterSuccessfulLogin() {
        willPasswordMatch(false);
        service.login("brett", "password");
        service.login("brett", "password");
        willPasswordMatch(true);
        service.login("brett", "password");
        willPasswordMatch(false);
        service.login("brett", "password");
        verify(account, never()).setRevoked(true);
    }
}