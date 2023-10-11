package org.example;

import lombok.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
public class SaxMapper extends DefaultHandler {
    @Getter
    @Setter
    private List<Animal> list = new ArrayList<>();
    private StringBuilder data;

    private boolean isName;
    private boolean isType;
    private boolean isFullOwnerName;
    private boolean isAge;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("Animal")) {
            list.add(new Animal());
        }
        else if (qName.equals("name")) {
            isName = true;
        }
        else if (qName.equals("type")) {
            isType = true;
        }
        else if (qName.equals("fullOwnerName")) {
            isFullOwnerName = true;
        }
        else if (qName.equals("age")) {
            isAge = true;
        }
        data = new StringBuilder();
    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (isName) {
            list.get(list.size() - 1).setName(data.toString());
            isName = false;
        }
        else if (isType) {
            list.get(list.size() - 1).setType(data.toString());
            isType = false;
        }
        else if (isFullOwnerName) {
            list.get(list.size() - 1).setFullOwnerName(data.toString());
            isFullOwnerName = false;
        }
        else if (isAge) {
            list.get(list.size() - 1).setAge(Integer.parseInt(data.toString()));
            isAge = false;
        }
    }
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}
