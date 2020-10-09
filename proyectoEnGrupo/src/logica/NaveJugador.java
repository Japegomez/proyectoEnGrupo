package logica;

public class NaveJugador extends ObjetoJuego {
	
	protected double velocidadAtaque;
	protected double danyoAtaque;
	protected double ataqueCargado;
	
	
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
	
	
}
