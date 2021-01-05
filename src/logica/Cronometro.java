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
				getTiempo(minutos + ":" + segundos + ":" + milesimas);
				Cronometro.this.setText("00:00:000");
			}
		}.start();

	}

	/**
	 * El cronometro para de seguir sumando
	 * 
	 */
	public void pausarCrono() {
		setCronometroActivo(false);
	}

	public String getTiempo(String s) {
		return s;
	}

	public boolean isCronometroActivo() {
		return cronometroActivo;
	}

	public void setCronometroActivo(boolean cronometroActivo) {
		this.cronometroActivo = cronometroActivo;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public int getSegundos() {
		return segundos;
	}

	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}

	/**
	 * @return Milesimas del cronometro de la partida.
	 */
	public int getMilesimas() {
		return milesimas;
	}

	/**
	 * Modifica las milesimas del cronometro
	 * 
	 * @param milesimas
	 */
	public void setMilesimas(int milesimas) {
		this.milesimas = milesimas;
	}

	public static void main(String[] args) {
		Cronometro c = new Cronometro();

		JFrame jp = new JFrame("prueba cronometro");
		jp.add(c);
		jp.setVisible(true);
		jp.pack();
	}
}
