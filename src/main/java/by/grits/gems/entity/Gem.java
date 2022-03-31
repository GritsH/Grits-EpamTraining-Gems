package by.grits.gems.entity;

import by.grits.gems.entity.enums.Preciousness;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Objects;

@XmlRootElement(name = "Gems")
public class Gem {
  @XmlAttribute private int id;
  @XmlAttribute private String name;
  @XmlAttribute private Preciousness preciousness;
  @XmlAttribute private String origin;
  @XmlAttribute private VisualParameters visualParameters;
  @XmlAttribute private double value;
  @XmlAttribute private LocalDateTime addedAt;

  public Gem() {}

  public Gem(
      int id,
      String name,
      Preciousness preciousness,
      String origin,
      VisualParameters visualParametersList,
      double value,
      LocalDateTime addedAt) {
    this.id = id;
    this.name = name;
    this.preciousness = preciousness;
    this.origin = origin;
    this.visualParameters = visualParametersList;
    this.value = value;
    this.addedAt = addedAt;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Preciousness getPreciousness() {
    return preciousness;
  }

  public void setPreciousness(Preciousness preciousness) {
    this.preciousness = preciousness;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public VisualParameters getVisualParameters() {
    return visualParameters;
  }

  public void setVisualParameters(VisualParameters visualParameters) {
    this.visualParameters = visualParameters;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public LocalDateTime getAddedAt() {
    return addedAt;
  }

  public void setAddedAt(LocalDateTime addedAt) {
    this.addedAt = addedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Gem gem = (Gem) o;
    return id == gem.id
        && Double.compare(gem.value, value) == 0
        && Objects.equals(name, gem.name)
        && preciousness == gem.preciousness
        && Objects.equals(origin, gem.origin)
        && Objects.equals(visualParameters, gem.visualParameters)
        && Objects.equals(addedAt, gem.addedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, preciousness, origin, visualParameters, value, addedAt);
  }

  @Override
  public String toString() {
    return "Gem{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", preciousness="
        + preciousness
        + ", origin='"
        + origin
        + '\''
        + ", visualParametersList="
        + visualParameters
        + ", value="
        + value
        + ", addedAt="
        + addedAt
        + '}';
  }
}
