package logica;

import java.util.*;

public class Usuario {
	public NaveJugador nave;
	public String nombreUsuario;//nombre que es usuario decida ponerse
	public String contrasenya;//contraseña del usuario
	
	public double nivel;//nivel del jugador
	public ArrayList<Partida> part;//Lista de partidas del jugador
	public int creditos; //creditos del jugador 

	/**Crea un usuario
	 * @param usuario String del nombre del usuario
	 * @param contrasenya String de la contrasenya del usuario
	 */
	public Usuario(String usuario, String contrasenya) {
		this.nave = new NaveJugador();
		this.nombreUsuario = usuario;
		this.contrasenya = contrasenya;
		this.nivel= 0;
		this.creditos=0;
		this.part = new ArrayList<Partida>();
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	/**
	 * @return NaveJugador
	 */
	public NaveJugador getNave() {
		return nave;
	}

	public void setNave(NaveJugador nave) {
		this.nave = nave;
	}

	public ArrayList<Partida> getPart() {
		return part;
	}

	public void setPart(ArrayList<Partida> part) {
		this.part = part;
	}

	/**
	 * @return nombreUsuario String Nombre del usuario  
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**Modifica el nombre del usuario
	 * @param nombreUsuario String Nuevo nombre del usuario 
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/**
	 * @return contrasenya  String Contrasenya del usuario
	 */
	public String getContrasenya() {
		return contrasenya;
	}

	/**Modifica la contrasenya del usuario
	 * @param contrasenya String  Nueva contrasenya del usuario
	 */
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	/**
	 * @return nivel Nivel del usuario
	 */
	public double getNivel() {
		return nivel;
	}

	/**Modifica el nivel del usuario
	 * @param nivel Nuevo nivel del usuario
	 */
	public void setNivel(double nivel) {
		this.nivel = nivel;
	}


}
