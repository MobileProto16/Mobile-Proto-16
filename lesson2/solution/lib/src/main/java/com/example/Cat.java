package com.example;

import java.util.Random;

/**
 * Created by abrahams on 6/22/16.
 */
public class Cat extends Animal {

    public Cat(String name, String color) {
        super(4, (new Random()).nextInt(26), name, color, "Cat");
    }

    public void grow() {
        this.setWeight(this.getWeight() * 3);
    }

}
