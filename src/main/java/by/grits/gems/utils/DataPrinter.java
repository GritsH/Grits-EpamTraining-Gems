package by.grits.gems.utils;

import by.grits.gems.entity.Gem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DataPrinter {
  final Logger LOGGER = LogManager.getLogger(DataPrinter.class);

  public void printData(List<Gem> gems) {
    if (!gems.isEmpty()) {
      for (Gem gem : gems) {
        LOGGER.info("ID: " + gem.getId());
        LOGGER.info("Name: " + gem.getName());
        LOGGER.info("Preciousness: " + gem.getPreciousness());
        LOGGER.info("Origin: " + gem.getOrigin());
        LOGGER.info("Visual parameters:");
        LOGGER.info("\tColor: " + gem.getVisualParameters().getColor());
        LOGGER.info("\tTransparency: " + gem.getVisualParameters().getTransparency());
        LOGGER.info("\tFaces Amount: " + gem.getVisualParameters().getFacesAmount());
        LOGGER.info("Value: " + gem.getValue());
        LOGGER.info("Added at: " + gem.getAddedAt());
      }
    } else {
      LOGGER.warn("List is empty, sry");
    }
  }
}
