package ir.maktab.service.menu;

import ir.maktab.domain.User;
import ir.maktab.util.ApplicationContext;
import java.util.*;

public class CustomerProfileMenu<E extends User> implements ProfileMenu<E> {

    Scanner input = new Scanner(System.in);

    @Override
    public void dashboard(E customer) {
        try {
            List<String> list = new ArrayList<>(
                    Arrays.asList("Money Transfer", "Assign or Change Password",
                            "Recent Transactions", "Log Out"));
            BaseMenu.optionMessage(list, true);

            switch (input.nextInt()) {
                case 1 -> {
                    ApplicationContext.creditCardServ.cardToCartTransfer(customer);
                    dashboard(customer);
                }
                case 2 -> {
                    ApplicationContext.creditCardServ.assignOrChangePassword(customer);
                    dashboard(customer);
                }
                case 3 -> {
                    ApplicationContext.accountServ.recentTransactions();
                    dashboard(customer);
                }
                case 4 -> {
                }
                default -> {
                    BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
                    dashboard(customer);
                }
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
            dashboard(customer);
        }
    }
}
