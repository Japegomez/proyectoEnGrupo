package logica;

import java.awt.Rectangle;

import gui.JLabelMeteorito;

/**
 * Clase de los enemigos que son Meteoritos
 * 
 * @author SpaceDefense
 *
 */
public class MeteoritoEnemigo extends ObjetoJuego {

	protected double danyoAJugador; // Daño que el meteorito hara al jugador en caso de impactar contra el
	private JLabelMeteorito lMeteorito; // JLabel del meteorito
	final public int VALOR_PUNTUACION = 100;
	final public int VALOR_CREDITOS = 10;
	private long time;
	private int currentSegundosPartida;

	/**
	 * Crea un meteorito
	 * 
	 */
	public MeteoritoEnemigo(int cSegundosPartida) {
		super(150.0, 150.0, 150, 0, 20);
		lMeteorito = new JLabelMeteorito();
		setPosX(this.getPosX());
		setPosY(this.getPosY());
		setTime(System.currentTimeMillis());
		setCurrentSegundosPartida(cSegundosPartida);
	}

	/**
	 * @return Valor de danyoAJugador
	 */
	public double getDanyoAJugador() {
		return danyoAJugador;
	}

	/**
	 * Metodo para cambiar el atributo danyoAJugador
	 * 
	 * @param danyoAJugador nuevo valor que se desea introducir
	 */
	public void setDanyoAJugador(double danyoAJugador) {
		this.danyoAJugador = danyoAJugador;
	}

	/**
	 * Modifica la posicion en el eje x del meteorito y del jlabelMeteorito
	 */
	@Override
	public void setPosX(double posX) {
		this.posX = posX;
		lMeteorito.setLocation((int) posX, (int) this.getPosY());
	}

	/**
	 * Modifica la posicion en el eje y del meteorito y del jlabelMeteorito
	 */
	@Override
	public void setPosY(double posY) {
		this.posY = posY;
		lMeteorito.setLocation((int) this.getPosX(), (int) posY);
	}

	/**
	 * @return jlabel del meteorito
	 */
	public JLabelMeteorito getlMeteorito() {
		return lMeteorito;
	}

	/**
	 * Modifica el jlabel del meteorito
	 * 
	 * @param lMeteorito Nuevo jlabel del meteorito
	 */
	public void setlMeteorito(JLabelMeteorito lMeteorito) {
		this.lMeteorito = lMeteorito;
	}

	/**
	 * Mueve el meteorito en linea recta con una velocidad constante
	 *
	 */
	@Override
	public void mover(double tiempo) {
		double posFinal = posY + (velocidadY * tiempo);
		for (int i = 0; i < posFinal-posY; i++) {
			this.setPosY(posY + i);
		}
		
	}

	/**
	 * Crea un rectangulo alrededor del meteorito
	 * 
	 * @return Un rectangulo alrededor del meteorito
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) posX, (int) posY, (int) lMeteorito.getHeight(), (int) lMeteorito.getWidth());

	}

	/**
	 * @return int Valor de la puntuacion al matar a un meteorito
	 */
	public int getVALOR_PUNTUACION() {
		return VALOR_PUNTUACION;
	}

	/**
	 * @return int Valor de los creditos al matar a un meteorito
	 */
	public int getVALOR_CREDITOS() {
		return VALOR_CREDITOS;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getCurrentSegundosPartida() {
		return currentSegundosPartida;
	}

	public void setCurrentSegundosPartida(int currentSegundosPartida) {
		this.currentSegundosPartida = currentSegundosPartida;
	}

}
