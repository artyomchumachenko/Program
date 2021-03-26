package com.company.dz.mindistpoint;

public class Point {

    private int x;
    private int y;
    private int z;

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static double distCalculation(Point firstPoint, Point secondPoint) {
        return Math.sqrt(Math.pow(firstPoint.getX() - secondPoint.getX(), 2) + Math.pow(firstPoint.getY()
                - secondPoint.getY(), 2) + Math.pow(firstPoint.getZ() - secondPoint.getZ(), 2));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public String getAll() {
        StringBuffer outPoint = new StringBuffer(String.format("(%d, %d, %d)", x, y, z));
        return outPoint.toString();
    }
}
