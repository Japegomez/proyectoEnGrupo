package logica;

import javax.swing.*;

public class Cronometro extends JLabel {
	private int hora; // horas de duracion de la partida
	private int minutos; // minutos de duracion de la partida
	private int segundos; // segundos de duracion de la partida
	private int milesimas; // milesimas de duracion de la partida
	private boolean cronometroActivo; // si esta jugando es igual a true, su muere igual a false

	public Cronometro() {
		super("00:00:000");

		cronometroActivo = true;
		new Thread() {
			public void run() {
				try {

					while (cronometroActivo) {
						Thread.sleep(1);
						milesimas += 1;
						if (milesimas == 1000) {
							milesimas = 0;
							segundos += 1;
							
							if (segundos == 60) {
								segundos = 0;
								minutos++;
							}
						}
						Cronometro.this.setText(minutos + ":" + segundos + ":" + milesimas);
					}

				} catch (Exception e) {
				}
				// Cuando se reincie se coloca nuevamente en 00:00:000
				
				Cronometro.this.setText("00:00:000");
			}
		}.start();

	}

	/**
	 * El cronometro para de seguir sumando.
	 */
	public void pausarCrono() {
		setCronometroActivo(false);
	}

	/**
	 * @return Si el cronometro esta corriendo.
	 */
	public boolean isCronometroActivo() {
		return cronometroActivo;
	}

	/**
	 * Modifica el estado del cronometro.
	 * 
	 * @param  cronometroActivo Nuevo estado del cronometro.
	 */
	public void setCronometroActivo(boolean cronometroActivo) {
		this.cronometroActivo = cronometroActivo;
	}

	/**
	 * @return Las horas jugadas de la partida.
	 */
	public int getHora() {
		return hora;
	}

	/**
	 * Modifica la hora de la partida.
	 * 
	 * @param hora Nueva hora de la partida.
	 */
	public void setHora(int hora) {
		this.hora = hora;
	}

	/**
	 * @return Los minutos jugados de la partida.
	 */
	public int getMinutos() {
		return minutos;
	}

	/**
	 * Modifica los minutos de la partida.
	 * 
	 * @param minutos Nuevos minutos de la partida.
	 */
	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	/**
	 * @return Los segundos jugados de la partida.
	 */
	public int getSegundos() {
		return segundos;
	}

	/**
	 * Modifica los segundos de la partida.
	 * 
	 * @param segundos Nuevos segundos de la partida.
	 */
	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}

	/**
	 * @return Las milesimas jugadas de la partida.
	 */
	public int getMilesimas() {
		return milesimas;
	}

	/**
	 * Modifica las milesimas de la partida.
	 * 
	 * @param milesimas Nuevas milesimas de la partida.
	 */
	public void setMilesimas(int milesimas) {
		this.milesimas = milesimas;
	}

}
