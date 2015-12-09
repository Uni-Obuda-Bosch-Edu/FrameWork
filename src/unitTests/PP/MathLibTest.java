package unitTests.PP;

import PP.MathLib;
import org.junit.Assert;
import org.junit.Test;

import java.awt.geom.Point2D;

public class MathLibTest {

    @Test
    public void Create_vector_without_multiplier() {
        //Arrange
        Point2D start = new Point2D.Double(250, 350);
        Point2D end = new Point2D.Double(250, 300);
        Point2D result;

        //Act
        result = MathLib.ToVector(start, end);

        //Assert
        Assert.assertEquals(0, result.getX(), 0);
        Assert.assertEquals(-50, result.getY(), 0);
    }

    @Test
    public void Create_vector_with_multiplier() {
        //Arrange
        Point2D start = new Point2D.Double(250, 350);
        Point2D end = new Point2D.Double(250, 300);
        Point2D result;

        //Act
        result = MathLib.ToVector(start, end, 2);

        //Assert
        Assert.assertEquals(0, result.getX(), 0);
        Assert.assertEquals(-100, result.getY(), 0);
    }

    @Test
    public void Transform_point_with_vector() {
        //Arrange
        Point2D start = new Point2D.Double(100, 100);
        Point2D vector = new Point2D.Double(-10, -10);
        Point2D result;

        //Act
        result = MathLib.TransformPointWithVector(start, vector);

        //Assert
        Assert.assertEquals(90, result.getX(), 0);
        Assert.assertEquals(90, result.getY(), 0);
    }

    @Test
    public void Rotate_point_with_90_degree() {
        //Arrange
        int alpha = 90;
        Point2D point = new Point2D.Double(10, 10);
        Point2D referencePoint = new Point2D.Double(0, 0);
        Point2D result;

        //Act
        result = MathLib.RotatePointCCW(point, referencePoint, alpha);

        //Assert
        Assert.assertEquals(-10, MathLib.Round(result.getX(),0), 0);
        Assert.assertEquals(10, MathLib.Round(result.getY(), 0), 0);
    }
}
