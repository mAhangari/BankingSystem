package ir.maktab.service.menu;

import ir.maktab.util.ApplicationContext;

import java.util.*;

public class EmployeeProfileMenu implements ProfileMenu {

    Scanner input = new Scanner(System.in);

    @Override
    public void dashboard() {
        try {
            List<String> list = new ArrayList<>(
                    Arrays.asList("Account Management", "Customer Account", "Log Out"));
            BaseMenu.optionMessage(list, true);

            switch (input.nextInt()) {
                case 1 -> {
                    ApplicationContext.accountServ.accountManagement();
                    dashboard();
                }

                case 2 -> {
                    ApplicationContext.accountServ.displayCustomerAccount();
                    dashboard();
                }

                case 3 -> {
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
