package ir.maktab.service;

import ir.maktab.base.service.BaseEntityService;
import ir.maktab.domain.*;
import java.util.Collection;
import java.util.List;

public interface CreditCardService extends BaseEntityService<CreditCard, Long> {

    Boolean cardToCartTransfer(User customer);

    Boolean assignOrChangePassword(User customer);

    void cardManagement(User customer);

    CreditCard findLastRecord();

    CreditCard findByCardNumber(Long cardNumber);

    List<CreditCard> findAllByAccountId(Collection<Account> accounts);
}
