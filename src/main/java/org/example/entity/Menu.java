package org.example.entity;

import org.example.service.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class Menu {
    Service service;
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


}
