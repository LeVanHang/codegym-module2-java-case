package controller;

import model.Account;

import java.io.*;
import java.util.ArrayList;

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
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void readData() {
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream(file));
            accounts = (ArrayList<Account>) o.readObject();
            o.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkRegisteredName(String name) {
        String admin = "admin";
        String none1 = "";
        String none2 = " ";
        if (name.equals(admin)) {
            System.err.println("Account đã tồn tại! please enter tên khác");
            return true;
        }
        if (name.equals(none1) || name.startsWith(none2)) {
            System.err.println("Không để trống tên tài khoản! Please nhập lại");
            return true;
        }

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getName().equals(name)) {
                System.err.println("Account đã tồn tại! Please enter tên khác");
                return true;
            }
        }
        return false;
    }

    public boolean checkRegistrationPassword(String password) {
        String none1 = "";
        String none2 = " ";
        if (password.equals(none1) || password.startsWith(none2)) {
            System.err.println("Không để trống password! please nhập lại");
            return true;
        }
        return false;
    }

    public boolean checkAdmin(Account account) {
        return account.getName().equals("admin") && account.getPassword().equals("admax");
    }

    public void checkLogin(Account account) {
        boolean check = false;
        for (Account a : accounts) {
            if (account.getName().equals(a.getName()) && account.getPassword().equals(a.getPassword())) {
                check = true;
                System.out.println("welcome TO bình nguyên vô tận");
                MenuCutomer.menuCutomer();
            }
        }
        if (!check) {
            System.err.println("Đố biết sai ở đâu, Pleace enter NOW!");
        }
    }


}
