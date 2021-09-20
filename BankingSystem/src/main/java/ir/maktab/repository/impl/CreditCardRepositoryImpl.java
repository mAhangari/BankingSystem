package ir.maktab.repository.impl;

import ir.maktab.base.repository.impl.BaseEntityRepositoryImpl;
import ir.maktab.domain.Account;
import ir.maktab.domain.CreditCard;
import ir.maktab.repository.CreditCardRepository;

import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.util.*;


public class CreditCardRepositoryImpl extends BaseEntityRepositoryImpl<CreditCard, Long>
        implements CreditCardRepository {

    public CreditCardRepositoryImpl(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public Class<CreditCard> getEntityClass() {
        return CreditCard.class;
    }

    @Override
    public CreditCard findLastRecord() {
        return em.createQuery("FROM CreditCard ORDER BY id DESC", CreditCard.class)
                .setMaxResults(1)
                .getSingleResult();
    }

    @Override
    public List<CreditCard> findAllByAccountId(Collection<Account> accounts) {
        List<CreditCard> creditCards = new ArrayList<>();
        for(Account account: accounts) {
            creditCards.add(
                    em.createQuery("FROM CreditCard C WHERE C.account =: account", CreditCard.class)
                            .setParameter("account", account)
                            .getSingleResult()
            );
        }
        return creditCards;
    }

    @Override
    public CreditCard findByCardNumber(Long cardNumber) {
        return em.createQuery("FROM CreditCard c " +
                                "WHERE c.cardNumber =: cardNumber",
                        CreditCard.class)
                .setParameter("cardNumber", cardNumber).getSingleResult();
    }
}
