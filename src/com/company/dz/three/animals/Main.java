package com.company.dz.three.animals;

public class Main {

    public static void main(String[] args) {
        final int NUMBER_OF_ANIMALS = 3;
        Animal[] animals = new Animal[NUMBER_OF_ANIMALS];
        animals[0] = new Cat("наелся", "балкон");
        animals[1] = new Dog("не хочет трапезничать", "гуляет");
        animals[2] = new Horse("хочет еще сено_О", "в гараже");
        Veterinar veterinar = new Veterinar();
        for (Animal animal : animals) {
            veterinar.treatAnimal(animal);
        }
        animals[0].makeNoise();
        animals[1].eat();
        animals[2].sleep();
    }
}
