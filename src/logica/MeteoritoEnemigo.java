package logica;

import javax.swing.JFrame;

import gui.JLabelMeteorito;

/** Clase de los enemigos que son Meteoritos
 * @author jonlc
 *
 */
/**
 * @author juxam
 *
 */
public class MeteoritoEnemigo extends ObjetoJuego{

	protected double danyoAJugador; //Daño que el meteorito hara al jugador en caso de impactar contra el
	private JLabelMeteorito lMeteorito;
	
	
	
	public MeteoritoEnemigo() {
		super(150.0,150.0,150,0,20,1);
		lMeteorito = new JLabelMeteorito();
		setPosX(this.getPosX());
		setPosY(this.getPosY());
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
	
	/**
	 *Modifica la posicion en el eje x del meteorito y del jlabelMeteorito 
	 */
	@Override
	public void setPosX(double posX) {
		this.posX = posX;
		lMeteorito.setLocation((int) posX, (int) this.getPosY());
	}
	
	 
	/**
	 *Modifica la posicion en el eje y del meteorito y del jlabelMeteorito 
	 */
	@Override
	public void setPosY(double posY) {
		this.posY = posY;
		lMeteorito.setLocation((int)this.getPosX(),(int) posY);
	}
	
	/**
	 * @return El jlabel del meteorito
	 */
	public JLabelMeteorito getlMeteorito() {
		return lMeteorito;
	}

	
	/**Modifica el jlabel del meteorito
	 * @param lMeteorito Nuevo jlabel del meteorito
	 */
	public void setlMeteorito(JLabelMeteorito lMeteorito) {
		this.lMeteorito = lMeteorito;
	}
	

	/**Mueve el meteorito en linea recta con una velocidad constante
	 *
	 */
	@Override 
	public void mover(double tiempo) {
		this.setPosY(posY + (velocidadY *tiempo));
	}
	
}
