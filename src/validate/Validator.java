package validate;

import java.util.Scanner;

public class Validator {
    public static int validateInputInteger(String message, Scanner sc) {
        System.out.print(message);
        do {
            try {
                return Integer.parseInt(sc.next());
            }catch (NumberFormatException e) {
                System.out.println("\u001B[31mDữ liệu không hợp lệ. Nhập lại!\u001B[0m");
            }
        }while (true);
    }

    public static Float validateInputFloat(String message, Scanner sc) {
        System.out.print(message);
        do {
            try {
                return Float.parseFloat(sc.next());
            }catch (NumberFormatException e) {
                System.out.println("\u001B[31mDữ liệu không hợp lệ. Nhập lại!\u001B[0m");
            }
        }while (true);
    }

    public static Double validateInputDouble(String message, Scanner sc) {
        System.out.print(message);
        do {
            try {
                return Double.parseDouble(sc.next());
            }catch (NumberFormatException e) {
                System.out.println("\u001B[31mDữ liệu không hợp lệ. Nhập lại!\u001B[0m");
            }
        }while (true);
    }

    public static Boolean validateInputBoolean(String message, Scanner sc) {
        System.out.print(message);
        do {
            try {
                return Boolean.parseBoolean(sc.next());
            }catch (NumberFormatException e) {
                System.out.println("\u001B[31mDữ liệu không hợp lệ. Nhập lại!\u001B[0m");
            }
        }while (true);
    }

    public static String validateInputString(String message, Scanner sc,int min,int max) {
        System.out.print(message);
        do {
            try {
                String s = sc.next().trim();
                if (s.length() > min && s.length() < max && !s.isEmpty()) {
                    return s;
                }
            }catch (NumberFormatException e) {
                System.out.println("\u001B[31mDữ liệu không hợp lệ. Nhập lại!\u001B[0m");
            }
        }while (true);
    }

    public static String validateInputEmail(String message, Scanner sc) {
        System.out.print(message);
        do {
            try {
                String email = sc.nextLine().trim().toLowerCase();
                String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
                if (email.isEmpty()) {
                    System.out.println("\u001B[31mEmail không được để trống.\u001B[0m");
                }
                else if (!email.matches(emailRegex)) {
                    System.out.println("\u001B[31mEmail không hợp lệ. Nhập lại!\u001B[0m");
                }
                else {
                    return email;
                }
            }catch (Exception e) {
                System.out.println("\u001B[31mDữ liệu không hợp lệ. Nhập lại!\u001B[0m");
            }
        }while (true);
    }

    public static String validateInputPhoneNumber(String message, Scanner sc) {
        do {
            try {
                String phoneNumber = sc.nextLine().trim().replaceAll("[^0-9]", "");
                if (phoneNumber.isEmpty()) {
                    System.out.println("\u001B[31mSố điện thoại không được để trống.\u001B[0m");
                }
                else if (!phoneNumber.matches("^(03|05|07|08|09)[0-9]{8}$")) {
                    System.out.println("\u001B[31mSố điện thoại không hợp lệ. Nhập lại!\u001B[0m");
                }
                else {
                    return phoneNumber;
                }
            }catch (Exception e){
                System.out.println("\u001B[31mDữ liệu không hợp lệ. Nhập lại!\u001B[0m");
            }
        }while (true);
    }
}
