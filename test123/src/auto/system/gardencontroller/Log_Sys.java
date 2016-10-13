package auto.system.gardencontroller;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import auto.system.gardenflowers.Flower;
import auto.system.gardenflowers.FlowerFullyGrownException;
import auto.system.gardensystems.Garden_ControlSystem;

public class Log_Sys {
	private static Logger logger,Logger_plant,Logger_system,Logger_errors;
	private static FileHandler File_Flower,File_System,File_Errors;
	
	
	public static void init() throws SecurityException, IOException{
		if(logger==null){
			logger = Logger.getLogger("Gardening System");
			
			Logger_plant= Logger.getLogger("Log of Plant");
			File_Flower= new FileHandler(Garden_Constants.FileName_Plant);
			Logger_plant.addHandler(File_Flower);
			File_Flower.setFormatter(new SimpleFormatter());
			
			Logger_system= Logger.getLogger("Log of Sensor");
			File_System= new FileHandler(Garden_Constants.FileName_System);
			Logger_system.addHandler(File_System);
			File_System.setFormatter(new SimpleFormatter());
			
			Logger_errors= Logger.getLogger("Log of errors");
			File_Errors= new FileHandler(Garden_Constants.FileName_errors);
			Logger_errors.addHandler(File_Errors);
			File_Errors.setFormatter(new SimpleFormatter());
			
			
			
			
			
		}
	}
	
	public static void writeLog(int lvl,String msg,Object caller){
		Level level;
		switch(lvl){
		case 0:
			level= Level.INFO;
			break;
		case 1:
			level = Level.WARNING;
			break;
		case 2:
			level = Level.SEVERE;
			break;
		default:
			level = Level.ALL;
			break;
		}
		if(caller instanceof Flower){
			Logger_plant.log(level, msg);
		}
		
		else if(caller instanceof Scheduler){
			Logger_errors.log(level, msg);
		}
		else{
			Logger_system.log(level, msg);
		}
		
		
	}

}
