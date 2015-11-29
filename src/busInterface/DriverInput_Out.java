package busInterface;

import virtualDataBus.Container.ShiftLeverPosition;

public interface DriverInput_Out {
	
	public void setEngineToggleButtonState(boolean buttonState);
	public void setWheelRotationPercent(double steeringWheelAngle);
	public void setBrakePedalPushPercent(double brakePedalAngle);
	public void setGasPedalPushPercent(double gasPedalPercent);
	public void setShiftLeverPosition(ShiftLeverPosition shiftLeverPosition);
	
}
