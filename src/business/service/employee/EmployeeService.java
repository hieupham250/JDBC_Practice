package business.service.employee;

import business.model.Employee;

import java.util.List;

public interface EmployeeService<T> {
    List<T> getAllEmployee();
    List<T> getEmployeeWithPagination(int page, int size);
    boolean createEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    boolean deleteEmployee(String id);
    List<Employee> getEmployeeByName(String name);
    List<Employee> getEmployeeByAge(int max,int min);
    List<Employee> sortEmployeeBySalaryDesc();
    List<Employee> sortEmployeeByNameAsc();
}
