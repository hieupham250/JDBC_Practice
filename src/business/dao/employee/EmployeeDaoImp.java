package business.dao.employee;

import business.config.ConnectionDB;
import business.model.Employee;
import datatype.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImp implements EmployeeDao<Employee> {
    @Override
    public List<Employee> getAllEmployee() {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Employee> employees = new ArrayList<Employee>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_employees()}");
            rs = callSt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployee_id(rs.getString("employee_id"));
                employee.setFull_name(rs.getString("full_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone_number(rs.getString("phone_number"));
                employee.setSalary_level(rs.getInt("salary_level"));
                employee.setSalary(rs.getBigDecimal("salary"));
                Date sqlDate = rs.getDate("birth_date");
                if (sqlDate != null) {
                    employee.setBirth_date(sqlDate.toLocalDate());
                }
                employee.setAddress(rs.getString("address"));
                String statusStr = rs.getString("status");
                if (statusStr != null) {
                    employee.setStatus(Status.valueOf(statusStr.toUpperCase()));
                }
                employee.setDepartment_id(rs.getInt("department_id"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return employees;
    }
}
