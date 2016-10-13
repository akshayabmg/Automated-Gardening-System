package auto.system.gardenflowers;
import auto.system.gardencontroller.Log_Sys;
public class Jasmine extends Flower {
	private int Quantity_water;	
	private int waterlevel_inc=6;
	private int max_heat= 100;
	private int today_heat=0;
	private int temperature_max=30; //todo try constant class
	public Jasmine() {
		super();
		this.flower();
		this.Quantity_water=0;
		today_heat=0;
	}
	
	@Override
	public String getName(){
		return "Jasmine";
	}
	
	
	@Override
	public void DayDefaults() {
		today_heat=0;
	};
	@Override
	public void flower_sprinkle(int amoutOfWater) throws FlowerFullyGrownException{
		if(this.getFlowerStage()==FlowerStage.Stage4_Flowered){
			Log_Sys.writeLog(0, " The Sprinkler for flower "+getName()+" is switched off ",Jasmine.this);
			return;
		}
		Quantity_water+=amoutOfWater;
		WaterQuantity(amoutOfWater);
		
	}

	private void WaterQuantity(int amt) throws FlowerFullyGrownException {
		
		if(Quantity_water>=waterlevel_inc){
			try {
				this.grow();
				Quantity_water/=waterlevel_inc;
			} catch (FlowerFullyGrownException e) {
				e.printStackTrace();
				Quantity_water+=amt;
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
		// TODO Auto-generated method stub
		return this.temperature_max;
	}

}
