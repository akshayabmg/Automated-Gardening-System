package auto.system.gardenView;

import application.Autogarden;
import auto.system.gardencontroller.Log_Sys;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainViewController {
	
	final Autogarden autoGarden;
	//public static int fertcountjas=5,fertcounttulip=3, fertcountrose=2;
	//public static int spraycountjas=2, spraycounttulip=3, spraycountrose=5;
	public static int i=1,j=1,k=1;
	public static int x=1,y=1,z=1;
	

	public MainViewController(Autogarden autoGarden) {
		// TODO Auto-generated constructor stub
		this.autoGarden = autoGarden;
	}
	
	@FXML
	public Button JasSprinkler;
	
	@FXML
	public void JasSprinkler(){
		
		autoGarden.getGarden_Sprinkler(2).Sprinkle_Manual(2,true);
	}
	
	@FXML 
	public Button LavendarSprinkler;
	
	@FXML
	public void LavendarSprinkler(){
		autoGarden.getGarden_Sprinkler(1).Sprinkle_Manual(2,true);

	}
	
	@FXML 
	public Button RoseSprinkler;
	
	@FXML
	public void RoseSprinkler(){
		autoGarden.getGarden_Sprinkler(0).Sprinkle_Manual(2,true);

	}
	
	@FXML
	public Button JasHeater;
	
	@FXML
	public void JasHeater(){
		autoGarden.getGarden_Heater(2).Heater_Manual(2, 10, true);

	}

	@FXML
	public Button LavendarHeater;
	
	@FXML
	public void LavendarHeater(){
		autoGarden.getGarden_Heater(1).Heater_Manual(2, 10, true);		
	}
	
	@FXML
	public Button RoseHeater;
	
	@FXML
	public void RoseHeater(){
		autoGarden.getGarden_Heater(0).Heater_Manual(2, 10, true);
	}
	
	@FXML
	public Button JasFert;

	
	@FXML
	public void JasFert()
	{
		if (i>=5)
		{
			System.out.println("You have fertilized Jasmine \t"+i+"\t times.You cannot fertlize more");
			
		}
		else
		{
			System.out.println("You have fertilized \t"+i+"\t times");
			i++;
			
		}
		
	}
	@FXML
	public Button LavendarFert;
	@FXML
	public void LavendarFert()
	{
		if (j>=4)
		{
			Log_Sys.writeLog(0,"You have fertilized Lavendar \t"+j+"\t times.You cannot fertlize more", MainViewController.this);
			
		}
		else
		{
			System.out.println("You have fertilized \t"+j+"\t times");
			j++;
			
		}

	}
	
	@FXML
	public Button RoseFert;
	
	@FXML
	public void RoseFert()
	{
		if (k>=3)
		{
			System.out.println("You have fertilized Rose \t"+k+"\t times.You cannot fertlize more");
			
		}
		else
		{
			System.out.println("You have fertilized \t"+k+"\t times");
			k++;
			
		}

	}
	
	@FXML
	public Button JasPest;
	
	@FXML
	public void JasPest()
	{
		if (x>=3)
		{
			System.out.println("You have sprayed insecticide to Jasmine \t"+x+"\t times.You cannot fertlize more");
			
		}
		else
		{
			System.out.println("You have sprayed insecticide \t"+x+"\t times");
			x++;
			
		}

	}
	
	@FXML
	public Button LavendarPest;
	
	@FXML
	public void LavendarPest()
	{
		if (y>=2)
		{
			System.out.println("You have sprayed insecticide to Lavendar \t"+y+"\t times.You cannot fertlize more");
		}
		else
		{
			System.out.println("You have sprayed insecticide \t"+y+"\t times");
			y++;
			
		}
	}
	
	@FXML 
	public Button RosePest;
	
	@FXML
	public void RosePest()
	{
		if (z>=5)
		{
			System.out.println("You have sprayed insecticide to Rose \t"+z+"\t times.You cannot fertlize more");
		}
		else
		{
			System.out.println("You have sprayed insecticide \t"+z+"\t times");
			z++;
			
		}
	}
	
	

}
