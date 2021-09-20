package ir.maktab.repository;

import ir.maktab.base.repository.BaseEntityRepository;
import ir.maktab.domain.Account;
import ir.maktab.domain.CreditCard;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface CreditCardRepository extends BaseEntityRepository<CreditCard, Long> {

    CreditCard findLastRecord();

    CreditCard findByCardNumber(Long cardNumber);

    List<CreditCard> findAllByAccountId(Collection<Account> accounts);
}
