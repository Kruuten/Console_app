package org.example.entity;

import java.time.LocalDate;
import java.util.List;

public class Manager {
    private int id;
    private String role;
    private String name;
    private String lastName;
    private LocalDate birthday;
    private LocalDate hireDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Manager(int id, String role, String name, String lastName, LocalDate birthday, LocalDate hireDate) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", hireDate=" + hireDate +
                '}';
    }
}
