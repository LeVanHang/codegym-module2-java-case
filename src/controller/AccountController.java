package controller;

import com.sun.tools.javac.Main;
import model.Account;

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
            System.err.print("Account đã tồn tại:");
            return true;
        }
        if (name.equals(none1) || name.startsWith(none2)) {
            System.err.print("Không để trống account name: ");
            return true;
        }

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getName().equals(name)) {
                System.err.print("Account đã tồn tại: ");
                return true;
            }
        }
        return false;
    }
    public boolean checkRegistrationPassword(String password) {
        String none1 = "";
        String none2 = " ";
        if (password.equals(none1) || password.startsWith(none2)) {
            System.err.print("Pleace enter password: ");
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
                System.out.println("Chào mừng đến với bình nguyên vô tận !");
                MenuCutomer.menuCutomer();
            }
        }
        if (!check) {
            System.err.println("Đố biết sai ở đâu Pleace enter NOW");
        }
    }
    public Account createAccount(Scanner scanner) {
        System.out.print("Account name: ");
        Pattern p = Pattern.compile("[a-zA-Z0-9]{6,18}$");
        String name = scanner.nextLine();
        while (!p.matcher(name).find()) {
            if (!p.matcher(name).find()) {
                System.err.println("Sai quy tắc tạo tài khoản, vui lòng nhập lại: ");
                name = scanner.nextLine();
            }
        }
        while (checkRegisteredName(name)) {
            name = scanner.nextLine();
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        while (checkRegistrationPassword(password)) {
            password = scanner.nextLine();
        }
        return new Account(name, password);
    }
    public void addAucount(Scanner scanner) {
        Account account = createAccount(scanner);
        accounts.add(account);
        System.out.println("Chào mừng đến với bình nguyên vô tận, mời chọn đăng nhập");
        writeData(accounts);
        Main.menuMain();
    }
    public void login(Scanner scanner) {
        int count = 0;
        do {
            System.out.print("Account name ");
            String name = scanner.nextLine();
            System.out.print("Password: ");
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
        System.err.println("Nhập sai quá số lần cho phép, chạy lại trương trình");
        System.exit(0);
    }
    public void displayAccount() {
        boolean check = false;
        for (Account account : accounts) {
            System.out.println(account);
            check = true;
        }
        if (!check) {
            System.err.println("Chưa có acconnt trong hệ thống, chọn 0 để quay lại trang chủ để đăng ký");
        }
    }
    public void deleteAccount(Scanner scanner) {
        boolean check = false;
        System.out.print("Enter account muốn xóa: ");
        String name = scanner.nextLine();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getName().equals(name)) {
                accounts.remove(i);
                check = true;
                System.out.println("Delete thành công");
                writeData(accounts);
            }
        }
        if (!check) {
            System.err.println("Account không tồn tại !");
        }
    }
    public void changePassword(Scanner scanner) {
        System.out.print("Enter password hiện tại: ");
        String password = scanner.nextLine();
        for (int i = 0 ; i < accounts.size() ; i++){
            if (accounts.get(i).getPassword().equals(password)){
                System.out.print("New password: ");
                String password1 = scanner.nextLine();
                accounts.get(i).setPassword(password1);
                System.out.println("change password thành công !");
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
                System.out.println("thành công change name!");
                writeData(accounts);
            }
        }
    }
    public void findAccountByName(Scanner scanner) {
        boolean check = false;
        System.out.print("Enter account find: ");
        String name = scanner.nextLine();
        for (Account account : accounts) {
            if (account.getName().equalsIgnoreCase(name)) {
                System.out.println(account);
                check = true;
            }
        }
        if (!check) {
            System.out.println("Không find thấy account name: " + name);
        }
    }
    public void sortAccountByName() {
        readData();
        Collections.sort(accounts);
        System.out.println("List account theo name:");
        displayAccount();
    }
}