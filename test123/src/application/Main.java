package application;
	
import java.io.IOException;

import auto.system.gardenView.*;
import auto.system.gardencontroller.Log_Sys;
import auto.system.gardenflowers.Jasmine;
import auto.system.gardenflowers.Lavendar;
import auto.system.gardenflowers.Rose;
import auto.system.gardensystems.Garden_Heater;
import auto.system.gardensystems.Garden_Sprinkler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private static Autogarden autogarden;
	public Main(){
		
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			setup();
			//final Autogarden autoGarden = new Autogarden();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/auto/system/gardenview/MainView.fxml"));
			loader.setControllerFactory(new Callback<Class<?>, Object>() {
				
				@Override
				public Object call(Class<?> param) {
					// TODO Auto-generated method stub
					return new MainViewController(autogarden);
				}
			});
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			primaryStage.setTitle("Automatic Garden");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void setup() throws SecurityException, IOException{
		Log_Sys.init();
		autogarden = new Autogarden();
		Rose rose= new Rose();
		 autogarden.Plant_add(rose);
		 
		 Lavendar lavendar= new Lavendar();
		 autogarden.Plant_add(lavendar);
		 
		 Jasmine jasmine= new Jasmine();
		 autogarden.Plant_add(jasmine);
		 
		 Garden_Sprinkler roseSprinkler= new Garden_Sprinkler(2);
		 roseSprinkler.setFlower(rose);
		 autogarden.ControlSystem_add(roseSprinkler);
		 autogarden.addGarden_Sprinkler(roseSprinkler);
		 
		 Garden_Heater roseHeater= new Garden_Heater(15,3);
		 roseHeater.setFlower(rose);
		 autogarden.ControlSystem_add(roseHeater);
		 autogarden.addGarden_Heater(roseHeater);
		 
		 Garden_Sprinkler lavendarSprinkler= new Garden_Sprinkler(2);
		 lavendarSprinkler.setFlower(lavendar);
		 autogarden.ControlSystem_add(lavendarSprinkler);
		 autogarden.addGarden_Sprinkler(lavendarSprinkler);
		 
		 Garden_Heater lavendarHeater= new Garden_Heater(15,3);
		 lavendarHeater.setFlower(lavendar);
		 autogarden.ControlSystem_add(lavendarHeater);
		 autogarden.addGarden_Heater(lavendarHeater);
		 
		 Garden_Sprinkler jasmineSprinkler= new Garden_Sprinkler(2);
		 jasmineSprinkler.setFlower(jasmine);
		 autogarden.ControlSystem_add(jasmineSprinkler);
		 autogarden.addGarden_Sprinkler(jasmineSprinkler);

		 
		 
		 Garden_Heater jasmineHeater= new Garden_Heater(15,3);
		 jasmineHeater.setFlower(jasmine);
		 autogarden.ControlSystem_add(jasmineHeater);
		 autogarden.addGarden_Heater(jasmineHeater);
		 
		 autogarden.Game_start();
	}
}
