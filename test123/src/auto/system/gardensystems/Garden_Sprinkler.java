package auto.system.gardensystems;

import java.util.Timer;
import java.util.TimerTask;

import auto.system.gardencontroller.Garden_Constants;
import auto.system.gardencontroller.Log_Sys;
import auto.system.gardencontroller.Events;
import auto.system.gardenflowers.Flower;
import auto.system.gardenflowers.FlowerFullyGrownException;

public class Garden_Sprinkler implements Garden_ControlSystem{
	private Flower flower;
	private boolean Today_sprinkler;
	private boolean Today_Rained; //TODO check for static modifier
	private TimerTask Task_Sprinkler;
	private int WaterAmount_Sprinkler;
	private Timer Life;
	
	
	public Garden_Sprinkler(int level) {
		this.WaterAmount_Sprinkler=level;
		
	}
	@Override
	public void setFlower(Flower p){
		this.flower=p;
		
		Task_Sprinkler = new TimerTask() {
			
			@Override
			public void run() {
				if(Today_Rained){
					Log_Sys.writeLog(1, " The Sprinkler for the flower "+flower.getName()+" is switched off due to rain ",Garden_Sprinkler.this);
					return;
				}
				if(Today_sprinkler){
					Log_Sys.writeLog(1, " The Sprinkler for the flower "+flower.getName()+" is switched off as it was sprinkled already",Garden_Sprinkler.this);
					return;
				}
				Log_Sys.writeLog(0, " The Sprinkler for the flower "+flower.getName()+" is switched on",Garden_Sprinkler.this);
				try {
					Today_sprinkler=true; 
					flower.flower_sprinkle(WaterAmount_Sprinkler);
					Log_Sys.writeLog(0, " The flower "+flower.getName()+" is sprinkled with " +WaterAmount_Sprinkler,Garden_Sprinkler.this);
					
				} catch (FlowerFullyGrownException e) {
					Log_Sys.writeLog(2, " The flower "+flower.getName()+" is fully Grown ." + e.getMessage(),Garden_Sprinkler.this.getFlower());					
				}
				Log_Sys.writeLog(0, " The Sprinkler for the flower "+flower.getName()+" is switched off",Garden_Sprinkler.this);
				Log_Sys.writeLog(0, " The flower "+flower.getName()+" is now " + flower.getFlowerStage(),Garden_Sprinkler.this.getFlower());
				
			}

			
		};
		
		
	}

	public void DayDefaults() {
		Today_Rained=false;
		Today_sprinkler=false;
		
	}
	@Override
	public void Application_start(){
		if(Life !=null){
			
			Life.schedule(Task_Sprinkler, Garden_Constants.Control_Systems_Delay, Garden_Constants.Duration_Day_In_Millis);
		}
		else{
			Life= new Timer(this.flower.getName());
			
			Life.schedule(Task_Sprinkler, Garden_Constants.Control_Systems_Delay, Garden_Constants.Duration_Day_In_Millis);
		}
		Log_Sys.writeLog(0, " The Sprinkler for the flower "+flower.getName()+" is started ",Garden_Sprinkler.this);
	}
	
	public void quit(){
		if(Life!=null){
			Life.cancel();
			Log_Sys.writeLog(0, " The Sprinkler for the flower "+flower.getName()+" is gone",Garden_Sprinkler.this);
			Life=null;
		}
	}
	@Override
	public void notifyEvent(Events event,Object amount){
		if(event== Events.Rainfall){
			this.Today_Rained=true;
			Log_Sys.writeLog(0, " The Sprinkler for the flower "+flower.getName()+" is warned of rain",Garden_Sprinkler.this);
			if(Today_sprinkler){
				Log_Sys.writeLog(2, " The flower "+flower.getName()+" is Already sprinkled",Garden_Sprinkler.this.getFlower());
			}
			try {
				flower.flower_sprinkle((Integer)amount);
				Log_Sys.writeLog(1, " The flower "+flower.getName()+" is sprinkled with" +amount +" (By rain)",Garden_Sprinkler.this.getFlower());
			} catch (FlowerFullyGrownException e) {
				Log_Sys.writeLog(2, " The flower "+flower.getName()+" is fully Grown." + e.getMessage(),Garden_Sprinkler.this.getFlower());					
			
		}
	}
		
		
	}
	public void Sprinkle_Manual(int amount,boolean isForced){
		if(isForced){
			Log_Sys.writeLog(1, " The Sprinkler for the flower "+flower.getName()+" is turned on manually ",Garden_Sprinkler.this.getFlower());
			try {
				this.Today_sprinkler=true;
				flower.flower_sprinkle(amount);
				
				Log_Sys.writeLog(0, " The flower "+flower.getName()+" is sprinkled with " +amount,Garden_Sprinkler.this);
				
			} catch (FlowerFullyGrownException e) {
				Log_Sys.writeLog(2, " The flower "+flower.getName()+" is in a dangerous state." + e.getMessage(),Garden_Sprinkler.this.getFlower());					
			}
		}
		else{
			if(Today_Rained){
				Log_Sys.writeLog(1, " The Sprinkler for the flower "+flower.getName()+" is swithced off due to rain ",Garden_Sprinkler.this);
				return;
			}
			if(Today_sprinkler){
				Log_Sys.writeLog(1, " The Sprinkler for the flower "+flower.getName()+" is switched off as sprinkled already",Garden_Sprinkler.this);
				return;
			}
			Log_Sys.writeLog(0, " The Sprinkler for the flower "+flower.getName()+" is swithced on",Garden_Sprinkler.this);
			try {
				this.Today_sprinkler=true;
				flower.flower_sprinkle(WaterAmount_Sprinkler);
				Log_Sys.writeLog(0, " The flower "+flower.getName()+" is sprinkled with " +WaterAmount_Sprinkler,Garden_Sprinkler.this);
			} catch (FlowerFullyGrownException e) {
				Log_Sys.writeLog(2, " The flower "+flower.getName()+" is critical." + e.getMessage(),Garden_Sprinkler.this.getFlower());					
			}
			Log_Sys.writeLog(0, " The Sprinkler for the flower "+flower.getName()+" is swithced off",Garden_Sprinkler.this);
			Log_Sys.writeLog(0, " The flower "+flower.getName()+" is now " + flower.getFlowerStage(),Garden_Sprinkler.this.getFlower());
		}
		
	}

	@Override
	public String Info() {		
		return "Sprinkler System for the flower "+this.flower.getName();
	}

	@Override
	public Flower getFlower() {
		return this.flower;
	}


	

	

}
