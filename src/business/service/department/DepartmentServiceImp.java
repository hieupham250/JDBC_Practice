package business.service.department;

import business.dao.department.DepartmentDao;
import business.dao.department.DepartmentDaoImp;
import business.model.Department;

import java.util.List;

public class DepartmentServiceImp implements DepartmentService<Department> {
    private DepartmentDao departmentDao;
    public DepartmentServiceImp() {
        departmentDao = new DepartmentDaoImp();
    }

    @Override
    public List getAllDepartments() {
        return departmentDao.getAllDepartments();
    }

    @Override
    public List getDepartmentsByPage(int page, int size) {
        return departmentDao.getDepartmentsByPage(page, size);
    }

    @Override
    public boolean createDepartment(Department department) {
        return departmentDao.createDepartment(department);
    }

    @Override
    public boolean updateDepartment(Department department) {
        return departmentDao.updateDepartment(department);
    }

    @Override
    public boolean deleteDepartment(int id) {
        return departmentDao.deleteDepartment(id);
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        return departmentDao.getDepartmentByName(departmentName);
    }
}
