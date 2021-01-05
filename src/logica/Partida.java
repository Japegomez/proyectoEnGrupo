package logica;
import java.util.Date;

import gui.VentanaJuego;

public class Partida implements Comparable<Partida>{
	private double puntuacion; //puntuacion que se obtiene al finalizar la partida
	private long fecha;

	/**Crea una partida
	 * 
	 */
	public Partida() {
		this.fecha = System.currentTimeMillis();
		this.puntuacion = 0;
	}

	public Partida(double puntuacion, long fecha) {
		super();
		this.puntuacion = puntuacion;
		this.fecha = fecha;
	}

	/**
	 * @return puntuacion de la partida
	 */
	public double getPuntuacion() {
		return puntuacion;
	}

	/**Modifica la puntuacion de la partida
	 * @param puntuacion
	 */
	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}

	/**
	 * @return Fecha de la partida.
	 */
	public long getFecha() {
		return fecha;
	}

	/**Modifica la fecha de la partida
	 * @param fecha
	 */
	public void setFecha(long fecha) {
		this.fecha = fecha;
	}

	/** Dos partidas son iguales si las fechas de las partidas coinciden.
	 * @param obj
	 */
	@Override
	public boolean equals(Object obj) {
			if (!(obj instanceof Partida)) return false;
			return fecha == ((Partida)obj).fecha; 
		
	}
	@Override
	public int hashCode() {
		return ((Long)fecha).hashCode();
	}

	
	@Override
	public int compareTo(Partida o) {
		return ((Double)puntuacion).compareTo(o.puntuacion);
	}
	
	@Override
	public String toString() {
		return "puntuacion: " + this.puntuacion + " / fecha: " + new Date(this.fecha).toString();
	}
	
}
