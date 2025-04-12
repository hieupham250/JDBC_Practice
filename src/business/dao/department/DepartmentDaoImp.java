package business.dao.department;

import business.config.ConnectionDB;
import business.model.Department;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImp implements DepartmentDao<Department> {
    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<Department>();
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_department()}");
            rs = callSt.executeQuery();
            while (rs.next()) {
                Department department = new Department();
                department.setDp_id(rs.getInt("dp_id"));
                department.setDp_name(rs.getString("dp_name"));
                department.setDp_description(rs.getString("dp_description"));
                department.setDp_status(rs.getBoolean("dp_status"));
                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return departments;
    }

    @Override
    public List<Department> getDepartmentsByPage(int page, int size) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Department> departments = new ArrayList<Department>();
        try {
            conn = ConnectionDB.openConnection();
            int offset = (page - 1) * size;
            callSt = conn.prepareCall("{call get_department_by_page(?, ?)}");
            callSt.setInt(1, size);
            callSt.setInt(2, offset);
            rs = callSt.executeQuery();
            while (rs.next()) {
                Department department = new Department();
                department.setDp_id(rs.getInt("dp_id"));
                department.setDp_name(rs.getString("dp_name"));
                department.setDp_description(rs.getString("dp_description"));
                department.setDp_status(rs.getBoolean("dp_status"));
                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return departments;
    }

    @Override
    public boolean createDepartment(Department department) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            List<Department> existing = getAllDepartments();
            if (!existing.isEmpty()) {
                for (Department d : existing) {
                    if (d.getDp_name() != null && department.getDp_name() != null &&
                            d.getDp_name().equalsIgnoreCase(department.getDp_name())) {
                        System.out.println("\u001B[31mKhông thể thêm: Tên phòng ban đã tồn tại.\u001B[0m");
                        return false;
                    }
                }
            }
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call create_department(?, ?, ?)}");
            callSt.setString(1, department.getDp_name());
            callSt.setString(2, department.getDp_description());
            callSt.setBoolean(3, department.isDp_status());
            int row = callSt.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean updateDepartment(Department department) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_department(?, ?, ?, ?)}");
            callSt.setInt(1, department.getDp_id());
            callSt.setString(2, department.getDp_name());
            callSt.setString(3, department.getDp_description());
            callSt.setBoolean(4, department.isDp_status());
            int row = callSt.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean deleteDepartment(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_department(?)}");
            callSt.setInt(1, id);
            int row = callSt.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_department_by_name(?)}");
            callSt.setString(1, departmentName);
            rs = callSt.executeQuery();
            if (rs.next()) {
                Department department = new Department();
                department.setDp_id(rs.getInt("dp_id"));
                department.setDp_name(rs.getString("dp_name"));
                department.setDp_description(rs.getString("dp_description"));
                department.setDp_status(rs.getBoolean("dp_status"));
                return department;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return null;
    }
}
