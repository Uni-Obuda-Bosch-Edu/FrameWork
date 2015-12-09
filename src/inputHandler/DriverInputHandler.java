package inputHandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import driverinput.DriverInput;
import virtualDataBus.Container.ShiftLeverPosition;

/*
 * Controls:
 * 		E: toggle engine
 * 		P/R/N/D: change shift lever position
 * 		Left/Right: turn steering wheel
 * 		Up/Down:
 * 			holding G: push/release gas pedal (releases brake)
 * 			holding B: push/release brake pedal (releases gas)
 * 		Space:  emergency brake (release gas, push brake)
 */
public class DriverInputHandler implements KeyListener {
	private final static double movementUnit = 2.0;
	private boolean holdingGasModKey = false;
	private boolean holdingBrakeModKey = false;
	private DriverInput driverInput;
	private InputVisualizer inputVisualizer;
	
	public DriverInputHandler(DriverInput driverInput, InputVisualizer inputVisualizer) {
		this.driverInput = driverInput;
		this.inputVisualizer = inputVisualizer;
		inputVisualizer.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {
		case KeyEvent.VK_E:
			driverInput.pushEngineToggleButton();
			break;
		case KeyEvent.VK_G:
			holdingGasModKey = true;
			break;
		case KeyEvent.VK_B:
			holdingBrakeModKey = true;
			break;
		case KeyEvent.VK_UP:
			if (holdingGasModKey) {
				driverInput.releaseBrakePedal(DriverInput.MaxPedalPushValue);
				driverInput.pushGasPedal(movementUnit);
			} else if (holdingBrakeModKey) {
				driverInput.releaseGasPedal(DriverInput.MaxPedalPushValue);
				driverInput.pushBrakePedal(movementUnit);
			}
			break;
		case KeyEvent.VK_DOWN:
			if (holdingGasModKey)
				driverInput.releaseGasPedal(movementUnit);
			else if (holdingBrakeModKey)
				driverInput.releaseBrakePedal(movementUnit);
			break;
		case KeyEvent.VK_RIGHT:
			driverInput.turnSteeringWheelRight(movementUnit);
			break;
		case KeyEvent.VK_LEFT:
			driverInput.turnSteeringWheelLeft(movementUnit);
			break;
		case KeyEvent.VK_P:
			driverInput.moveShiftLeverTo(ShiftLeverPosition.Parking);
			break;
		case KeyEvent.VK_R:
			driverInput.moveShiftLeverTo(ShiftLeverPosition.Reverse);
			break;
		case KeyEvent.VK_N:
			driverInput.moveShiftLeverTo(ShiftLeverPosition.Neutral);
			break;
		case KeyEvent.VK_D:
			driverInput.moveShiftLeverTo(ShiftLeverPosition.Drive);
			break;
		case KeyEvent.VK_SPACE:
			driverInput.releaseGasPedal(DriverInput.MaxPedalPushValue);
			driverInput.pushBrakePedal(DriverInput.MaxPedalPushValue);
			break;
		default:
			return;
		}
		inputVisualizer.repaintInputs();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {
		case KeyEvent.VK_G:
			holdingGasModKey = false;
			break;
		case KeyEvent.VK_B:
			holdingBrakeModKey = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
