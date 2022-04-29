package org.example.Entity;

import java.time.LocalDate;

public class Other {
 private String name;
 private String lastName;
 private LocalDate birthday;
 private LocalDate hireDate;
 private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Other(String name, String lastName, LocalDate birthday, LocalDate hireDate, String description) {
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.hireDate = hireDate;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Other{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", hireDate=" + hireDate +
                ", description='" + description + '\'' +
                '}';
    }
}
