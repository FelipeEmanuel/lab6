package state;

public abstract class Status {
	
	Status status;
	
	public Status() {
	}
	
	public abstract String getStatus();
	
	public abstract Status newStatus();
	
	public abstract boolean doseDisp();
	
	public abstract void changeStatus();

}
