package auto.system.gardenflowers;

public class FlowerFullyGrownException extends Exception {
	String Message="";
	public FlowerFullyGrownException() {
		Message="The flower has grown completely";
	}
 public String getMessage(){
	return Message;
	 
 }
}
