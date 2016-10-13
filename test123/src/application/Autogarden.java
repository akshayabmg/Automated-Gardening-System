package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import auto.system.gardencontroller.Scheduler;
import auto.system.gardencontroller.Log_Sys;
import auto.system.gardenflowers.Jasmine;
import auto.system.gardenflowers.Lavendar;
import auto.system.gardenflowers.Flower;
import auto.system.gardenflowers.Rose;
import auto.system.gardensystems.Garden_ControlSystem;
import auto.system.gardensystems.Garden_Heater;
import auto.system.gardensystems.Garden_Sprinkler;

public class Autogarden {
	ArrayList<Flower> flowers;
	ArrayList<Garden_ControlSystem> garden_systems;
	Scheduler garden_scheduler;
	ArrayList<Garden_Sprinkler> Garden_Sprinklers;
	ArrayList<Garden_Heater> Garden_Heaters;
	
	
	public Autogarden() {
	
	 flowers= new ArrayList<Flower>();
	 garden_systems = new ArrayList<Garden_ControlSystem>();
	 Garden_Sprinklers =new ArrayList<Garden_Sprinkler>();
	 Garden_Heaters = new ArrayList<Garden_Heater>();
	 
	}

	public void Plant_add(Flower P){
		this.flowers.add(P);
	}
	public void ControlSystem_add(Garden_ControlSystem system){
		this.garden_systems.add(system);
		
	}
	private void Systems_start() {
		garden_scheduler= new Scheduler(garden_systems);
		for(Garden_ControlSystem system:garden_systems){
			system.Application_start();
		}
		
	}
	public void Game_start(){
		Systems_start();
		garden_scheduler.Application_start();
		 
	}

	public static void main(String[] args) throws SecurityException, IOException {	
		Autogarden autogarden=new Autogarden();
		 Log_Sys.init();
		 
		 
		 Rose rose= new Rose();
		 autogarden.Plant_add(rose);
		 
		 Lavendar lavendar= new Lavendar();
		 autogarden.Plant_add(lavendar);
		 
		 Jasmine jasmine= new Jasmine();
		 autogarden.Plant_add(jasmine);
		 
		 Garden_Sprinkler roseSprinkler= new Garden_Sprinkler(2);
		 roseSprinkler.setFlower(rose);
		 autogarden.ControlSystem_add(roseSprinkler);
		 
		 Garden_Heater roseHeater= new Garden_Heater(15,3);
		 roseHeater.setFlower(rose);
		 autogarden.ControlSystem_add(roseHeater);
		 
		 Garden_Sprinkler lavendarSprinkler= new Garden_Sprinkler(2);
		 lavendarSprinkler.setFlower(lavendar);
		 autogarden.ControlSystem_add(lavendarSprinkler);
		 
		 Garden_Heater lavendarHeater= new Garden_Heater(15,3);
		 lavendarHeater.setFlower(lavendar);
		 autogarden.ControlSystem_add(lavendarHeater);
		 
		 Garden_Sprinkler jasmineSprinkler= new Garden_Sprinkler(2);
		 jasmineSprinkler.setFlower(jasmine);
		 autogarden.ControlSystem_add(jasmineSprinkler);
		 
		 Garden_Heater jasmineHeater= new Garden_Heater(15,3);
		 jasmineHeater.setFlower(jasmine);
		 autogarden.ControlSystem_add(jasmineHeater);
		 
		 
		 autogarden.Game_start();
		 
		 

	}
	public void addGarden_Sprinkler(Garden_Sprinkler spr){
		this.Garden_Sprinklers.add(spr);
	}
	public Garden_Sprinkler getGarden_Sprinkler(int i){
		return this.Garden_Sprinklers.get(i);
	}
    public void addGarden_Heater(Garden_Heater spr){
    	this.Garden_Heaters.add(spr);
    }
    public Garden_Heater getGarden_Heater(int i){
    	return this.Garden_Heaters.get(i);
    }
    }
