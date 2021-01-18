package logica;

import java.awt.Rectangle;
import gui.JLabelNave;

/**
 * @author SpaceDefense
 *
 */
public class NaveJugador extends ObjetoJuego {
	
	private double velocidadAtaque;//tiempo que tarda la nave en atacar (en milisegundos)
	private double danyoAtaque;//danyo que hace cada disparo de la nave
	private JLabelNave lNave;//JLabel de la nave
	
	/**
	 * @return Velocidad de ataque de la nave
	 */
	public double getVelocidadAtaque() {
		return velocidadAtaque;
	}
	
	/**Modifica el tiempo que tarda la nave en atacar
	 * @param velocidadAtaque (en milisegundos)
	 */
	public void setVelocidadAtaque(double velocidadAtaque) {
		this.velocidadAtaque = velocidadAtaque;
	}
	
	/**
	 * @return Danyo de ataque de la nave
	 */
	public double getDanyoAtaque() {
		return danyoAtaque;
	}
	
	/**Modifica el danyo de ataque de la nave
	 * @param danyoAtaque Nuevo danyo de ataque de la nave
	 */
	public void setDanyoAtaque(double danyoAtaque) {
		this.danyoAtaque = danyoAtaque;
	}
	
	/**
	 * @return JLabelNave
	 */
	public JLabelNave getlNave() {
		return lNave;
	}

	/**Modifica el JLabel de la nave
	 * @param lNave
	 */
	public void setlNave(JLabelNave lNave) {
		this.lNave = lNave;
	}

	/**Modifica la posicion en el eje x de la nave y del jLabelNave
	 *
	 */
	@Override
	public void setPosX(double posX) {
		double diferencia = this.getPosX() - posX;
		if (diferencia > 0) {
			for (double i = this.getPosX(); i > posX; i--) {
				lNave.setLocation((int) i, (int) this.getPosY());
			}
		} else if (diferencia < 0) {
			for (double i = this.getPosX(); i < posX; i++) {
				lNave.setLocation((int) i, (int) this.getPosY());
			}
		}
		this.posX = posX;
	}
	/**Modifica la posicion en el eje y de la nave y del JLabelNave 
	 *
	 */
	@Override
	public void setPosY(double posY) {
		double diferencia = this.getPosY() - posY;
		if (diferencia > 0) {
			for (double i = this.getPosY(); i > posY; i--) {
				lNave.setLocation((int) this.getPosX(), (int) i);
			}
		} else if (diferencia < 0) {
			for (double i = this.getPosY(); i < posY; i++) {
				lNave.setLocation((int) this.getPosX(), (int) i);
			}
		}
		this.posY = posY;
	}
	
	/**Crea una nave con valores por defecto
	 * 
	 */
	public NaveJugador() {
		super(0.0,0.0,10,10,150.0);
		this.velocidadAtaque = 3000;
		this.danyoAtaque = 5;
		lNave = new JLabelNave();
		setPosX(this.getPosX());
		setPosY(this.getPosY());
	}
	
	public NaveJugador(int vida, double velocidadX, double velocidadY, double velocidadAtaque,
			double danyoAtaque, double ataqueCargado) {
		super(0.0, 0.0, vida, velocidadX, velocidadY);
		this.velocidadAtaque = velocidadAtaque;
		this.danyoAtaque = danyoAtaque;
		lNave = new JLabelNave();
	}

	/**Crea un rectangulo alrededor de la nave
	 * @return Un rectangulo alredeor de la nave
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) posX,(int) posY, lNave.getWidth(),lNave.getHeight() );
	}
		
}
