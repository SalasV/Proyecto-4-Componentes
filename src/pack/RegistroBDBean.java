package pack;

import java.io.Serializable;
import java.util.Date;

public class RegistroBDBean implements Serializable{
	
	private String usuario;
	private String tipoConsulta;
	private String sentencia;
	private Date fechaConsulta;
	private int numeroRegistros;

	public RegistroBDBean() {

	}
}
