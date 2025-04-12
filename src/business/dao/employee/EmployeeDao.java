package business.dao.employee;

import java.util.List;

public interface EmployeeDao<T> {
    List<T> getAllEmployee();
}
