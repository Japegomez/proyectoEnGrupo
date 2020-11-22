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
	private double ataqueCargado;//tiempo que tarda la nave en recuperar su ataque cargado
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
	 * @return devuelve el tiempo que tarda en cargarse el ataque cargado (en milisegundos)
	 */
	public double getAtaqueCargado() {
		return ataqueCargado;
	}

	/**Modifica el tiempo que tarda en cargarse el ataque cargado
	 * @param ataqueCargado nuevo tiempo
	 */
	public void setAtaqueCargado(double ataqueCargado) {
		this.ataqueCargado = ataqueCargado;
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
		this.posX = posX;
		lNave.setLocation((int) posX, (int) this.getPosY());
	}
	/**Modifica la posicion en el eje y de la nave y del JLabelNave 
	 *
	 */
	@Override
	public void setPosY(double posY) {
		this.posY = posY;
		lNave.setLocation((int)this.getPosX(),(int) posY);
	}
	
	/**Crea una nave con valores por defecto
	 * 
	 */
	public NaveJugador() {
		super(0.0,0.0,10,10,150.0);
		this.velocidadAtaque = 5;
		this.danyoAtaque = 5;
		this.ataqueCargado = 5;
		lNave = new JLabelNave();
		setPosX(this.getPosX());
		setPosY(this.getPosY());
	}
	
	public NaveJugador(int vida, double velocidadX, double velocidadY, double velocidadAtaque,
			double danyoAtaque, double ataqueCargado) {
		super(0.0, 0.0, vida, velocidadX, velocidadY);
		this.velocidadAtaque = velocidadAtaque;
		this.danyoAtaque = danyoAtaque;
		this.ataqueCargado = ataqueCargado;
		lNave = new JLabelNave();
	}

	/**Crea un rectangulo alrededor de la nave
	 * @return Un rectangulo alredeor de la nave
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) posX,(int) posY, lNave.getWidth(),lNave.getHeight() );
	}
		
}
