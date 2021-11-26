package ru.stqa.pft.sanbox;

public class PointStart {
    public static void main(String[] args) {
        Point p1 = new Point(8, -1);
        Point p2 = new Point(4, 2);
        System.out.println("Расстояние между точками p1 и p2, вычисленное функцией = " + Point.distance(p1, p2));
        System.out.println("Расстояние между точками p1 и p2, вычисленное методом = " + p1.distance(p2));
    }
}
