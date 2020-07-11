package subClass;
import superClass.Appliance;
public class SmartAppliance extends Appliance{
	protected float reductionPerc;
	protected int lowWatt;
	
	public SmartAppliance() {
		super();
		this.reductionPerc=0f;
		this.appliance=true;
	}
	
	public SmartAppliance(String type,int location, int onWatt, float probability, float reductionPerc) {
		super(type, location, onWatt, probability);
		this.reductionPerc=reductionPerc;
		this.lowWatt=(int) (onWatt*reductionPerc);
		this.appliance=true;
	}
	
	public SmartAppliance(String type,int location, int onWatt, float probability, float reductionPerc, boolean a) {
		super(type, location, onWatt, probability,a);
		this.reductionPerc=reductionPerc;
		this.lowWatt=(int) (onWatt*reductionPerc);
		this.appliance=true;
	}
	
	public SmartAppliance(Appliance a, float reductionPerc) {
		super(a.getType(),a.getLocation(),a.getOnWatt(),a.getProbability());
		this.reductionPerc=reductionPerc;
		this.lowWatt=(int) (onWatt*(1-reductionPerc));
		this.appliance=true;
	}
	//setters
	public void setReductionPerc(float reductionPerc) {
		this.reductionPerc=reductionPerc;
	}
	public void setLowWatt(int lowWatt) {
		this.lowWatt=lowWatt;
	}
	//getters
	public float GetReductionPerc() {
		return this.reductionPerc;
	}
	public int getLowWatt() {
		return this.lowWatt;
	}
	public boolean getAppliance() {
		return this.appliance;
	}
	//toString
	public String toString() {
		return super.toString()+"\nReduction percentage on low: "+this.reductionPerc*100+"%"
				+"\nAppliance type: smart";
	}
	
	public void turnLow() {
		this.state=true;
		this.currentWatt=this.getLowWatt();
	}
}
