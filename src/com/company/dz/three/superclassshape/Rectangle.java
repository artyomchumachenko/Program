package com.company.dz.three.superclassshape;

public class Rectangle extends Shape {
    private int x1;
    private int y1;
    private int x2Diagonal;
    private int y2Diagonal;
    private String title = "Прямоугольник";

    public Rectangle(String color, int x1, int y1, int x2Diagonal, int y2Diagonal) {
        super(color);
        this.x1 = x1;
        this.x2Diagonal = x2Diagonal;
        this.y1 = y1;
        this.y2Diagonal = y2Diagonal;
    }

    public boolean equals(Rectangle firstRect, Rectangle secondRect) {
        if (firstRect.getColor() == secondRect.getColor() && firstRect.x1 == secondRect.x1 && firstRect.x2Diagonal ==
                secondRect.x2Diagonal && firstRect.y1 == secondRect.y1 && firstRect.y2Diagonal == secondRect.y2Diagonal) {
            return true;
        } else {
            return false;
        }
    }

    public void draw() {
        System.out.println("Рисую " + title);
    }
}
