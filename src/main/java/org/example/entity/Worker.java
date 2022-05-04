package org.example.entity;

import java.time.LocalDate;

public class Worker {
    private int id;
    private String role;
    private String name;
    private String lastName;
    private LocalDate birthday;
    private LocalDate hireDate;
    private int superior_id;

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

    public int getSuperior_id() {
        return superior_id;
    }

    public void setSuperior_id(int superior_id) {
        this.superior_id = superior_id;
    }

    public Worker(int id, String role, String name, String lastName, LocalDate birthday, LocalDate hireDate, int superior_id) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.hireDate = hireDate;
        this.superior_id = superior_id;
    }

    @Override
    public String toString() {
        return "Worker{"+ "Id: '" + getId() + '\''
                + "Role: '" + getRole() + '\''
                + "Name: '" + getName() + '\''
                + ", Last Name: '" + getLastName() + '\''
                + ", Birthday=" + getName()
                + ", Hire Date=" + getLastName() +
                '}';
    }
}
