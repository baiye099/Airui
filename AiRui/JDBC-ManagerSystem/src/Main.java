import controller.MenuController;
import util.PasswordUtil;

public class Main {
    public static void main(String[] args) {
        System.out.println(PasswordUtil.hashPassword("123456"));
        MenuController menuController = new MenuController();
        menuController.start();
    }
}