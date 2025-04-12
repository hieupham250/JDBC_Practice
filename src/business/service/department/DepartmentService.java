package business.service.department;

import business.model.Department;

import java.util.List;

public interface DepartmentService<T> {
    List<T> getAllDepartments();
    List<T> getDepartmentsByPage(int page, int size);
    boolean createDepartment(Department department);
    boolean updateDepartment(Department department);
    boolean deleteDepartment(int id);
    Department getDepartmentByName(String departmentName);
}
