package com.company.dz.three.animals;

public class Cat extends Animal {
    private String wool = "Spotty";

    public Cat(String food, String location) {
        super(food, location);
    }

    public void makeNoise() {
        System.out.println("Я котька и говорю мяв");
    }

    public void eat() {
        System.out.println("хавает Whiskas");
    }
}
