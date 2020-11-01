package logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;

public class BaseDatos {
	private static Connection conexion;
	private static Statement s;
	private static ResultSet rs;
	
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
	public static void cerrarConexion() {
		try {
			conexion.close();
			System.out.println( "Conexión cerrada" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void registrarUsuario(String usuario, String contrasenya) {
		try {
			s = conexion.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String com = "";
		com = "insert into usuario ( nombre, contrasenya, nivel ) values (" + usuario +"," + contrasenya + ",0)";
		try {
			s.executeUpdate( com );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**Comprueba si el usuario existe en la base de datos
	 * @param nombreUsuario nombre del Usuario que se desea comprobar
	 * @return devuelve true si no existe, false si si existe.
	 */
	public static boolean compruebaUsuario(String nombreUsuario) {
		try  {
			s = conexion.createStatement();
			String com = "select * from usuario where nombre=" + nombreUsuario + ";";
			rs = s.executeQuery( com ); //si la query se ejecuta es porque existe ya un usuario registrado de nombre NombreUsuario
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}
}
