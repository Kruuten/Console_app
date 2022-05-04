package org.example.entity;

import java.time.LocalDate;
import java.util.List;

public class Manager extends Worker{

    public Manager(int id, String role, String name, String lastName, LocalDate birthday, LocalDate hireDate, int superior_id) {
        super(id, role, name, lastName, birthday, hireDate, superior_id);
    }

    @Override
    public String toString() {
        return "Manager{" + "Id: '" + getId() + '\''
                + ", Role: '" + getRole() + '\''
                + ", Name: '" + getName() + '\''
                + ", Lastname: '" + getLastName() + '\''
                + ", Birthday: '" + getBirthday() + '\''
                + ", Hire date: : '" + getHireDate() + '\''
                +
                '}';
    }
}
