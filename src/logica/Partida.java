package logica;
import gui.VentanaJuego;

public class Partida {
	private int puntuacion; //puntuacion que se obtiene al finalizar la partida

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
	public int getPuntuacion() {
		return puntuacion;
	}

	/**Modifica la puntuacion de la partida
	 * @param puntuacion
	 */
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	
}
