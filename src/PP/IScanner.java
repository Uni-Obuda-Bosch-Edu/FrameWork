package PP;

import busInterface.*;

import java.awt.geom.Point2D;

public interface IScanner {

    void Scan(UltraSound_Out sensor);

    Point2D GenerateProjectedOrigo(Point2D sensorCenter, Point2D correspondingCornerPosition, int projectionDistance);
}
