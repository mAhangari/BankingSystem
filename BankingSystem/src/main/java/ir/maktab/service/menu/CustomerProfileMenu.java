package ir.maktab.service.menu;

import ir.maktab.util.ApplicationContext;
import java.util.*;

public class CustomerProfileMenu implements ProfileMenu {

    Scanner input = new Scanner(System.in);

    @Override
    public void dashboard() {
        try {
            List<String> list = new ArrayList<>(
                    Arrays.asList("Card Management", "Money Transfer", "Assign or Change Password",
                            "Recent Transactions", "Log Out"));
            BaseMenu.optionMessage(list, true);

            switch (input.nextInt()) {
                case 1 -> {
                    ApplicationContext.creditCardServ.cardManagement();
                    dashboard();
                }
                case 2 -> {
                    ApplicationContext.creditCardServ.CardToCartTransfer();
                    dashboard();
                }
                case 3 -> {
                    ApplicationContext.creditCardServ.assignOrChangePassword();
                    dashboard();
                }
                case 4 -> {
                    ApplicationContext.accountServ.recentTransactions();
                    dashboard();
                }
                case 5 -> {
                }
                default -> {
                    BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
                    dashboard();
                }
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
            dashboard();
        }
    }
}
