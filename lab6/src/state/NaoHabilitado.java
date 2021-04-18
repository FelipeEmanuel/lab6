package state;

public class NaoHabilitado extends Status{
	
	String status;
	
	public NaoHabilitado() {
		status = "NÃO HABILITADO PARA RECEBER A VACINA";
	}
	
	public String getStatus() {
		return status;
	}
	
	public void changeStatus() {
	}
	
	public boolean doseDisp() {
		return false;
	}
	
	public Status newStatus() {
		return new PrimeiraDoseDisp();
	}

}
