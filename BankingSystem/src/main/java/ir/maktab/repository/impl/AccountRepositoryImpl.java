package ir.maktab.repository.impl;

import ir.maktab.base.repository.impl.BaseEntityRepositoryImpl;
import ir.maktab.domain.Account;
import ir.maktab.domain.CreditCard;
import ir.maktab.domain.Customer;
import ir.maktab.repository.AccountRepository;
import javax.persistence.EntityManagerFactory;

public class AccountRepositoryImpl extends BaseEntityRepositoryImpl<Account, Long> implements AccountRepository {

    public AccountRepositoryImpl(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public Class<Account> getEntityClass() {
        return Account.class;
    }

    @Override
    public Account findByAccountNumber(Long accountNumber) {
        return em.createQuery("FROM Account a " +
                        "WHERE a.accountNumber =: accountNumber ",
                        Account.class)
                .setParameter("accountNumber", accountNumber)
                .getSingleResult();
    }

    @Override
    public Account findLastRecord() {
        return em.createQuery("FROM Account ORDER BY id DESC", Account.class)
                .setMaxResults(1)
                .getSingleResult();
    }
}
