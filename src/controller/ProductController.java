package controller;

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
        System.out.print("Mã sản phẩm: ");
        int id = Integer.parseInt(scanner.nextLine());
        while (checkId(id)) {
            System.err.println("Mã sản phẩm đã tồn tại Pleace nhập mã khác:");
            id = Integer.parseInt(scanner.nextLine());
        }
        System.out.print("Tên sản phẩm: ");
        String name = scanner.nextLine();
        System.out.print("Giá sản phẩm: ");
        double price = Double.parseDouble(scanner.nextLine());
        return new Product(id, name, price);
    }

    public void addProduct(Scanner scanner) {
        Product product = createProduct(scanner);
        products.add(product);
        System.out.println("Add sản phẩm thành công !");
        writeData(products);
    }

    public void updateProductById(Scanner scanner) {
        boolean check = false;
        System.out.print("Nhập mã sản phẩm sữa: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                check = true;
                System.out.print("Mã sản phẩm mới: ");
                int id1 = Integer.parseInt(scanner.nextLine());
                System.out.print("Tên mới: ");
                String name1 = scanner.nextLine();
                System.out.print("Giá mới: ");
                double price = Double.parseDouble(scanner.nextLine());
                products.get(i).setId(id1);
                products.get(i).setName(name1);
                products.get(i).setPrice(price);
                System.out.println("Sữa thông tin sản phẩm thành công !");
                writeData(products);
            }
        }
        if (!check) {
            System.err.println("Không tìm thấy sản phẩm ! " + id);
        }
    }

    public void deleteProductById(Scanner scanner) {
        boolean check = false;
        System.out.print("Nhập mã sản phẩm cần delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                System.out.println("Delete sản phẩm thành công !");
                writeData(products);
                check = true;
            }
        }
        if (!check) {
            System.err.println("Không tìm thấy sản phẩm ! " + id);
        }
    }

    public void displayAllProduct() {
        boolean check = false;
        for (Product product : products) {
            System.out.println(product);
            check = true;
        }
        if (!check) {
            System.err.println("Chưa có sản phẩm nào trong hệ thống !");
        }
    }

    public void searchProductByName(Scanner scanner) {
        boolean check = false;
        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String name = scanner.nextLine();
        for (Product product : products) {
            if (product.getName().contains(name)) {
                System.out.println(product);
                check = true;
            }
        }
        if (!check) {
            System.err.println("Không tìm thấy thông tin sản phẩm cần tìm là: " + name);
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
        System.out.println("Danh sách sản phẩm được sắp xếp tăng dần: ");
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
        System.out.println("Danh sách sản phẩm được sắp xếp theo mã số tăng dần: ");
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
        System.out.println("Danh sách sản phẩm được sắp xếp theo giá giảm dần: ");
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
        System.out.println("Sản phẩm có giá cao nhất trong hệ thống: ");
        System.out.println(products.get(product));
    }

    public void sortProductByPrice(Scanner scanner) {
        int choose = -1;
        do {
            System.out.println("1. Sắp xếp theo giá sản phẩm tăng dần");
            System.out.println("2. Sắp xếp theo giá sản phẩm giảm dần");
            System.out.println("3. Sắp xếp sản phẩm theo mã số tăng dần ");
            System.out.println("0. Quay về Menu");
            try {
                choose = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Chức năng không tồn tại: ");
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

