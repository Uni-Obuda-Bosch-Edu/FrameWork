package virtualDataBus;

import Wheels.Implementation.Wheels;
import busInterface.*;

public class Container implements Engine_Out, DriverInput_Out, Gearbox_Out, Wheels_Out, Public_In{
	
	public enum ShiftLeverPosition {Parking, Reverse, Neutral, Break}
	
	public static Container getInstance(){
		if(instance == null)
			instance = new Container();
		return instance;
	}

	public boolean getEngineToggleButtonState() {
		return EngineToggleButtonState;
	}

	public void setEngineToggleButtonState(boolean buttonState) {
		EngineToggleButtonState = buttonState;		
	}
	public double getSteeringWheelSignedPercentage() {
		return SteeringWheelAngle;
	}
	public void setWheelRotationPercent(double steeringWheelAngle) {
		SteeringWheelAngle = steeringWheelAngle;
	}
	
	public double getSteeringWheelMaxAngle() {
		return SteeringWheelMaxAngle;
	}

	public void setSteeringWheelMaxAngle(double steeringWheelMaxAngle) {
		SteeringWheelMaxAngle = steeringWheelMaxAngle;
	}
	public double getBrakePedalPercentage() {
		return BreakPedalPercent;
	}

	public void setBrakePedalPushPercent(double breakPedalAngle) {
		BreakPedalPercent = breakPedalAngle;
	}

	public double getGasPedalPercentage() {
		return GasPedalPercent;
	}

	public void setGasPedalPushPercent(double gasPedalAngle) {
		GasPedalPercent = gasPedalAngle;
	}

	public int getCurrentGear() {
		return CurrentGear;
	}

	public void setCurrentGear(int currentGear) {
		CurrentGear = currentGear;
	}

	public int getMaxGear() {
		return MaxGear;
	}

	public void setMaxGear(int maxGear) {
		MaxGear = maxGear;
	}

	public int getShiftLeverPosition() {
		return ShiftLeverPosition;
	}

	public void setShiftLeverPosition(int shiftLeverPosition) {
		ShiftLeverPosition = shiftLeverPosition;
	}

	public double getGearTorque() {
		return GearTorque;
	}

	public void setGearTorque(double gearTorque) {
		GearTorque = gearTorque;
	}

	public int getGearRevolution() {
		return GearRevolution;
	}

	public void setGearRevolution(int gearRevolution) {
		GearRevolution = gearRevolution;
	}

	public int getGearMode() {
		return GearMode;
	}

	public void setGearMode(int gearMode) {
		GearMode = gearMode;
	}

	public double getEngineTorque() {
		return EngineTorque;
	}

	public void setEngineTorque(double engineTorque) {
		EngineTorque = engineTorque;
	}

	public int getEngineRevolution() {
		return EngineRevolution;
	}

	public void setEngineRevolution(int engineRevolution) {
		EngineRevolution = engineRevolution;
	}

	public double getWaterTemperature() {
		return WaterTemperature;
	}

	public void setWaterTemperature(double waterTemperature) {
		WaterTemperature = waterTemperature;
	}

	public double getOilTemperature() {
		return OilTemperature;
	}

	public void setOilTemperature(double oilTemperature) {
		OilTemperature = oilTemperature;
	}

	public double getOilPressure() {
		return OilPressure;
	}

	public void setOilPressure(double oilPressure) {
		OilPressure = oilPressure;
	}

	public int getServiceCode() {
		return ServiceCode;
	}

	public void setServiceCode(int serviceCode) {
		ServiceCode = serviceCode;
	}

	@Override
	public double getCenterOfXAxis() {
		return CenterOfXAxis;
	}

	@Override
	public double getCenterOfYAxis() {
		return CenterOfYAxis;
	}

	@Override
	public double getMotionVectorXWithLengthAsSpeedInKm() {
		return MotionVectorXWithLengthAsSpeedInKm;
	}

	@Override
	public double getMotionVectorYWithLengthAsSpeedInKm() {
		return MotionVectorYWithLengthAsSpeedInKm;
	}

	@Override
	public void setCenterOfXAxis(double XPos) {
		CenterOfXAxis = XPos;
	}

	@Override
	public void setCenterOfYAxis(double YPos) {
		CenterOfYAxis = YPos;
	}

	@Override
	public void setMotionVectorXWithLengthAsSpeedInKm(double XSpeedVector) {
		MotionVectorXWithLengthAsSpeedInKm = XSpeedVector;
	}

	@Override
	public void setMotionVectorYWithLengthAsSpeedInKm(double YSpeedVector) {
		MotionVectorYWithLengthAsSpeedInKm = YSpeedVector;
	}
	
	
	/*DriverInput*/
	private double SteeringWheelAngle;
	private double SteeringWheelMaxAngle;
	private double BreakPedalPercent;
	private double GasPedalPercent;
	private int CurrentGear;
	private int MaxGear;
	private int ShiftLeverPosition;
	private boolean EngineToggleButtonState;
	
	/*Gearbox*/
	private double GearTorque;
	private int GearRevolution;
	private int GearMode;
	
	/*Engine*/
	private double EngineTorque;
	private int EngineRevolution;
	private double WaterTemperature;
	private double OilTemperature;
	private double OilPressure;
	private int ServiceCode;
	
	/*Wheels*/
	private double CenterOfXAxis;
	private double CenterOfYAxis;
	private double MotionVectorXWithLengthAsSpeedInKm;
	private double MotionVectorYWithLengthAsSpeedInKm;
	
	private Wheels Wheels;
	
	
	private static Container instance = null;
	
	
	private Container(){
		//do not instantiate; do not subclass;
		Wheels = new Wheels(10, this, this);
	}

	


}
