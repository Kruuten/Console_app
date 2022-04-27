package org.example.Entity;

import java.time.LocalDate;
import java.util.List;

public class Manager extends Worker {
    private List<Worker> managerWorkers;

    public List<Worker> getWorkers() {
        return managerWorkers;
    }

    public void setWorkers(List<Worker> workers) {
        this.managerWorkers = workers;
    }

    public Manager(String name, String lastName, LocalDate birthday, LocalDate hire_date, List<Worker> managerWorkers) {
        super(name, lastName, birthday, hire_date);
        this.managerWorkers = managerWorkers;
    }

}
