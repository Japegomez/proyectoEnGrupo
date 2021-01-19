package logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BaseDatos {
	private static Connection conexion;
	
	/**Abre la conexion con la base de datos
	 * @param nombre nombre de la base de datos con la que se desea conectar
	 * @return true si se ha conectado, false si ha habido un error.
	 */
	public static void initBD( String nombre ) {
		try {
			if (conexion==null) {
				Class.forName("org.sqlite.JDBC"); 
				conexion = DriverManager.getConnection("jdbc:sqlite:" + nombre );
				Main.logger.log(Level.INFO, "Conexion a la base de datos abierta");
			}
			else {
				Main.logger.log(Level.INFO, "Ya existe una conexión abierta");
			}
		} catch(ClassNotFoundException | SQLException e) {
			Main.logger.log( Level.SEVERE, "Error al abrir la conexión de la base de datos ", e );
		}
	}
	/**Cierra la conexion con la base de datos
	 * 
	 */
	public static void cerrarConexion() {
		try {
			conexion.close();
			conexion = null;
			Main.logger.log(Level.INFO, "Conexion a la base de datos cerrada");
		} catch (SQLException e) {
			Main.logger.log( Level.SEVERE, "Error al cerrar la conexión de la base de datos ", e );
		}
	}
	/**Registra un nuevo usuario en la base de datos
	 * @param usuario nombre del usuario
	 * @param contrasenya contrasenya del usuario
	 */
	public static void registrarUsuario(Usuario usu) {
		try {
			PreparedStatement s = conexion.prepareStatement("insert into usuario ( nombre, contrasenya, nivel, creditos ) values (?, ?, ?, ?)");
			s.setString(1, usu.getNombreUsuario());
			s.setString(2, usu.getContrasenya());
			s.setInt(3, usu.getNivel());
			s.setDouble(4,  usu.getCreditos());
			s.execute();
			Main.logger.log(Level.INFO, "Se ha registrado al usuario: "+ usu);
		} catch (SQLException e) {
			Main.logger.log( Level.SEVERE, "Error al registar el usuario: " + usu, e );
		}
	}
	
	public static void registrarNave(Usuario usu) {
		try {
			PreparedStatement s = conexion.prepareStatement("insert into nave ( idusuario, velocidadAtaque, danyoAtaque, vida, velocidadX, velocidadY) values (?, ?, ?, ?, ?, ?)");
			s.setInt(1, obtenerIdUsuario(usu.getNombreUsuario()));
			s.setDouble(2, usu.getNave().getVelocidadAtaque());
			s.setDouble(3, usu.getNave().getDanyoAtaque());
			s.setInt(4, usu.getNave().getVida());
			s.setDouble(5, usu.getNave().getVelocidadX());
			s.setDouble(6, usu.getNave().getVelocidadY());
			s.executeUpdate();
			Main.logger.log(Level.INFO, "Se ha registrado la nave del usuario: "+ usu);
		} catch (SQLException e) {
			Main.logger.log( Level.SEVERE, "Error al registar la nave del usuario: " + usu, e );
		}
	}
	/**Comprueba si el usuario existe en la base de datos
	 * @param nombreUsuario nombre del usuario que se desea comprobar
	 * @return devuelve true si no existe, false si ya existe.
	 */
	public static boolean compruebaUsuario(String nombreUsuario) {
		try  {
			PreparedStatement s = conexion.prepareStatement("SELECT * FROM usuario where nombre = ?");
			s.setString(1, nombreUsuario);
			ResultSet rs = s.executeQuery();
			if(rs.next()) return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	/**Comprueba si la contrasenya se corresponde al usuario
	 * @param nombreUsuario nombre del usuario
	 * @param contrasenya contrasenya
	 * @return true si se corresponde, false si no.
	 */
	public static boolean compruebaContrasenya(String nombreUsuario, String contrasenya) {
		try  {
			PreparedStatement s = conexion.prepareStatement("select * from usuario where nombre = ? and contrasenya = ?");
			s.setString(1, nombreUsuario);
			s.setString(2, contrasenya);
			ResultSet rs = s.executeQuery();

			if(rs.next()) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	/**Devuelve la Id del usuario
	 * @param nombreUsuario Nombre del usuario cuyo Id se desea obtener
	 * @return ID del usuario, -1 si no ha conseguido encontrar al usuario
	 */
	public static int obtenerIdUsuario(String nombreUsuario) {
		int id = -1;
		try {
			PreparedStatement s = conexion.prepareStatement("select * from usuario where nombre = ?");
			s.setString(1, nombreUsuario);
			ResultSet rs = s.executeQuery();
			if(rs.next()) id = rs.getInt("idusuario");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	/**Aniade una partida a la base de datos
	 * @param d puntuacion de la partida
	 * @param nombreUsuario nombre del usuario que ha jugado la partida
	 */
	public static void aniadirPartida(double puntuacion, String nombreUsuario) {
		try {
			PreparedStatement s = conexion.prepareStatement("insert into partida ( idusuario, puntuacion, fecha ) values (?, ?, ?)");
			s.setInt(1, obtenerIdUsuario(nombreUsuario));
			s.setDouble(2, puntuacion);
			s.setFloat(3, System.currentTimeMillis());
			s.executeUpdate();
			Main.logger.log(Level.INFO, "Se ha guardado una partida del usuario " + nombreUsuario +  " que ha obtenido "
					+ puntuacion + " puntos");
		} catch (SQLException e) { 
			Main.logger.log( Level.SEVERE, "Error al guardar partida", e );
		}
	}
	public static NaveJugador obtenerNave(Usuario usu) {
		NaveJugador nave = null;
		try {
			PreparedStatement s = conexion.prepareStatement("select * from nave where idusuario = ?");
			s.setInt(1, obtenerIdUsuario(usu.getNombreUsuario()));
			ResultSet rs = s.executeQuery();
			nave =  new NaveJugador(rs.getInt("vida"),rs.getDouble("velocidadX"),rs.getDouble("velocidadY"),rs.getDouble("velocidadAtaque"),rs.getDouble("danyoAtaque"));
		} catch (SQLException e) {
			Main.logger.log( Level.SEVERE, "Error al obtener la nave de: " + usu, e );
		}
		return nave;
	}
	
	public static void setNave(Usuario usu) {
		try {
			PreparedStatement s = conexion.prepareStatement("update nave set vida = ?, velocidadAtaque = ?, danyoAtaque = ? where idusuario = ?");
			s.setInt(1, usu.getNave().getVida());
			s.setDouble(2, usu.getNave().getVelocidadAtaque());
			s.setDouble(3, usu.getNave().getDanyoAtaque());
			s.setInt(4, obtenerIdUsuario(usu.getNombreUsuario()));
			s.executeUpdate();
			Main.logger.log(Level.INFO, "se ha actualizado la nave de " + usu);
		} catch (SQLException e) {
			Main.logger.log( Level.SEVERE, "Error al actualizar la nave de " + usu, e );
			JOptionPane.showMessageDialog(null, "Error al actualizar la nave, intentelo otra vez", "ERROR", JOptionPane.ERROR);
		}
		
	}

	public static int obtenerCreditos(Usuario usu) {
		
			try {
				PreparedStatement s = conexion.prepareStatement("select creditos from usuario where idusuario = ?");
				s.setInt(1, obtenerIdUsuario(usu.getNombreUsuario()));
				ResultSet rs = s.executeQuery();
				return rs.getInt("creditos");
			} catch (SQLException e) {
			}
			
		return 0;
	}

	public static void setCreditosUsuario(Usuario usu) {
		try {
			PreparedStatement s = conexion.prepareStatement("update usuario set creditos = ? where idusuario = ?");
			s.setDouble(1, usu.getCreditos());
			s.setInt(2, obtenerIdUsuario(usu.getNombreUsuario()));
			s.executeUpdate();
		} catch (SQLException e) {
		}
	}
	public static void rellenarTabla(DefaultTableModel modelo) {

		try {
			PreparedStatement s = conexion.prepareStatement("select nombre, puntuacion from partida p,usuario u where p.idusuario = u.idusuario order by puntuacion desc");
			ResultSet rs = s.executeQuery();
			for (int i = 1; i < 4; i++) 
			{
				if (rs.next()){
					Object [] fila = new Object[3];
					fila[0] = i;
					for (int j=1;j<3;j++)
						fila[j] = rs.getObject(j);
					modelo.addRow(fila);
				}
			}
			Main.logger.log(Level.INFO, "tabla de puntuaciones rellenada");
		} catch (SQLException e) {
			Main.logger.log( Level.SEVERE, "Error al rellenar la tabla de puntuaciones", e );
			JOptionPane.showMessageDialog(null, "Error al obtener las clasificaciones, intentelo mas tarde", "ERROR", JOptionPane.ERROR);
		}


	}
	public static ArrayList<Partida> getPartidas(Usuario usu) {
		ArrayList<Partida> aPartidas = new ArrayList<>();
		try {
			PreparedStatement s = conexion.prepareStatement("select puntuacion, fecha from partida where idusuario = ?");
			s.setInt(1, obtenerIdUsuario(usu.getNombreUsuario()));
			ResultSet rs =  s.executeQuery();
			while (rs.next()) {
				aPartidas.add(new Partida(rs.getInt("puntuacion"),rs.getLong("fecha")));
			}
		} catch (SQLException e) {
			Main.logger.log( Level.SEVERE, "Error al obtener las partidas de " + usu, e );
		}
		return aPartidas;
	}
	public static void borrarPartida(Partida p) {
		try {
			PreparedStatement s = conexion.prepareStatement("delete from partida where fecha = ?");
			s.setLong(1, p.getFecha());
			s.executeUpdate();
			Main.logger.log(Level.INFO, "partida borrada");
		} catch (SQLException e) {
			Main.logger.log( Level.SEVERE, "Error al borrar la partida: " + p, e );
		}
		
	}
	public static void crearTablas() {
		try {
			Statement stat = conexion.createStatement();
			stat.setQueryTimeout(30);
			String sql = "CREATE TABLE if not exists nave ( idusuario INTEGER NOT NULL, velocidadAtaque DOUBLE, danyoAtaque DOUBLE,"
					+ "idnave INTEGER PRIMARY KEY AUTOINCREMENT, vida INTEGER, velocidadX DOUBLE, velocidadY DOUBLE);";
			stat.executeUpdate(sql);
			Main.logger.log( Level.INFO, "BD creación de tabla\t" + sql);
			sql = "CREATE TABLE if not exists partida (idusuario INTEGER NOT NULL, idpartida INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "puntuacion INTEGER, fecha INTEGER);";
			stat.executeUpdate(sql);
			Main.logger.log( Level.INFO, "BD creación de tabla\t" + sql);
			sql = "CREATE TABLE if not exists usuario (nombre TEXT, contrasenya TEXT, nivel INTEGER, "
					+ "idusuario INTEGER PRIMARY KEY AUTOINCREMENT, creditos INTEGER);";

			stat.executeUpdate(sql);
			Main.logger.log( Level.INFO, "BD creación de tabla\t" + sql);
		} catch (SQLException e) {
			Main.logger.log( Level.SEVERE, "Error al crear las tablas de la base de datos", e );
		}
		
		
	}
	

		
	
}
