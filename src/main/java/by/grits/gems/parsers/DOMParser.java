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
  public List<Gem> readXMLDOMParser()
      throws ParserConfigurationException, IOException, SAXException {
    List<Gem> gems = new ArrayList<>();
    Gem gem;

    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    Document document = documentBuilder.parse(new File("src/main/resources/gems.xml"));
    document.getDocumentElement().normalize();

    NodeList gemList = document.getElementsByTagName("gem");
    for (int i = 0; i < gemList.getLength(); i++) {
      Node gemNode = gemList.item(i);

      if (gemNode.getNodeType() == Node.ELEMENT_NODE) {
        Element element = (Element) gemNode;

        gem = new Gem();
        gem.setId(Integer.parseInt(element.getAttribute("id")));
        gem.setName(element.getElementsByTagName("name").item(0).getTextContent());
        gem.setPreciousness(
            Preciousness.valueOf(
                element.getElementsByTagName("preciousness").item(0).getTextContent()));
        gem.setOrigin(element.getElementsByTagName("origin").item(0).getTextContent());
        if ("visualParameters".equals(element.getNodeName())) {
          VisualParameters visualParameters = new VisualParameters();
          NodeList visualParametersList = element.getChildNodes();
          for (int j = 0; j < visualParametersList.getLength(); j++) {
            Node parametersElement = visualParametersList.item(j);
            if ("color".equals(parametersElement.getNodeName())) {
              visualParameters.setColor(Color.valueOf(parametersElement.getTextContent()));
            } else if ("transparency".equals(parametersElement.getNodeName())) {
              visualParameters.setTransparency(
                  Integer.parseInt(parametersElement.getTextContent()));
            } else if ("facesAmount".equals(parametersElement.getNodeName())) {
              visualParameters.setFacesAmount(Integer.parseInt(parametersElement.getTextContent()));
            }
          }
          gem.setVisualParameters(visualParameters);
        }
        gem.setValue(
            Double.parseDouble(element.getElementsByTagName("value").item(0).getTextContent()));
        gem.setAddedAt(
            LocalDateTime.parse(
                String.valueOf(element.getElementsByTagName("addedAt").item(0).getTextContent()),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ROOT)));

        gems.add(gem);
      }
    }

    return gems;
  }
}
