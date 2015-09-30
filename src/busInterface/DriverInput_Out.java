package busInterface;

public interface DriverInput_Out {
	
	public void setEngineToggleButtonState(boolean buttonState);
	public void setWheelRotationPercent(double steeringWheelAngle);
	public void setSteeringWheelMaxAngle(double steeringWheelMaxAngle);
	public void setBrakePedalPushPercent(double breakPedalAngle);
	public void setBrakePedalMaxAngle(double breakPedalMaxAngle);
	public void setGasPedalPushPercent(double gasPedalAngle);
	public void setGasPedalMaxAngle(double gasPedalMaxAngle);
	public void setCurrentGear(int currentGear);
	public void setMaxGear(int maxGear);
	public void setShiftLeverPosition(int shiftLeverPosition);
	
}
