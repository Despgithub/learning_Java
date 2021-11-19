package ru.stqa.pft.sanbox;

public class Point {
    double x, y, x1, y1, x2, y2;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
    }

    public double distance() {
        return Math.sqrt((this.x2 - this.x1) * (this.x2 - this.x1) + (this.y2 - this.y1) * (this.y2 - this.y1));
    }

    public static void main(String[] args) {
        Point p1 = new Point(8, -1);
        Point p2 = new Point(4, 2);
        System.out.println("Расстояние между точками, вычисленное функцией = " + distance(p1, p2));

        Point d = new Point(8, -1, 4, 2);
        System.out.println("Расстояние между точками, вычисленное методом = " + d.distance());
    }
}


