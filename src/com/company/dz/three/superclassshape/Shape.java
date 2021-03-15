package com.company.dz.three.superclassshape;

public abstract class Shape {
    private String color;

    public Shape(String color) {
        this.color = color;
    }

    public abstract void draw();

    public boolean equals(Shape firstShape, Shape secondShape) {
        return firstShape.color == secondShape.color;
    }

    public String getColor() {
        return color;
    }
}
