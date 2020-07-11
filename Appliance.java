package superClass;

public abstract class Appliance {
	protected String type;
	protected static int ID=0;
	protected int onWatt, location, currentWatt, currentID;
	protected float probability;
	public boolean state,appliance;
	
	public Appliance() {
		type="";
		ID++;
		currentID=ID;
		onWatt=0;
		location=0;
		probability=0f;
		state=true;
		appliance=false;
		currentWatt=onWatt;
	}
	
	public Appliance(String type,int location, int onWatt, float probability) {
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
	
	public Appliance(String type,int location, int onWatt, float probability, boolean a) {
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
	
	public void setType(String type) {
		this.type=type;
	}
	public void setLocation(int location) {
		this.location=location;
	}
	public void setOnWatt(int onWatt) {
		this.onWatt=onWatt;
		this.currentWatt=onWatt;
	}
	public void setProbability(float probability) {
		this.probability=probability;
	}
	public void setState(boolean a) {
		this.state=a;
	}
	
	public String getType() {
		return this.type;
	}
	public int getLocation() {
		return this.location;
	}
	public int getOnWatt() {
		return this.onWatt;
	}
	public float getProbability() {
		return this.probability;
	}
	public boolean getState() {
		return this.state;
	}
	public boolean getAppliance() {
		return this.appliance;
	}
	public int getCurrentwhat() {
		return this.currentWatt;
	}
	//toString
	public String toString() {
		return"ID: "+this.currentID+"\nAppliance type: "+this.type+"\nLocation: "+this.location+"\nOn Wattage: "+this.onWatt
				+"\nCurrent Wattage"+this.currentWatt+"\nProbability: "+this.probability*100+"%";
	}
	
	public void turnOff() {
		this.state=false;
		this.currentWatt=0;
	}
	
	public void turnOn() {
		this.state=true;
		this.currentWatt=onWatt;
	}
	
	public abstract void turnLow();
}
