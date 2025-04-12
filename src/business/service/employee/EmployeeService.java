package business.service.employee;

import java.util.List;

public interface EmployeeService<T> {
    List<T> getAllEmployee();
}
