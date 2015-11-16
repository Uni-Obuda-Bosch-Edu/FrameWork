package unitTests.PP;

import PP.VerticalScanner;
import unitTests.mock.DetectedObj;
import junit.framework.Assert;
import org.junit.Test;
import virtualDataBus.Container;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VerticalScannerTest {
    final double CAR_WIDTH_PX = 100;
    final double CAR_LENGTH_PX = 240;

    int _projectionDistance = 50;
    Point2D _sensorCenter;
    Point2D _correspondingCornerPosition;
    Container _bus;
    VerticalScanner _vScenner;

    @org.junit.Before
    public void Setup() {
        _bus = Container.getInstance();
        _vScenner = new VerticalScanner(_bus);
    }

    @Test
    public void Generate_projected_origo() {
        //Arrange
        _sensorCenter = new Point2D.Double(CAR_LENGTH_PX, CAR_LENGTH_PX + _projectionDistance + (CAR_WIDTH_PX / 2) + 10);
        _correspondingCornerPosition = new Point2D.Double(CAR_LENGTH_PX, _sensorCenter.getY() - (CAR_WIDTH_PX / 2));
        Point2D projectedPoint;

        //Act
        projectedPoint = _vScenner.GenerateProjectedOrigo(_sensorCenter, _correspondingCornerPosition, _projectionDistance);

        //Assert
        Assert.assertEquals(CAR_LENGTH_PX, projectedPoint.getX(), 0);
        Assert.assertEquals(CAR_LENGTH_PX + 10, projectedPoint.getY(), 0);
    }

    private ArrayList<DetectedObj> CreateMap(int...line) {
        ArrayList<DetectedObj> map = new ArrayList<>();

        double offset = CAR_WIDTH_PX/2;
        for (int i = 0; i < line.length; i++) {

            double x = offset+i*CAR_WIDTH_PX;
            double y = CAR_LENGTH_PX / 2;

            if(line[i] == 1)
            {
                DetectedObj car = new DetectedObj(i+1,x,y, DetectedObj.Orientation.Vertical);
                map.add(car);

                System.out.print("Car"+car.getId()+" ("+(x-offset)+"-"+(x+offset)+") added\r\n");
            }
            else
                System.out.print("Space ("+(x-offset)+"-"+(x+offset)+") added\r\n");
        }

        return map;
    }
}
