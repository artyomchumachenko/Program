package com.company.dz.three.animals;

public class Horse extends Animal {
    private boolean canParticipateInRace = true;

    public Horse(String food, String location) {
        super(food, location);
    }

    public void makeNoise() {
        System.out.println("Стучит копытом по tabley");
    }

    public void eat() {
        System.out.println("съел всё сено_О");
    }
}
