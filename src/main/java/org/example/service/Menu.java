package org.example.service;

import org.example.App;
import org.example.entity.Manager;
import org.example.entity.Other;
import org.example.entity.Worker;
import org.example.service.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

public class Menu {
    App app;
    Service service = new Service();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public int mainMenu() throws IOException {
        System.out.println("Available commands:");
        System.out.println("1) Show all company");
        System.out.println("2) Show workers");
        System.out.println("3) Show managers");
        System.out.println("4) Show others");
        System.out.println("5) Create new employee");
        System.out.println("6) Edit employee");
        System.out.println("7) Edit managers workers");
        System.out.println("8) Delete employee");
        System.out.println("9) Sort by surname");
        System.out.println("10) Sort by hire date");
        System.out.println("11) exit");
        System.out.println("Choose the number");

        int number = Integer.parseInt(reader.readLine());
        while (number > 11 || number < 1) {
            System.out.println("Wrong number chosen. Choose in 1-10");
            number = Integer.parseInt(reader.readLine());
        }
        return number;
    }

    public void showEmployees(List<Manager> list) {
        for (Manager manager : list) {
            System.out.println("Role: " + manager.getRole());
            System.out.println("Id: " + manager.getId());
            System.out.println("Name: " + manager.getName());
            System.out.println("Last Name: " + manager.getLastName());
            System.out.println("Birthday: " + manager.getBirthday());
            System.out.println("Hire Date: " + manager.getHireDate());
            if (manager instanceof Worker)
                System.out.println("Managers id: " + ((Worker) manager).getSuperior_id());
            if (manager instanceof Other)
                System.out.println("Description: " + ((Other) manager).getDescription());
            System.out.println();
        }
    }

    public String choseEmployeeRoleMenu() throws IOException {
        System.out.println("Chose role: 1 - Worker, 2 - Manager, 3 - Other");
        int number = Integer.parseInt(reader.readLine());
        while (number > 3 || number < 1) {
            System.out.println("Wrong number chosen. Choose in 1-3");
            number = Integer.parseInt(reader.readLine());
        }
        String role = null;
        switch(number) {
            case 1:
                role = "Worker";
                break;
            case 2:
                role = "Manager";
                break;
            case 3:
                role = "Other";
                break;
        }
        return role;
    }

    public Manager createEmployeeData(String employeeRole) throws IOException {
        int id;
        String name;
        String lastName;
        LocalDate birthday;
        LocalDate hireDate;
        int superior_id = 0;
        String description = null;
        Manager manager = null;
        System.out.println("Write name");
        name = reader.readLine();
        System.out.println("Write last name");
        lastName = reader.readLine();
        System.out.println("Write birthday date, format dd.MM.yyyy");
        birthday = service.parseDate(reader.readLine());
        System.out.println("Write hire date date, format dd.MM.yyyy");
        hireDate = service.parseDate(reader.readLine());
        if (employeeRole.equals("Worker")) {
            System.out.println("Write superior id");
            superior_id = Integer.parseInt(reader.readLine());
        }
        if (employeeRole.equals("Other")) {
            System.out.println("write description");
            description = reader.readLine();
        }
        try {
            id = service.parseLastId(Service.buildDocument());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        switch (employeeRole) {
            case "Worker":
                manager = new Worker(id, employeeRole, name, lastName, birthday, hireDate, superior_id);
                break;
            case "Manager":
                manager = new Manager(id, employeeRole, name, lastName, birthday, hireDate);
                break;
            case "Other":
                manager = new Other(id, employeeRole, name, lastName, birthday, hireDate, description);
                break;
        }
        return manager;
    }

    public Manager pickEmployee(List<Manager> list) throws IOException {
        Manager pickManager = null;
        System.out.print("Chose employee ID to edit:");
        int id = Integer.parseInt(reader.readLine());
        for (Manager manager : list){
            if (manager.getId() == id)
                pickManager = manager;
        }
        return pickManager;
    }

    public Manager editEmployeeData(Manager employee) {
        System.out.println("What do you want to edit?");
        System.out.println("1) Name");
        System.out.println("2) Last name");
        System.out.println("3) Birthday");
        System.out.println("4) Hire date");
        if (employee instanceof Worker)
            System.out.println("5) Superior manager");
        if (employee instanceof Other)
            System.out.println("6) Description");

    }

}
