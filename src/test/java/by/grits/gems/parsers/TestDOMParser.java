package by.grits.gems.parsers;

import by.grits.gems.entity.Gem;
import by.grits.gems.entity.VisualParameters;
import by.grits.gems.entity.enums.Color;
import by.grits.gems.entity.enums.Preciousness;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TestDOMParser {
  Gem gem1;
  Gem gem2;
  Gem gem3;
  private DOMParser domParser;
  private List<Gem> gems;

  @BeforeEach
  public void setup() {
    gem1 =
        new Gem(
            1,
            "Quartz",
            Preciousness.PRECIOUS,
            "djdjdjdj",
            new VisualParameters(Color.BLACK, 10, 10),
            0.0,
            LocalDateTime.parse(
                "2022-03-30 11:11:11",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ROOT)));
    gem2 =
        new Gem(
            2,
            "dddd",
            Preciousness.SEMI_PRECIOUS,
            "hfhfhf",
            new VisualParameters(Color.BLACK, 10, 5),
            0.1,
            LocalDateTime.parse(
                "2022-03-30 11:11:11",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ROOT)));
    gem3 =
        new Gem(
            3,
            "qqqqqq",
            Preciousness.SEMI_PRECIOUS,
            "swswswsw",
            new VisualParameters(Color.BLACK, 15, 5),
            0.5,
            LocalDateTime.parse(
                "2022-03-30 11:11:11",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ROOT)));

    domParser = new DOMParser();
    gems = new ArrayList<>();
  }

  @Test
  void shouldParseXMLWithThree() throws ParserConfigurationException, IOException, SAXException {
    gems.add(gem1);
    gems.add(gem2);
    gems.add(gem3);

    List<Gem> resultGems = domParser.readXMLDOMParser("src/test/resources/testGems3.xml");
    for (int gemIterator = 0; gemIterator < resultGems.size(); gemIterator++) {
      Assertions.assertEquals(gems.get(gemIterator).getId(), resultGems.get(gemIterator).getId());
      Assertions.assertEquals(
          gems.get(gemIterator).getName(), resultGems.get(gemIterator).getName());
      Assertions.assertEquals(
          gems.get(gemIterator).getValue(), resultGems.get(gemIterator).getValue());
      Assertions.assertEquals(
          gems.get(gemIterator).getOrigin(), resultGems.get(gemIterator).getOrigin());
      Assertions.assertEquals(
          gems.get(gemIterator).getAddedAt(), resultGems.get(gemIterator).getAddedAt());
      Assertions.assertEquals(
          gems.get(gemIterator).getPreciousness(), resultGems.get(gemIterator).getPreciousness());
      Assertions.assertEquals(
          gems.get(gemIterator).getVisualParameters().getColor(),
          resultGems.get(gemIterator).getVisualParameters().getColor());
    }
  }

  @Test
  void shouldParseXMLWithOne() throws ParserConfigurationException, IOException, SAXException {
    gems.add(gem1);

    List<Gem> resultGems = domParser.readXMLDOMParser("src/test/resources/testGems1.xml");

    for (int gemIterator = 0; gemIterator < resultGems.size(); gemIterator++) {
      Assertions.assertEquals(gems.get(gemIterator).getId(), resultGems.get(gemIterator).getId());
      Assertions.assertEquals(
          gems.get(gemIterator).getName(), resultGems.get(gemIterator).getName());
      Assertions.assertEquals(
          gems.get(gemIterator).getValue(), resultGems.get(gemIterator).getValue());
      Assertions.assertEquals(
          gems.get(gemIterator).getOrigin(), resultGems.get(gemIterator).getOrigin());
      Assertions.assertEquals(
          gems.get(gemIterator).getAddedAt(), resultGems.get(gemIterator).getAddedAt());
      Assertions.assertEquals(
          gems.get(gemIterator).getPreciousness(), resultGems.get(gemIterator).getPreciousness());
      Assertions.assertEquals(
          gems.get(gemIterator).getVisualParameters().getColor(),
          resultGems.get(gemIterator).getVisualParameters().getColor());
    }
  }

  @Test
  void shouldParseEmptyXML() throws ParserConfigurationException, IOException, SAXException {
    List<Gem> resultGems = domParser.readXMLDOMParser("src/test/resources/testGems0.xml");
    Assertions.assertEquals(gems.size(), resultGems.size());
  }
}
