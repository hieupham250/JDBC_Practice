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
}
