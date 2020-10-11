package logica;

import gui.JLabelNave;

public class NaveJugador extends ObjetoJuego {
	
	protected double velocidadAtaque;
	protected double danyoAtaque;
	protected double ataqueCargado;
	private JLabelNave lNave;
	
	
	/**Devuelve la velocidad de ataque de la nave
	 * @return Velocidad de ataque de la nave
	 */
	public double getVelocidadAtaque() {
		return velocidadAtaque;
	}
	
	/**Modifica la velocidad de ataque de la nave
	 * @param velocidadAtaque Nueva velocidad de ataque de la nave
	 */
	public void setVelocidadAtaque(double velocidadAtaque) {
		this.velocidadAtaque = velocidadAtaque;
	}
	
	/**Devuelve el danyo del ataque de la nave
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

	public double getAtaqueCargado() {
		return ataqueCargado;
	}

	public void setAtaqueCargado(double ataqueCargado) {
		this.ataqueCargado = ataqueCargado;
	}

	public JLabelNave getlNave() {
		return lNave;
	}

	public void setlNave(JLabelNave lNave) {
		this.lNave = lNave;
	}

	public NaveJugador(double velocidadAtaque, double danyoAtaque, double ataqueCargado) {
		super();
		this.velocidadAtaque = velocidadAtaque;
		this.danyoAtaque = danyoAtaque;
		this.ataqueCargado = ataqueCargado;
		lNave = new JLabelNave();
	}
	
	@Override
	public void setPosX(double posX) {
		this.posX = posX;
		lNave.setLocation((int) posX, (int) this.getPosY());
	}
	@Override
	public void setPosY(double posY) {
		this.posY = posY;
		lNave.setLocation((int)this.getPosX(),(int) posY);
	}
	
	
}
