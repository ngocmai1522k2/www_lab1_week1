package vn.edu.iuh.fit.models;

import jakarta.persistence.Id;

//*CREATE TABLE IF NOT EXISTS `role` (
//  `role_id` varchar(50) NOT NULL,
//  `role_name` varchar(50) NOT NULL,
//  `description` varchar(50) DEFAULT NULL,
//  `status` tinyint(4) NOT NULL,
//  PRIMARY KEY (`role_id`)
public class Role {
    String role_id;
    String role_name;
    String description;
    Integer status;

    public Role() {
        super();
    }

    public Role(String role_id, String role_name, String description, Integer status) {
        this.role_id = role_id;
        this.role_name = role_name;
        this.description = description;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id='" + role_id + '\'' +
                ", role_name='" + role_name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
