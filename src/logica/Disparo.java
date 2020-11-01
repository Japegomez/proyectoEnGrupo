package logica;

import gui.JLabelDisparo;
import gui.JLabelMeteorito;

public class Disparo extends ObjetoJuego{
	
	private int danyo;
	private int velocidad;
	private JLabelDisparo lDisparo;
	


	public Disparo(double posX, double posY, int vida, double velocidadX, double velocidadY, double aceleracion) {
		super(posX, posY, vida, velocidadX, velocidadY, aceleracion);
		this.danyo = 5;
		this.velocidad = 5;
	}

	/**Devuelve el danyo que hace un disparo cuando da con un meteorito.
	 * @return Danyo del disparo
	 */
	public int getDanyo() {
		return danyo;
	}

	/**Modifica el danyo del disparo.
	 * @param danyo Nuevo entero del danyo del disparo.
	 */
	public void setDanyo(int danyo) {
		this.danyo = danyo;
	}

	/**Devuelve la velocidad a la que va el disparo.
	 * @return La velocidad del disparo.
	 */
	public int getVelocidad() {
		return velocidad;
	}

	/**Modifica la velocidad del disparo.
	 * @param velocidad Nuevo entero de la velocidad del disparo.
	 */
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	/**
	 *Modifica la posicion en el eje x del disparo y del jlabeldisparo
	 */
	@Override
	public void setPosX(double posX) {
		this.posX = posX;
		lDisparo.setLocation((int) posX, (int) this.getPosY());
	}
	
	 
	/**
	 *Modifica la posicion en el eje y del disparo y del jlabeldisparo
	 */
	@Override
	public void setPosY(double posY) {
		this.posY = posY;
		lDisparo.setLocation((int)this.getPosX(),(int) posY);
	}
	/**
	 * @return El jlabel del meteorito
	 */
	public JLabelDisparo getlDisparo() {
		return lDisparo;
	}
	
	/**Mueve el disparo en linea recta con una velocidad constante
	 *
	 */
	@Override 
	public void mover(double tiempo) {
		this.setPosY((posY + (velocidadY *tiempo))*(-1));
	}
}
