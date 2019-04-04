package pack;

import java.util.Calendar;

public class RegistroBD {

	private String bd;
	private String usuario;
	private String tipoConsulta;
	private String sentencia;
	private Calendar fechaConsulta;
	private int numeroRegistros;

	public RegistroBD() {

	}

	public RegistroBD(String bd,String usuario, String tipoConsulta, String sentencia, Calendar fechaConsulta,
			int numeroRegistros) {
		this.bd=bd;
		this.usuario = usuario;
		this.tipoConsulta = tipoConsulta;
		this.sentencia = sentencia;
		this.fechaConsulta = fechaConsulta;
		this.numeroRegistros = numeroRegistros;
	}
	
	public String getBd() {
		return bd;
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

	public void setBd(String bd) {
		this.bd = bd;
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

	public String getFecha() {
		return fechaConsulta.get(Calendar.DAY_OF_MONTH) + "/"
				+ fechaConsulta.get(Calendar.MONTH) + "/" + fechaConsulta.get(Calendar.YEAR) + " "
				+ fechaConsulta.get(Calendar.HOUR_OF_DAY) + ":" + fechaConsulta.get(Calendar.MINUTE) + ":"
				+ fechaConsulta.get(Calendar.SECOND);
	}
	
	@Override
	public String toString() {
		return "RegistroBD [database= "+bd+", usuario= " + usuario + ", tipoConsulta= " + tipoConsulta + ", sentencia= " + sentencia
				+ ", fechaConsulta= " + getFecha() + " " + ", numeroRegistros= " + numeroRegistros + "]";
	}

	
}
