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
    private Document document;
    private static final String FILE = "src/main/resources/Employees.xml";
    List<Worker> workers;
    List<Worker> managers;
    List<Worker> others;


    public void showList(String category) {
        try {
            document = buildDocument();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Node rootNode = document.getFirstChild();
        NodeList rootChildren = rootNode.getChildNodes();
        Node employeeNode;

        for (int i = 0; i < rootChildren.getLength(); i++) {
            employeeNode = rootChildren.item(i);
            if (rootChildren.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            switch (rootChildren.item(i).getNodeName()) {
                case  "workers":
                        workers = employeeList(employeeNode, category);
                    break;
                case "managers":
                        managers = employeeList(employeeNode, category);
                    break;
                case "others":
                        others = employeeList(employeeNode,category);
                    break;
            }
        }
            switch (category){
                case "worker":
                    System.out.println(workers);
                    break;
                case "manager":
                    System.out.println(managers);
                    break;
                case "other":
                    System.out.println(others);
                    break;
            }
    }

    private List<Worker> employeeList(Node node, String category){
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
                if (!childList.item(i).getNodeName().equals(category)) {
                    continue;
                }
                NodeList employeeElement = childList.item(i).getChildNodes();
                for (int j = 0; j < employeeElement.getLength(); j++) {

                    if (employeeElement.item(j).getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }

                    switch (employeeElement.item(j).getNodeName()) {
                        case "name":
                            name = employeeElement.item(j).getTextContent();
                            break;
                        case "lastname":
                            lastName = employeeElement.item(j).getTextContent();
                            break;
                        case "birthdate":
                            birthday = parseDate(employeeElement.item(j).getTextContent());
                            break;
                        case "hire_date":
                            hireDate = parseDate(employeeElement.item(j).getTextContent());
                            break;
                        case "description":
                            description = employeeElement.item(j).getTextContent();
                            break;
                        case "workers":
                            Node managerWorkers = employeeElement.item(j);
                            subordinate = employeeList(managerWorkers, "worker");
                            break;
                    }
                }
                switch (category) {
                    case "worker":
                        Worker worker = new Worker(name, lastName, birthday, hireDate);
                        employeesList.add(worker);
                        break;
                    case "manager":
                        Manager manager = new Manager(name, lastName, birthday, hireDate, subordinate);
                        employeesList.add(manager);
                        break;
                    case "other":
                        Other other = new Other(name, lastName, birthday, hireDate, description);
                        employeesList.add(other);
                        break;
                }
            }

        return employeesList;
    }
    private static Document buildDocument() throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        return documentBuilderFactory.newDocumentBuilder().parse(FILE);
    }
    private LocalDate parseDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }
}

