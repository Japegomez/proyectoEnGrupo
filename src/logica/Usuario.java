package logica;

import java.util.*;

public class Usuario {

	public String nombreUsuario;//nombre que es usuario decida ponerse
	public String contrasenya;//contraseña del usuario
	
	public double nivel;//nivel del jugador que ira aumentando en caso de jugar
	public TreeMap<Integer,String > partidas;// treemap de las partidas que el usuario ira jugando esta ordenado por la puntuacion que obtenga en esa partida
	
	/**Metodo constructor para crear un usuario por defecto
	 */
	public Usuario() {
		this.nombreUsuario = "";
		this.contrasenya = "";
		this.nivel= 0;
		this.partidas = new TreeMap <Integer,String>();
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

	/**Metodo para obtener el treemap de las partidas del usuario
	 * @return
	 */
	public TreeMap<Integer, String> getPartidas() {
		return partidas;
	}

	/**Metodo para cambiar el treemap que almacena las partidas que ha jugado un usuario
	 * @param partidas
	 */
	public void setPartidas(TreeMap<Integer, String> partidas) {
		this.partidas = partidas;
	}
	
	
	
	
}
