package ir.maktab.util;

import ir.maktab.repository.impl.AccountRepositoryImpl;
import ir.maktab.repository.impl.CreditCardRepositoryImpl;
import ir.maktab.repository.impl.UserRepositoryImpl;
import ir.maktab.service.impl.AccountServiceImpl;
import ir.maktab.service.impl.CreditCardServiceImpl;
import ir.maktab.service.menu.Menu;
import ir.maktab.service.impl.UserServiceImpl;

public class ApplicationContext {

    public static final UserRepositoryImpl userRepo;
    public static final UserServiceImpl userServ;

    public static final CreditCardRepositoryImpl creditCardRepo;
    public static final CreditCardServiceImpl creditCardServ;

    public static final AccountRepositoryImpl accountRepo;
    public static final AccountServiceImpl accountServ;


    public static final Menu menu;
    public static final CheckInputInformation chInInformation;

    static {
        userRepo = new UserRepositoryImpl(HibernateUtil.getEntityMangerFactory());
        userServ = new UserServiceImpl(userRepo);

        creditCardRepo = new CreditCardRepositoryImpl(HibernateUtil.getEntityMangerFactory());
        creditCardServ = new CreditCardServiceImpl(creditCardRepo);

        accountRepo = new AccountRepositoryImpl(HibernateUtil.getEntityMangerFactory());
        accountServ = new AccountServiceImpl(accountRepo);

        menu = new Menu();
        chInInformation = new CheckInputInformation();
    }
}

