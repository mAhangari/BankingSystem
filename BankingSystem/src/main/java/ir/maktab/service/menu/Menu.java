package ir.maktab.service.menu;

import ir.maktab.util.ApplicationContext;
import java.util.*;

public class Menu extends BaseMenu {

    Scanner input = new Scanner(System.in);

    public void showMenu() {
        try {
            List<String> list = new ArrayList<>(Arrays.asList("Login User", "Exit"));
            optionMessage(list, true);

            switch (input.nextInt()) {
                case 1 -> {
                    ApplicationContext.userServ.login();
                    showMenu();
                }
                case 2 -> {
                }
                default -> {
                    singlePrintMessage(WRONG_NUMBER);
                    showMenu();
                }
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            singlePrintMessage(WRONG_NUMBER);
            showMenu();
        }
    }
}
