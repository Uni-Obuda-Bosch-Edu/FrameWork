package driverinput;

import busInterface.DriverInput_Out;
import busInterface.Public_In;
import virtualDataBus.Container.ShiftLeverPosition;

public class DriverInput {
	private final static boolean DefaultEngineButtonState = false;
	private final static ShiftLeverPosition DefaultShiftLeverPosition = ShiftLeverPosition.Parking;
	private final static double DefaultSteeringWheelRotationPercent = 0.0;
	private final static double DefaultPedalPushPercent = 0.0;
	public final static int MaxSteeringWheelRotationPerDirection = 100;
	public final static int MaxPedalPushValue = 100;
	
	private Public_In in;
	private DriverInput_Out out;

	public DriverInput(Public_In in, DriverInput_Out out) {
		this.in = in;
		this.out = out;
		out.setEngineToggleButtonState(DefaultEngineButtonState);
		out.setShiftLeverPosition(DefaultShiftLeverPosition);
		out.setWheelRotationPercent(DefaultSteeringWheelRotationPercent);
		out.setGasPedalPushPercent(DefaultPedalPushPercent);
		out.setBrakePedalPushPercent(DefaultPedalPushPercent);
	}

	public void pushEngineToggleButton() {
		boolean currentState = in.getEngineToggleButtonState();
		if (currentState == DefaultEngineButtonState && in.getShiftLeverPosition() != ShiftLeverPosition.Parking)
			return;
		out.setEngineToggleButtonState(!currentState);
	}

	public void turnSteeringWheelLeft(double percent) {
		double currentRotation = in.getSteeringWheelSignedPercentage();
		currentRotation -= Math.min(Math.abs(percent), MaxSteeringWheelRotationPerDirection + currentRotation);
		out.setWheelRotationPercent(currentRotation);
	}

	public void turnSteeringWheelRight(double percent) {
		double currentPosition = in.getSteeringWheelSignedPercentage();
		currentPosition += Math.min(Math.abs(percent), MaxSteeringWheelRotationPerDirection - currentPosition);
		out.setWheelRotationPercent(currentPosition);
	}

	public void pushGasPedal(double percent) {
		double currentPosition = in.getGasPedalPercentage();
		currentPosition += Math.min(Math.abs(percent), MaxPedalPushValue - currentPosition);
		out.setGasPedalPushPercent(currentPosition);
	}

	public void releaseGasPedal(double percent) {
		double currentPosition = in.getGasPedalPercentage();
		currentPosition -= Math.min(Math.abs(percent), currentPosition);
		out.setGasPedalPushPercent(currentPosition);
	}

	public void pushBrakePedal(double percent) {
		double currentPosition = in.getBrakePedalPercentage();
		currentPosition += Math.min(Math.abs(percent), MaxPedalPushValue - currentPosition);
		out.setBrakePedalPushPercent(currentPosition);
	}

	public void releaseBrakePedal(double percent) {
		double currentPosition = in.getBrakePedalPercentage();
		currentPosition -= Math.min(Math.abs(percent), currentPosition);
		out.setBrakePedalPushPercent(currentPosition);
	}

	public void moveShiftLeverTo(ShiftLeverPosition position) {
		if (in.getBrakePedalPercentage() != MaxPedalPushValue)
			return;
		out.setShiftLeverPosition(position);
	}
}
