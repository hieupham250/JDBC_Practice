package validate;

import business.model.Employee;
import business.service.employee.EmployeeService;
import business.service.employee.EmployeeServiceImp;
import datatype.Gender;
import datatype.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ValidateEmployee {
    public static String validateEmployeeId(String message, Scanner sc) {
        EmployeeService employeeService = new EmployeeServiceImp();
        List<Employee> employees = employeeService.getAllEmployee();
        System.out.print(message);
        String id;
        while (true) {
            id = sc.nextLine().trim();
            if (id.equals("")) {
                System.out.println("Không được để trống mã nhân viên, vui lòng nhập lại.");
            } else if (!id.matches("^E\\d{4}$")) {
                System.out.println("\u001B[31mMã nhân viên phải có định dạng E#### (E và 4 chữ số). Vui lòng nhập lại.\u001B[0m");
            } else {
                String finalId = id;
                boolean isDuplicate = employees.stream()
                        .anyMatch(e -> e.getEmployee_id().equals(finalId));
                if (isDuplicate) {
                    System.out.println("\u001B[31mMã nhân viên đã tồn tại, vui lòng nhập mã khác.\u001B[0m");
                } else {
                    return id;
                }
            }
        }
    }

    public static String validateEmployeeName(String message, Scanner sc) {
        System.out.print(message);
        String name;
        while (true) {
            name = sc.nextLine();
            if (name.equals("")) {
                System.out.println("\u001B[31mTên nhân viên không được để trống. Nhập lại.\u001B[0m");
            } else if (name.length() < 15 || name.length() > 150) {
                System.out.println("\u001B[31mTên nhân viên không được phải gồm 15 -> 150 kí tự\u001B[0m");
            } else {
                return name;
            }
        }
    }

    public static Gender validateGender(String message, Scanner sc) {
        System.out.print(message);
        try {
            return Gender.valueOf(sc.nextLine().trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("\u001B[31mGiới tính không hợp lệ (male/female/other)\u001B[0m");
        }
    }

    public static Status validateStatus(String message, Scanner sc) {
        System.out.print(message);
        try {
            return Status.valueOf(sc.nextLine().trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("\u001B[31mTrạng thái không hợp lệ (active/inactive/onleave/policyleave)\u001B[0m");
        }
    }

    public static int validateSalaryLevel(String message, Scanner sc) {
        System.out.print(message);
        while (true) {
            try {
                int level = Integer.parseInt(sc.nextLine());
                if (level > 0) {
                    return level;
                } else {
                    System.out.println("\u001B[31mBậc lương phải lớn hơn 0.\u001B[0m");
                }
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mBậc lương phải là số nguyên.\u001B[0m");
            }
        }
    }

    public static BigDecimal validateSalary(String message, Scanner sc) {
        System.out.print(message);
        while (true) {
            try {
                BigDecimal salary = new BigDecimal(sc.nextLine());
                if (salary.compareTo(BigDecimal.ZERO) > 0) {
                    return salary;
                } else {
                    System.out.println("\u001B[31mLương phải lớn hơn 0.\u001B[0m");
                }
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mLương phải là số thực.\u001B[0m");
            }
        }
    }

    public static LocalDate validateBirthDate(String message, Scanner sc) {
        System.out.println(message);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            try {
                return LocalDate.parse(sc.nextLine(), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("\u001B[31mNgày sinh không hợp lệ. Định dạng đúng: dd/MM/yyyy\u001B[0m");
            }
        }
    }

    public static String validateAddress(String message, Scanner sc) {
        System.out.print(message);
        String address;
        while (true) {
            address = sc.nextLine().trim();
            if (address.isEmpty()) {
                System.out.println("\u001B[31mĐịa chỉ không được để trống.\u001B[0m");
            } else {
                return address;
            }
        }
    }

    public  static int validateDepartment(String message, Scanner sc) {
        System.out.print(message);
        while (true) {
            try {
                int deptId = sc.nextInt();
                sc.nextLine();
                if (deptId > 0) {
                    return deptId;
                } else {
                    System.out.println("\u001B[31mMã phòng ban phải lớn hơn 0.\u001B[0m");
                }
            } catch (Exception e) {
                System.out.println("\u001B[31mMã phòng ban phải là số nguyên.\u001B[0m");
                sc.nextLine();
            }
        }
    }
}
