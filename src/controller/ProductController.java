package controller;

import view.MenuCutomer;
import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ProductController implements Serializable {
    ArrayList<Product> products = new ArrayList<>();
    File file = new File("product.txt");

    public void writeData(ArrayList<Product> products) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(file));
            o.writeObject(products);
            o.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void readData() {
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream(file));
            products = (ArrayList<Product>) o.readObject();
            o.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean checkId(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }

    public Product createProduct(Scanner scanner) {
        System.out.print("Ma san pham: ");
        int id = Integer.parseInt(scanner.nextLine());
        while (checkId(id)) {
            System.err.println("Ma san pham da ton tai vung long nhap ma khac:");
            id = Integer.parseInt(scanner.nextLine());
        }
        System.out.print("Ten san pham: ");
        String name = scanner.nextLine();
        System.out.print("Gia san pham: ");
        double price = Double.parseDouble(scanner.nextLine());
        return new Product(id, name, price);
    }

    public void addProduct(Scanner scanner) {
        Product product = createProduct(scanner);
        products.add(product);
        System.out.println("Them san pham thanh cong !");
        writeData(products);
    }

    public void updateProductById(Scanner scanner) {
        boolean check = false;
        System.out.print("Nhap ma san pham muon sua: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                check = true;
                System.out.print("Ma san pham moi: ");
                int id1 = Integer.parseInt(scanner.nextLine());
                System.out.print("Ten moi: ");
                String name1 = scanner.nextLine();
                System.out.print("Gia moi: ");
                double price = Double.parseDouble(scanner.nextLine());
                products.get(i).setId(id1);
                products.get(i).setName(name1);
                products.get(i).setPrice(price);
                System.out.println("Sua thong tin san pham thanh cong !");
                writeData(products);
            }
        }
        if (!check) {
            System.err.println("Khong tim thay san pham ! " + id);
        }
    }

    public void deleteProductById(Scanner scanner) {
        boolean check = false;
        System.out.print("Nhap ma san pham can xoa: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                System.out.println("Xoa san pham thanh cong !");
                writeData(products);
                check = true;
            }
        }
        if (!check) {
            System.err.println("Khong tim thay san pham ! " + id);
        }
    }

    public void displayAllProduct() {
        boolean check = false;
        for (Product product : products) {
            System.out.println(product);
            check = true;
        }
        if (!check) {
            System.err.println("Chua co san pham nao trong he thong !");
        }
    }

    public void searchProductByName(Scanner scanner) {
        boolean check = false;
        System.out.print("Nhap ten san pham can tim: ");
        String name = scanner.nextLine();
        for (Product product : products) {
            if (product.getName().contains(name)) {
                System.out.println(product);
                check = true;
            }
        }
        if (!check) {
            System.err.println("Khong tim thay san pham co ten la: " + name);
        }
    }

    public void sortProductsByPriceAscending() {
        Comparator<Product> comparator = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return (int) (o1.getPrice() - o2.getPrice());
            }
        };
        products.sort(comparator);
        System.out.println("Danh sach san pham da duoc xep theo gia tang dan: ");
        displayAllProduct();
    }
    public void sortProductById(){
        Comparator<Product> comparator = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return (int) (o1.getId() - o2.getId());
            }
        };
        products.sort(comparator);
        System.out.println("Danh sach san pham da duoc xep theo ma so tang dan: ");
        displayAllProduct();
    }

    public void sortProductByPriceDescending() {
        Comparator<Product> comparator = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return (int) (o2.getPrice() - o1.getPrice());
            }
        };
        products.sort(comparator);
        System.out.println("Danh sach san pham da duoc xep theo gia giam dan: ");
        displayAllProduct();
    }

    public void searchProductHighestPrice() {
        double max = 0;
        int product = 0;
        for (int i = 0; i < products.size(); i++) {
            if (max < products.get(i).getPrice()) {
                max = products.get(i).getPrice();
                product = i;
            }
        }
        System.out.println("San pham co gia cao nhat trong he thong la: ");
        System.out.println(products.get(product));
    }

    public void sortProductByPrice(Scanner scanner) {
        int choose = -1;
        do {
            System.out.println("1. Sap xep theo gia san pham (tang dan)");
            System.out.println("2. Sap xep theo gia san pham (giam dan)");
            System.out.println("3. Sap xep san pham theo ma so (tang dan) ");
            System.out.println("0. Quay ve Menu");
            try {
                choose = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Chuc nang khong ton tai ! vui long chon lai: ");
            }
            switch (choose) {
                case 1 -> sortProductsByPriceAscending();
                case 2 -> sortProductByPriceDescending();
                case 3 -> sortProductById();
                case 0 -> MenuCutomer.menuCutomer();
            }
        }
        while (choose != 0);
    }
}

