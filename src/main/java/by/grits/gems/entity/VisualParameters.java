package by.grits.gems.entity;

import by.grits.gems.entity.enums.Color;

public class VisualParameters {
  private Color color;
  private int transparency;
  private int facesAmount;

  public VisualParameters() {}

  public VisualParameters(Color color, int transparency, int facesAmount) {
    this.color = color;
    this.transparency = transparency;
    this.facesAmount = facesAmount;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public int getTransparency() {
    return transparency;
  }

  public void setTransparency(int transparency) {
    this.transparency = transparency;
  }

  public int getFacesAmount() {
    return facesAmount;
  }

  public void setFacesAmount(int facesAmount) {
    this.facesAmount = facesAmount;
  }
}
