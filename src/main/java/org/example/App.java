package org.example;

import org.example.entity.Menu;
import org.example.entity.Worker;
import org.example.service.Service;
import org.w3c.dom.Document;

import java.io.IOException;


public class App {
    private static Document document;
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
                    service.showList(document, "company");
                    Thread.sleep(2000);
                    break;
                case 2:
                    service.showList(document, "worker");
                    Thread.sleep(2000);
                    break;
                case 3:
                    service.showList(document,"manager");
                    Thread.sleep(2000);
                    break;
                case 4:
                    service.showList(document,"other");
                    Thread.sleep(2000);
                    break;
                case 5:
                    break;
                case 11:
                    System.out.println("Goodbye!");
                    loop = false;
                    break;
            }
        } while (loop);
    }
}

