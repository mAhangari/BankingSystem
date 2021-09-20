package ir.maktab.service;

import ir.maktab.base.service.BaseEntityService;
import ir.maktab.domain.Account;
import ir.maktab.domain.CreditCard;
import ir.maktab.domain.Customer;
import ir.maktab.domain.User;
import java.util.Collection;
import java.util.List;

public interface CreditCardService extends BaseEntityService<CreditCard, Long> {

    Boolean cardToCartTransfer(Customer customer);

    Boolean assignOrChangePassword(Customer customer);

    void cardManagement(User customer);

    CreditCard findLastRecord();

    CreditCard findByCardNumber(Long cardNumber);

    List<CreditCard> findAllByAccountId(Collection<Account> accounts);
}
