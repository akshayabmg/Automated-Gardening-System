package auto.system.gardenflowers;

public class FlowertOverHeatedException extends Exception {
	String Message="";
	public FlowertOverHeatedException() {
		Message="The flower has overheated";
	}
 public String getMessage(){
	return Message;
	 
 }
}
