package state;

public class SegundaDoseTomada extends Status{
	
	public SegundaDoseTomada() {
		
	}
	
	public String getStatus() {
		return "Ambas as doses foram aplicadas. Vacina��o finalizada!";
	}
	
	public void changeStatus() {
	}
	
	public boolean doseDisp() {
		return false;
	}
	
	
	public Status newStatus() {
		return new SegundaDoseTomada();
	}
}
