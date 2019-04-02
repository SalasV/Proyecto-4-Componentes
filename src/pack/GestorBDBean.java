package pack;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

public class GestorBDBean implements Serializable {

	private String ip;
	private String bd;
	private String usuario;
	private String contraseña;
	private Connection connection;
	private Statement statement;
	private ResultSet resultset;
	private RegistroBD registro;
	private ArrayList<RegistroBD> registros = new ArrayList<>();

	public GestorBDBean() {

	}

	private void conexion(String ip, String bd, String usuario, String contraseña) {
		this.ip = ip;
		this.bd = bd;
		this.usuario = usuario;
		this.contraseña = contraseña;
		try {
			if(connection!=null) {
				desconexion();
			}
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + bd, usuario, contraseña);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void desconexion() {
		try {
			resultset.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void select(String consulta) {
		try {
			registro = new RegistroBD();
			statement = connection.createStatement();
			resultset = statement.executeQuery(consulta);
			int count = 0;
			while (resultset.next()) {
				count++;
			}
			
			registro.setBd(this.bd);
			registro.setUsuario(this.usuario);
			registro.setTipoConsulta("SELECT");
			registro.setSentencia(consulta);
			registro.setNumeroRegistros(count);
			registro.setFechaConsulta(Calendar.getInstance());
			
			registros.add(registro);
			
			for (RegistroBD registroBD : registros) {
					System.out.println(registroBD.toString());
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String consulta) {
		try {
			statement = connection.createStatement();

			int numRegistros = statement.executeUpdate(consulta);

			registro.setUsuario(this.usuario);
			registro.setTipoConsulta("DELETE");
			registro.setSentencia(consulta);
			registro.setNumeroRegistros(numRegistros);
			registro.setFechaConsulta(Calendar.getInstance());

			registros.add(registro);
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

			registros.add(registro);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(String consulta) {
		try {
			statement = connection.createStatement();

			int numRegistros = statement.executeUpdate(consulta);

			registro.setUsuario(this.usuario);
			registro.setTipoConsulta("UPDATE");
			registro.setSentencia(consulta);
			registro.setNumeroRegistros(numRegistros);
			registro.setFechaConsulta(Calendar.getInstance());

			registros.add(registro);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consultar(String db, String dato) {
		if (dato.equals("SELECT") || dato.equals("DELETE") || dato.equals("UPDATE") || dato.equals("INSERT")) {
			for (RegistroBD registroBD : registros) {
				if (registroBD.getBd().equals(db) && registroBD.getTipoConsulta().equals(dato)) {
					System.out.println(registroBD.toString());
				}
			}
		} else {
			for (RegistroBD registroBD : registros) {
				if (registroBD.getBd().equals(db) && registroBD.getUsuario().equals(dato)) {
					System.out.println(registroBD.toString());
				}
			}
		}
	}

	public void consultar(String db, String usuario, String tipoConsulta) {
		for (RegistroBD registroBD : registros) {
			if (registroBD.getBd().equals(db) && registroBD.getUsuario().equals(usuario)
					&& registroBD.getTipoConsulta().equals(tipoConsulta)) {
				System.out.println(registroBD.toString());
			}
		}
	}

	public static void main(String[] args) {
		GestorBDBean db = new GestorBDBean();
		db.conexion("localhost", "scrumprojectmanager", "root", "");
		db.select("select * from users");

		db.conexion("localhost", "bd_institut", "root", "");
		db.select("select * from alumnos");

		
		
		//db.consultar("bd_institut", "SELECT");
		//db.consultar("scrumprojectmanager", "SELECT");
		
		//db.consultar("scrumprojectmanager","root", "SELECT");
	}
}
