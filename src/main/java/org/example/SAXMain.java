package org.example;

import com.sun.xml.bind.v2.util.XmlFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SAXMain {
    static File file = new File("src\\file.xml");
    public static void main(String[] args) throws SAXException, JAXBException, ParserConfigurationException, IOException,
            XMLStreamException {
        Animals animals = new Animals(
                Arrays.asList(new Animal("Murzik", "Cat", "Darya Shumova", 11),
                        new Animal("Govorun", "Parrot", "Darya Shumova", 5),
                        new Animal("Druzhok", "Dog", "Ivan Bronin", 11),
                        new Animal("Leo", "Chameleon", "Andrey Usov", 2),
                        new Animal("Chernysh", "Cat", "Darya Shumova", 3),
                        new Animal("Puaro", "Cat", "Nikita Labuda", 14),
                        new Animal("Linda", "Сapybara", "Vadim Solncev", 5),
                        new Animal("Tomas", "Сapybara", "Vadim Solncev", 4),
                        new Animal("Barsik", "Cat", "Maria Ivanova", 20)));
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter(file));
        writer.writeStartDocument("1.0");
        writer.writeStartElement("Animals");
        List<Animal> list = animals.getAnimals();
        for (Animal animal :
            list) {
            writeObjToDoc(animal, writer);
        }
        writer.writeEndElement();
        writer.writeEndDocument();
        writer.flush();
        writer.close();
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        SAXParser parser = parserFactory.newSAXParser();
        SaxMapper mapper = new SaxMapper();
        parser.parse(file, mapper);
        list = mapper.getList();

        System.out.println(list);
    }
    static public void writeObjToDoc(Animal animal, XMLStreamWriter writer) throws XMLStreamException{
        writer.writeStartElement("Animal");

        writer.writeStartElement("name");
        writer.writeCharacters(animal.getName());
        writer.writeEndElement();

        writer.writeStartElement("type");
        writer.writeCharacters(animal.getType());
        writer.writeEndElement();

        writer.writeStartElement("fullOwnerName");
        writer.writeCharacters(animal.getFullOwnerName());
        writer.writeEndElement();

        writer.writeStartElement("age");
        writer.writeCharacters(Integer.toString(animal.getAge()));
        writer.writeEndElement();

        writer.writeEndElement();

    }
}
