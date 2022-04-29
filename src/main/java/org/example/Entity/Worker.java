package org.example.Entity;

import java.time.LocalDate;

public class Worker {
    private String name;
    private String lastName;
    private LocalDate birthday;
    private LocalDate hireDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getHire_date() {
        return hireDate;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hireDate = hire_date;
    }

    public Worker(String name, String lastName, LocalDate birthday, LocalDate hire_date) {
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.hireDate = hire_date;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", hireDate=" + hireDate +
                '}';
    }
}
