package view;

import controller.AccountController;
import controller.ProductController;

import java.util.Scanner;

public class MenuAdmin {
    public static void menuAdmin() {
        Scanner scanner = new Scanner(System.in);
        AccountController accountController = new AccountController();
        accountController.readData();
        ProductController productController = new ProductController();
        productController.readData();
        int choose = -1;
        do {
            System.out.println(" >>>>>>>>   Menu AdmiN <<<<<<<");
            System.out.println("|       1. add new product    ");
            System.out.println("|       2. Sua infor product theo i        ");
            System.out.println("|       3. Delete product theo id product        ");
            System.out.println("|       4. Display List all product         ");
            System.out.println("|       5. Find product theo name      ");
            System.out.println("|       6. Sort product theo gia");
            System.out.println("|       7. Find product co gia max ");
            System.out.println("|       8. Diaplay list cac acc  ");
            System.out.println("|       9. Delete acc theo name ");
            System.out.println("|       10.Sort acc theo name ");
            System.out.println("|       0. Dang xuat                             ");
            System.out.println(" ----------------------------------------------");
            System.out.print("   ------> Vui Long Chon Chuc Nang ");
            try {
                choose = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Chuc Nang Khong Ton Tai ");
            }
            switch (choose) {
                case 1 -> productController.addProduct(scanner);
                case 2 -> productController.updateProductById(scanner);
                case 3 -> productController.deleteProductById(scanner);
                case 4 -> productController.displayAllProduct();
                case 5 -> productController.searchProductByName(scanner);
                case 6 -> productController.sortProductByPrice(scanner);
                case 7 -> productController.searchProductHighestPrice();
                case 8 -> accountController.displayAccount();
                case 9 -> accountController.deleteAccount(scanner);
                case 10 -> accountController.sortAccountByName();
                case 0 -> Main.menuMain();
            }
        }
        while (true);
    }
}