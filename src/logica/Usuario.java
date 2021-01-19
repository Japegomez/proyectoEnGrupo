package logica;

import java.util.*;

public class Usuario {
	public NaveJugador nave;
	public String nombreUsuario;// nombre que es usuario decida ponerse
	public String contrasenya;// contraseña del usuario

	public int nivel;// nivel del jugador
	public ArrayList<Partida> part;// Lista de partidas del jugador
	public double creditos; // creditos del jugador

	/**
	 * Crea un usuario
	 * 
	 * @param usuario     String del nombre del usuario
	 * @param contrasenya String de la contrasenya del usuario
	 */
	public Usuario(String usuario, String contrasenya) {
		this.nave = new NaveJugador();
		this.nombreUsuario = usuario;
		this.contrasenya = contrasenya;
		this.nivel = 0;
		this.creditos = 0;
		this.part = new ArrayList<Partida>();
	}

	/**
	 * Metodo para obtener los creditos del usuario
	 * 
	 * @return creditos disponibles para el usuario
	 */
	public double getCreditos() {
		return creditos;
	}

	/**
	 * Metodo para alterar los creditos que tiene el usuario
	 * 
	 * @param creditos valor de creditos que se obtiene
	 */
	public void setCreditos(double creditos) {
		this.creditos = creditos;
	}

	/**
	 * Le quita al usuario los creditos equivalentes al coste de una mejora
	 * 
	 */
	public void quitarCreditos() {
		this.creditos = this.creditos - 20;
	}

	/**
	 * @return NaveJugador
	 */
	public NaveJugador getNave() {
		return nave;
	}

	/**
	 * Modifica la nave del usuario.
	 * 
	 * @param nave Nueva nave.
	 */
	public void setNave(NaveJugador nave) {
		this.nave = nave;
	}

	/**
	 * @return ArrayList de partidas del usuario.
	 */
	public ArrayList<Partida> getPart() {
		return part;
	}

	/**
	 * Modicia el ArrayList de partidas.
	 * 
	 * @param part Nuevo ArrayList de partidas.
	 */
	public void setPart(ArrayList<Partida> part) {
		this.part = part;
	}

	/**
	 * @return nombreUsuario String Nombre del usuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * Modifica el nombre del usuario
	 * 
	 * @param nombreUsuario String Nuevo nombre del usuario
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/**
	 * @return contrasenya String Contrasenya del usuario
	 */
	public String getContrasenya() {
		return contrasenya;
	}

	/**
	 * Modifica la contrasenya del usuario
	 * 
	 * @param contrasenya String Nueva contrasenya del usuario
	 */
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	/**
	 * @return nivel Nivel del usuario
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * Modifica el nivel del usuario
	 * 
	 * @param nivel Nuevo nivel del usuario
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	@Override
	public String toString() {
		return nombreUsuario;
	}
}
