package unitTests.driverinput;

import busInterface.DriverInput_Out;
import busInterface.Public_In;
import virtualDataBus.Container.ShiftLeverPosition;

public class DummyBus implements Public_In, DriverInput_Out {

	private boolean buttonState;
	private double steeringWheelAngle;
	private double brakePedalAngle;
	private double gasPedalPercent;
	private ShiftLeverPosition shiftLeverPosition;

	@Override
	public void setEngineToggleButtonState(boolean buttonState) {
		this.buttonState = buttonState;
	}

	@Override
	public void setWheelRotationPercent(double steeringWheelAngle) {
		this.steeringWheelAngle = steeringWheelAngle;
	}

	@Override
	public void setBrakePedalPushPercent(double brakePedalAngle) {
		this.brakePedalAngle = brakePedalAngle;
	}

	@Override
	public void setGasPedalPushPercent(double gasPedalPercent) {
		this.gasPedalPercent = gasPedalPercent;
	}

	@Override
	public void setShiftLeverPosition(ShiftLeverPosition shiftLeverPosition) {
		this.shiftLeverPosition = shiftLeverPosition;
	}

	@Override
	public double getSteeringWheelSignedPercentage() {
		return steeringWheelAngle;
	}

	@Override
	public double getBrakePedalPercentage() {
		return brakePedalAngle;
	}

	@Override
	public double getGasPedalPercentage() {
		return gasPedalPercent;
	}

	@Override
	public boolean getEngineToggleButtonState() {
		return buttonState;
	}

	@Override
	public ShiftLeverPosition getShiftLeverPosition() {
		return shiftLeverPosition;
	}

	@Override
	public double getGearTorque() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGearRevolution() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGearMode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEngineRevolution() {
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

	@Override
	public int getIndexState() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getPPActivated() {
		// TODO Auto-generated method stub
		return false;
	}

}
