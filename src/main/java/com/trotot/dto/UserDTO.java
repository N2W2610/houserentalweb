package com.trotot.dto;

public class UserDTO {

    private String email;
    private String password;
    private String phone;
    private String role;
    private String full_name;
    private int status;
    private int id;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }

    public String getFull_name() {
        return full_name;
    }

    public int getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public void setId(int id) {
        this.id = id;
    }
}
