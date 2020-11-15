package logica;
import gui.VentanaJuego;

public class Partida {
	private double puntuacion; //puntuacion que se obtiene al finalizar la partida
	private int creditos;

	/**Crea una partida
	 * 
	 */
	public Partida() {
		super();
		this.puntuacion = 0;
	}

	/**
	 * @return puntuacion de la partida
	 */
	public double getPuntuacion() {
		return puntuacion;
	}

	/**Modifica la puntuacion de la partida
	 * @param puntuacion
	 */
	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}


}
