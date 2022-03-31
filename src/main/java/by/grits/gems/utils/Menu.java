package by.grits.gems.utils;

import by.grits.gems.parsers.DOMParser;
import by.grits.gems.parsers.SAXParserFile;
import by.grits.gems.parsers.StAXParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Menu {
  private static final Logger LOGGER = LogManager.getLogger();
  private final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
  private XMLValidator validator = new XMLValidator();

  public void showMainMenu() throws ParserConfigurationException, IOException, SAXException {
    String path = "src/main/resources/gems.xml";

    while (true) {
      LOGGER.info("1 - DOM");
      LOGGER.info("2 - StAX");
      LOGGER.info("3 - SAX");
      LOGGER.info("e - exit");

      switch (scanner.nextLine()) {
        case "1":
          DOMMenu(path);
          break;
        case "2":
          StAXMenu(path);
          break;
        case "3":
          SAXMenu(path);
          break;
        case "e":
          System.exit(0);
          break;
        default:
          LOGGER.info("no such command");
      }
    }
  }

  public void DOMMenu(String path) throws ParserConfigurationException, IOException, SAXException {
    if (validator.validateXMLSchema("src/main/resources/gems1.xsd", path)) {
      while (true) {
        LOGGER.info("1 - read file");
        LOGGER.info("2 - back");
        switch (scanner.nextLine()) {
          case "1":
            DOMParser domParser = new DOMParser();
            DataPrinter dataPrinter = new DataPrinter();
            dataPrinter.printData(domParser.readXMLDOMParser(path));
            break;
          case "2":
            showMainMenu();
            break;
          default:
            LOGGER.info("no such command");
        }
      }
    }
  }

  public void StAXMenu(String path) throws ParserConfigurationException, IOException, SAXException {
    if (validator.validateXMLSchema("src/main/resources/gems1.xsd", path)) {
      while (true) {
        LOGGER.info("1 - read file");
        LOGGER.info("2 - back");
        switch (scanner.nextLine()) {
          case "1":
            StAXParser stAXParser = new StAXParser();
            DataPrinter dataPrinter = new DataPrinter();
            dataPrinter.printData(stAXParser.readXMLSTAXParser(path));
            break;
          case "2":
            showMainMenu();
            break;
          default:
            LOGGER.info("no such command");
        }
      }
    }
  }

  public void SAXMenu(String path) throws ParserConfigurationException, IOException, SAXException {
    if (validator.validateXMLSchema("src/main/resources/gems1.xsd", path)) {
      while (true) {
        LOGGER.info("1 - read file");
        LOGGER.info("2 - back");
        switch (scanner.nextLine()) {
          case "1":
            SAXParserFile saxParserFile = new SAXParserFile();
            DataPrinter dataPrinter = new DataPrinter();
            dataPrinter.printData(saxParserFile.parseFile(path));
            break;
          case "2":
            showMainMenu();
            break;
          default:
            LOGGER.info("no such command");
        }
      }
    }
  }
}
