package ru.stqa.pft.sanbox;

public class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p2) {
        return Math.sqrt((p2.x - this.x) * (p2.x - this.x) + (p2.y - this.y) * (p2.y - this.y));
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
    }
}

class PointStart {
    public static void main(String[] args) {
        Point p1 = new Point(8, -1);
        Point p2 = new Point(4, 2);
        System.out.println("Расстояние между точками p1 и p2, вычисленное функцией = " + Point.distance(p1, p2));
        System.out.println("Расстояние между точками p1 и p2, вычисленное методом = " + p1.distance(p2));
    }
}


