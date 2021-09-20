package ir.maktab.service;

import ir.maktab.base.service.BaseEntityService;
import ir.maktab.domain.Account;
import ir.maktab.domain.Customer;
import ir.maktab.domain.User;

public interface AccountService extends BaseEntityService<Account, Long> {

    void displayCustomerAccount(Customer customer);

    void recentTransactions();

    void accountManagement(User employee);

    Account findByAccountNumber(Long accountNumber);

    Account findLastRecord();

}
