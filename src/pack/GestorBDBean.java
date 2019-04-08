package pack;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
	private String contra;
	private Connection connection;
	private Statement statement;
	private ResultSet resultset;
	private static Evento evt;
	private PropertyChangeSupport evento;

	public GestorBDBean() {
		evento = new PropertyChangeSupport(this);
		evt = new Evento();
		addPropertyChangeListener(evt);
	}

	public void conexion(String ip, String bd, String usuario, String contra) {
		this.ip = ip;
		this.bd = bd;
		this.usuario = usuario;
		this.contra = contra;
		try {
			if (connection != null) {
				desconexion();
			}
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + bd, usuario, contra);
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
			RegistroBD registro = new RegistroBD();

			statement = connection.createStatement();
			resultset = statement.executeQuery(consulta);
			int count = 0;
			while (resultset.next()) {
				count++;
			}
			System.out.println(count);
			registro.setBd(this.bd);
			registro.setUsuario(this.usuario);
			registro.setTipoConsulta("SELECT");
			registro.setSentencia(consulta);
			registro.setNumeroRegistros(count);
			registro.setFechaConsulta(Calendar.getInstance());
			evento.firePropertyChange("SELECT", null, registro);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String consulta) {
		try {
			RegistroBD registro = new RegistroBD();

			statement = connection.createStatement();
			int numRegistros = statement.executeUpdate(consulta);
			registro.setBd(this.bd);
			registro.setUsuario(this.usuario);
			registro.setTipoConsulta("DELETE");
			registro.setSentencia(consulta);
			registro.setNumeroRegistros(numRegistros);
			registro.setFechaConsulta(Calendar.getInstance());

			evento.firePropertyChange("DELETE", null, registro);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(String consulta) {
		try {
			RegistroBD registro = new RegistroBD();

			statement = connection.createStatement();
			statement.executeUpdate(consulta);
			registro.setBd(this.bd);
			registro.setUsuario(this.usuario);
			registro.setTipoConsulta("INSERT");
			registro.setSentencia(consulta);
			registro.setNumeroRegistros(1);
			registro.setFechaConsulta(Calendar.getInstance());

			evento.firePropertyChange("INSERT", null, registro);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(String consulta) {
		try {
			RegistroBD registro = new RegistroBD();

			statement = connection.createStatement();

			int numRegistros = statement.executeUpdate(consulta);

			registro.setBd(this.bd);
			registro.setUsuario(this.usuario);
			registro.setTipoConsulta("UPDATE");
			registro.setSentencia(consulta);
			registro.setNumeroRegistros(numRegistros);
			registro.setFechaConsulta(Calendar.getInstance());

			evento.firePropertyChange("UPDATE", null, registro);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void procedure(String consulta) {
		RegistroBD registro = new RegistroBD();

		Statement st;
		try {
			st = (Statement) connection.createStatement();
			st.execute(consulta);
			registro.setBd(this.bd);
			registro.setUsuario(this.usuario);
			registro.setTipoConsulta("PROCEDURE");
			registro.setSentencia(consulta);
			registro.setFechaConsulta(Calendar.getInstance());
			evento.firePropertyChange("PROCEDURE", null, registro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error en el procedure");
		}
	}

	public ArrayList<RegistroBD> consultar(String db, String dato) {
		ArrayList<RegistroBD> registros = new ArrayList<>();
		if (dato.equals("SELECT") || dato.equals("DELETE") || dato.equals("UPDATE") || dato.equals("INSERT")
				|| dato.equals("PROCEDURE")) {
			for (RegistroBD registroBD : evt.getLog()) {
				if (registroBD.getBd().equals(db) && registroBD.getTipoConsulta().equals(dato)) {
					System.out.println(registroBD.getSentencia() + " - " + registroBD.getFecha() + " - "
							+ registroBD.getUsuario());
					registros.add(registroBD);
				}
			}
		} else {
			for (RegistroBD registroBD : evt.getLog()) {
				if (registroBD.getBd().equals(db) && registroBD.getUsuario().equals(dato)) {
					System.out.println(registroBD.getSentencia() + " - " + registroBD.getFecha() + " - "
							+ registroBD.getTipoConsulta());
					registros.add(registroBD);
				}
			}
		}
		return registros;
	}

	public ArrayList<RegistroBD> consultar(String db, String usuario, String tipoConsulta) {
		ArrayList<RegistroBD> registros = new ArrayList<>();
		for (RegistroBD registroBD : evt.getLog()) {
			if (registroBD.getBd().equals(db) && registroBD.getUsuario().equals(usuario)
					&& registroBD.getTipoConsulta().equals(tipoConsulta)) {
				System.out.println(registroBD.getSentencia() + " - " + registroBD.getFecha());
				registros.add(registroBD);
			}
		}
		return registros;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		evento.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		evento.removePropertyChangeListener(listener);
	}

}