package ir.maktab.service.ExceptionHandling;

public class AccountRevokedException extends RuntimeException {

    //private static final long serialVersionUID = 1L;

    public AccountRevokedException() {

    }

    public AccountRevokedException(String message) {
        super(message);
    }
}
