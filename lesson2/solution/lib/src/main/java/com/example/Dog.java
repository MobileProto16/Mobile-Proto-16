package com.example;

import java.util.Random;

/**
 * Created by abrahams on 6/22/16.
 */
public class Dog extends Animal {

    public Dog(String name, String color) {
        super(4, (new Random()).nextInt(26), name, color, "Dog");
    }

    public void grow() {
        this.setWeight(this.getWeight() * 1.5);
    }

}
