package subClass;
import superClass.Appliance;
public class RegularAppliance extends Appliance {
	
	RegularAppliance(){
		super();
	}
	
	public RegularAppliance(String type,int location, int onWatt, float probability) {
		this.type=type;
		this.location=location;
		this.onWatt=onWatt;
		this.probability=probability;
		this.currentWatt=onWatt;
		this.state=true;
		this.appliance=false;
		ID++;
		currentID=ID;
	}
	
	public RegularAppliance(String type,int location, int onWatt, float probability, boolean a) {
		this.type=type;
		this.location=location;
		this.onWatt=onWatt;
		this.currentWatt=onWatt;
		this.probability=probability;
		this.state=a;
		this.appliance=false;
		ID++;
		currentID=ID;
	}
	public void turnLow() {
		this.state=true;
		this.currentWatt=onWatt;
	}
	
	public String toString() {
		return super.toString()+"\nType of appliance: Regular.";
	}

}
