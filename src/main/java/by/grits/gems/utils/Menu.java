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

  public void showMainMenu() throws ParserConfigurationException, IOException, SAXException {

    while (true) {
      LOGGER.info("1 - DOM");
      LOGGER.info("2 - StAX");
      LOGGER.info("3 - SAX");
      LOGGER.info("e - exit");

      switch (scanner.nextLine()) {
        case "1":
          DOMMenu();
          break;
        case "2":
          StAXMenu();
          break;
        case "3":
          SAXMenu();
          break;
        case "e":
          System.exit(0);
          break;
        default:
          LOGGER.info("no such command");
      }
    }
  }

  public void DOMMenu() throws ParserConfigurationException, IOException, SAXException {
    while (true) {
      LOGGER.info("1 - read file");
      LOGGER.info("2 - back");
      switch (scanner.nextLine()) {
        case "1":
          DOMParser domParser = new DOMParser();
          DataPrinter dataPrinter = new DataPrinter();
          dataPrinter.printData(domParser.readXMLDOMParser());
          break;
        case "2":
          showMainMenu();
          break;
        default:
          LOGGER.info("no such command");
      }
    }
  }

  public void StAXMenu() throws ParserConfigurationException, IOException, SAXException {
    while (true) {
      LOGGER.info("1 - read file");
      LOGGER.info("2 - back");
      switch (scanner.nextLine()) {
        case "1":
          StAXParser stAXParser = new StAXParser();
          DataPrinter dataPrinter = new DataPrinter();
          dataPrinter.printData(stAXParser.readXMLSTAXParser());
          break;
        case "2":
          showMainMenu();
          break;
        default:
          LOGGER.info("no such command");
      }
    }
  }

  public void SAXMenu() throws ParserConfigurationException, IOException, SAXException {
    while (true) {
      LOGGER.info("1 - read file");
      LOGGER.info("2 - back");
      switch (scanner.nextLine()) {
        case "1":
          SAXParserFile saxParserFile = new SAXParserFile();
          DataPrinter dataPrinter = new DataPrinter();
          dataPrinter.printData(saxParserFile.parseFile("src/main/resources/gems.xml"));
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
