package logica;

import java.util.*;

public class Usuario {

	public String nombreUsuario;//nombre que es usuario decida ponerse
	public String contrasenya;//contraseña del usuario
	
	public double nivel;//nivel del jugador que ira aumentando en caso de jugar
	public ArrayList<Partida> part;
	/**Metodo constructor para crear un usuario
	 * 
	 */
	public Usuario(String usuario, String contrasenya) {
		this.nombreUsuario = usuario;
		this.contrasenya = contrasenya;
		this.nivel= 0;
		this.part = new ArrayList<Partida>();
	}

	/**Metodo para obtener el nombre del usuario
	 * @return nombtreUsuario String Nombre del usuario  
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**Metodo para cambiar el nombre del usuario por otro
	 * @param nombreUsuario String Nuevo nombre del usuario 
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/**Metodo para obtener la contraseña del usuario
	 * @return contrasenya  String Contraseña del ususario
	 */
	public String getContrasenya() {
		return contrasenya;
	}

	/**Metodo para cambiar la contraseña del usuario por otra
	 * @param contrasenya String  Nueva contrasenya del usuario
	 */
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	/**Metodo para obtener el nivel del usuario
	 * @return nivel Nivel del usuario
	 */
	public double getNivel() {
		return nivel;
	}

	/**Metodo para cambiar el nivel del usuario
	 * @param nivel Nuevo nivel del usuario
	 */
	public void setNivel(double nivel) {
		this.nivel = nivel;
	}
}
