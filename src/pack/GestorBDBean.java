package pack;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
	private static Evento evt;
	private PropertyChangeSupport evento;

	public GestorBDBean() {
		evento = new PropertyChangeSupport(this);
		evt = new Evento();
		addPropertyChangeListener(evt);
	}

	public void conexion(String ip, String bd, String usuario, String contraseña) {
		this.ip = ip;
		this.bd = bd;
		this.usuario = usuario;
		this.contraseña = contraseña;
		try {
			if (connection != null) {
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
			RegistroBD registro = new RegistroBD();

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

	public void consultar(String db, String dato) {
		if (dato.equals("SELECT") || dato.equals("DELETE") || dato.equals("UPDATE") || dato.equals("INSERT")) {
			for (RegistroBD registroBD : evt.getLog()) {
				if (registroBD.getBd().equals(db) && registroBD.getTipoConsulta().equals(dato)) {
					System.out.println(registroBD.getSentencia() + " - " + registroBD.getFecha() + " - "
							+ registroBD.getUsuario());
				}
			}
		} else {
			for (RegistroBD registroBD : evt.getLog()) {
				if (registroBD.getBd().equals(db) && registroBD.getUsuario().equals(dato)) {
					System.out.println(registroBD.getSentencia() + " - " + registroBD.getFecha() + " - "
							+ registroBD.getTipoConsulta());
				}
			}
		}
	}

	public void consultar(String db, String usuario, String tipoConsulta) {
		for (RegistroBD registroBD : evt.getLog()) {
			if (registroBD.getBd().equals(db) && registroBD.getUsuario().equals(usuario)
					&& registroBD.getTipoConsulta().equals(tipoConsulta)) {
				System.out.println(registroBD.getSentencia() + " - " + registroBD.getFecha());
			}
		}
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		evento.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		evento.removePropertyChangeListener(listener);
	}

}
