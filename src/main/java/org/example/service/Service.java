package org.example.service;

import org.example.entity.Manager;
import org.example.entity.Other;
import org.example.entity.Worker;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private static final String FILE = "src/main/resources/Employees.xml";
    Manager worker;



    public List<Manager> employeeList(Document document, String employeeRole) {
        NodeList rootChildren = document.getElementsByTagName("employee");
        Node employeeNode;
        List<Manager> workers = new ArrayList<>();
        for (int i = 0; i < rootChildren.getLength(); i++) {
            employeeNode = rootChildren.item(i);
            if (rootChildren.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            worker = parseEmployee(employeeNode, employeeRole);
            if (worker != null)
                workers.add(worker);
        }
        return workers;
    }

    private Manager parseEmployee(Node node, String employeeRole) {
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

    public int parseLastId(Document document) {
        int parseId = 0;
        int id = 0;
        NodeList rootChildren = document.getElementsByTagName("employee");
        Node employeeNode;
        for (int i = 0; i < rootChildren.getLength(); i++) {
            employeeNode = rootChildren.item(i);
            if (rootChildren.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (employeeNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) employeeNode;
                id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
            }
            if (id > parseId)
                parseId = id;
        }
        return parseId+1;
    }

    public void createEmployee(Manager manager, Document document) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        Element root = document.getDocumentElement();
        Element newEmployee = document.createElement("employee");

        Element id = document.createElement("id");
        id.appendChild(document.createTextNode(Integer.toString(manager.getId())));
        newEmployee.appendChild(id);

        Element role = document.createElement("role");
        role.appendChild(document.createTextNode(manager.getRole()));
        newEmployee.appendChild(role);

        Element name = document.createElement("name");
        name.appendChild(document.createTextNode(manager.getName()));
        newEmployee.appendChild(name);

        Element lastName = document.createElement("lastname");
        lastName.appendChild(document.createTextNode(manager.getLastName()));
        newEmployee.appendChild(lastName);

        Element birthday = document.createElement("birthdate");
        birthday.appendChild(document.createTextNode(manager.getBirthday().format(formatter)));
        newEmployee.appendChild(birthday);

        Element hireDate = document.createElement("hire_date");
        hireDate.appendChild(document.createTextNode(manager.getHireDate().format(formatter)));
        newEmployee.appendChild(hireDate);

        if (manager instanceof Worker) {
            Element superior_id = document.createElement("superior_id");
            superior_id.appendChild(document.createTextNode(Integer.toString(((Worker) manager).getSuperior_id())));
            newEmployee.appendChild(superior_id);
        }

        if (manager instanceof Other) {
            Element description = document.createElement("description");
            description.appendChild(document.createTextNode(((Other) manager).getDescription()));
            newEmployee.appendChild(description);
        }

        root.appendChild(newEmployee).normalize();
        try {
            DOMSource source = new DOMSource(document);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            StreamResult result = new StreamResult(FILE);
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

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

