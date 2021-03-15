package com.company.dz.three.animals;

public class Animal {
    private String food;
    private String location;

    public Animal(String food, String location) {
        this.food = food;
        this.location = location;
    }

    public void makeNoise() {
        System.out.println("Животное даёт голос");
    }

    public void eat() {
        System.out.println("Животное кушает");
    }

    public void sleep() {
        System.out.println("Животное спит");
    }

    public String getFood() {
        return food;
    }

    public String getLocation() {
        return location;
    }
}
