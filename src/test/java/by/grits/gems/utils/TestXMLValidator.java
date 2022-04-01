package by.grits.gems.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestXMLValidator {
  private XMLValidator validator;

  @BeforeEach
  public void setup() {
    validator = new XMLValidator();
  }

  @Test
  void shouldNotValidateXML() {
    boolean result =
        validator.validateXMLSchema(
            "src/main/resources/gems1.xsd", "src/test/resources/notValidXML.xml");
    Assertions.assertFalse(result);
  }

  @Test
  void shouldValidateXML() {
    boolean result =
        validator.validateXMLSchema(
            "src/main/resources/gems1.xsd", "src/test/resources/testGems1.xml");
    Assertions.assertTrue(result);
  }
}
