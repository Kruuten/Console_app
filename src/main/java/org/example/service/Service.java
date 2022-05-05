package org.example.service;

import org.example.entity.Manager;
import org.example.entity.Other;
import org.example.entity.Worker;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private static final String FILE = "src/main/resources/Employees.xml";
    Manager worker;



    public void showList(Document document, String employeeRole) {
        NodeList rootChildren = document.getElementsByTagName("employee");
        Node employeeNode;
        List<Manager> workers = new ArrayList<>();
        for (int i = 0; i < rootChildren.getLength(); i++) {
            employeeNode = rootChildren.item(i);
            if (rootChildren.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            worker = employeeList(employeeNode, employeeRole);
            if (worker != null)
                workers.add(worker);
        }
        System.out.println(workers);
    }

    private Manager employeeList(Node node, String employeeRole) {
        int id;
        int superior_id = 0;
        String role;
        String name;
        String lastName;
        LocalDate birthday;
        LocalDate hireDate;
        String description = null;
        if (node == null) {
            return null;
        }
        Manager search = null;

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
            role = element.getElementsByTagName("role").item(0).getTextContent();
            name = element.getElementsByTagName("name").item(0).getTextContent();
            lastName = element.getElementsByTagName("lastname").item(0).getTextContent();
            birthday = parseDate(element.getElementsByTagName("birthdate").item(0).getTextContent());
            hireDate = parseDate(element.getElementsByTagName("hire_date").item(0).getTextContent());
            if (element.getElementsByTagName("superior_id").item(0) != null) {
                superior_id = Integer.parseInt(element.getElementsByTagName("superior_id").item(0).getTextContent());
            }
            if (element.getElementsByTagName("description").item(0) != null) {
                description = element.getElementsByTagName("description").item(0).getTextContent();
            }
            if (role.equals(employeeRole)) {
                switch (employeeRole) {
                    case "Worker":
                        search = new Worker(id, role, name, lastName, birthday, hireDate, superior_id);
                        break;
                    case "Manager":
                        search = new Manager(id, role, name, lastName, birthday, hireDate);
                        break;
                    case "Other":
                        search = new Other(id, role, name, lastName, birthday, hireDate, description);
                        break;
                }
            }
        }
        return search;
    }

    public static Document buildDocument() throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        return documentBuilderFactory.newDocumentBuilder().parse(FILE);
    }
    public LocalDate parseDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }
}

