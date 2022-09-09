package view;

import controller.AccountController;
import java.io.Serializable;
import java.util.Scanner;

public class Main implements Serializable {
    public static void main(String[] args) {
        Main.menuMain();
    }
    public static void menuMain() {
        Scanner scanner = new Scanner(System.in);
        AccountController accountController = new AccountController();
        accountController.readData();
        int chon = -1;
        System.out.println("*****************************");
        System.out.println("------------->Trang Chủ<-------------");
        System.out.println("**          1. Đăng Nhập              **");
        System.out.println("**          2. Đăng Ký                  **");
        System.out.println("------------------------------------------");
        System.out.println("**          0. Thoát Hệ Thống        **");
        System.out.println("*****************************");
        do {

            System.out.print("  --> Vui Lòng Chọn Chức Năng:");
            try {
                chon = Integer.parseInt(scanner.nextLine());

            } catch (Exception e) {
                System.err.println("Chức Năng Không Tồn Tại");
            }
            switch (chon) {
                case 1 -> accountController.login(scanner);
                case 2 -> accountController.addAucount(scanner);
                case 0 -> System.exit(0);
            }
        }
        while (true);
    }
}
