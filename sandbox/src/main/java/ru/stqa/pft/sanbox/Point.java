package ru.stqa.pft.sanbox;

public class Point {
    double x, y;

    public Point(double x1, double y1) {
        this.x = x1;
        this.y = y1;
    }

    public double distance(Point p2) {
        return Math.sqrt((p2.x - this.x) * (p2.x - this.x) + (p2.y - this.y) * (p2.y - this.y));
    }

    public static void main(String[] args) {
        Point p1 = new Point(8, -1);
        Point p2 = new Point(4, 2);
        System.out.println("Расстояние между точками p1 и p2 = " + p1.distance(p2));
    }
}


