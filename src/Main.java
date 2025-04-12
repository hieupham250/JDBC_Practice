import business.model.Account;
import pressentation.DepartmentUI;
import pressentation.EmployeeUI;
import pressentation.LoginUI;
import validate.Validator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Account loggedInAccount;

        do {
            System.out.println("========== ĐĂNG NHẬP ==========");
            loggedInAccount = LoginUI.login(sc);
        } while (loggedInAccount == null);

        do {
            System.out.println("================= MENU =================");
            System.out.println("1. Quản lý phòng ban");
            System.out.println("2. Quản lý nhân viên");
            System.out.println("3. Thống kê");
            System.out.println("4. Đăng xuất");
            int choice = Validator.validateInputInteger("Lựa chọn của bạn: ", sc);
            sc.nextLine();
            switch (choice) {
                case 1:
                    DepartmentUI.menuDepartment(sc);
                    break;
                case 2:
                    EmployeeUI.menuEmployee(sc);
                    break;
                case 3:

                    break;
                case 4:
                    loggedInAccount = LoginUI.logout(loggedInAccount);
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        } while (true);
    }
}