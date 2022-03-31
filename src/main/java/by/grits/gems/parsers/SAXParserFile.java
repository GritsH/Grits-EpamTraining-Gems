package by.grits.gems.parsers;

import by.grits.gems.entity.Gem;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SAXParserFile {

  public List<Gem> parseFile(String path)
      throws ParserConfigurationException, SAXException, IOException {
    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    SAXParser parser = saxParserFactory.newSAXParser();
    SAXHandler saxHandler = new SAXHandler();
    parser.parse(new File(path), saxHandler);
    return saxHandler.getGems();
  }
}
