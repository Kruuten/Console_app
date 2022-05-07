package org.example;

import org.example.entity.Manager;
import org.example.service.Menu;
import org.example.service.Service;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.List;


public class App {
    private static Document document;
    static String employeeRole = null;
    static List<Manager> employees;
    static Manager employee;
    public static void main(String[] args) throws IOException, InterruptedException {
        Menu menu = new Menu();
        Service service = new Service();
        try {
            document = Service.buildDocument();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Hi, what do you want to do?");
        boolean loop = true;
        do {
            int number = menu.mainMenu();
            switch (number) {
                case 1:
                    employees = service.employeeList(document, "all");
                    menu.showEmployees(employees);
                    Thread.sleep(2000);
                    break;
                case 2:
                    employees = service.employeeList(document, "Worker");
                    menu.showEmployees(employees);
                    Thread.sleep(2000);
                    break;
                case 3:
                    employees = service.employeeList(document,"Manager");
                    menu.showEmployees(employees);
                    Thread.sleep(2000);
                    break;
                case 4:
                    employees = service.employeeList(document, "Other");
                    menu.showEmployees(employees);
                    Thread.sleep(2000);
                    break;
                case 5:
                    employeeRole = menu.choseEmployeeRoleMenu();
                    Manager manager = menu.createEmployeeData(employeeRole);
                    service.createEmployee(manager, document);
                    break;
                case 6:
                    employeeRole = menu.choseEmployeeRoleMenu();
                    employees = service.employeeList(document, employeeRole);
                    menu.showEmployees(employees);
                    employee = menu.pickEmployee(employees);


                case 11:
                    System.out.println("Goodbye!");
                    loop = false;
                    break;
            }
        } while (loop);
    }
}

