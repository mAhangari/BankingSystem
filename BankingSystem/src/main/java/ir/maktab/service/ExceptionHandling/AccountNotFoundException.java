package ir.maktab.service.ExceptionHandling;

public class AccountNotFoundException extends RuntimeException {

    //private static final long serialVersionUID = 1L;

    public AccountNotFoundException() {

    }

    public AccountNotFoundException(String message) {
        super(message);
    }
}