package pack;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class Evento implements PropertyChangeListener {

	private ArrayList<RegistroBD> log = new ArrayList<>();
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		log.add((RegistroBD) evt.getNewValue());
	}

	public ArrayList<RegistroBD> getLog() {
		return log;
	}

	public void setLog(ArrayList<RegistroBD> log) {
		this.log = log;
	}
	
	public RegistroBD getLog(int i) {
		return log.get(i);
	}

	public void setLog(RegistroBD registro,int i) {
		this.log.set(i, registro);
	}
	
	

}
