package business.model;

import datatype.Gender;
import datatype.Status;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {
    private String employee_id;
    private String full_name;
    private String email;
    private String phone_number;
    private Gender gender;
    private int salary_level;
    private BigDecimal salary;
    private LocalDate birth_date;
    private String address;
    private Status status;
    private int department_id;

    public Employee() {}

    public Employee(String employee_id, String full_name, String email, String phone_number, Gender gender, int salary_level, BigDecimal salary, LocalDate birth_date, String address, Status status, int department_id) {
        this.employee_id = employee_id;
        this.full_name = full_name;
        this.email = email;
        this.phone_number = phone_number;
        this.gender = gender;
        this.salary_level = salary_level;
        this.salary = salary;
        this.birth_date = birth_date;
        this.address = address;
        this.status = status;
        this.department_id = department_id;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getSalary_level() {
        return salary_level;
    }

    public void setSalary_level(int salary_level) {
        this.salary_level = salary_level;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }
}
