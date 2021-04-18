package state;

public class PrimeiraDoseDisp extends Status{
	
	public PrimeiraDoseDisp() {	
	}
	
	public String getStatus() {
		return "Habilitado para receber a primeira dose!";
	}
	
	public void changeStatus() {
	}
	
	public boolean doseDisp() {
		return true;
	}
	
	
	public Status newStatus() {
		return new PrimeiraDoseTomada();
	}

}
