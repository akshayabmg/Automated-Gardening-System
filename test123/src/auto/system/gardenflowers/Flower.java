package auto.system.gardenflowers;

public abstract class Flower {

	FlowerStage flower_stage;
	public void grow() throws FlowerFullyGrownException{
		switch (this.flower_stage) {
		case Stage1_Seed:
			flower_stage=FlowerStage.Stage2_Sapling;
			break;
		case Stage2_Sapling:
			flower_stage=FlowerStage.Stage3_Flower;
			break;
		case Stage3_Flower:
			flower_stage=FlowerStage.Stage4_Flowered;
			break;
		case Stage4_Flowered:
			throw new FlowerFullyGrownException();			
		}
	};
	
	public void Flower_harvest(){
		this.flower_stage=FlowerStage.Stage4_Flowered;
	}
	
	public void flower(){
		this.flower_stage=FlowerStage.Stage1_Seed;
		}
	public void flower_sprinkle(int amoutOfWater) throws FlowerFullyGrownException{
	//for subclass implementation 	
	}
	public String getName(){
		return null;}
	public FlowerStage getFlowerStage(){
		return this.flower_stage;
	}

	public void flower_heat(int Duration_heater, int Temp_heater) throws FlowertOverHeatedException {
	
	}
	public void DayDefaults(){}

	public int get_MaxHeatTemp() {
		return 0;
	}
}
