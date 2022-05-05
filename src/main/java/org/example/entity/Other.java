package org.example.entity;

import java.time.LocalDate;

public class Other extends Manager {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Other(int id, String role, String name, String lastName, LocalDate birthday, LocalDate hireDate, String description) {
        super(id, role, name, lastName, birthday, hireDate);
        this.description = description;
    }

    @Override
    public String toString() {
        return "Other{" +
                "id=" + getId() +
                ", role='" + getRole() + '\'' +
                ", name='" + getName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", birthday=" + getBirthday() +
                ", hireDate=" + getHireDate() +
                ", description='" + description + '\'' +
                '}';
    }
}
