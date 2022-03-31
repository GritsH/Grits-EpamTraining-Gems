package by.grits.gems.parsers;

import by.grits.gems.entity.Gem;
import by.grits.gems.entity.VisualParameters;
import by.grits.gems.entity.enums.Color;
import by.grits.gems.entity.enums.Preciousness;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DOMParser {
  public List<Gem> readXMLDOMParser(String path)
      throws ParserConfigurationException, IOException, SAXException {
    List<Gem> gems = new ArrayList<>();
    Gem gem;

    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    Document document = documentBuilder.parse(new File(path));
    document.getDocumentElement().normalize();

    NodeList gemNodes = document.getElementsByTagName("gem");
    for (int gemNodesIterator = 0; gemNodesIterator < gemNodes.getLength(); gemNodesIterator++) {
      gem = new Gem();
      VisualParameters visualParameters = new VisualParameters();
      Node gemNode = gemNodes.item(gemNodesIterator);
      Element element = (Element) gemNode;
      NodeList gemElements = gemNode.getChildNodes();

      for (int gemElementsIterator = 0;
          gemElementsIterator < gemElements.getLength();
          gemElementsIterator++) {
        Node gemElement = gemElements.item(gemElementsIterator);
        gem.setId(Integer.parseInt(element.getAttribute("id")));
        if ("name".equals(gemElement.getNodeName())) {
          gem.setName(gemElement.getTextContent());
        }
        if ("preciousness".equals(gemElement.getNodeName())) {
          gem.setPreciousness(Preciousness.valueOf(gemElement.getTextContent()));
        }
        if ("origin".equals(gemElement.getNodeName())) {
          gem.setOrigin(gemElement.getTextContent());
        }
        if ("value".equals(gemElement.getNodeName())) {
          gem.setValue(Double.parseDouble(gemElement.getTextContent()));
        }
        if ("addedAt".equals(gemElement.getNodeName())) {
          gem.setAddedAt(
              LocalDateTime.parse(
                  gemElement.getTextContent(),
                  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ROOT)));
        }
        if ("visualParameters".equals(gemElement.getNodeName())) {
          NodeList parametersList = gemElement.getChildNodes();
          for (int parametersIterator = 0;
              parametersIterator < parametersList.getLength();
              parametersIterator++) {
            Node parametersElement = parametersList.item(parametersIterator);
            if ("color".equals(parametersElement.getNodeName())) {
              visualParameters.setColor(Color.valueOf(parametersElement.getTextContent()));
            }
            if ("facesAmount".equals(parametersElement.getNodeName())) {
              visualParameters.setFacesAmount(Integer.parseInt(parametersElement.getTextContent()));
            }
            if ("transparency".equals(parametersElement.getNodeName())) {
              visualParameters.setTransparency(
                  Integer.parseInt(parametersElement.getTextContent()));
            }
          }
        }
        gem.setVisualParameters(visualParameters);
      }
      gems.add(gem);
    }
    return gems;
  }
}
