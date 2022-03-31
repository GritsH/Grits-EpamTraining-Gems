package by.grits.gems.parsers;

import by.grits.gems.entity.Gem;
import by.grits.gems.entity.VisualParameters;
import by.grits.gems.entity.enums.Color;
import by.grits.gems.entity.enums.Preciousness;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SAXHandler extends DefaultHandler {
  List<Gem> gems;
  VisualParameters visualParameters;
  Gem gem;
  private StringBuilder currentValue = new StringBuilder();

  public List<Gem> getGems() {
    return gems;
  }

  @Override
  public void startDocument() {
    gems = new ArrayList<>();
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes)
      throws SAXException {
    currentValue.setLength(0);

    if (qName.equalsIgnoreCase("gem")) {
      gem = new Gem();
      String id = attributes.getValue("id");
      gem.setId(Integer.parseInt(id));
    }
    if ("name".equals(qName)) {
      currentValue = new StringBuilder();
    }
    if ("preciousness".equals(qName)) {
      currentValue = new StringBuilder();
    }
    if ("origin".equals(qName)) {
      currentValue = new StringBuilder();
    }
    if ("visualParameters".equals(qName)) {
      visualParameters = new VisualParameters();
    }
    if ("color".equals(qName)) {
      currentValue = new StringBuilder();
    }
    if ("transparency".equals(qName)) {
      currentValue = new StringBuilder();
    }
    if ("facesAmount".equals(qName)) {
      currentValue = new StringBuilder();
    }
    if ("value".equals(qName)) {
      currentValue = new StringBuilder();
    }
    if ("addedAt".equals(qName)) {
      currentValue = new StringBuilder();
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    if ("gem".equals(qName)) {
      gem.setVisualParameters(visualParameters);
      gems.add(gem);
    }
    if ("id".equals(qName)) {
      gem.setId(Integer.parseInt(String.valueOf(currentValue)));
    }
    if ("name".equals(qName)) {
      gem.setName(currentValue.toString());
    }
    if ("preciousness".equals(qName)) {
      gem.setPreciousness(Preciousness.valueOf(currentValue.toString()));
    }
    if ("origin".equals(qName)) {
      gem.setOrigin(currentValue.toString());
    }
    if ("color".equals(qName)) {
      visualParameters.setColor(Color.valueOf(currentValue.toString()));
    }
    if ("transparency".equals(qName)) {
      visualParameters.setTransparency(Integer.parseInt(currentValue.toString()));
    }
    if ("facesAmount".equals(qName)) {
      visualParameters.setFacesAmount(Integer.parseInt(currentValue.toString()));
    }
    if ("value".equals(qName)) {
      gem.setValue(Double.parseDouble(currentValue.toString()));
    }
    if ("addedAt".equals(qName)) {
      gem.setAddedAt(
          LocalDateTime.parse(
              currentValue.toString(),
              DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ROOT)));
    }
  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    if (currentValue == null) {
      currentValue = new StringBuilder();
    } else {
      currentValue.append(ch, start, length);
    }
  }
}
