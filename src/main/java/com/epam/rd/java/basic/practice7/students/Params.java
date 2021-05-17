package com.epam.rd.java.basic.practice7.students;

import java.util.Objects;

public class Params {
    private double height;
    private double weight;
    private String colour;
    private static final String EOL = System.lineSeparator();

    public Params(){}

    public Params(double height, double weight, String colour){
        this.colour = colour;
        this.weight = weight;
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getColour() {
        return colour;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, weight, colour);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()){
            return false;
        }
        Params params = (Params) obj;
        return params.getHeight() == getHeight() && params.getWeight() == getWeight() &&
                params.getColour().hashCode() == getColour().hashCode();
    }

    @Override
    public String toString() {
        return  "Height: " + getHeight() + EOL +
                "Weight: " + getWeight() + EOL +
                "Colour: " + getColour();
    }
}
