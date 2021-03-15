package com.company.dz.three.animals;

public class Dog extends Animal {
    private String type = "Fighting";

    public Dog(String food, String location) {
        super(food, location);
    }

    public void makeNoise() {
        System.out.println("Я пёс и я говорю гау");
    }

    public void eat() {
        System.out.println("грызёт соседскую кошку");
    }
}
