package business.service.employee;

import business.dao.employee.EmployeeDao;
import business.dao.employee.EmployeeDaoImp;
import business.model.Employee;

import java.util.List;

public class EmployeeServiceImp implements EmployeeService<Employee> {
    private EmployeeDao employeeDao;
    public EmployeeServiceImp() {
        employeeDao = new EmployeeDaoImp();
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeDao.getAllEmployee();
    }

    @Override
    public List<Employee> getEmployeeWithPagination(int page, int size) {
        return employeeDao.getEmployeeWithPagination(page, size);
    }

    @Override
    public boolean createEmployee(Employee employee) {
        return employeeDao.createEmployee(employee);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployee(String id) {
        return employeeDao.deleteEmployee(id);
    }

    @Override
    public List<Employee> getEmployeeByName(String name) {
        return employeeDao.getEmployeeByName(name);
    }

    @Override
    public List<Employee> getEmployeeByAge(int max, int min) {
        return employeeDao.getEmployeeByAge(max, min);
    }

    @Override
    public List<Employee> sortEmployeeBySalaryDesc() {
        return employeeDao.sortEmployeeBySalaryDesc();
    }

    @Override
    public List<Employee> sortEmployeeByNameAsc() {
        return employeeDao.sortEmployeeByNameAsc();
    }
}
