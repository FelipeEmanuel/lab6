package state;

public class SegundaDoseDisp extends Status{
	
	public SegundaDoseDisp() {	
	}
	
	public String getStatus() {
		return "Habilitado para receber a segunda dose";
	}
	
	public void changeStatus() {
	}
	
	public boolean doseDisp() {
		return true;
	}
	
	
	public Status newStatus() {
		return new SegundaDoseTomada();
	}
}
