package com.example;

/**
 * Created by abrahams on 6/22/16.
 */
public abstract class Animal {

    private int legs;
    private double weight;
    private String name, color, species;

    public Animal(int legs, double weight, String name, String color, String species) {
        this.legs = legs;
        this.weight = weight;
        this.name = name;
        this.color = color;
        this.species = species;
    }

    public int getLegs() {
        return legs;
    }

    public void setLeg(int leg) {
        this.legs = leg;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public abstract void grow();
}
