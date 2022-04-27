package org.example.Service;

import org.example.Entity.Worker;
import org.example.Entity.Manager;
import org.example.Entity.Other;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Service {
//    private Worker worker;
    private Manager manager;
    private Other other;
    private Document document;
    private static final String FILE = "src/main/resources/Employees.xml";

    public void showList(String category) {
        readFile();
        NodeList list = document.getElementsByTagName(category);
        System.out.println("Current Element :" + category);
        System.out.println();
        if (category.equals("worker")){
            List<Worker> workers = initWorkers(list);
            for (Worker worker : workers){
                System.out.println("name: " + worker.getName());
                System.out.println("lastname: " + worker.getLastname());
                System.out.println("birthday: " + worker.getBirthday());
                System.out.println("hire date: " + worker.getHire_date());
                System.out.println();
            }

        }
    }
    public void readFile() {
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(FILE);
            document.getDocumentElement().normalize();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Worker> initWorkers(NodeList nodeList){
        List<Worker> workers = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String name = element.getElementsByTagName("name").item(0).getTextContent() ;
                String lastName = element.getElementsByTagName("lastname").item(0).getTextContent();
                String birthDate = element.getElementsByTagName("birthdate").item(0).getTextContent();
                String hireDate = element.getElementsByTagName("hire_date").item(0).getTextContent();

                Worker worker = new Worker(name, lastName, parseDate(birthDate), parseDate(hireDate));
                workers.add(worker);
            }
    }
        return workers;
}


    public LocalDate parseDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }
}





//    String workers = null;
//    String description = null;
//                if (category.equals("manager")) {
//        workers = element.getElementsByTagName("workers").item(0).getTextContent();
//    }
//                if (category.equals("other")) {
//        description = element.getElementsByTagName("description").item(0).getTextContent();
//    }


//                if (category.equals("manager")) {
//        System.out.println("workers : " + workers);
//    }
//                if ((category.equals("other"))) {
//        System.out.println("description : " + description);
//    }
