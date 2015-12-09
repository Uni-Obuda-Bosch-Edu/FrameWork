package PP;


import busInterface.*;

import java.awt.geom.Point2D;

public class VerticalScanner implements IScanner {

    private final IPP_Out _ppOut;

    public VerticalScanner(IPP_Out ppOut) {
        this._ppOut = ppOut;
    }

    @Override
    public void Scan(UltraSound_Out sensor) {
        //Project o1 point
        _ppOut.setParkingFoundOnLeft(false);
    }

    @Override
    public Point2D GenerateProjectedOrigo(Point2D sensorCenter, Point2D correspondingCornerPosition, int projectionDistance) {
        double sensorCornerDistance = sensorCenter.distance(correspondingCornerPosition);
        double co = projectionDistance / sensorCornerDistance;

        Point2D vector = MathLib.ToVector(sensorCenter, correspondingCornerPosition, co);

        return MathLib.TransformPointWithVector(correspondingCornerPosition,vector);
    }
}
