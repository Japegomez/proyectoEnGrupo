package logica;

import java.awt.Rectangle;

import gui.JLabelDisparo;
import gui.JLabelMeteorito;

public class Disparo extends ObjetoJuego{
	
	private int danyo; //danyo del disparo
	private int velocidad; //velocidad del disparo
	private JLabelDisparo lDisparo; //JLabel del disparo
	


	/**Crea un disparo
	 * @param posX 
	 * @param posY
	 * @param vida
	 * @param velocidadX
	 * @param velocidadY
	 */
	public Disparo(double posX, double posY, int vida, double velocidadX, double velocidadY) {
		super(posX, posY, vida, velocidadX, velocidadY);
		this.danyo = 5;
		this.velocidad = 5;
	}

	/**Devuelve el danyo que hace un disparo.
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
	 *Modifica la posicion en el eje x del disparo y de su JLabel
	 */
	@Override
	public void setPosX(double posX) {
		this.posX = posX;
		lDisparo.setLocation((int) posX, (int) this.getPosY());
	}
	
	 
	/**
	 *Modifica la posicion en el eje y del disparo y de su JLabel
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
	/**Crea un rectangulo alrededor del meteorito
	 * @return Un rectangulo alrededor del meteorito
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)posX,(int)posY,(int)lDisparo.getHeight(),(int)lDisparo.getWidth());
		
	}
}
