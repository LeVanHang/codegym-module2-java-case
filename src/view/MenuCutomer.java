package view;

import controller.AccountController;
import controller.ProductController;

import java.util.Scanner;

public class MenuCutomer {
    public static void menuCutomer() {
        Scanner scanner = new Scanner(System.in);
        ProductController productController = new ProductController();
        AccountController accountController = new AccountController();
        productController.readData();
        accountController.readData();
        int chon = -1;
        do {
            System.out.println(" >>>>>>>>   Menu Cutomer  <<<<<<<");
            System.out.println("|       1. Display List sản phẩm      ");
            System.out.println("|       2. Search sản phẩm theo name        ");
            System.out.println("|       3. sort sản phẩm theo giá        ");
            System.out.println("|       4. tìm sản phẩm có giá cao nhất         ");
            System.out.println("|       5. change pass         ");
            System.out.println("|       6. change name acc         ");
            System.out.println("|       0. Đăng xuất                              ");
            System.out.println(" ----------------------------------------------");
            System.out.print("   ------>Vui lòng chọn chức năng: ");
            try {
                chon = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("chức năng không tồn tại: ");
            }

            switch (chon) {
                case 1 -> productController.displayAllProduct();
                case 2 -> productController.searchProductByName(scanner);
                case 3 -> productController.sortProductByPrice(scanner);
                case 4 -> productController.searchProductHighestPrice();
                case 5 -> accountController.changePassword(scanner);
                case 6 -> accountController.changeLoginName(scanner);
                case 0 -> Main.menuMain();
            }
        }
        while (true);
    }
}