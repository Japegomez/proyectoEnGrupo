package logica;

import gui.JLabelMeteorito;

/** Clase de los enemigos que son Meteoritos
 * @author jonlc
 *
 */
public class MeteoritoEnemigo extends ObjetoJuego{

	protected double danyoAJugador; //Daño que el meteorito hara al jugador en caso de impactar contra el
	private JLabelMeteorito lMeteorito;
	protected double tiempo;//tiempo que tarda el meteorito en llegar hasta abajo de la pantalla, ira aumentando junto a la dificultad.
	
	
	
	public MeteoritoEnemigo() {
		super(150.0,150.0,150,150,150.0,150.0);
		lMeteorito = new JLabelMeteorito();
	}
	
	
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

	public JLabelMeteorito getlMeteorito() {
		return lMeteorito;
	}

	public void setlMeteorito(JLabelMeteorito lMeteorito) {
		this.lMeteorito = lMeteorito;
	}
	
	@Override
	public void setPosX(double posX) {
		this.posX = posX;
		lMeteorito.setLocation((int) posX, (int) this.getPosY());
	}
	@Override
	public void setPosY(double posY) {
		this.posY = posY;
		lMeteorito.setLocation((int)this.getPosX(),(int) posY);
	}
	
	
	@Override 
	public void moverRecto() {
		
	}
	
	@Override 
	public void moverParabola() {
		
	}
	
}
