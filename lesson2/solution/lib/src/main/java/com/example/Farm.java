package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by abrahams on 6/22/16.
 */
public class Farm {

    private ArrayList<Animal> animals;

    public Farm() {
        this.animals = new ArrayList<>();
    }

    public void addAnimal(Animal a) {
        this.animals.add(a);
    }

    public Animal getAnimal(int i) {
        return animals.get(i);
    }

    public ArrayList<Animal> getHeaviestAnimals() {
        ArrayList<Animal> sorted = new ArrayList<>(animals);
        Collections.sort(sorted, new Comparator<Animal>() {
            @Override
            public int compare(Animal a1, Animal a2)
            {
                double diff = a1.getWeight() - a2.getWeight();
                if (diff < 0)
                    return 1;
                if (diff == 0)
                    return 0;
                return -1;
            }
        });
        return sorted;
    }

    public void printCatNames() {
        for (Animal a : animals) {
            if (a.getSpecies().toUpperCase().equals("CAT")) {
                System.out.println(a.getName());
            }
        }
    }

    public double averageLegs() {
        double total = 0;
        int count = 0;
        for (Animal a : animals) {
            count += 1;
            total += a.getLegs();
        }
        return total / count;
    }

    public static void main(String[] args) {
        Cat c = new Cat("Meowth", "black");
        Dog d = new Dog("Puppy", "brown");
        Cow cow = new Cow("Mooer", "white");
        System.out.println("Test 1 Passed: " + (c.getWeight() >= 0 && c.getWeight() <= 25));
        System.out.println("Test 2 Passed: " + (d.getWeight() >= 0 && d.getWeight() <= 25));
        System.out.println("Test 3 Passed: " + (cow.getWeight() >= 100 && cow.getWeight() <= 200));

        double old_weight_cat = c.getWeight();
        double old_weight_dog = d.getWeight();
        double old_weight_cow = cow.getWeight();
        c.grow();
        d.grow();
        cow.grow();
        System.out.println("Test 4 Passed: " + (c.getWeight() / old_weight_cat == 3));
        System.out.println("Test 5 Passed: " +
                (Math.abs(d.getWeight() / old_weight_dog - 1.5) < 0.01));
        System.out.println("Test 6 Passed: " + (cow.getWeight() / old_weight_cow == 5));

        Farm farm = new Farm();
        farm.addAnimal(c);
        farm.addAnimal(d);
        farm.addAnimal(cow);

        ArrayList<Animal> sorted = farm.getHeaviestAnimals();
        for(int i = 0; i < sorted.size() - 1; i++) {
            System.out.println("Test " + (i + 7) + " Passed: " + (sorted.get(i).getWeight() > sorted.get(i + 1).getWeight()));
        }
        System.out.println("Test 9 Passed: " + (farm.getAnimal(0) == c));
        System.out.println("Test 10 Passed: " + (farm.getAnimal(1) == d));
        System.out.println("Test 11 Passed: " + (farm.getAnimal(2) == cow));
        c.setLeg(7);  // lol 7 legged cat
        System.out.println("Test 12 Passed: " + (farm.averageLegs() == 5));

        System.out.println("Printing 'Meowth'...");
        farm.printCatNames();

    }
}


