package pack;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class GestorBDBean implements Serializable {

	private String ip;
	private String bd;
	private String usuario;
	private String contraseña;
	private Connection connection;
	private Statement statement;
	private ResultSet resultset;
	private RegistroBD registro = new RegistroBD();

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
			connection = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + bd, usuario, contraseña);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void select(String consulta) {
		try {
			statement = connection.createStatement();
			resultset = statement.executeQuery(consulta);
			int count = 0;
			while (resultset.next()) {
				count++;
			}
			registro.setUsuario(this.usuario);
			registro.setTipoConsulta("SELECT");
			registro.setSentencia(consulta);
			registro.setNumeroRegistros(count);
			registro.setFechaConsulta(Calendar.getInstance());

			System.out.println(registro.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String consulta) {
		try {
			statement = connection.createStatement();

			int registros = statement.executeUpdate(consulta);

			registro.setUsuario(this.usuario);
			registro.setTipoConsulta("DELETE");
			registro.setSentencia(consulta);
			registro.setNumeroRegistros(registros);
			registro.setFechaConsulta(Calendar.getInstance());

			System.out.println(registro.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(String consulta) {
		try {
			statement = connection.createStatement();
			statement.executeUpdate(consulta);

			registro.setUsuario(this.usuario);
			registro.setTipoConsulta("INSERT");
			registro.setSentencia(consulta);
			registro.setNumeroRegistros(1);
			registro.setFechaConsulta(Calendar.getInstance());

			System.out.println(registro.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(String consulta) {
		try {
			statement = connection.createStatement();

			int registros = statement.executeUpdate(consulta);

			registro.setUsuario(this.usuario);
			registro.setTipoConsulta("UPDATE");
			registro.setSentencia(consulta);
			registro.setNumeroRegistros(registros);
			registro.setFechaConsulta(Calendar.getInstance());

			System.out.println(registro.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		GestorBDBean db = new GestorBDBean("localhost", "scrumprojectmanager", "root", "");
		db.conexion();
		db.update("update users set password = '1234' where groupID=1");
	}
}
