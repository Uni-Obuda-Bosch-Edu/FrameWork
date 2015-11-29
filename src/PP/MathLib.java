package PP;

import java.awt.geom.Point2D;

public final class MathLib {
    private MathLib() {
    }

    public static double Round(double value, int preceision){
        double co = Math.pow(10,preceision);
        return (double)Math.round(value * co) / co;
    }

    public static Point2D ToVector(Point2D start, Point2D end){
        return ToVector(start, end, 1);

    }

    public static Point2D ToVector(Point2D start, Point2D end, double multiplier){
        return new Point2D.Double((end.getX()-start.getX())*multiplier,(end.getY()-start.getY())*multiplier);

    }

    public static Point2D TransformPointWithVector(Point2D start, Point2D vector){
        return new Point2D.Double(start.getX()+ vector.getX(),start.getY()+vector.getY());
    }

    public static Point2D RotatePointCCW(Point2D pointToRoteate, Point2D referencePoint, int alpha){
        //x'=[(x-u)*cos(alfa)-(y-v)*sin(Alfa)]+u
        //y'=[(x-u)*sin(alfa)+(y-v)*cos(Alfa)]+v

        double x =
                (pointToRoteate.getX()-referencePoint.getX())
                *Math.cos(Math.toRadians(alpha))
                -(pointToRoteate.getY()-referencePoint.getY())
                *Math.sin(Math.toRadians(alpha))
                + referencePoint.getX();

        double y =
                (pointToRoteate.getX()-referencePoint.getX())
                *Math.sin(Math.toRadians(alpha))
                +(pointToRoteate.getY()-referencePoint.getY())
                *Math.cos(Math.toRadians(alpha))
                + referencePoint.getY();

        return new Point2D.Double(x,y);

    }
}
