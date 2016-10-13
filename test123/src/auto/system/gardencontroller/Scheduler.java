package auto.system.gardencontroller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

import auto.system.gardensystems.Garden_ControlSystem;

public class Scheduler {
	private TimerTask Task_day;
	private Timer Timer_day;
	private ArrayList<Garden_ControlSystem> systems;
	private int Passed_Days=0;
	
	public Scheduler(ArrayList<Garden_ControlSystem> values) {
		this.systems=values;
		Task_day= new TimerTask() {				

			@Override
			public void run() {
			   		for(Garden_ControlSystem system:systems){
			   			
			   			if((Passed_Days+1)%5==0){
			   				Log_Sys.writeLog(0, "A brand new day with rain has started for "+ system.Info() ,Scheduler.this);
			   				system.notifyEvent(Events.Rainfall, (ThreadLocalRandom.current().nextInt(1,4)));
			   			}
			   			else if((Passed_Days+1)%4==0){
			   				Log_Sys.writeLog(0, "A brand new day with snow has started for "+ system.Info() ,Scheduler.this);
			   				system.notifyEvent(Events.Snowfall, (ThreadLocalRandom.current().nextInt(1,4)));
			   			
			   			}
			   			else{
			   				Log_Sys.writeLog(0, "A brand new normal day has started for "+ system.Info() ,Scheduler.this);
			   			}
			   			system.DayDefaults();
			   			Passed_Days++;
			   			
			   		}
			}
		};
	}
	public void Application_start(){
		if(Timer_day!=null){
			Timer_day.schedule(Task_day,0,Garden_Constants.Duration_Day_In_Millis);
		}
		else{
			
			Timer_day= new Timer("Newone");
			Timer_day.schedule(Task_day,0,Garden_Constants.Duration_Day_In_Millis);
		}
		Log_Sys.writeLog(0, "Start of Autogarden",Scheduler.this);
		
	}
	
	

}
