package org.example.service;

import org.example.entity.Manager;
import org.example.entity.Other;
import org.example.entity.Worker;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private static final String FILE = "src/main/resources/Employees.xml";
    List<Worker> workers;


    public void showList(Document document, String employeeRole) {
        Node rootNode = document.getFirstChild();
        NodeList rootChildren = rootNode.getChildNodes();
        Node employeeNode;

        for (int i = 0; i < rootChildren.getLength(); i++) {
            employeeNode = rootChildren.item(i);
            if (rootChildren.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            workers = employeeList(employeeNode, employeeRole);
            System.out.println(workers);
        }
    }

    private List<Worker> employeeList(Node node, String employeeRole){
        int id = 0;
        int superior_id = 0;
        String role = null;
        String name = null;
        String lastName = null;
        LocalDate birthday = null;
        LocalDate hireDate = null;
        String description = null;
        List<Worker> subordinate = null;

        if (node == null) {
            return null;
        }
        List<Worker> employeesList = new ArrayList<>();
            NodeList childList = node.getChildNodes();
            for (int i = 0; i < childList.getLength(); i++) {
                if (childList.item(i).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                if (!childList.item(i).getNodeName().equals(employeeRole)) {
                    continue;
                }
                NodeList employeeElement = childList.item(i).getChildNodes();
                for (int j = 0; j < employeeElement.getLength(); j++) {

                    if (employeeElement.item(j).getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }

                    String content = employeeElement.item(j).getTextContent();
                    switch (employeeElement.item(j).getNodeName()) {
                        case "id":
                            id = Integer.parseInt(content);
                            break;
                        case "role":
                            role =  content;
                        case "name":
                            name = content;
                            break;
                        case "lastname":
                            lastName = content;
                            break;
                        case "birthdate":
                            birthday = parseDate(content);
                            break;
                        case "hire_date":
                            hireDate = parseDate(content);
                            break;
                        case "description":
                            description = content;
                            break;
                        case "superior_id":
                            superior_id = Integer.parseInt(content);
                            break;
                    }
                }
                Worker worker;
                switch (role) {
                    case "worker":
                        worker = new Worker(id, role, name, lastName, birthday, hireDate, superior_id);
                        employeesList.add(worker);
                        break;
                    case "manager":
                        worker = new Manager(id,role, name, lastName, birthday, hireDate, superior_id);
                        employeesList.add(worker);
                        break;
                    case "other":
                        worker = new Other(id, role, name, lastName, birthday, hireDate, superior_id, description);
                        employeesList.add(worker);
                        break;
                }
            }

        return employeesList;
    }

    public void addNewEmployee(Worker worker){

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

