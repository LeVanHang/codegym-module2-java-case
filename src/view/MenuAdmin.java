package view;

import controller.AccountController;
import controller.ProductController;
import model.Account;

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
            System.out.println(" >>>>>>>>   Menu Admin <<<<<<<");
            System.out.println("|       1. Thêm sản phẩm mới     ");
            System.out.println("|       2. Sữa thông tin sản phẩm theo mã sản phẩm        ");
            System.out.println("|       3. Xóa sản phẩm theo mã số sản phẩm         ");
            System.out.println("|       4. Hiển thị danh sách tất cả sản phẩm          ");
            System.out.println("|       5. Tìm kiếm sản phẩm theo tên       ");
            System.out.println("|       6. Sắp xếp sản phẩm theo giá");
            System.out.println("|       7. Tìm sản phẩm có giá ca nhất ");
            System.out.println("|       8. Hiển thị danh sách các tài khoản ");
            System.out.println("|       9. Xóa tài khoản theo tên ");
            System.out.println("|       10. Sắp xếp tài khoản theo tên ");
            System.out.println("|       0. Đăng xuất                             ");
            System.out.println(" ----------------------------------------------");
            System.out.print("   ------> Vui lòng chọn chức năng: ");
            try {
                choose = Integer.parseInt(scanner.nextLine());
            }
            catch (Exception e) {
                System.err.println("Chức năng không tồn tại vui lòng chọn lại");
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
