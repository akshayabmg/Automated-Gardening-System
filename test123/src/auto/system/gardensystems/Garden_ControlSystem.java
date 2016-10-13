package auto.system.gardensystems;

import auto.system.gardencontroller.Events;
import auto.system.gardenflowers.Flower;

public interface Garden_ControlSystem {
	void DayDefaults();
	String Info();
	void Application_start();
	void quit();
	Flower getFlower();
	void setFlower(Flower p);
	void notifyEvent(Events event, Object amount);
	

}
