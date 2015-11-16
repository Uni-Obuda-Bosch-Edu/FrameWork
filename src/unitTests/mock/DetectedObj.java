package unitTests.mock;

public class DetectedObj{
    private final int _id;
    private final double _x;
    private final double _y;
    private final Orientation _vertical;

    public double getX() {
        return _x;
    }

    public double getY() {
        return _y;
    }

    public int getId() {
        return _id;
    }

    public enum Orientation{
        Vertical,
        Horizontal
    }

    public DetectedObj(int id,double x, double y, Orientation vertical) {
        this._id = id;
        this._x = x;
        this._y = y;
        this._vertical = vertical;
    }
}
