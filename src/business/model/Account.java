package business.model;

import datatype.Role;

import java.time.LocalDate;

public class Account {
    private int id;
    private String username;
    private String password;
    private Role role;
    private LocalDate create_at;
    private boolean status;

    public Account() {}

    public Account(int id, String username, String password, Role role, LocalDate create_at, boolean status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.create_at = create_at;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDate create_at) {
        this.create_at = create_at;
    }

    @Override
    public String toString() {
        return "Account [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", create_at=" + create_at + ", status=" + status + "]";
    }
}
