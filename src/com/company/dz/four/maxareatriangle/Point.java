package com.company.dz.four.maxareatriangle;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static double calcLength(Point firstPoint, Point secondPoint) {
        return Math.sqrt(Math.pow(firstPoint.getX() - secondPoint.getX(), 2)
                + Math.pow(firstPoint.getY() - secondPoint.getY(), 2));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
