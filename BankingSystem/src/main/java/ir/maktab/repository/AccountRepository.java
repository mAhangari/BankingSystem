package ir.maktab.repository;

import ir.maktab.base.repository.BaseEntityRepository;
import ir.maktab.domain.Account;
import ir.maktab.domain.Customer;

public interface AccountRepository extends BaseEntityRepository<Account, Long> {

    Account findByAccountNumber(Long accountNumber);

    Account findLastRecord();
}
