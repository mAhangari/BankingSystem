package ir.maktab.service.impl;

import ir.maktab.base.service.impl.BaseEntityServiceImpl;
import ir.maktab.domain.Account;
import ir.maktab.domain.CreditCard;
import ir.maktab.domain.Customer;
import ir.maktab.domain.User;
import ir.maktab.repository.CreditCardRepository;
import ir.maktab.service.CreditCardService;
import ir.maktab.service.menu.BaseMenu;
import ir.maktab.util.ApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CreditCardServiceImpl extends BaseEntityServiceImpl<CreditCard, Long, CreditCardRepository>
        implements CreditCardService {

    Scanner input = new Scanner(System.in);

    public CreditCardServiceImpl(CreditCardRepository repository) {
        super(repository);
    }

    @Override
    public CreditCard findLastRecord() {
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
    public List<CreditCard> findAllByAccountId(Collection<Account> accounts) {
        EntityManager em = repository.getEntityManager();
        try {
            return repository.findAllByAccountId(accounts);
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Boolean cardToCartTransfer(Customer customer) {
        try {
            BaseMenu.singleSetMessage("Enter source card number");
            Long sourceCardNumber = input.nextLong();

            BaseMenu.singleSetMessage("Enter destination card number");
            Long destinationCardNumber = input.nextLong();

            BaseMenu.singleSetMessage("Enter Amount");
            String amount = input.next();

            CreditCard sourceCard = checkSourceCardExists(customer, sourceCardNumber);
            if (sourceCard == null) {
                BaseMenu.singlePrintMessage("Source card does not exists.");
                return false;
            }

            CreditCard destinationCard = findByCardNumber(destinationCardNumber);
            if (destinationCard == null) {
                BaseMenu.singlePrintMessage("Destination card does not exists.");
            } else {
                BaseMenu.singleSetMessage("Enter second password");
                String secondPassword = input.next();

                BaseMenu.singleSetMessage("Enter CVV2");
                String cvv2 = input.next();

                String expireDate;
                do {
                    BaseMenu.singleSetMessage("Enter expire date");
                    expireDate = input.next();
                } while (ApplicationContext.chInInformation.checkDate(expireDate));

                Date date = Date.valueOf(expireDate);
                if (sourceCard.getSecondPassword().equals(secondPassword) &&
                        sourceCard.getCvv2().equals(cvv2) &&
                        sourceCard.getExpireDate().equals(date)) {
                    if (Long.parseLong(amount) < (Long.parseLong(sourceCard.getAccount().getBalance()) + 600L)) {
                        sourceCard
                                .getAccount()
                                .setBalance(String
                                        .valueOf(Long
                                                .parseLong(sourceCard
                                                        .getAccount()
                                                        .getBalance()) - (Long.parseLong(amount) + 600L)));
                        save(sourceCard);
                        destinationCard
                                .getAccount()
                                .setBalance(String
                                        .valueOf(Long
                                                .parseLong(destinationCard.getAccount().getBalance())
                                                + Long.parseLong(amount)));
                        save(destinationCard);
                        return true;
                    } else BaseMenu.singlePrintMessage("Balance id not enough");
                } else BaseMenu.singlePrintMessage("Card information is wrong");
                cardToCartTransfer(customer);
            }
            return false;
        } catch (InputMismatchException e) {
            input.nextLine();
            BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
            cardToCartTransfer(customer);
        }
        return false;
    }

    private CreditCard checkSourceCardExists(Customer customer, Long sourceCardNumber) {
        CreditCard sourceCard = findByCardNumber(sourceCardNumber);
        if (sourceCard == null) {
            return null;
        } else if (sourceCard.getAccount().getCustomer().getId().equals(customer.getId()))
            return sourceCard;
        else return null;

    }

    @Override
    public CreditCard findByCardNumber(Long cardNumber) {
        EntityManager em = repository.getEntityManager();
        try {
            return repository.findByCardNumber(cardNumber);
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Boolean assignOrChangePassword(Customer customer) {
        try {
            List<String> list = new ArrayList<>(Arrays.asList(
                    "Change first Password", "Change second Password", "Exit"
            ));
            BaseMenu.optionMessage(list, true);

            switch (input.nextInt()) {
                case 1 -> {
                    List<CreditCard> cards = ApplicationContext
                            .creditCardServ
                            .findAllByAccountId(customer.getAccounts());

                    BaseMenu.optionMessage(cards
                            .stream()
                            .map(a -> a.getCardNumber()
                                    .toString())
                            .collect(Collectors.toList()), false);

                    BaseMenu.singleSetMessage("Select One option or insert '0' to exit");
                    int option = input.nextInt();
                    if (option > cards.size()) {
                        BaseMenu.singlePrintMessage("This option those not exists");
                        assignOrChangePassword(customer);
                        break;
                    } else if (option == 0) {
                        assignOrChangePassword(customer);
                        break;
                    } else {
                        BaseMenu.singlePrintMessage("Password should include 4 numbers");
                        BaseMenu.singleSetMessage("Enter new first Password");
                        int newPassword = input.nextInt();
                        if (String.valueOf(newPassword).length() == 4) {
                            BaseMenu.singleSetMessage("Enter Password again to confirm");
                            int confirmPassword = input.nextInt();

                            if (newPassword == confirmPassword) {
                                cards.get(option - 1).setFirstPassword(confirmPassword);
                                save(cards.get(option - 1));
                            } else {
                                BaseMenu.singlePrintMessage("Password doesn't match");
                            }
                        } else BaseMenu.singlePrintMessage("Password should include 4 numbers");
                    }

                    assignOrChangePassword(customer);
                }
                case 2 -> {
                    List<CreditCard> cards = ApplicationContext
                            .creditCardServ
                            .findAllByAccountId(customer.getAccounts());

                    BaseMenu.optionMessage(cards
                            .stream()
                            .map(a -> a.getCardNumber()
                                    .toString())
                            .collect(Collectors.toList()), false);

                    BaseMenu.singleSetMessage("Select One option or insert '0' to exit");
                    int option = input.nextInt();
                    if (option > cards.size()) {
                        BaseMenu.singlePrintMessage("This option those not exists");
                        assignOrChangePassword(customer);
                        break;
                    } else if (option == 0) {
                        assignOrChangePassword(customer);
                        break;
                    } else {
                        BaseMenu.singlePrintMessage("Password should include 5 or more numbers");
                        BaseMenu.singleSetMessage("Enter new second Password");
                        String newSecondPassword = input.next();
                        if (newSecondPassword.length() >= 5) {
                            BaseMenu.singleSetMessage("Enter Password again to confirm");
                            String confirmSecondPassword = input.next();

                            if (newSecondPassword.equals(confirmSecondPassword)) {
                                cards.get(option - 1).setSecondPassword(confirmSecondPassword);
                                save(cards.get(option - 1));
                            } else {
                                BaseMenu.singlePrintMessage("Password doesn't match");
                            }
                        } else BaseMenu.singlePrintMessage("Password should include 5 or more numbers");
                    }

                    assignOrChangePassword(customer);
                }
                case 3 -> {

                }
                default -> {
                    BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
                    assignOrChangePassword(customer);
                }
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
            assignOrChangePassword(customer);
        }
        return false;
    }

    @Override
    public void cardManagement(User customer) {
        try {
            List<String> list = new ArrayList<>(Arrays.asList(
                    "Create New Card", "Inactive Card", "Remove Card", "Update Card information", "Exit"
            ));
            BaseMenu.optionMessage(list, true);

            switch (input.nextInt()) {
                case 1 -> {
                    BaseMenu.singleSetMessage("Enter Account number");
                    Long accountNumber = input.nextLong();

                    Account checkAccount = checkAccountExists(accountNumber);

                    if (checkAccount == null) {
                        BaseMenu.singlePrintMessage("This account does not exists.");
                        cardManagement(customer);
                        break;
                    } else if (checkAccount.getCreditCard() != null) {
                        BaseMenu.singlePrintMessage("This Account already has a card.");
                        cardManagement(customer);
                        break;
                    } else {
                        CreditCard newCard = new CreditCard();
                        cardGeneration(newCard);
                        newCard.setAccount(checkAccount);
                        save(newCard);
                    }
                    cardManagement(customer);
                }
                case 2 -> {
                    BaseMenu.singleSetMessage("Enter Account number");
                    Long accountNumber = input.nextLong();

                    Account checkAccount = checkAccountExists(accountNumber);
                    if (checkAccount == null) {
                        BaseMenu.singlePrintMessage("This account does not exists.");
                        cardManagement(customer);
                    } else if (checkAccount.getCreditCard() != null) {
                        checkAccount.getCreditCard().setIsActive(false);
                        ApplicationContext.accountServ.save(checkAccount);
                    }
                }
                case 3 -> {
                    BaseMenu.singleSetMessage("Enter Account number");
                    Long accountNumber = input.nextLong();

                    Account checkAccount = checkAccountExists(accountNumber);
                    if (checkAccount == null) {
                        BaseMenu.singlePrintMessage("This account does not exists.");
                        cardManagement(customer);
                    } else if (checkAccount.getCreditCard() != null) {
                        delete(checkAccount.getCreditCard());
                    }
                }
                case 4 -> {
                    BaseMenu.singleSetMessage("Enter Account number");
                    Long accountNumber = input.nextLong();

                    Account checkAccount = checkAccountExists(accountNumber);
                    if (checkAccount == null) {
                        BaseMenu.singlePrintMessage("This account does not exists.");
                        cardManagement(customer);
                    } else if (checkAccount.getCreditCard() != null) {
                        Random random = new Random();
                        checkAccount.getCreditCard().setCvv2(String.valueOf(random.nextInt(1000, 10000)));
                        checkAccount.getCreditCard().setIsActive(true);
                        LocalDate dateTime = LocalDate.now();
                        dateTime = dateTime.plusYears(4L);
                        checkAccount.getCreditCard().setExpireDate(Date.valueOf(dateTime));
                        checkAccount.getCreditCard().setFirstPassword(random.nextInt(1000, 10000));
                        save(checkAccount.getCreditCard());
                    }
                }
                case 5 -> {

                }
                default -> {
                    BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
                    cardManagement(customer);
                }
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
            cardManagement(customer);
        }
    }

    private void cardGeneration(CreditCard newCard) {
        CreditCard creditCard = findLastRecord();
        if (creditCard == null)
            newCard.setCardNumber(1111111111111111L);

        else newCard.setCardNumber(creditCard.getCardNumber() + 50L);

        Random random = new Random();
        newCard.setCvv2(String.valueOf(random.nextInt(1000, 10000)));
        newCard.setIsActive(true);
        LocalDate dateTime = LocalDate.now();
        dateTime = dateTime.plusYears(4L);
        newCard.setExpireDate(Date.valueOf(dateTime));
        newCard.setFirstPassword(random.nextInt(1000, 10000));
    }

    private Account checkAccountExists(Long accountNumber) {
        return ApplicationContext.accountServ.findByAccountNumber(accountNumber);
    }
}
