package ir.maktab.service.menu;

import ir.maktab.domain.Customer;
import ir.maktab.domain.Employee;
import ir.maktab.domain.User;
import ir.maktab.domain.enumeration.UserType;
import ir.maktab.util.ApplicationContext;
import java.util.*;

public class EmployeeProfileMenu implements ProfileMenu<Employee> {

    Scanner input = new Scanner(System.in);

    @Override
    public void dashboard(User employee) {
        try {
            List<String> list = new ArrayList<>(
                    Arrays.asList("Account Management", "Card Management", "Customer Account", "Log Out"));
            BaseMenu.optionMessage(list, true);

            switch (input.nextInt()) {
                case 1 -> {
                    ApplicationContext.accountServ.accountManagement(employee);
                    dashboard(employee);
                }

                case 2 -> {
                    BaseMenu.singleSetMessage("Enter customer username");
                    String username = input.next();
                    User user = ApplicationContext.userServ.findByUsername(username);
                    if (user.getUserType().equals(UserType.Customer)){
                        ApplicationContext.creditCardServ.cardManagement(user);
                    }else {
                        BaseMenu.singlePrintMessage("Username is incorrect");
                    }
                    dashboard(employee);
                }

                case 3 -> {
                    BaseMenu.singleSetMessage("Enter customer username");
                    String username = input.next();
                    User user = ApplicationContext.userServ.findByUsername(username);
                    if (user.getUserType().equals(UserType.Customer)){
                        ApplicationContext.accountServ.displayCustomerAccount((Customer) user);
                    }else {
                        BaseMenu.singlePrintMessage("Username is incorrect");
                    }
                    dashboard(employee);
                }

                case 4 -> {
                }
                default -> {
                    BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
                    dashboard(employee);
                }
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
            dashboard(employee);
        }
    }
}
