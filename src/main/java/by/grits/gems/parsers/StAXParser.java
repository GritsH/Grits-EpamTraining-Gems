package by.grits.gems.parsers;

import by.grits.gems.entity.Gem;
import by.grits.gems.entity.VisualParameters;
import by.grits.gems.entity.enums.Color;
import by.grits.gems.entity.enums.Preciousness;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StAXParser {
  public List<Gem> readXMLSTAXParser() {
    List<Gem> gems = new ArrayList<>();
    Gem gem = new Gem();
    VisualParameters visualParameters = new VisualParameters();

    XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
    try {
      XMLEventReader xmlEventReader =
          xmlInputFactory.createXMLEventReader(new FileInputStream("src/main/resources/gems.xml"));
      while (xmlEventReader.hasNext()) {
        XMLEvent xmlEvent = xmlEventReader.nextEvent();
        if (xmlEvent.isStartElement()) {
          StartElement startElement = xmlEvent.asStartElement();
          if ("gem".equals(startElement.getName().getLocalPart())) {
            //gem = new Gem();
            gem.setId(
                Integer.parseInt(startElement.getAttributeByName(new QName("id")).getValue()));
          } else if ("name".equals(startElement.getName().getLocalPart())) {
            xmlEvent = xmlEventReader.nextEvent();
            gem.setName(xmlEvent.asCharacters().getData());
          } else if ("preciousness".equals(startElement.getName().getLocalPart())) {
            xmlEvent = xmlEventReader.nextEvent();
            gem.setPreciousness(Preciousness.valueOf(xmlEvent.asCharacters().getData()));
          } else if ("origin".equals(startElement.getName().getLocalPart())) {
            xmlEvent = xmlEventReader.nextEvent();
            gem.setOrigin(xmlEvent.asCharacters().getData());
          }else if("visualParameters".equals(startElement.getName().getLocalPart())){
            //visualParameters = new VisualParameters();
            xmlEvent = xmlEventReader.nextEvent();
          }else if("color".equals(startElement.getName().getLocalPart())){
            xmlEvent = xmlEventReader.nextEvent();
            visualParameters.setColor(Color.valueOf(xmlEvent.asCharacters().getData()));
          }else if("transparency".equals(startElement.getName().getLocalPart())){
            xmlEvent = xmlEventReader.nextEvent();
            visualParameters.setTransparency(Integer.parseInt(xmlEvent.asCharacters().getData()));
          }else if("facesAmount".equals(startElement.getName().getLocalPart())){
            xmlEvent = xmlEventReader.nextEvent();
            visualParameters.setFacesAmount(Integer.parseInt(xmlEvent.asCharacters().getData()));
          }else if ("value".equals(startElement.getName().getLocalPart())) {
            xmlEvent = xmlEventReader.nextEvent();
            gem.setValue(Double.parseDouble(xmlEvent.asCharacters().getData()));
          } else if ("addedAt".equals(startElement.getName().getLocalPart())) {
            xmlEvent = xmlEventReader.nextEvent();
            gem.setAddedAt(
                LocalDateTime.parse(
                    String.valueOf(xmlEvent.asCharacters().getData()),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ROOT)));
          }
        }
        if (xmlEvent.isEndElement()) {
          EndElement endElement = xmlEvent.asEndElement();
          if ("gem".equals(endElement.getName().getLocalPart())) {
            gem.setVisualParameters(visualParameters);
            gems.add(gem);
          }
        }
      }
    } catch (XMLStreamException | FileNotFoundException ex) {
      ex.printStackTrace();
    }

    return gems;
  }
}
