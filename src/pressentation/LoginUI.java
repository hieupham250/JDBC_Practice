package pressentation;

import business.model.Account;
import business.service.account.AccountService;
import business.service.account.AccountServiceImp;

import java.util.Scanner;

public class LoginUI {
    public static Account login(Scanner sc) {
        AccountService accountService = new AccountServiceImp();
        System.out.print("Tên đăng nhập: ");
        String username = sc.nextLine().trim();
        System.out.print("Mật khẩu: ");
        String password = sc.nextLine().trim();
        Account account = accountService.login(username, password);
        if (account != null) {
            System.out.println("\u001B[32mĐăng nhập thành công! Xin chào: " + account.getUsername() + "\u001B[0m");
        } else {
            System.out.println("\u001B[31mĐăng nhập thất bại. Kiểm tra lại tài khoản hoặc mật khẩu.\u001B[0m");
        }
        return account;
    }

    public static Account logout(Account currentAccount) {
        if (currentAccount != null) {
            System.out.println("\u001BĐăng xuất thành công! Tạm biệt " + currentAccount.getUsername() + "\u001B[0m");
        } else {
            System.out.println("\u001BBạn chưa đăng nhập.\u001B[0m");
        }
        return null;
    }
}
