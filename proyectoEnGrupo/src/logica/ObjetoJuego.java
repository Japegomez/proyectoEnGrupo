package logica;

public class ObjetoJuego {
	
	protected double posX;
	protected double posY;
	protected int vida;
	protected double velocidadX;
	protected double velocidadY;
	protected double velocidadMovimiento;
	
	public void ObjetoJuego() {
		
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public double getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(double velocidadX) {
		this.velocidadX = velocidadX;
	}

	public double getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadY(double velocidadY) {
		this.velocidadY = velocidadY;
	}
	
	public void mover() {
		this.posX += this.velocidadX * this.velocidadMovimiento;
		this.posY += this.velocidadY * this.velocidadMovimiento;
		
	}
}
