package org.example.Service;

import org.example.Entity.Manager;
import org.example.Entity.Other;
import org.example.Entity.Worker;
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

    public void showList(String category) {
        try {
            document = buildDocument();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Node rootNode = document.getFirstChild();

        NodeList rootChildren = rootNode.getChildNodes();
        Node workersNode = null;
        Node managersNode = null;
        Node othersNode = null;
        List<Worker> workers = new ArrayList<>();
        List<Manager> managers = new ArrayList<>();
        List<Other> others = new ArrayList<>();

        for (int i = 0; i < rootChildren.getLength(); i++) {
            if (rootChildren.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            switch (rootChildren.item(i).getNodeName()) {
                case "workers":
                    if (category.equals("workers")) {
                        workersNode = rootChildren.item(i);
                         workers = workersList(workersNode);
                    }
                    break;
                case "managers":
                    if (category.equals("managers")) {
                        managersNode = rootChildren.item(i);
                        managers = managerList(managersNode);
                    }
                    break;
                case "others":
                    if (category.equals("others")) {
                        othersNode = rootChildren.item(i);
                        others = othersList(othersNode);
                    }
                    break;
            }

        }
        switch (category){
            case "workers":
                System.out.println("Workers: ");
                for (Worker worker : workers){
                    System.out.println("Name: " + worker.getName());
                    System.out.println("Lastname: " + worker.getLastname());
                    System.out.println("Birthdate: " + worker.getBirthday());
                    System.out.println("Hire Date: " + worker.getBirthday());
                    System.out.println();
                }
                break;
            case "managers" :
                System.out.println("Managers: ");
                for (Manager manager : managers){
                    System.out.println("Name: " + manager.getName());
                    System.out.println("Lastname: " + manager.getLastName());
                    System.out.println("Birthdate: " + manager.getBirthday());
                    System.out.println("Hire Date: " + manager.getBirthday());
                    System.out.println("Managed workers: " + manager.getManagerWorkers());
                    System.out.println();
                }
                break;
            case "others":
                System.out.println("Others: ");
                for (Other other : others){
                    System.out.println("Name: " + other.getName());
                    System.out.println("Lastname: " + other.getLastName());
                    System.out.println("Birthdate: " + other.getBirthday());
                    System.out.println("Hire Date: " + other.getBirthday());
                    System.out.println("Managed workers: " + other.getDescription());
                    System.out.println();
                }
        }

    }

    public List<Worker> workersList(Node node) {
        if (node == null) {
            return null;
        }
        List<Worker> workersList = new ArrayList<>();
        NodeList workersChilds = node.getChildNodes();
        for (int i = 0; i < workersChilds.getLength(); i++) {
            if (workersChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (!workersChilds.item(i).getNodeName().equals("worker")) {
                continue;
            }

            String name = null;
            String surname = null;
            LocalDate birthday = null;
            LocalDate hireDate = null;
            NodeList workerElement = workersChilds.item(i).getChildNodes();
            for (int j = 0; j < workerElement.getLength(); j++) {

                if (workerElement.item(j).getNodeType() != Node.ELEMENT_NODE){
                    continue;
                }

                switch (workerElement.item(j).getNodeName()){
                    case "name" :
                        name = workerElement.item(j).getTextContent();
                        break;
                    case "lastname" :
                        surname = workerElement.item(j).getTextContent();
                        break;
                    case "birthdate" :
                        birthday = parseDate(workerElement.item(j).getTextContent());
                        break;
                    case "hire_date":
                        hireDate = parseDate(workerElement.item(j).getTextContent());
                        break;
                }
            }
            Worker worker = new Worker(name, surname, birthday, hireDate);
            workersList.add(worker);
        }
        return workersList;
    }

    public List<Manager> managerList(Node node) {
        List<Manager> managersList = new ArrayList<>();
        if (node == null) {
            return null;
        }
        NodeList managersChild = node.getChildNodes();
        List<Worker> subordinate = new ArrayList<>();
        for (int i = 0; i < managersChild.getLength(); i++) {
            if (managersChild.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (!managersChild.item(i).getNodeName().equals("manager")) {
                continue;
            }

            String name = null;
            String surname = null;
            LocalDate birthday = null;
            LocalDate hireDate = null;
            Node managerWorkers;
            NodeList managersElement = managersChild.item(i).getChildNodes();
            for (int j = 0; j < managersElement.getLength(); j++) {

                if (managersElement.item(j).getNodeType() != Node.ELEMENT_NODE){
                    continue;
                }

                switch (managersElement.item(j).getNodeName()){
                    case "name" :
                        name = managersElement.item(j).getTextContent();
                        break;
                    case "lastname" :
                        surname = managersElement.item(j).getTextContent();
                        break;
                    case "birthdate" :
                        birthday = parseDate(managersElement.item(j).getTextContent());
                        break;
                    case "hire_date":
                        hireDate = parseDate(managersElement.item(j).getTextContent());
                        break;
                    case "workers":
                        managerWorkers = managersElement.item(j);
                        subordinate = workersList(managerWorkers);
                }
            }
            Manager manager = new Manager(name, surname, birthday, hireDate, subordinate);
            managersList.add(manager);
        }
        return managersList;
    }

    private List<Other> othersList(Node node) {
        List<Other> otherList = new ArrayList<>();
        if (node == null) {
            return null;
        }
        NodeList othersChild = node.getChildNodes();
        for (int i = 0; i < othersChild.getLength(); i++) {
            if (othersChild.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (!othersChild.item(i).getNodeName().equals("other")) {
                continue;
            }

            String name = null;
            String lastName = null;
            LocalDate birthday = null;
            LocalDate hireDate = null;
            String description = null;
            NodeList othersElement = othersChild.item(i).getChildNodes();
            for (int j = 0; j < othersElement.getLength(); j++) {

                if (othersElement.item(j).getNodeType() != Node.ELEMENT_NODE){
                    continue;
                }

                switch (othersElement.item(j).getNodeName()){
                    case "name" :
                        name = othersElement.item(j).getTextContent();
                        break;
                    case "lastname" :
                        lastName = othersElement.item(j).getTextContent();
                        break;
                    case "birthdate" :
                        birthday = parseDate(othersElement.item(j).getTextContent());
                        break;
                    case "hire_date":
                        hireDate = parseDate(othersElement.item(j).getTextContent());
                        break;
                    case "description":
                        description = othersElement.item(j).getTextContent();
                        break;
                }
            }
            Other other = new Other(name, lastName, birthday, hireDate, description);
            otherList.add(other);
        }
        return otherList;
    }
    private static Document buildDocument() throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        return documentBuilderFactory.newDocumentBuilder().parse(FILE);
    }
    public LocalDate parseDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }
}

