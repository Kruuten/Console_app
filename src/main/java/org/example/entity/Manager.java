package org.example.entity;

import java.time.LocalDate;
import java.util.List;

public class Manager extends Worker{
    private List<Worker> managerWorkers;
    public List<Worker> getManagerWorkers() {
        return managerWorkers;
    }

    public void setManagerWorkers(List<Worker> managerWorkers) {
        this.managerWorkers = managerWorkers;
    }

    public Manager(String name, String lastName, LocalDate birthday, LocalDate hire_date, List<Worker> managerWorkers) {
        super(name, lastName, birthday, hire_date);
        this.managerWorkers = managerWorkers;
    }

    @Override
    public String toString() {
        return "Manager{" + "Name: '" + getName() + '\''
                + ", Lastname: '" + getLastname() + '\''
                + ", Birthday: '" + getBirthday() + '\''
                + ", Hire date: : '" + getHire_date() + '\''
                + ", Manager workers'" + getManagerWorkers() + '\'' +
                '}';
    }
}
