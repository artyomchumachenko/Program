package com.company.dz.three.superclassshape;

public class Main {

    public static void main(String[] args) {
        final int NUMBER_OF_FORMS = 2;
        Shape[] shapes = new Shape[NUMBER_OF_FORMS];
        shapes[0] = new Circle("Red", 1, 2, 3);
        shapes[1] = new Rectangle("Red", 1, 2, 3, 6);
        for (Shape shape : shapes) {
            shape.draw();
        }
        System.out.println(shapes[0].equals(shapes[1]));
        System.out.println(shapes[0].equals(shapes[0]));
    }
}
