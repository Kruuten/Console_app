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

    public Other(String name, String lastName, LocalDate birthday, LocalDate hire_date, String description) {
        super(name, lastName, birthday, hire_date);
        this.description = description;
    }

    @Override
    public String toString() {
        return "Other{" + "Name: '" + getName() + '\''
                + ", Lastname: '" + getLastname() + '\''
                + ", Birthday: '" + getBirthday() + '\''
                + ", Hire date: : '" + getHire_date() + '\''
                + ", description: '" + description + '\'' +
                '}';
    }
}
