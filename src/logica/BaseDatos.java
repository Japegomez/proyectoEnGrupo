package logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JOptionPane;

public class BaseDatos {
	private static Connection conexion;
	private static Statement s;
	private static ResultSet rs;
	
	/**Abre la conexion con la base de datos
	 * @param nombre nombre de la base de datos con la que se desea conectar
	 * @return true si se ha conectado, false si ha habido un error.
	 */
	public static boolean abrirConexion( String nombre ) {
		try {
			System.out.println( "Conexión abierta" );
			Class.forName("org.sqlite.JDBC");  // Carga la clase de BD para sqlite
			conexion = DriverManager.getConnection("jdbc:sqlite:" + nombre );
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**Cierra la conexion con la base de datos
	 * 
	 */
	public static void cerrarConexion() {
		try {
			conexion.close();
			System.out.println( "Conexión cerrada" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**Registra un nuevo usuario en la base de datos
	 * @param usuario nombre del usuario
	 * @param contrasenya contrasenya del usuario
	 */
	public static void registrarUsuario(String usuario, String contrasenya) {
		try {
		s = conexion.createStatement();
		String com = "insert into usuario ( nombre, contrasenya, nivel ) values ('" + usuario +"','" + contrasenya + "','0')";
			s.executeUpdate( com );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**Comprueba si el usuario existe en la base de datos
	 * @param nombreUsuario nombre del usuario que se desea comprobar
	 * @return devuelve true si no existe, false si ya existe.
	 */
	public static boolean compruebaUsuario(String nombreUsuario) {
		boolean exists = false;
		try  {
			s = conexion.createStatement();
			String com = "SELECT * FROM usuario where nombre = '" + nombreUsuario + "'";
			rs = s.executeQuery( com );
			
			while(rs.next()) {
				String user = rs.getString("nombre");
				if (user != null) {
					exists = true;
					return exists;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exists;
	}
	
	
	/**Comprueba si la contrasenya se corresponde al usuario
	 * @param nombreUsuario nombre del usuario
	 * @param contrasenya contrasenya
	 * @return true si se corresponde, false si no.
	 */
	public static boolean compruebaContrasenya(String nombreUsuario, String contrasenya) {
		boolean exists = false;
		try  {
			s = conexion.createStatement();
			String com = "select * from usuario where nombre ='" + nombreUsuario + "' and contrasenya = '" +contrasenya+ "'";
			rs = s.executeQuery( com );

			while(rs.next()) {
				String user = rs.getString("nombre");
				if (user != null) {
					exists = true;
					return exists;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exists;
		
	}
	
	/**Devuelve la Id del usuario
	 * @param nombreUsuario Nombre del usuario cuyo Id se desea obtener
	 * @return
	 */
	public static String obtenerIdUsuario(String nombreUsuario) {
		String id= "";
		String com = "select * from usuario where nombre ='" + nombreUsuario + "'";
		try {
			rs = s.executeQuery(com);
			if(rs.next()) id = rs.getString("idusuario");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	/**Aniade una partida a la base de datos
	 * @param puntuacion puntuacion de la partida
	 * @param nombreUsuario nombre del usuario que ha jugado la partida
	 */
	public static void aniadirPartida(int puntuacion, String nombreUsuario) {
		try {
			s = conexion.createStatement();
			String com = "insert into partida ( idusuario, puntuacion ) values ('" +obtenerIdUsuario(nombreUsuario)+"','" + puntuacion + "')";
				s.executeUpdate( com );
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
