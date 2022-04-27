package org.example.Entity;

import java.time.LocalDate;

public class Other extends Worker {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Other(String name, String lastname, LocalDate birthday, LocalDate hire_date, String description) {
        super(name, lastname, birthday, hire_date);
        this.description = description;
    }
}
