package controller;

import view.MenuAdmin;
import view.MenuCutomer;
import model.Account;
import view.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AccountController {
    ArrayList<Account> accounts = new ArrayList<>();
    File file = new File("account.txt");
    public void writeData(ArrayList<Account> accounts) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(file));
            o.writeObject(accounts);
            o.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void readData() {
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream(file));
            accounts = (ArrayList<Account>) o.readObject();
            o.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    public boolean checkRegisteredName(String name) {
        String adm = "admiN";
        String none1 = "";
        String none2 = " ";
        if (name.equals(adm)) {
            System.err.print("Acc đã tồn tại :");
            return true;
        }
        if (name.equals(none1) || name.startsWith(none2)) {
            System.err.print("Không để trống acc: ");
            return true;
        }

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getName().equals(name)) {
                System.err.print("Acc đã tồn tại: ");
                return true;
            }
        }
        return false;
    }
    public boolean checkRegistrationPassword(String password) {
        String none1 = "";
        String none2 = " ";
        if (password.equals(none1) || password.startsWith(none2)) {
            System.err.print("Không để trống pass: ");
            return true;
        }
        return false;
    }
    public boolean checkAdmin(Account account) {
        return account.getName().equals("admiN") && account.getPassword().equals("Admax");
    }
    public void checkLogin(Account account) {
        boolean check = false;
        for (Account a : accounts) {
            if (account.getName().equals(a.getName()) && account.getPassword().equals(a.getPassword())) {
                check = true;
                System.out.println("welcome to Bình nguyên vô tận !");
                MenuCutomer.menuCutomer();
            }
        }
        if (!check) {
            System.err.println("Đố biết sai ở đâu");
        }
    }
    public Account createAccount(Scanner scanner) {
        System.out.print("Acc name: ");
        Pattern p = Pattern.compile("[a-zA-Z0-9]{6,18}$");
        String name = scanner.nextLine();
        while (!p.matcher(name).find()) {
            if (!p.matcher(name).find()) {
                System.err.println("Sai quy tắc '[a-zA-Z0-9]{6,18}$' : ");
                name = scanner.nextLine();
            }
        }
        while (checkRegisteredName(name)) {
            name = scanner.nextLine();
        }
        System.out.print("Enter pass: ");
        String password = scanner.nextLine();
        while (checkRegistrationPassword(password)) {
            password = scanner.nextLine();
        }
        return new Account(name, password);
    }
    public void addAucount(Scanner scanner) {
        Account account = createAccount(scanner);
        accounts.add(account);
        System.out.println("Đăng ký thành công");
        writeData(accounts);
        Main.menuMain();
    }
    public void login(Scanner scanner) {
        int count = 0;
        do {
            System.out.print("Acc name: ");
            String name = scanner.nextLine();
            System.out.print("pass: ");
            String password = scanner.nextLine();
            Account account = new Account(name, password);
            if (checkAdmin(account)) {
                System.out.println("oKaEri admiN");
                MenuAdmin.menuAdmin();
            } else {
                checkLogin(account);
            }
            count++;
        }
        while (count < 3) ;
        System.err.println("Nhập quá số lần sai");
        System.exit(0);
    }
    public void displayAccount() {
        boolean check = false;
        for (Account account : accounts) {
            System.out.println(account);
            check = true;
        }
        if (!check) {
            System.err.println("Chưa có tài khoản trong hệ thống, vui lòng nhấn 0 để trở về Trang Chủ và chọn đăng ký");
        }
    }
    public void deleteAccount(Scanner scanner) {
        boolean check = false;
        System.out.print("Nhập tên Acc muốn delete: ");
        String name = scanner.nextLine();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getName().equals(name)) {
                accounts.remove(i);
                check = true;
                System.out.println("delete thành công");
                writeData(accounts);
            }
        }
        if (!check) {
            System.err.println("Acc không tồn tại !");
        }
    }
    public void changePassword(Scanner scanner) {
        System.out.print("Enter pass hiện tại: ");
        String password = scanner.nextLine();
        for (int i = 0 ; i < accounts.size() ; i++){
            if (accounts.get(i).getPassword().equals(password)){
                System.out.print("Enter new pass: ");
                String password1 = scanner.nextLine();
                accounts.get(i).setPassword(password1);
                System.out.println("Change pass thành công !");
                writeData(accounts);
            }
        }
    }
    public void changeLoginName(Scanner scanner) {
        System.out.print("Enter name hiện tại: ");
        String name = scanner.nextLine();
        for (Account account : accounts) {
            if (account.getName().equals(name)) {
                System.out.print("name new: ");
                String name1 = scanner.nextLine();
                account.setName(name1);
                System.out.println("change name thành công !");
                writeData(accounts);
            }
        }
    }
    public void findAccountByName(Scanner scanner) {
        boolean check = false;
        System.out.print("enter acc cần find: ");
        String name = scanner.nextLine();
        for (Account account : accounts) {
            if (account.getName().equalsIgnoreCase(name)) {
                System.out.println(account);
                check = true;
            }
        }
        if (!check) {
            System.out.println("không tìm thấy acc có name là: " + name);
        }
    }
    public void sortAccountByName() {
        readData();
        Collections.sort(accounts);
        System.out.println("List acc sắp xếp theo tên:");
        displayAccount();
    }
}
