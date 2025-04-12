package validate;

import business.model.Department;
import business.service.department.DepartmentService;
import business.service.department.DepartmentServiceImp;

import java.util.List;
import java.util.Scanner;

public class ValidateDepartment {
    public static String validateDepartmentName(String message, Scanner sc) {
        DepartmentService departmentService = new DepartmentServiceImp();
        List<Department> departments = departmentService.getAllDepartments();
        System.out.print(message);
        while(true){
            String departmentName = sc.nextLine();
            departmentName = departmentName.replaceAll("\\p{C}", "").trim();
            if(departmentName.isEmpty()){
                System.out.println("\u001B[31mKhông được để trống tên phòng ban.\u001B[0m");
            } else {
                String finalDepartmentName = departmentName;
                boolean isDuplicate = departments.stream()
                        .anyMatch(d -> d.getDp_name() != null &&
                                d.getDp_name().equalsIgnoreCase(finalDepartmentName));
                if (isDuplicate) {
                    System.out.println("\u001B[31mTên phòng ban đã tồn tại. Vui lòng nhập tên khác.\u001B[0m");
                } else {
                    return departmentName;
                }
            }
        }
    }

    public static String validateDepartmentDescription(String message, Scanner sc) {
        System.out.print(message);
        String departmentDescription;
        while (true) {
            departmentDescription = sc.nextLine().trim();
            if (departmentDescription.isEmpty()) {
                System.out.println("\u001B[31mMô tả không được để trống.\u001B[0m");
            } else if (departmentDescription.length() > 255) {
                System.out.println("\u001B[31mMô tả không được vượt quá 255 ký tự.\u001B[0m");
            } else {
                return departmentDescription;
            }
        }
    }

    public static Boolean validateDepartmentStatus(String message, Scanner sc) {
        System.out.print(message + " (true/false): ");
        while (true) {
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("true")) {
                return true;
            } else if (input.equals("false")) {
                return false;
            } else {
                System.out.println("\u001B[31mGiá trị trạng thái không hợp lệ. Vui lòng nhập 'true' hoặc 'false'.\u001B[0m");
            }
        }
    }
}
