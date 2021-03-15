package com.company.dz.three.animals;

public class Veterinar {
    public void treatAnimal(Animal animal) {
        System.out.println("Уровень трапезничества: " + animal.getFood() + "\nМесто: " + animal.getLocation() + "\n");
    }
}
