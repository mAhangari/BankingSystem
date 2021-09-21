package ir.maktab.service.impl;

import ir.maktab.base.service.impl.BaseEntityServiceImpl;
import ir.maktab.domain.*;
import ir.maktab.repository.AccountRepository;
import ir.maktab.service.AccountService;
import ir.maktab.service.menu.BaseMenu;
import ir.maktab.util.ApplicationContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.*;
import java.util.stream.Collectors;

public class AccountServiceImpl extends BaseEntityServiceImpl<Account, Long, AccountRepository>
        implements AccountService {
    Scanner input = new Scanner(System.in);

    public AccountServiceImpl(AccountRepository repository) {
        super(repository);
    }

    @Override
    public Account findLastRecord() {
        EntityManager em = repository.getEntityManager();
        try {
            return repository.findLastRecord();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public void displayCustomerAccount(Customer customer) {
        List<Account> accounts = customer.getAccounts().stream().toList();
        BaseMenu.optionMessage(accounts
                .stream().map(a -> a.getAccountNumber().toString()).collect(Collectors.toList()), false);
        BaseMenu.singleSetMessage("Select an option or insert '0' to exit");
        try {
            int option = input.nextInt();
            if (option != 0) {

                if (option > accounts.size() || option < 0) {
                    BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
                } else {
                    BaseMenu.singlePrintMessage(accounts.get(option - 1).toString());
                }
                displayCustomerAccount(customer);
            }

        } catch (InputMismatchException e) {
            input.nextLine();
            BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
            displayCustomerAccount(customer);
        }

    }

    @Override
    public void recentTransactions() {

    }

    @Override
    public void accountManagement(IUser employee) {
        try {
            List<String> list = new ArrayList<>(Arrays.asList(
                    "Create New Account", "Remove Account", "Exit"
            ));
            BaseMenu.optionMessage(list, true);

            switch (input.nextInt()) {
                case 1 -> {
                    Account newAccount = new Account();
                    accountGeneration(newAccount, (Employee) employee);
                    accountManagement(employee);
                }
                case 2 -> {
                    BaseMenu.singleSetMessage("Enter Account number: ");
                    Long accountNumber = input.nextLong();

                    Account checkAccount = checkAccountExists(accountNumber);
                    if (checkAccount == null) {
                        BaseMenu.singlePrintMessage("This account does not exists.");
                        accountManagement(employee);
                    } else {
                        delete(checkAccount);
                    }
                }
                case 3 -> {

                }
                default -> {
                    BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
                    accountManagement(employee);
                }
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
            accountManagement(employee);
        }

    }

    private void accountGeneration(Account newAccount, Employee employee) {
        BaseMenu.singleSetMessage("Enter customer username");
        String customerUsername = input.next();
        User customer = ApplicationContext.userServ.findUserByUsername(customerUsername);
        if (customer == null)
            return;
        BaseMenu.singleSetMessage("Insert Customer Balance");
        String balance = input.next();
        newAccount.setBalance(balance);
        Account account = findLastRecord();
        if (account == null)
            newAccount.setAccountNumber(1111111111L);

        else newAccount.setAccountNumber(account.getAccountNumber() + 1L);
        newAccount.setBankBranch(employee.getBankBranch());
        newAccount.setCustomer((Customer) customer);
        save(newAccount);
    }

    @Override
    public Account findByAccountNumber(Long accountNumber) {
        EntityManager em = repository.getEntityManager();
        try {
            return repository.findByAccountNumber(accountNumber);
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    private Account checkAccountExists(Long accountNumber) {
        return ApplicationContext.accountServ.findByAccountNumber(accountNumber);
    }
}
