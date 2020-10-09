package logica;

/**
 * @author juxam
 *
 */
public class ObjetoJuego {
	
	protected double posX;
	protected double posY;
	protected int vida;
	protected double velocidadX;
	protected double velocidadY;
	protected double aceleracion;
	
	public void ObjetoJuego() {
		
	}

	/** Devuelve la coordenada x del centro del objeto de juego, con respecto a la pantalla
	 * @return	Coordenada x en píxels
	 */
	public double getPosX() {
		return posX;
	}
	
	/** Modifica la coordenada x del centro del objeto de juego, con respecto a la pantalla
	 * @param x	Nueva coordenada x
	 */
	public void setPosX(double posX) {
		this.posX = posX;
	}
	/** Devuelve la coordenada y del centro del objeto de juego, con respecto a la pantalla
	 * @return	Coordenada y en píxels
	 */
	public double getPosY() {
		return posY;
	}
	/** Modifica la coordenada y del centro del objeto de juego, con respecto a la pantalla
	 * @param y	Nueva coordenada y
	 */
	public void setPosY(double posY) {
		this.posY = posY;
	}

	/**Devuelve la vida del objeto juego
	 * @return Vida del objeto
	 */
	public int getVida() {
		return vida;
	}

	/**Modifica la vida del objeto juego
	 * @param Nuevo valor de vida 
	 */
	public void setVida(int vida) {
		this.vida = vida;
	}

	/**Devuelve la velocidad del objeto juego respecto al eje x
	 * @return Velocidad respecto al eje x
	 */
	public double getVelocidadX() {
		return velocidadX;
	}

	/**Modifica la velocidad del objeto juego respecto al eje x
	 * @param velocidadX Nueva velocidad respecto al eje x
	 */
	public void setVelocidadX(double velocidadX) {
		this.velocidadX = velocidadX;
	}
	
	/**Devuelve la velocidad del objeto juego respecto al eje y
	 * @return Velocidad respecto al eje x
	 */
	public double getVelocidadY() {
		return velocidadY;
	}
	
	/**Modifica la velocidad del objeto juego respecto al eje y
	 * @param velocidadY Nueva velocidad respecto al eje y
	 */
	public void setVelocidadY(double velocidadY) {
		this.velocidadY = velocidadY;
	}
	
	/**Devuelve la aceleracion del objeto juego
	 * @return Aceleracion en double
	 */
	public double getAceleracion() {
		return aceleracion;
	}

	/**Modifica la aceleracion del objeto juego
	 * @param aceleracion nueva aceleracion 
	 */
	public void setAceleracion(double aceleracion) {
		this.aceleracion = aceleracion;
	}

	public void mover() {
			
	}
}
