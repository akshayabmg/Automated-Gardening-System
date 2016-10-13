package auto.system.gardensystems;

import java.util.Timer;
import java.util.TimerTask;

import auto.system.gardencontroller.Garden_Constants;
import auto.system.gardencontroller.Log_Sys;
import auto.system.gardencontroller.Events;
import auto.system.gardenflowers.Flower;
import auto.system.gardenflowers.FlowerFullyGrownException;
import auto.system.gardenflowers.FlowertOverHeatedException;

public class Garden_Heater implements Garden_ControlSystem {
	private Flower flower;
	private boolean Today_Heater;
	private boolean Today_snowed; 
	private TimerTask Task_Heater;
	private int Temp_Heater;
	private int Duration_Heater;
	private Timer Heater_Life;
	

	public Garden_Heater(int temp,int duration) {
	this.Temp_Heater=temp;
	this.Duration_Heater=duration;
	}
	@Override
	public void DayDefaults() {
		Today_Heater=false;
		Today_snowed=false;

	}

	@Override
	public String Info() {
		return "Heater System for the flower "+this.flower.getName();
	}

	@Override
	public void Application_start() {
		{
			if(Heater_Life !=null){
				
				Heater_Life.schedule(Task_Heater, Garden_Constants.Control_Systems_Delay, Garden_Constants.Duration_Day_In_Millis);
			}
			else{
				Heater_Life= new Timer(this.flower.getName());
				
				Heater_Life.schedule(Task_Heater, Garden_Constants.Control_Systems_Delay, Garden_Constants.Duration_Day_In_Millis);
			}
			Log_Sys.writeLog(0, " The Heater for the flower "+flower.getName()+" is started ",Garden_Heater.this);
		}

	}

	@Override
	public void quit() {
		if(Heater_Life!=null){
			Heater_Life.cancel();
			Log_Sys.writeLog(0, " The Heater for the flower "+flower.getName()+" is gone",Garden_Heater.this);
			Heater_Life=null;
		}

	}

	@Override
	public Flower getFlower() {
		return this.flower;
	}

	@Override
	public void setFlower(Flower p) {
		if(Temp_Heater>p.get_MaxHeatTemp()){
			Log_Sys.writeLog(2, " The flower "+flower.getName()+" cannot withstand the temperature",Garden_Heater.this.getFlower() );					
		}
		this.flower=p;
		Task_Heater= new TimerTask() {
			
			@Override
			public void run() {
				if(Today_snowed){
					Log_Sys.writeLog(1, " The Heater for the flower "+flower.getName()+" is switched on for "+Duration_Heater+" due to random event-snow ",Garden_Heater.this);
					try {
						flower.flower_heat(Duration_Heater,Temp_Heater);
					} catch (FlowertOverHeatedException e) {
						Log_Sys.writeLog(2, " The flower "+flower.getName()+" is overheated." + e.getMessage(),Garden_Heater.this.getFlower());					
					}
				}
				if(Today_Heater){
					Log_Sys.writeLog(1, " The Heater for the flower "+flower.getName()+" is switched off because it is heated already for the day",Garden_Heater.this);
					return;
				}
				Log_Sys.writeLog(0, " The Heater for the flower "+flower.getName()+" is switched on",Garden_Heater.this);
				try {
					Today_Heater=true; 
					flower.flower_heat(Duration_Heater,Temp_Heater);
					Log_Sys.writeLog(0, " The flower "+flower.getName()+" is heated for " +Duration_Heater,Garden_Heater.this.getFlower());
					
				} catch (FlowertOverHeatedException e) {
					Log_Sys.writeLog(2, " The flower "+flower.getName()+" is heated in excess." + e.getMessage(),Garden_Heater.this);					
				}
				Log_Sys.writeLog(0, " The Heater for the flower "+flower.getName()+" is switched off",Garden_Heater.this);
				
			}
		};

	}

	@Override
	public void notifyEvent(Events event, Object amount) {
		if(event== Events.Snowfall){
			this.Today_snowed=true;
			Log_Sys.writeLog(0, " The Heater for the flower "+flower.getName()+" is warned of random event-snow",Garden_Heater.this);
			if(Today_Heater){
				Log_Sys.writeLog(2, " The flower "+flower.getName()+" is heated for the day due to snow.",Garden_Heater.this.getFlower());
				Today_Heater=false;
			}
		
		
		}
	}
	
	public void Heater_Manual(int duration,int temp,boolean isForced){
		if(temp>flower.get_MaxHeatTemp()&& !isForced){
			Log_Sys.writeLog(2, "The flower "+flower.getName()+" cannot take this temperature",Garden_Heater.this.getFlower());
			return;
		}
		if(Today_Heater && isForced){
			Log_Sys.writeLog(1, "The flower "+flower.getName()+" is already heated",Garden_Heater.this);
			try {
				this.flower.flower_heat(duration, temp);
			} catch (FlowertOverHeatedException e) {
				Log_Sys.writeLog(2, " The flower "+flower.getName()+" cannot take excess of heat." + e.getMessage(),Garden_Heater.this.getFlower());					
			}
		}
		else{
			try {
				this.flower.flower_heat(duration, temp);
			} catch (FlowertOverHeatedException e) {
				Log_Sys.writeLog(2, " The flower "+flower.getName()+" cannot take excess of heat." + e.getMessage(),Garden_Heater.this.getFlower());					
			}
		}	
		
	}
}
