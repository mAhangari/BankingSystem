package ir.maktab.service.ExceptionHandling;

public class AccountLoginLimitReachedException extends RuntimeException {

    //private static final long serialVersionUID = 1L;

    public AccountLoginLimitReachedException() {
    }

    public AccountLoginLimitReachedException(String message) {
        super(message);
    }
}