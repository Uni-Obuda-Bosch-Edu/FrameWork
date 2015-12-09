package unitTests.driverinput;

import java.util.Random;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;

import driverinput.DriverInput;
import virtualDataBus.Container;
import virtualDataBus.Container.ShiftLeverPosition;

public class DriverInputTest {

	private DummyBus bus;
	private DriverInput input;
	private final double defaultSteeringWheelRotation = 0.0;
	private final double maxSteeringWheelRotationPerDirection = 100.0;
	private final double minPedalPushPercent = 0.0;
	private final double maxPedalPushPercent = 100.0;
	private final double noMoveValue = 0.0;
	private final double noDifferenceAllowed = 0.0;
	private double randomInRangeValue;
	private double randomOutOfRangeValue;
	
	@org.junit.Before
	public void beforeTest() {
		bus = new DummyBus();
		input = new DriverInput(bus, bus);
		Random rand = new Random();
		randomInRangeValue = rand.nextInt(99) + 1;
		randomOutOfRangeValue = rand.nextInt(99) + 100;
	}

	@Test
	public void testPushEngineToggleButton_InParking() {
		bus.setEngineToggleButtonState(false);
		bus.setShiftLeverPosition(ShiftLeverPosition.Parking);
		input.pushEngineToggleButton();
		Assert.assertTrue(bus.getEngineToggleButtonState());
	}
	
	@Test
	public void testPushEngineToggleButton_NotInParking() {
		bus.setEngineToggleButtonState(false);
		bus.setShiftLeverPosition(ShiftLeverPosition.Neutral);
		input.pushEngineToggleButton();
		Assert.assertFalse(bus.getEngineToggleButtonState());
	}
	
	@Test
	public void testTurnSteeringWheelLeft_NoMove() {
		final double turnPercent = noMoveValue;
		bus.setWheelRotationPercent(defaultSteeringWheelRotation);
		input.turnSteeringWheelLeft(turnPercent);
		Assert.assertEquals(bus.getSteeringWheelSignedPercentage(), defaultSteeringWheelRotation, noDifferenceAllowed);
		
	}
	
	@Test
	public void testTurnSteeringWheelLeft_InRange() {
		final double turnPercent = randomInRangeValue;
		bus.setWheelRotationPercent(defaultSteeringWheelRotation);
		input.turnSteeringWheelLeft(turnPercent);
		Assert.assertEquals(bus.getSteeringWheelSignedPercentage(), -turnPercent, noDifferenceAllowed);
		
	}
	
	@Test
	public void testTurnSteeringWheelLeft_OutOfRange() {
		final double turnPercent = randomOutOfRangeValue;
		bus.setWheelRotationPercent(defaultSteeringWheelRotation);
		input.turnSteeringWheelLeft(turnPercent);
		Assert.assertEquals(bus.getSteeringWheelSignedPercentage(), -maxSteeringWheelRotationPerDirection, noDifferenceAllowed);
		
	}
	
	@Test
	public void testTurnSteeringWheelRight_NoMove() {
		final double turnPercent = noMoveValue;
		bus.setWheelRotationPercent(defaultSteeringWheelRotation);
		input.turnSteeringWheelRight(turnPercent);
		Assert.assertEquals(bus.getSteeringWheelSignedPercentage(), defaultSteeringWheelRotation, noDifferenceAllowed);
		
	}
	
	@Test
	public void testTurnSteeringWheelRight_InRange() {
		final double turnPercent = randomInRangeValue;
		bus.setWheelRotationPercent(defaultSteeringWheelRotation);
		input.turnSteeringWheelRight(turnPercent);
		Assert.assertEquals(bus.getSteeringWheelSignedPercentage(), turnPercent, noDifferenceAllowed);
		
	}
	
	@Test
	public void testTurnSteeringWheelRight_OutOfRange() {
		final double turnPercent = randomOutOfRangeValue;
		bus.setWheelRotationPercent(defaultSteeringWheelRotation);
		input.turnSteeringWheelRight(turnPercent);
		Assert.assertEquals(bus.getSteeringWheelSignedPercentage(), maxSteeringWheelRotationPerDirection, noDifferenceAllowed);
		
	}
	
	@Test
	public void testPushGasPedal_NoMove() {
		final double pushPercent = noMoveValue;
		bus.setGasPedalPushPercent(minPedalPushPercent);
		input.pushGasPedal(pushPercent);
		Assert.assertEquals(bus.getGasPedalPercentage(), minPedalPushPercent, noDifferenceAllowed);
	}
	
	@Test
	public void testPushGasPedal_InRange() {
		final double pushPercent = randomInRangeValue;
		bus.setGasPedalPushPercent(minPedalPushPercent);
		input.pushGasPedal(pushPercent);
		Assert.assertEquals(bus.getGasPedalPercentage(), pushPercent, noDifferenceAllowed);
	}
	
