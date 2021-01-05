package logica;

import java.awt.Rectangle;

import gui.JLabelDisparo;

public class Disparo extends ObjetoJuego{
	
	private int danyo; //Danyo del disparo de la nave
	private JLabelDisparo lDisparo; // JLabel del disparo
	private int velocidad; //velocidad a la que se mueve el disparo
	private long time;


	/**Crea un disparo
	 * @param posX 
	 * @param posY
	 * @param vida
	 * @param velocidadX
	 * @param velocidadY
	 * @param  
	 */
	public Disparo(double posX, double posY, int vida, double velocidadX, double velocidadY) {
		super(posX, posY, vida, velocidadX, velocidadY);
		this.danyo = 5;
		lDisparo = new JLabelDisparo();
		setPosX(this.getPosX());
		setPosY(this.getPosY());
		setTime(System.currentTimeMillis());
	}

	/**Devuelve el danyo que hace un disparo cuando da con un meteorito (int)
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
		double posFinal = posY - (velocidadY * tiempo);
		for (double i = posY; i > posFinal; i--) {
			this.setPosY(i);
		}
	}
	/**Crea un rectangulo alrededor del meteorito
	 * @return Un rectangulo alrededor del meteorito
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)posX,(int)posY,(int)lDisparo.getHeight(),(int)lDisparo.getWidth());
		
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
