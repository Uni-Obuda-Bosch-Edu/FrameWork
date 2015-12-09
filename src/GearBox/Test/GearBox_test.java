package GearBox.Test;
import static org.junit.Assert.*;

import org.junit.Test;

import GearBox.Core.GearBoxImpl.GearBoxImpl;




public class GearBox_test {

	private Mock_Container bus = Mock_Container.getInstance();
	private GearBoxImpl gearBox = new GearBoxImpl();
	private int DRIVE = 7;
	private int REVERSE = 10;
	private double pushBreakPedal = 100.0;
	
	private double def_torque = 100;	// [Nm]
	private int def_revolution = (int)(3000/60); // [1/s]
	private double def_gear_ratio =  5.2469;
	private double def_gear_ratio_rev = -11.2961;
	private int speed = 0;	//[m/s]
	
	@Test
	public void testSetGearTorque() {
		bus.setEngineTorque(def_torque);
		bus.setEngineRevolution(def_revolution);
		
		bus.setBrakePedalPushPercent(pushBreakPedal);
		bus.setShiftLeverPosition(DRIVE);
		speed = 20;
		bus.setCurrentGear(speed);	
		gearBox.setGearMode(0);	// felesleges input
		
		double torque = gearBox.getGearTorque();
		double torq_calc = def_torque * def_gear_ratio;
		assertEquals((int)torque,(int)torq_calc);

	}
	@Test
	public void testSetGearTorque_reverse() {
		bus.setEngineTorque(def_torque);
		bus.setEngineRevolution(def_revolution);
		
		bus.setBrakePedalPushPercent(pushBreakPedal);
		bus.setShiftLeverPosition(REVERSE);	
		speed = -20;
		bus.setCurrentGear(speed);
		gearBox.setGearMode(0);	// felesleges input
		
		double torque = gearBox.getGearTorque();
		double torq_calc = (def_torque * (def_gear_ratio_rev));
		assertEquals((int)torque,(int)torq_calc);
	}

	@Test
	public void testSetGearRevolution() {
		bus.setEngineTorque(def_torque);
		bus.setEngineRevolution(def_revolution);
		
		bus.setBrakePedalPushPercent(pushBreakPedal);
		bus.setShiftLeverPosition(DRIVE);
		speed = 20;
		bus.setCurrentGear(speed);
		gearBox.setGearMode(0);	// felesleges input
		
		double revolution = gearBox.getGearRevolution();
				
		double rev_calc = def_revolution/ def_gear_ratio;
		assertEquals((int)revolution,(int)rev_calc);
	}
	@Test
	public void testSetGearRevolution_reverse() {
		bus.setEngineTorque(def_torque);
		bus.setEngineRevolution(def_revolution);
		
		bus.setBrakePedalPushPercent(pushBreakPedal);
		bus.setShiftLeverPosition(REVERSE);
		speed = -20;
		bus.setCurrentGear(speed);
		gearBox.setGearMode(0);	// felesleges input
		
		double revolution = gearBox.getGearRevolution();
	
		double rev_calc = (def_revolution / (def_gear_ratio_rev));
		assertEquals((int)revolution,(int)rev_calc);

	}

	@Test
	public void testGetShiftLeverPosition() {	// fokozatok vegigvaltasa
				
		bus.setBrakePedalPushPercent(pushBreakPedal);
		bus.setShiftLeverPosition(DRIVE);
	
		speed = 2;
		bus.setCurrentGear(speed);
		gearBox.setGearMode(0);	// felesleges input
		int mode = gearBox.getGearMode();
		assertEquals(1,mode);
		
		speed = 10;
		bus.setCurrentGear(speed);
		gearBox.setGearMode(0);	// felesleges input
		mode = gearBox.getGearMode();
		assertEquals(2,mode);
		
		speed = 15;
		bus.setCurrentGear(speed);
		gearBox.setGearMode(0);	// felesleges input
		mode = gearBox.getGearMode();
		assertEquals(3,mode);
		
		speed = 25;
		bus.setCurrentGear(speed);
		gearBox.setGearMode(0);	// felesleges input
		mode = gearBox.getGearMode();
		assertEquals(4,mode);
		
		speed = 30;
		bus.setCurrentGear(speed);
		gearBox.setGearMode(0);	// felesleges input
		mode = gearBox.getGearMode();
		assertEquals(5,mode);
		
		speed = 50;
		bus.setCurrentGear(speed);
		gearBox.setGearMode(0);	// felesleges input
		mode = gearBox.getGearMode();
		assertEquals(6,mode);
		
		bus.setBrakePedalPushPercent(pushBreakPedal);
		bus.setShiftLeverPosition(REVERSE);

		speed = -20;
		bus.setCurrentGear(speed);
		gearBox.setGearMode(0);	// felesleges input
		mode = gearBox.getGearMode();
		assertEquals(0,mode);
	}

	
	@Test
	public void testGetGearMode() {
		bus.setBrakePedalPushPercent(pushBreakPedal);
		bus.setShiftLeverPosition(DRIVE);

		speed = 2;
		bus.setCurrentGear(speed);
		gearBox.setGearMode(0);	// felesleges input
	
		int mode = gearBox.getGearMode();
		assertEquals(1,mode);
	}

}
