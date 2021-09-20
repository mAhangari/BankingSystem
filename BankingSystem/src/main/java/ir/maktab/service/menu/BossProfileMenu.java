package ir.maktab.service.menu;

import ir.maktab.domain.Boss;
import ir.maktab.domain.User;

import java.util.*;

public class BossProfileMenu implements ProfileMenu<Boss> {

    Scanner input = new Scanner(System.in);

    @Override
    public void dashboard(User boss) {
        try {
            List<String> list = new ArrayList<>(
                    Arrays.asList("", "Log Out"));
            BaseMenu.optionMessage(list, true);

            switch (input.nextInt()) {
                case 1, 2 -> {
                }
                default -> {
                    BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
                    dashboard(boss);
                }
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            BaseMenu.singlePrintMessage(BaseMenu.WRONG_NUMBER);
            dashboard(boss);
        }
    }
}
