package logica;

/**
 * @author juxam
 *
 */
abstract public class ObjetoJuego {
	
	protected double posX;//posicion del objeto respecto al eje x (en pixels)
	protected double posY;//posicion del objeto respecto al eje y (en pixels)
	protected int vida;//vida del objeto
	protected double velocidadX;//velocidad en el eje x 
	protected double velocidadY;//velocidad en el eje y


	/**
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
	/**
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

	/**
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

	/**
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
	
	/**
	 * @return Velocidad respecto al eje y
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

	
	/**Crea un objeto de juego
	 * @param posX 
	 * @param posY
	 * @param vida
	 * @param velocidadX
	 * @param velocidadY
	 */
	public ObjetoJuego(double posX, double posY, int vida, double velocidadX, double velocidadY) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.vida = vida;
		this.velocidadX = velocidadX;
		this.velocidadY = velocidadY;
	}
	


	public void mover(double tiempo) {	
	}
	
}
