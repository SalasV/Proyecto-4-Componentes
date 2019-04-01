package pack;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorBDBean implements Serializable {

	private String ip;
	private String bd;
	private String usuario;
	private String contraseña;

	public GestorBDBean() {

	}

	public GestorBDBean(String ip, String bd, String usuario, String contraseña) {
		this.ip = ip;
		this.bd = bd;
		this.usuario = usuario;
		this.contraseña = contraseña;
	}



	private void conexion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + bd, usuario, contraseña);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		GestorBDBean db = new GestorBDBean("localhost:3306", "forhonor", "root", "");
		db.conexion();
		}
}
