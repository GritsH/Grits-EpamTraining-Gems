package by.grits.Gems.entity;

import by.grits.Gems.entity.enums.Color;

public class VisualParameters {
    private Color color;
    private int transparency;
    private int facesAmount;

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
