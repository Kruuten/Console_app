package org.example.entity;

import java.time.LocalDate;

public class Worker extends Manager {
    private int superior_id;

    public int getSuperior_id() {
        return superior_id;
    }

    public void setSuperior_id(int superior_id) {
        this.superior_id = superior_id;
    }

    public Worker(int id, String role, String name, String lastName, LocalDate birthday, LocalDate hireDate, int superior_id) {
        super(id, role, name, lastName, birthday, hireDate);
        this.superior_id = superior_id;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + getId() +
                ", role='" + getRole() + '\'' +
                ", name='" + getName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", birthday=" + getBirthday() +
                ", hireDate=" + getHireDate() +
                ", superior_id=" + superior_id +
                '}';
    }
}
