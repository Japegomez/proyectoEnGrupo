package logica;

public class MeteoritoEnemigo extends ObjetoJuego{

	protected double danyoAJugador; //Daño que el meteorito hara al jugador en caso de impactar contra el

	/** Metodo para obtener el atributo danyoAJugador
	 * @return Valor de danyoAJugador 
	 */
	public double getDanyoAJugador() {
		return danyoAJugador;
	}

	/** Metodo para cambiar el atributo danyoAJugador
	 * @param danyoAJugador nuevo valor que se desea introducir
	 */
	public void setDanyoAJugador(double danyoAJugador) {
		this.danyoAJugador = danyoAJugador;
	}
	
	
}
