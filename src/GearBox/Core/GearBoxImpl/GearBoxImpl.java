package GearBox.Core.GearBoxImpl;



//import virtualDataBus.Container;
import GearBox.Test.*;
import busInterface.Gearbox_Out;
import busInterface.Public_In;



public class GearBoxImpl implements Public_In, Gearbox_Out {

	Mock_Container bus;

	private final double gearRatios[] = { -11.2961, 12.5848, 7.6574, 5.2469, 4.0080, 2.5109, 1.00 };
	
	static private class SpeedRanges{	// dimension in [m/s]
		static public double speed_0_lo = 0.0;
		static public double speed_0_up = 8.491666667;
		static public double speed_1_up = 13.955555556;
		static public double speed_2_up = 20.366666667;
		static public double speed_3_up = 26.661111111;
		static public double speed_4_up = 42.558333333;
	};
	
	private double BreakLimit_lo = 96.0;
	private double BreakLimit_hi = 100.0;
	
	private int DRIVE = 7;
	private int REVERSE = 10;
	private int NEUTRAL = 8;
	private int PARKING = 9;
	
	private int gearStatus;
	private double torque;
	private double revolution;

	@Override
	public void setGearTorque(double gearTorque) {
		shiftDrive();
		bus.setWheelTorqueInNewton(calcOutputTorque(gearTorque));
	}

	@Override
	public void setGearRevolution(int gearRevolution) {
		shiftDrive();
		bus.setWheelRevoltuion(calcOutputRevolution(gearRevolution));
	}

	private boolean breakPedalCheck(){
		double breakPedalPercentage = bus.getBrakePedalPercentage();
		if (breakPedalPercentage <= BreakLimit_hi && breakPedalPercentage >= BreakLimit_lo) {
			return true;
		}
			return false;
	}

	public void setGearMode(int gearMode) {	// nem varunk input parametert
		
		if (breakPedalCheck()) {
			if (isShiftLeverPositionD(bus.getShiftLeverPosition())) {
				ShiftLeverPositionD();
			} else if (isShiftLeverPositionN(bus.getShiftLeverPosition())) {
				ShiftLeverPositionN();
			} else if (isShiftLeverPositionP(bus.getShiftLeverPosition())) {
				ShiftLeverPositionP();
			} else if (isShiftLeverPositionR(bus.getShiftLeverPosition())) {
				ShiftLeverPositionR();
			}
		}
	}

	private boolean isShiftLeverPositionD(int ShiftLeverPosition) {
		return ShiftLeverPosition == DRIVE;
	}

	private boolean isShiftLeverPositionN(int ShiftLeverPosition) {
		return ShiftLeverPosition == NEUTRAL;
	}

	private boolean isShiftLeverPositionP(int ShiftLeverPosition) {
		return ShiftLeverPosition == PARKING;
	}

	private boolean isShiftLeverPositionR(int ShiftLeverPosition) {
		return ShiftLeverPosition == REVERSE;
	}

	private void ShiftLeverPositionD() {
		setGearTorque( bus.getEngineTorque());
		setGearRevolution(bus.getEngineRevolution());
	}

	private void ShiftLeverPositionN() {
		setGearTorque(0);
		setGearRevolution(0);
	}

	private void ShiftLeverPositionP() {
		setGearTorque(0);
		setGearRevolution(0);
	}

	private void ShiftLeverPositionR() {
		setGearTorque( bus.getEngineTorque());
		setGearRevolution(bus.getEngineRevolution());
	}

	private void shiftDrive() {
		double inputSpeed = bus.getCurrentGear();
		
		if (inputSpeed >= SpeedRanges.speed_0_lo && inputSpeed < SpeedRanges.speed_0_up) {
			gearStatus = 1;
		} else if (inputSpeed >= SpeedRanges.speed_0_up && inputSpeed < SpeedRanges.speed_1_up) {
			gearStatus = 2;
		} else if (inputSpeed >= SpeedRanges.speed_1_up && inputSpeed < SpeedRanges.speed_2_up) {
			gearStatus = 3;
		} else if (inputSpeed >= SpeedRanges.speed_2_up && inputSpeed < SpeedRanges.speed_3_up) {
			gearStatus = 4;
		} else if (inputSpeed >= SpeedRanges.speed_3_up && inputSpeed < SpeedRanges.speed_4_up) {
			gearStatus = 5;
		} else if (inputSpeed >= SpeedRanges.speed_4_up) {
			gearStatus = 6;
		} else if (inputSpeed < 0) {
			gearStatus = 0;
		}
	}

	private double calcOutputTorque(double gearTorque) {
		torque = gearTorque * gearRatios[gearStatus];
		return torque;
	}

	private double calcOutputRevolution(double Revolution) {
		revolution = (Revolution / gearRatios[gearStatus]);
		return revolution;
	}

	public GearBoxImpl() {
		bus = Mock_Container.getInstance();
		gearStatus = 0;

	}

	@Override
	public double getSteeringWheelSignedPercentage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBrakePedalPercentage() {
		return bus.getBrakePedalPercentage();
	}

	@Override
	public double getGasPedalPercentage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getEngineToggleButtonState() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCurrentGear() {
			return bus.getCurrentGear();

	}

