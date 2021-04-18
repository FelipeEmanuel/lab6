package state;

import java.util.Calendar;

public class PrimeiraDoseTomada extends Status{
	long data;
	Calendar calendar = Calendar.getInstance();
	
	public PrimeiraDoseTomada() {
		data = calendar.getTimeInMillis();
	}
	
	public PrimeiraDoseTomada(long data) {
		this.data = data;
	}
	
	public String getStatus() {
		return "Primeira dose já aplicada! Na fila para receber a segunda dose!";
	}
	
	public void changeStatus() {
	}
	
	public boolean doseDisp() {
		if((Calendar.getInstance().getTimeInMillis() - data) > 1641600)
			return true;
		return false;
	}
	
	
	public Status newStatus() {
		if((Calendar.getInstance().getTimeInMillis() - data) > 1641600)
			return new SegundaDoseTomada();
		return new PrimeiraDoseTomada(data);
	}

}
