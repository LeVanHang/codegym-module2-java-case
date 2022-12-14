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
        System.out.print("id product: ");
        int id = Integer.parseInt(scanner.nextLine());
        while (checkId(id)) {
            System.err.println("id da ton tai, enter id khac:");
            id = Integer.parseInt(scanner.nextLine());
        }
        System.out.print("Name product ");
        String name = scanner.nextLine();
        System.out.print("nedan product: ");
        double price = Double.parseDouble(scanner.nextLine());
        return new Product(id, name, price);
    }

    public void addProduct(Scanner scanner) {
        Product product = createProduct(scanner);
        products.add(product);
        System.out.println("Add product !");
        writeData(products);
    }

    public void updateProductById(Scanner scanner) {
        boolean check = false;
        System.out.print("enter id muon sua ");
        int id = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                check = true;
                System.out.print("new id product ");
                int id1 = Integer.parseInt(scanner.nextLine());
                System.out.print("new name");
                String name1 = scanner.nextLine();
                System.out.print("new nedan ");
                double price = Double.parseDouble(scanner.nextLine());
                products.get(i).setId(id1);
                products.get(i).setName(name1);
                products.get(i).setPrice(price);
                System.out.println("sua infor product thanh cong !");
                writeData(products);
            }
        }
        if (!check) {
            System.err.println("Khong find product ! " + id);
        }
    }

    public void deleteProductById(Scanner scanner) {
        boolean check = false;
        System.out.print("enter id can delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                System.out.println("Delete product thanh cong!");
                writeData(products);
                check = true;
            }
        }
        if (!check) {
            System.err.println("Khong find product! " + id);
        }
    }

    public void displayAllProduct() {
        boolean check = false;
        for (Product product : products) {
            System.out.println(product);
            check = true;
        }
        if (!check) {
            System.err.println("Chua co product trong he thong !");
        }
    }

    public void searchProductByName(Scanner scanner) {
        boolean check = false;
        System.out.print("Enter name product can search: ");
        String name = scanner.nextLine();
        for (Product product : products) {
            if (product.getName().contains(name)) {
                System.out.println(product);
                check = true;
            }
        }
        if (!check) {
            System.err.println("khong search product co name la" + name);
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
        System.out.println("List product sort theo gia tang dan ");
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
        System.out.println("List product duoc sort theo id tang dan ");
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
        System.out.println("List product da duoc sort theo gia giam dan ");
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
        System.out.println("San pham co gia cao nhat trong he thong ");
        System.out.println(products.get(product));
    }

    public void sortProductByPrice(Scanner scanner) {
        int choose = -1;
        do {
            System.out.println("1. sort theo gia product (tang dan)");
            System.out.println("2. sort theo gia product (giam dan)");
            System.out.println("3. sort product theo id (tang dan) ");
            System.out.println("0. quay ve Menu");
            try {
                choose = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Chuc nang khong ton tai: ");
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

