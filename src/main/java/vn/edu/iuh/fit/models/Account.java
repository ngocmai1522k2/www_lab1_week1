package vn.edu.iuh.fit.models;


import jakarta.persistence.*;

/**
 * `account_id` varchar(50) NOT NULL,
 `full_name` varchar(50) NOT NULL,
 `password` varchar(50) NOT NULL,
 `email` varchar(50) DEFAULT NULL,
 `phone` varchar(50) DEFAULT NULL,
 `status` tinyint(4) NOT NULL DEFAULT 1,**/

public class Account {
    String account_id;
    String full_name;
    String password;
    String email;
    String phone;
    Integer status;

    public Account() {
        super();
    }

    public Account(String account_id, String full_name, String password, String email, String phone, Integer status) {
        this.account_id = account_id;
        this.full_name = full_name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id='" + account_id + '\'' +
                ", full_name='" + full_name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
