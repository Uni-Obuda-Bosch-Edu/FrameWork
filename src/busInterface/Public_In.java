package busInterface;

public interface Public_In {
	
	public double getSteeringWheelSignedPercentage();
	public double getBrakePedalPercentage();
	public double getGasPedalPercentage();
	public boolean getEngineToggleButtonState();
	
	public int getCurrentGear();
	public int getMaxGear();
	public int getShiftLeverPosition();
	
	public double getGearTorque();    
	public int getGearRevolution();  
	public int getGearMode();   
	
	public double getEngineTorque();
	public int getEngineRevolution();
	public double getWaterTemperature();
	public double getOilTemperature();
	public double getOilPressure(); 
	public int getServiceCode(); 
	
	public double getCenterOfXAxis();
	public double getCenterOfYAxis();
	public double getMotionVectorXWithLengthAsSpeedInKm();
	public double getMotionVectorYWithLengthAsSpeedInKm();
};
   
                   