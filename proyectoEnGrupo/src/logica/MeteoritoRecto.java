package logica;

public class MeteoritoRecto  extends MeteoritoEnemigo{
	
	@Override 
	public void moverRecto() {
		this.posY = posY + (velocidadY * Math.cos(Math.random()*1));
	}

	
	@Override 
	public void moverParabola() {
		int anguloSalida= (int) Math.floor(Math.random()*(180-90)+90);
		this.posX= velocidadX * Math.cos(anguloSalida);
		this.posY= posY + velocidadY*Math.sin(anguloSalida) - (1/2*(aceleracion*aceleracion)); ;
	}
}
