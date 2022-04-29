package org.example.Entity;

import java.time.LocalDate;
import java.util.List;

public class Manager{
    private String name;
    private String lastName;
    private LocalDate birthday;
    private LocalDate hireDate;
    private List<Worker> managerWorkers;

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

    public List<Worker> getManagerWorkers() {
        return managerWorkers;
    }

    public void setManagerWorkers(List<Worker> managerWorkers) {
        this.managerWorkers = managerWorkers;
    }

    public Manager(String name, String lastName, LocalDate birthday, LocalDate hireDate, List<Worker> managerWorkers) {
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.hireDate = hireDate;
        this.managerWorkers = managerWorkers;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", hireDate=" + hireDate +
                ", managerWorkers=" + managerWorkers +
                '}';
    }
}