	@Test
	public void testPushGasPedal_OutOfRange() {
		final double pushPercent = randomOutOfRangeValue;
		bus.setGasPedalPushPercent(minPedalPushPercent);
		input.pushGasPedal(pushPercent);
		Assert.assertEquals(bus.getGasPedalPercentage(), maxPedalPushPercent, noDifferenceAllowed);
	}
	
	@Test
	public void testReleaseGasPedal_NoMove() {
		final double releasePercent = noMoveValue;
		bus.setGasPedalPushPercent(maxPedalPushPercent);
		input.releaseGasPedal(releasePercent);
		Assert.assertEquals(bus.getGasPedalPercentage(), maxPedalPushPercent, noDifferenceAllowed);
	}
	
	@Test
	public void testReleaseGasPedal_InRange() {
		final double releasePercent = randomInRangeValue;
		bus.setGasPedalPushPercent(maxPedalPushPercent);
		input.releaseGasPedal(releasePercent);
		Assert.assertEquals(bus.getGasPedalPercentage(), maxPedalPushPercent - releasePercent, noDifferenceAllowed);
	}
	
	@Test
	public void testReleaseGasPedal_OutOfRange() {
		final double releasePercent = randomOutOfRangeValue;
		bus.setGasPedalPushPercent(maxPedalPushPercent);	
		input.releaseGasPedal(releasePercent);
		Assert.assertEquals(bus.getGasPedalPercentage(), minPedalPushPercent, noDifferenceAllowed);
	}
	
	@Test
	public void testPushBrakePedal_NoMove() {
		final double pushPercent = noMoveValue;
		bus.setBrakePedalPushPercent(minPedalPushPercent);
		input.pushBrakePedal(pushPercent);
		Assert.assertEquals(bus.getBrakePedalPercentage(), minPedalPushPercent, noDifferenceAllowed);
	}
	
	@Test
	public void testPushBrakePedal_InRange() {
		final double pushPercent = randomInRangeValue;
		bus.setBrakePedalPushPercent(minPedalPushPercent);
		input.pushBrakePedal(pushPercent);
		Assert.assertEquals(bus.getBrakePedalPercentage(), pushPercent, noDifferenceAllowed);
	}
	
	@Test
	public void testPushBrakePedal_OutOfRange() {
		final double pushPercent = randomOutOfRangeValue;
		bus.setBrakePedalPushPercent(minPedalPushPercent);
		input.pushBrakePedal(pushPercent);
		Assert.assertEquals(bus.getBrakePedalPercentage(), maxPedalPushPercent, noDifferenceAllowed);
	}
	
	@Test
	public void testReleaseBrakePedal_NoMove() {
		final double releasePercent = noMoveValue;
		bus.setBrakePedalPushPercent(maxPedalPushPercent);
		input.releaseBrakePedal(releasePercent);
		Assert.assertEquals(bus.getBrakePedalPercentage(), maxPedalPushPercent, noDifferenceAllowed);
	}
	
	@Test
	public void testReleaseBrakePedal_InRange() {
		final double releasePercent = randomInRangeValue;
		bus.setBrakePedalPushPercent(maxPedalPushPercent);
		input.releaseBrakePedal(releasePercent);
		Assert.assertEquals(bus.getBrakePedalPercentage(), maxPedalPushPercent - releasePercent, noDifferenceAllowed);
	}
	
	@Test
	public void testReleaseBrakePedal_OutOfRange() {
		final double releasePercent = randomOutOfRangeValue;
		bus.setBrakePedalPushPercent(maxPedalPushPercent);	
		input.releaseBrakePedal(releasePercent);
		Assert.assertEquals(bus.getBrakePedalPercentage(), minPedalPushPercent, noDifferenceAllowed);
	}
	
	@Test
	public void testMoveShiftLeverTo_PushingBrake() {
		final ShiftLeverPosition newLeverPosition = ShiftLeverPosition.Drive;
		bus.setShiftLeverPosition(ShiftLeverPosition.Parking);
		bus.setBrakePedalPushPercent(maxPedalPushPercent);
		input.moveShiftLeverTo(newLeverPosition);
		Assert.assertEquals(bus.getShiftLeverPosition(), newLeverPosition);		
	}
	
	@Test
	public void testMoveShiftLeverTo_NotPushingBrake() {
		final ShiftLeverPosition startLeverPosition = ShiftLeverPosition.Parking;
		bus.setShiftLeverPosition(startLeverPosition);
		bus.setBrakePedalPushPercent(minPedalPushPercent);
		input.moveShiftLeverTo(ShiftLeverPosition.Drive);
		Assert.assertEquals(bus.getShiftLeverPosition(), startLeverPosition);	
	}
}
