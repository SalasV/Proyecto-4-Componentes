package pack;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorBDBean implements Serializable {

	private String ip;
	private String bd;
	private String usuario;
	private String contraseņa;

	public GestorBDBean() {

	}

	public GestorBDBean(String ip, String bd, String usuario, String contraseņa) {
		this.ip = ip;
		this.bd = bd;
		this.usuario = usuario;
		this.contraseņa = contraseņa;
	}



	private void conexion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + bd, usuario, contraseņa);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		GestorBDBean db = new GestorBDBean("localhost:3306", "forhonor", "root", "");
		db.conexion();
		}
}
