package pressentation;

import business.model.Employee;
import business.service.employee.EmployeeService;
import business.service.employee.EmployeeServiceImp;
import datatype.Gender;
import datatype.Status;
import validate.ValidateEmployee;
import validate.Validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EmployeeUI {
    public static void menuEmployee(Scanner sc) {
        do {
            System.out.println("================= QUẢN LÝ NHÂN VIÊN =================");
            System.out.println("1. Xem danh sách nhân viên (phân trang)");
            System.out.println("2. Thêm nhân viên");
            System.out.println("3. Cập nhật thông tin nhân viên");
            System.out.println("4. Xóa nhân viên (INACTIVE)");
            System.out.println("5. Tìm kiếm nhân viên theo tên");
            System.out.println("6. Tìm kiếm nhân viên theo khoảng tuổi");
            System.out.println("7. Sắp xếp nhân viên theo lương giảm dần");
            System.out.println("8. Sắp xếp nhân viên theo tên tăng dần");
            System.out.println("9. Quay lại menu chính");
            int choice = Validator.validateInputInteger("Lựa chọn của bạn: ", sc);
            switch (choice) {
                case 1:
                    displayEmployeeWithPage(sc);
                    break;
                case 2:
                    addEmployee(sc);
                    break;
                case 3:
                    updateEmployee(sc);
                    break;
                case 4:
                    deleteEmployee(sc);
                    break;
                case 5:
                    searchEmployeeByName(sc);
                    break;
                case 6:
                    searchEmployeeByAgeRange(sc);
                    break;
                case 7:
                    sortBySalaryDesc(sc);
                    break;
                case 8:
                    sortByNameAsc(sc);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ!\u001B[0m");
            }
        } while (true);
    }

    public static void displayEmployeeWithPage(Scanner sc) {
        EmployeeService employeeService = new EmployeeServiceImp();
        int page = 1;
        int size = 10;
        while (true) {
            List<Employee> employees = employeeService.getEmployeeWithPagination(page, size);
            if (employees.isEmpty()) {
                System.out.println("\u001B[31mKhông có nhân viên nào ở trang này!\u001B[0m");
            } else {
                System.out.println("--- Trang " + page + " ---");
                for (Employee employee : employees) {
                    System.out.println(employee);
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

    public static void addEmployee(Scanner sc) {
        EmployeeService employeeService = new EmployeeServiceImp();
        int n = Validator.validateInputInteger("Nhập số lượng nhân viên muốn thêm: ", sc);
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            Employee employee = new Employee();
            employee.inputData(sc);
            if (employeeService.createEmployee(employee)) {
                System.out.println("\u001B[32mThêm phòng ban thành công!\u001B[0m");
            }
        }

    }

    public static void updateEmployee(Scanner sc) {
        EmployeeService employeeService = new EmployeeServiceImp();
        System.out.print("Nhập mã nhân viên cần cập nhật: ");
        String employeeId = sc.nextLine().trim();
        List<Employee> employees = employeeService.getAllEmployee();
        boolean found = false;
        for (Employee employee : employees) {
            if (employeeId.equals(employee.getEmployee_id())) {
                System.out.println("Thông tin hiện tại:");
                System.out.println(employee);
                boolean isUpdating = true;
                while (isUpdating) {
                    System.out.println("--- Chọn thông tin muốn cập nhật ---");
                    System.out.println("1. Họ và tên");
                    System.out.println("2. Email");
                    System.out.println("3. Số điện thoại");
                    System.out.println("4. Giới tính");
                    System.out.println("5. Cấp bậc lương");
                    System.out.println("6. Lương");
                    System.out.println("7. Ngày sinh");
                    System.out.println("8. Địa chỉ");
                    System.out.println("9. Trạng thái");
                    System.out.println("10. Mã phòng ban");
                    System.out.println("11. Thoát");
                    int choice = Validator.validateInputInteger("Lựa chọn của bạn: ", sc);
                    sc.nextLine();
                    switch (choice) {
                        case 1:
                            String fullName = ValidateEmployee.validateEmployeeName("Nhập họ tên mới: ", sc);
                            employee.setFull_name(fullName);
                            break;
                        case 2:
                            String email = Validator.validateInputEmail("Nhập email mới: ", sc);
                            employee.setEmail(email);
                            break;
                        case 3:
                            String phoneNumber = Validator.validateInputPhoneNumber("Nhập số điện thoại mới: ", sc);
                            employee.setPhone_number(phoneNumber);
                            break;
                        case 4:
                            Gender gender = ValidateEmployee.validateGender("Nhập giới tính mới (MALE/FEMALE/OTHER): ", sc);
                            employee.setGender(gender);
                            break;
                        case 5:
                            int salaryLevel = ValidateEmployee.validateSalaryLevel("Nhập cấp bậc lương mới: ", sc);
                            employee.setSalary_level(salaryLevel);
                            break;
                        case 6:
                            BigDecimal salary = ValidateEmployee.validateSalary("Nhập lương mới: ", sc);
                            employee.setSalary(salary);
                            break;
                        case 7:
                            LocalDate birthDate = ValidateEmployee.validateBirthDate("Nhập ngày sinh mới (dd/MM/yyyy): ", sc);
                            employee.setBirth_date(birthDate);
                            break;
                        case 8:
                            String address = ValidateEmployee.validateAddress("Nhập địa chỉ mới: ", sc);
                            employee.setAddress(address);
                            break;
                        case 9:
                            Status status = ValidateEmployee.validateStatus("Nhập trạng thái mới (ACTIVE/INACTIVE/ONLEAVE/POLICYLEAVE): ", sc);
                            employee.setStatus(status);
                            break;
                        case 10:
                            int departmentId = ValidateEmployee.validateDepartment("Nhập mã phòng ban mới: ", sc);
                            employee.setDepartment_id(departmentId);
                            break;
                        case 11:
                            isUpdating = false;
                            break;
                        default:
                            System.out.println("\u001B[31mLựa chọn không hợp lệ! Vui lòng chọn lại.\u001B[0m");
                    }
                }
            }
        }
    }

    public static void deleteEmployee(Scanner sc) {
        EmployeeService employeeService = new EmployeeServiceImp();
        System.out.print("Nhập mã nhân viên cần xóa (INACTIVE): ");
        String id = sc.nextLine().trim();
        List<Employee> employees = employeeService.getAllEmployee();
        boolean found = false;
        for (Employee e : employees) {
            if (e.getEmployee_id().equalsIgnoreCase(id)) {
                if (employeeService.deleteEmployee(id)) {
                    System.out.println("\u001B[32mĐã đặt trạng thái INACTIVE cho nhân viên.\u001B[0m");
                } else {
                    System.out.println("\u001B[31mKhông thể cập nhật trạng thái.\u001B[0m");
                }
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy nhân viên với mã: " + id);
        }
    }

    public static void searchEmployeeByName(Scanner sc) {
        EmployeeService employeeService = new EmployeeServiceImp();
        System.out.print("Nhập tên nhân viên cần tìm: ");
        String name = sc.nextLine().trim();
        List<Employee> employees = employeeService.getEmployeeByName(name);
        if (employees.isEmpty()) {
            System.out.println("Không tìm thấy nhân viên nào.");
        } else {
            for (Employee e : employees) {
                System.out.println(e);
            }
        }
    }

    public static void searchEmployeeByAgeRange(Scanner sc) {
        EmployeeService employeeService = new EmployeeServiceImp();
        int min = Validator.validateInputInteger("Nhập tuổi nhỏ nhất: ", sc);
        sc.nextLine();
        int max = Validator.validateInputInteger("Nhập tuổi lớn nhất: ", sc);
        sc.nextLine();
        List<Employee> employees = employeeService.getEmployeeByAge(min, max);
        if (employees.isEmpty()) {
            System.out.println("\u001B[31mKhông tìm thấy nhân viên nào trong khoảng tuổi.\u001B[0m");
        } else {
            for (Employee e : employees) {
                System.out.println(e.toString());
            }
        }
    }

    public static void sortBySalaryDesc(Scanner sc) {
        EmployeeService employeeService = new EmployeeServiceImp();
        List<Employee> employees = employeeService.sortEmployeeBySalaryDesc();
        if (employees.isEmpty()) {
            System.out.println("\u001B[31mKhông có nhân viên nào.\u001B[0m");
        } else {
            for (Employee e : employees) {
                System.out.println(e);
            }
        }
    }

    public static void sortByNameAsc(Scanner sc) {
        EmployeeService employeeService = new EmployeeServiceImp();
        List<Employee> employees = employeeService.sortEmployeeByNameAsc();
        if (employees.isEmpty()) {
            System.out.println("\u001B[31mKhông có nhân viên nào.\u001B[0m");
        } else {
            for (Employee e : employees) {
                System.out.println(e);
            }
        }
    }
}
