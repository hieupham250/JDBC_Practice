package business.dao.employee;

import business.config.ConnectionDB;
import business.model.Employee;
import datatype.Gender;
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
                String genderStr = rs.getString("gender");
                if (genderStr != null) {
                    employee.setGender(Gender.valueOf(genderStr.toUpperCase()));
                }
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

    @Override
    public List<Employee> getEmployeeWithPagination(int page, int size) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Employee> employees = new ArrayList<Employee>();
        try {
            conn = ConnectionDB.openConnection();
            int offset = (page - 1) * size;
            callSt = conn.prepareCall("{call get_employee_by_page(?, ?)}");
            callSt.setInt(1, size);
            callSt.setInt(2, offset);
            rs = callSt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployee_id(rs.getString("employee_id"));
                employee.setFull_name(rs.getString("full_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone_number(rs.getString("phone_number"));
                String genderStr = rs.getString("gender");
                if (genderStr != null) {
                    employee.setGender(Gender.valueOf(genderStr.toUpperCase()));
                }
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

    @Override
    public boolean createEmployee(Employee employee) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call create_employee(?,?,?,?,?,?,?,?,?,?,?)}");
            callSt.setString(1, employee.getEmployee_id());
            callSt.setString(2, employee.getFull_name());
            callSt.setString(3, employee.getEmail());
            callSt.setString(4, employee.getPhone_number());
            callSt.setString(5, employee.getGender().toString());
            callSt.setInt(6, employee.getSalary_level());
            callSt.setBigDecimal(7, employee.getSalary());
            callSt.setString(8, employee.getBirth_date().toString());
            callSt.setString(9, employee.getAddress());
            callSt.setString(10, employee.getStatus().toString());
            callSt.setInt(11, employee.getDepartment_id());
            callSt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_employee(?,?,?,?,?,?,?,?,?,?,?)}");
            callSt.setString(1, employee.getEmployee_id());
            callSt.setString(2, employee.getFull_name());
            callSt.setString(3, employee.getEmail());
            callSt.setString(4, employee.getPhone_number());
            callSt.setString(5, employee.getGender().toString());
            callSt.setInt(6, employee.getSalary_level());
            callSt.setBigDecimal(7, employee.getSalary());
            callSt.setString(8, employee.getBirth_date().toString());
            callSt.setString(9, employee.getAddress());
            callSt.setString(10, employee.getStatus().toString());
            callSt.setInt(11, employee.getDepartment_id());
            callSt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(String id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_employee(?)}");
            callSt.setString(1, id);
            callSt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public List<Employee> getEmployeeByName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Employee> employees = new ArrayList<Employee>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_employee_by_name(?)}");
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployee_id(rs.getString("employee_id"));
                employee.setFull_name(rs.getString("full_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone_number(rs.getString("phone_number"));
                String genderStr = rs.getString("gender");
                if (genderStr != null) {
                    employee.setGender(Gender.valueOf(genderStr.toUpperCase()));
                }
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

    @Override
    public List<Employee> getEmployeeByAge(int max, int min) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Employee> employees = new ArrayList<Employee>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call search_employee_by_age_range(?,?)}");
            callSt.setInt(1, max);
            callSt.setInt(2, min);
            rs = callSt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployee_id(rs.getString("employee_id"));
                employee.setFull_name(rs.getString("full_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone_number(rs.getString("phone_number"));
                String genderStr = rs.getString("gender");
                if (genderStr != null) {
                    employee.setGender(Gender.valueOf(genderStr.toUpperCase()));
                }
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

    @Override
    public List<Employee> sortEmployeeBySalaryDesc() {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Employee> employees = new ArrayList<Employee>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sort_employees_by_salary_desc()}");
            rs = callSt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployee_id(rs.getString("employee_id"));
                employee.setFull_name(rs.getString("full_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone_number(rs.getString("phone_number"));
                String genderStr = rs.getString("gender");
                if (genderStr != null) {
                    employee.setGender(Gender.valueOf(genderStr.toUpperCase()));
                }
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

    @Override
    public List<Employee> sortEmployeeByNameAsc() {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Employee> employees = new ArrayList<Employee>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sort_employees_by_name_asc()}");
            rs = callSt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployee_id(rs.getString("employee_id"));
                employee.setFull_name(rs.getString("full_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone_number(rs.getString("phone_number"));
                String genderStr = rs.getString("gender");
                if (genderStr != null) {
                    employee.setGender(Gender.valueOf(genderStr.toUpperCase()));
                }
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
