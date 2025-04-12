package business.model;

import validate.ValidateDepartment;

import java.util.Scanner;

public class Department {
    private int dp_id;
    private String dp_name;
    private String dp_description;
    private boolean dp_status;

    public Department() {}

    public Department(int dp_id, String dp_name, String dp_description, boolean dp_status) {
        this.dp_id = dp_id;
        this.dp_name = dp_name;
        this.dp_description = dp_description;
        this.dp_status = dp_status;
    }

    public int getDp_id() {
        return dp_id;
    }

    public void setDp_id(int dp_id) {
        this.dp_id = dp_id;
    }

    public String getDp_name() {
        return dp_name;
    }

    public void setDp_name(String dp_name) {
        this.dp_name = dp_name;
    }

    public String getDp_description() {
        return dp_description;
    }

    public void setDp_description(String dp_description) {
        this.dp_description = dp_description;
    }

    public boolean isDp_status() {
        return dp_status;
    }

    public void setDp_status(boolean dp_status) {
        this.dp_status = dp_status;
    }

    public void inputData(Scanner sc){
        this.dp_name = ValidateDepartment.validateDepartmentName("Nhập tên phòng ban", sc);
        this.dp_description = ValidateDepartment.validateDepartmentDescription("Nhập mô tả phòng ban", sc);
        this.dp_status = ValidateDepartment.validateDepartmentStatus("Nhập trạng thái của phòng ban", sc);
    }
    @Override
    public String toString() {
        return String.format("ID: %d | Tên: %s | Mô tả: %s | Trạng thái: %s",
                dp_id, dp_name, dp_description,
                dp_status ? "Hoạt động" : "Không hoạt động");
    }
}