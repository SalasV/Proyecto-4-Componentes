package pack;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class Evento implements PropertyChangeListener {

	ArrayList<RegistroBD> log = new ArrayList<>();
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		log.add((RegistroBD) evt.getNewValue());
	}

}
