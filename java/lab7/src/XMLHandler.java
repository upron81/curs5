import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLHandler {
    // Загрузить абонентов из XML
    public static List<Subscriber> loadFromXML(String filePath) throws Exception {
        List<Subscriber> subscribers = new ArrayList<>();
        File xmlFile = new File(filePath);

        if (!xmlFile.exists()) {
            throw new Exception("Файл не найден.");
        }

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("subscriber");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            String name = element.getElementsByTagName("name").item(0).getTextContent();
            String phoneNumber = element.getElementsByTagName("phoneNumber").item(0).getTextContent();
            int tariffId = Integer.parseInt(element.getElementsByTagName("tariffId").item(0).getTextContent());
            subscribers.add(new Subscriber(name, phoneNumber, tariffId));
        }
        return subscribers;
    }

    // Сохранить абонентов в XML
    public static void saveToXML(List<Subscriber> subscribers, String filePath) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();
        Element rootElement = doc.createElement("subscribers");
        doc.appendChild(rootElement);

        for (Subscriber subscriber : subscribers) {
            Element subscriberElement = doc.createElement("subscriber");
            rootElement.appendChild(subscriberElement);

            Element nameElement = doc.createElement("name");
            nameElement.appendChild(doc.createTextNode(subscriber.getName()));
            subscriberElement.appendChild(nameElement);

            Element phoneElement = doc.createElement("phoneNumber");
            phoneElement.appendChild(doc.createTextNode(subscriber.getPhoneNumber()));
            subscriberElement.appendChild(phoneElement);

            Element tariffIdElement = doc.createElement("tariffId");
            tariffIdElement.appendChild(doc.createTextNode(String.valueOf(subscriber.getTariffId())));
            subscriberElement.appendChild(tariffIdElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
    }

    // Загрузить тарифы из XML
    public static List<Tariff> loadTariffsFromXML(String filePath) throws Exception {
        List<Tariff> tariffs = new ArrayList<>();
        File xmlFile = new File(filePath);

        if (!xmlFile.exists()) {
            throw new Exception("Файл не найден.");
        }

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("tariff");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            String name = element.getElementsByTagName("name").item(0).getTextContent();
            double cost = Double.parseDouble(element.getElementsByTagName("cost").item(0).getTextContent());
            tariffs.add(new Tariff(name, cost));
        }
        return tariffs;
    }

    // Сохранить тарифы в XML
    public static void saveTariffsToXML(List<Tariff> tariffs, String filePath) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();
        Element rootElement = doc.createElement("tariffs");
        doc.appendChild(rootElement);

        for (Tariff tariff : tariffs) {
            Element tariffElement = doc.createElement("tariff");
            rootElement.appendChild(tariffElement);

            Element nameElement = doc.createElement("name");
            nameElement.appendChild(doc.createTextNode(tariff.getName()));
            tariffElement.appendChild(nameElement);

            Element costElement = doc.createElement("cost");
            costElement.appendChild(doc.createTextNode(String.valueOf(tariff.getCost())));
            tariffElement.appendChild(costElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
    }
}
