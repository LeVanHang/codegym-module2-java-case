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
            System.out.println("|       1. Hien thi danh sach san pham      ");
            System.out.println("|       2. Tim kiem san pham theo ten        ");
            System.out.println("|       3. Sap xep cac san pham theo gia         ");
            System.out.println("|       4. Tim san pham co gia cao nhat         ");
            System.out.println("|       5. Doi mat khau         ");
            System.out.println("|       6. Doi ten tai khoan         ");
            System.out.println("|       0. Dang xuat                              ");
            System.out.println(" ----------------------------------------------");
            System.out.print("   ------>Vui long chon chuc nang: ");
            try {
                chon = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Chuc nang khong ton tai ! vui long chon lai: ");
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