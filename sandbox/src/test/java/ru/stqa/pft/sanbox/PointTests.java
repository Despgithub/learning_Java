package ru.stqa.pft.sanbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testPoint() {
        Point d = new Point(8, -1, 4, 2);
        Assert.assertEquals(d.distance(), 5.0);
        Point d1 = new Point(2, -5, -4, 3);
        Assert.assertEquals(d1.distance(), 10.0);
        Point d2 = new Point(2, 6, -5, 6);
        Assert.assertEquals(d2.distance(), 7.0);
    }
}
