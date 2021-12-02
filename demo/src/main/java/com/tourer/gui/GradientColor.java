package com.tourer.gui;

import java.awt.Color;
import java.util.Objects;

public class GradientColor {
    Color mainColor;
    Color secondaryColor;


    public GradientColor() {
    }

    public GradientColor(Color mainColor, Color secondaryColor) {
        this.mainColor = mainColor;
        this.secondaryColor = secondaryColor;
    }

    public Color getMainColor() {
        return this.mainColor;
    }

    public void setMainColor(Color mainColor) {
        this.mainColor = mainColor;
    }

    public Color getSecondaryColor() {
        return this.secondaryColor;
    }

    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public GradientColor mainColor(Color mainColor) {
        setMainColor(mainColor);
        return this;
    }

    public GradientColor secondaryColor(Color secondaryColor) {
        setSecondaryColor(secondaryColor);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GradientColor)) {
            return false;
        }
        GradientColor gradientColor = (GradientColor) o;
        return Objects.equals(mainColor, gradientColor.mainColor) && Objects.equals(secondaryColor, gradientColor.secondaryColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainColor, secondaryColor);
    }

    @Override
    public String toString() {
        return "{" +
            " mainColor='" + getMainColor() + "'" +
            ", secondaryColor='" + getSecondaryColor() + "'" +
            "}";
    }

}
