package ir.maktab.service.menu;

import java.util.*;

public class BossProfileMenu implements ProfileMenu {

    Scanner input = new Scanner(System.in);

    @Override
    public void dashboard() {
        try {
            List<String> list = new ArrayList<>(
                    Arrays.asList("", "Log Out"));
            BaseMenu.optionMessage(list, true);

            switch (input.nextInt()) {
                case 1, 2 -> {
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
