package by.grits.gems.parsers;

import by.grits.gems.entity.Gem;
import by.grits.gems.entity.VisualParameters;
import by.grits.gems.entity.enums.Color;
import by.grits.gems.entity.enums.Preciousness;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestStAXParser {
  Gem gem1;
  Gem gem2;
  Gem gem3;
  private StAXParser staxParser;
  private List<Gem> expectedGems;

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

    staxParser = new StAXParser();
    expectedGems = new ArrayList<>();
  }

  @Test
  void shouldParseXMLWithThree() {
    expectedGems.add(gem1);
    expectedGems.add(gem2);
    expectedGems.add(gem3);

    List<Gem> resultGems = staxParser.readXMLSTAXParser("src/test/resources/testGems3.xml");
    for (int gemIndx = 0; gemIndx < resultGems.size(); gemIndx++) {
      Gem expectedGem = expectedGems.get(gemIndx);
      Gem resultGem = resultGems.get(gemIndx);

      assertEquals(expectedGem.getId(), resultGem.getId());
      assertEquals(expectedGem.getName(), resultGem.getName());
      assertEquals(expectedGem.getValue(), resultGem.getValue());
      assertEquals(expectedGem.getOrigin(), resultGem.getOrigin());
      assertEquals(expectedGem.getAddedAt(), resultGem.getAddedAt());
      assertEquals(expectedGem.getPreciousness(), resultGem.getPreciousness());
      assertEquals(
          expectedGem.getVisualParameters().getColor(), resultGem.getVisualParameters().getColor());
    }
  }

  @Test
  void shouldParseXMLWithOne() {
    expectedGems.add(gem1);

    List<Gem> resultGems = staxParser.readXMLSTAXParser("src/test/resources/testGems1.xml");
    for (int gemIndx = 0; gemIndx < resultGems.size(); gemIndx++) {
      Gem expectedGem = expectedGems.get(gemIndx);
      Gem resultGem = resultGems.get(gemIndx);

      assertEquals(expectedGem.getId(), resultGem.getId());
      assertEquals(expectedGem.getName(), resultGem.getName());
      assertEquals(expectedGem.getValue(), resultGem.getValue());
      assertEquals(expectedGem.getOrigin(), resultGem.getOrigin());
      assertEquals(expectedGem.getAddedAt(), resultGem.getAddedAt());
      assertEquals(expectedGem.getPreciousness(), resultGem.getPreciousness());
      assertEquals(
          expectedGem.getVisualParameters().getColor(), resultGem.getVisualParameters().getColor());
    }
  }

  @Test
  void shouldParseEmptyXML() {
    List<Gem> resultGems = staxParser.readXMLSTAXParser("src/test/resources/testGems0.xml");
    assertEquals(expectedGems.size(), resultGems.size());
  }
}
