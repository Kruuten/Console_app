package org.example.entity;

import java.time.LocalDate;

public class Other extends Worker {
 private String description;
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Other(int id, String role, String name, String lastName, LocalDate birthday, LocalDate hireDate, int superior_id, String description) {
        super(id, role, name, lastName, birthday, hireDate, superior_id);
        this.description = description;
    }

    @Override
    public String toString() {
        return "Other{" + "Id: '" + getId() + '\''
                + "Role: '" + getRole() + '\''
                + "Name: '" + getName() + '\''
                + ", Lastname: '" + getLastName() + '\''
                + ", Birthday: '" + getBirthday() + '\''
                + ", Hire date: : '" + getHireDate() + '\''
                + ", description: '" + getDescription() + '\'' +
                '}';
    }
}
