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
            System.out.println("|       1. Display List product      ");
            System.out.println("|       2. Search product theo name        ");
            System.out.println("|       3. sort product theo gia        ");
            System.out.println("|       4. Sexrch product co gia Max         ");
            System.out.println("|       5. change pass         ");
            System.out.println("|       6. change name acc         ");
            System.out.println("|       0. Dang Xuat                              ");
            System.out.println(" ----------------------------------------------");
            System.out.print("   ------>Vui long chon chuc nang ");
            try {
                chon = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("chuc nang khong ton tai ");
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