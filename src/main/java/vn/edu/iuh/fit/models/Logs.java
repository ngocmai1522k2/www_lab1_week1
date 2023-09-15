package vn.edu.iuh.fit.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;
import java.time.LocalDate;

///* CREATE TABLE IF NOT EXISTS `log` (
//  `id` bigint(20) NOT NULL AUTO_INCREMENT,
//  `account_id` varchar(50) NOT NULL,
//  `login_time` datetime NOT NULL DEFAULT current_timestamp(),
//  `logout_time` datetime NOT NULL DEFAULT current_timestamp(),
//  `notes` varchar(250) NOT NULL DEFAULT '',

public class Logs {
    private Integer id;
    private Account account;
    private LocalDate login_time;
    private LocalDate logout_time;
    private String notes;

    public Logs() {
        super();
    }

    public Logs(Integer id, Account account, LocalDate login_time, LocalDate logout_time, String notes) {
        this.id = id;
        this.account = account;
        this.login_time = login_time;
        this.logout_time = logout_time;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Logs{" +
                "id=" + id +
                ", account=" + account +
                ", login_time=" + login_time +
                ", logout_time=" + logout_time +
                ", notes='" + notes + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDate getLogin_time() {
        return login_time;
    }

    public void setLogin_time(LocalDate login_time) {
        this.login_time = login_time;
    }

    public LocalDate getLogout_time() {
        return logout_time;
    }

    public void setLogout_time(LocalDate logout_time) {
        this.logout_time = logout_time;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
