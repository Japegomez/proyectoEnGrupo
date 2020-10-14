package gui;

import javax.swing.*;

import logica.MeteoritoEnemigo;
import logica.NaveJugador;

public class VentanaJuego extends JFrame {
	
	NaveJugador nave;
	public VentanaJuego() {
		super("jugando...");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(400, 150);
		setSize(500, 500);
		setResizable(false);
		//getContentPane().setLayout(null);
		
		nave = new NaveJugador(5, 5, 5);
		
		this.add(nave.getlNave());
		nave.setPosX(250);
		nave.setPosY(250);
		
		MeteoritoEnemigo me1 = new MeteoritoEnemigo();
		
		this.add(me1.getlMeteorito());
		me1.setPosX(400);
		me1.setPosY(400);

	}

}
