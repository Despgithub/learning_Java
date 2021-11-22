package ru.stqa.pft.sanbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testPoint1() {
        Point p1 = new Point(8, -1);
        Point p2 = new Point(4, 2);
        Assert.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testPoint2() {
        Point p1 = new Point(2, -5);
        Point p2 = new Point(-4, 3);
        Assert.assertEquals(p1.distance(p2), 10.0);
    }

    @Test
    public void testPoint3() {
        Point p1 = new Point(2, 6);
        Point p2 = new Point(-5, 6);
        Assert.assertEquals(p1.distance(p2), 7.0);
    }
}
