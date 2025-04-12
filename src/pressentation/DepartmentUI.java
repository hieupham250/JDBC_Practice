package pressentation;

import business.model.Department;
import business.model.Employee;
import business.service.department.DepartmentService;
import business.service.department.DepartmentServiceImp;
import business.service.employee.EmployeeService;
import business.service.employee.EmployeeServiceImp;
import validate.ValidateDepartment;
import validate.Validator;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class DepartmentUI {
    public static void menuDepartment(Scanner sc) {
        do {
            System.out.println("================= QUẢN LÝ PHÒNG BAN =================");
            System.out.println("1. Xem danh sách phòng ban (phân trang)");
            System.out.println("2. Thêm mới phòng ban");
            System.out.println("3. Cập nhật phòng ban");
            System.out.println("4. Xóa phòng ban");
            System.out.println("5. Tìm kiếm phòng ban theo tên");
            System.out.println("6. Quay lại menu chính");
            int choice = Validator.validateInputInteger("Lựa chọn của bạn: ", sc);
            sc.nextLine();
            switch (choice) {
                case 1:
                    displayDepartmentWithPage(sc);
                    break;
                case 2:
                    addDepartment(sc);
                    break;
                case 3:
                    updateDepartment(sc);
                    break;
                case 4:
                    deleteDepartment(sc);
                    break;
                case 5:
                    searchDepartmentByName(sc);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
            }
        } while (true);
    }

    public static void displayDepartmentWithPage(Scanner sc) {
        DepartmentService departmentService = new DepartmentServiceImp();
        int page = 1;
        int size = 5;
        while (true) {
            List<Department> departments = departmentService.getDepartmentsByPage(page, size);
            if (departments.isEmpty()) {
                System.out.println("\u001B[31mKhông có phòng ban nào ở trang này!\u001B[0m");
            } else {
                System.out.println("--- Trang " + page + " ---");
                for (Department department : departments) {
                    System.out.println(department);
                }
            }
            System.out.println("[1] Trang sau | [2] Trang trước | [3] Thoát");
            int choice = Validator.validateInputInteger("Chọn hành động: ", sc);
            sc.nextLine();
            if (choice == 1) {
                page++;
            } else if (choice == 2) {
                if (page > 1) {
                    page--;
                } else {
                    System.out.println("\u001B[31mĐang ở trang đầu tiên.\u001B[0m");
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
            }
        }
    }

    public static void addDepartment(Scanner sc) {
        DepartmentServiceImp service = new DepartmentServiceImp();
        int n = Validator.validateInputInteger("Nhập số lượng phòng ban muốn thêm: ", sc);
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            Department department = new Department();
            department.inputData(sc);
            if (service.createDepartment(department)) {
                System.out.println("\u001B[32mThêm phòng ban thành công!\u001B[0m");
            }
        }
    }

    public static void updateDepartment(Scanner sc) {
        DepartmentServiceImp service = new DepartmentServiceImp();
        int id = Validator.validateInputInteger("Nhập mã phòng ban muốn cập nhật: ", sc);
        sc.nextLine();
        List<Department> departments = service.getAllDepartments();
        Optional<Department> isCheck = departments.stream()
                .filter(d -> d.getDp_id() == id)
                .findFirst();
        if (isCheck.isPresent()) {
            Department department = isCheck.get();
            System.out.println("--- Thông tin hiện tại của phòng ban ---");
            System.out.println(department);
            boolean isUpdating = true;
            while (isUpdating) {
                System.out.println("--- Chọn thông tin muốn cập nhật ---");
                System.out.println("1. Tên phòng ban");
                System.out.println("2. Mô tả phòng ban");
                System.out.println("3. Trạng thái hoạt động");
                System.out.println("4. Hoàn tất cập nhật");
                int choice = Validator.validateInputInteger("Lựa chọn của bạn: ", sc);
                sc.nextLine();
                switch (choice) {
                    case 1:
                        String newName = ValidateDepartment.validateDepartmentName("Nhập tên mới phòng ban: ", sc);
                        department.setDp_name(newName);
                        break;
                    case 2:
                        String newDescription = ValidateDepartment.validateDepartmentDescription("Nhập mô tả mới phòng ban: ", sc);
                        department.setDp_description(newDescription);
                        break;
                    case 3:
                        boolean newStatus = ValidateDepartment.validateDepartmentStatus("Nhập trạng thái mới của phòng ban: ", sc);
                        department.setDp_status(newStatus);
                        break;
                    case 4:
                        isUpdating = false;
                        break;
                    default:
                        System.out.println("\u001B[31mLựa chọn không hợp lệ! Vui lòng chọn lại.\u001B[0m");
                }
            }

            if (service.updateDepartment(department)) {
                System.out.println("\u001B[32mCập nhật phòng ban thành công!\u001B[0m");
            } else {
                System.out.println("\u001B[31mCập nhật thất bại!\u001B[0m");
            }
        } else {
            System.out.println("\u001B[31mKhông tìm thấy phòng ban với mã: " + id + "\u001B[0m");
        }
    }

    public static void deleteDepartment(Scanner sc) {
        EmployeeServiceImp employeeService = new EmployeeServiceImp();
        DepartmentServiceImp departmentService = new DepartmentServiceImp();
        int id = Validator.validateInputInteger("Nhập mã phòng ban muốn xóa: ", sc);
        sc.nextLine();
        List<Department> departments = departmentService.getAllDepartments();
        Optional<Department> department = departments.stream()
                .filter(d -> d.getDp_id() == id)
                .findFirst();
        if (department.isPresent()) {
            List<Employee> employees = employeeService.getAllEmployee();
            boolean hasEmployee = employees.stream().anyMatch(e -> e.getDepartment_id() == id);
            if (hasEmployee) {
                System.out.println("\u001B[31mKhông thể xóa phòng ban vì vẫn còn nhân viên trong đó.\u001B[0m");
            } else {
                boolean isDeleted = departmentService.deleteDepartment(id);
                if (isDeleted) {
                    System.out.println("\u001B[32mXóa phòng ban thành công!\u001B[0m");
                } else {
                    System.out.println("\u001B[31mXóa phòng ban thất bại!\u001B[0m");
                }
            }
        }
    }

    public static void searchDepartmentByName(Scanner sc) {
        System.out.print("Nhập tên phòng ban cần tìm: ");
        String name = sc.nextLine();
        DepartmentServiceImp departmentService = new DepartmentServiceImp();
        Department finDepartment = departmentService.getDepartmentByName(name);
        if (finDepartment != null) {
            System.out.println(finDepartment);
        } else {
            System.out.println("\u001B[31mKhông tìm thấy phòng ban có tên: " + name + "\u001B[0m");
        }
    }
}
