package pack;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class RegistroBD implements PropertyChangeListener {

	private String usuario;
	private String tipoConsulta;
	private String sentencia;
	private Calendar fechaConsulta;
	private int numeroRegistros;

	public RegistroBD() {

	}

	public RegistroBD(String usuario, String tipoConsulta, String sentencia, Calendar fechaConsulta,
			int numeroRegistros) {
		super();
		this.usuario = usuario;
		this.tipoConsulta = tipoConsulta;
		this.sentencia = sentencia;
		this.fechaConsulta = fechaConsulta;
		this.numeroRegistros = numeroRegistros;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public String getSentencia() {
		return sentencia;
	}

	public Calendar getFechaConsulta() {
		return fechaConsulta;
	}

	public int getNumeroRegistros() {
		return numeroRegistros;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public void setSentencia(String sentencia) {
		this.sentencia = sentencia;
	}

	public void setFechaConsulta(Calendar fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	public void setNumeroRegistros(int numeroRegistros) {
		this.numeroRegistros = numeroRegistros;
	}

	@Override
	public String toString() {
		return "RegistroBD [usuario= " + usuario + ", tipoConsulta= " + tipoConsulta + ", sentencia= " + sentencia
				+ ", fechaConsulta= " + fechaConsulta.get(Calendar.DAY_OF_MONTH) + "/" + fechaConsulta.get(Calendar.MONTH) + "/"
				+ fechaConsulta.get(Calendar.YEAR)+" "+ fechaConsulta.get(Calendar.HOUR_OF_DAY) + ":"
				+ fechaConsulta.get(Calendar.MINUTE) + ":" + fechaConsulta.get(Calendar.SECOND) + " "
				+ ", numeroRegistros= " + numeroRegistros + "]";
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

	}
}
