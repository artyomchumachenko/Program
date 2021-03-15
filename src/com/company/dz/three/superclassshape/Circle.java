package com.company.dz.three.superclassshape;

public class Circle extends Shape {
    private int xCenter;
    private int yCenter;
    private int radius;
    private String title = "Круг";

    public Circle(String color, int xCenter, int yCenter, int radius) {
        super(color);
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.radius = radius;
    }

    public boolean equals(Circle firstCircle, Circle secondCircle) {
        if (firstCircle.getColor() == secondCircle.getColor() && firstCircle.xCenter == secondCircle.xCenter &&
                firstCircle.yCenter == secondCircle.yCenter && firstCircle.radius == secondCircle.radius) {
            return true;
        } else {
            return false;
        }
    }

    public void draw() {
        System.out.println("Рисую " + title);
    }
}