	@Override
	public int getMaxGear() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getShiftLeverPosition() {
		
		return bus.getShiftLeverPosition();
	}

	@Override
	public double getGearTorque() {
		return torque;
	}

	@Override
	public int getGearRevolution() {
		return (int)revolution;
	}

	@Override
	public int getGearMode() {
		return gearStatus;
	}

	@Override
	public double getEngineTorque() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEngineRevolution() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWaterTemperature() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOilTemperature() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOilPressure() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getServiceCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getCenterOfXAxis() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getCenterOfYAxis() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMotionVectorXWithLengthAsSpeedInKm() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMotionVectorYWithLengthAsSpeedInKm() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWheelTorqueInNewton() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMaximumTorqueInNewton() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMaximumBrakeTorqueInNewton() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getFrictionalCoefficientOfBrakes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDiameterOfDriveAxesInMeters() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getLengthOfAxesInMeters() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDistanceBetweenAxesInMeters() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDiameterOfWheelsInMeters() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWidthOfWheelsInMeters() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDriveWheelStateZeroBasedDegree() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMaximumDriveWheelStateZeroBasedDegree() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMaximumWheelsTurnDegree() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTotalMassInKg() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getInnerFrictionalCoefficientInNewton() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*@Override
	public double getSteeringWheelAngle() {
		return bus.getSteeringWheelAngle();
	}

	@Override
	public double getSteeringWheelMaxAngle() {
		return bus.getSteeringWheelMaxAngle();
	}

	@Override
	public double getBrakePedalAngle() {
		return bus.getBrakePedalAngle();
	}

	@Override
	public double getBrakePedalMaxAngle() {
		return bus.getBrakePedalMaxAngle();
	}

	@Override
	public double getGasPedalAngle() {
		return bus.getGasPedalAngle();
	}

	@Override
	public double getGasPedalMaxAngle() {
		return bus.getGasPedalMaxAngle();
	}

	@Override
	public int getCurrentGear() {
		return bus.getCurrentGear();
	}

	@Override
	public int getMaxGear() {
		return bus.getMaxGear();
	}

	@Override
	public int getShiftLeverPosition() {
		return bus.getShiftLeverPosition();
	}

	@Override
	public double getGearTorque() {
		return bus.getGearTorque();
	}

	@Override
	public int getGearRevolution() {
		return bus.getGearRevolution();
	}

	@Override
	public int getGearMode() {
		return bus.getGearMode();
	}

	@Override
	public double getEngineTorque() {
		return bus.getEngineTorque();
	}

	@Override
	public int getEngineRevolution() {
		return bus.getEngineRevolution();
	}

	@Override
	public double getWaterTemperature() {
		return bus.getWaterTemperature();
	}

	@Override
	public double getOilTemperature() {
		return bus.getOilTemperature();
	}

	@Override
	public double getOilPressure() {
		return bus.getOilPressure();
	}

	@Override
	public int getServiceCode() {
		return bus.getServiceCode();
	}

	@Override
	public double getCenterOfXAxis() {
		return bus.getCenterOfXAxis();
	}

	@Override
	public double getCenterOfYAxis() {
		return bus.getCenterOfYAxis();
	}

	@Override
	public double getMotionVectorXWithLengthAsSpeedInKm() {
		return bus.getMotionVectorXWithLengthAsSpeedInKm();
	}

	@Override
	public double getMotionVectorYWithLengthAsSpeedInKm() {
		return getMotionVectorYWithLengthAsSpeedInKm();
	}

	@Override
	public double getWheelTorqueInNewton() {
		return bus.getWheelTorqueInNewton();
	}

	@Override
	public double getMaximumTorqueInNewton() {
		return bus.getMaximumBrakeTorqueInNewton();
	}

	@Override
	public double getFrictionalCoefficientOfBrakes() {
		return bus.getFrictionalCoefficientOfBrakes();
	}

	@Override
	public double getDiameterOfDriveAxesInMeters() {
		return bus.getDiameterOfDriveAxesInMeters();
	}

	@Override
	public double getLengthOfAxesInMeters() {
		return bus.getLengthOfAxesInMeters();
	}

	@Override
	public double getDistanceBetweenAxesInMeters() {
		return bus.getDistanceBetweenAxesInMeters();
	}

	@Override
	public double getDiameterOfWheelsInMeters() {
		return bus.getDiameterOfWheelsInMeters();
	}

	@Override
	public double getWidthOfWheelsInMeters() {
		return bus.getWidthOfWheelsInMeters();
	}

	@Override
	public double getDriveWheelStateZeroBasedDegree() {
		return bus.getDriveWheelStateZeroBasedDegree();
	}

	@Override
	public double getMaximumDriveWheelStateZeroBasedDegree() {
		return bus.getMaximumDriveWheelStateZeroBasedDegree();
	}

	@Override
	public double getMaximumWheelsTurnDegree() {
		return bus.getMaximumWheelsTurnDegree();
	}

	@Override
	public double getTotalMassInKg() {
		return bus.getTotalMassInKg();
	}

	@Override
	public double getInnerFrictionalCoefficientInNewton() {
		return bus.getInnerFrictionalCoefficientInNewton();
	}

	@Override
	public double getMaximumBrakeTorqueInNewton() {
		return bus.getMaximumBrakeTorqueInNewton();
	}*/

}