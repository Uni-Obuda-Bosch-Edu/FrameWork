package Wheels.Test;

import busInterface.Public_In;
import busInterface.Wheels_Out;

public class DummyBus implements Public_In, Wheels_Out{

	
	private double XPos;

	@Override
	public void setCenterOfXAxis(double XPos) {
		// TODO Auto-generated method stub
		this.XPos = XPos;
	}

	@Override
	public void setCenterOfYAxis(double YPos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMotionVectorXWithLengthAsSpeedInKm(double XSpeedVector) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMotionVectorYWithLengthAsSpeedInKm(double YSpeedVector) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getSteeringWheelSignedPercentage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBrakePedalPercentage() {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxGear() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getShiftLeverPosition() {
		// TODO Auto-generated method stub
		return 0;
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

}
