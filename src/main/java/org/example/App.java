package org.example;

import org.example.service.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        Service service = new Service();
        System.out.println("Hi, what do you want to do?");
        boolean loop = true;
        do {
            int number = showMenu();
            switch (number) {
                case 1:
                    service.showList("company");
                    Thread.sleep(2000);
                    break;
                case 2:
                    service.showList("worker");
                    Thread.sleep(2000);
                    break;
                case 3:
                    service.showList("manager");
                    Thread.sleep(2000);
                    break;
                case 4:
                    service.showList("other");
                    Thread.sleep(2000);
                    break;
                case 11:
                    System.out.println("Goodbye!");
                    loop = false;
                    break;
            }
        } while (loop);

    }

    public static int showMenu() throws IOException {
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

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(reader.readLine());
        while (number > 11 || number < 1) {
            System.out.println("Wrong number chosen. Choose in 1-10");
            number = Integer.parseInt(reader.readLine());
        }
        return number;
    }
}

