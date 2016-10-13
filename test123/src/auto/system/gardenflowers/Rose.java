package auto.system.gardenflowers;

import auto.system.gardencontroller.Log_Sys;

public class Rose extends Flower {
	private int Water_Quantity;	
	private int waterlevel_inc=10;
	private int max_heat= 100;
	private int today_heat=0;
	private int temperature_max=20; 
	public Rose() {
		super();
		this.flower();
		this.Water_Quantity=0;
		today_heat=0;
	}
	
	@Override
	public String getName(){
		return "Rose";
	}
	
	
	@Override
	public void DayDefaults() {
		today_heat=0;
	};
	@Override
	public void flower_sprinkle(int amoutOfWater) throws FlowerFullyGrownException{
		if(this.getFlowerStage()==FlowerStage.Stage4_Flowered){
			Log_Sys.writeLog(0, " The Sprinkler for plant "+getName()+" is switched off ",Rose.this);
			return;
		}
		Water_Quantity+=amoutOfWater;
		WaterQuantity(amoutOfWater);
		
	}

	private void WaterQuantity(int amt) throws FlowerFullyGrownException {
		
		if(Water_Quantity>=waterlevel_inc){
			try {
				this.grow();
				Water_Quantity/=waterlevel_inc;
			} catch (FlowerFullyGrownException e) {
				e.printStackTrace();
				Water_Quantity+=amt;
				throw e;
			}
			
		}
		
	}
	@Override
	public void flower_heat(int Duration_heater, int Temp_heater) throws FlowertOverHeatedException {
		if(Duration_heater*Temp_heater>=max_heat){
			today_heat+=(Duration_heater*Temp_heater);
			throw new FlowertOverHeatedException();
		}
		else{
			today_heat+=(Duration_heater*Temp_heater);
		}
	}

	@Override
	public int get_MaxHeatTemp() {
		return this.temperature_max;
	}

	
}
