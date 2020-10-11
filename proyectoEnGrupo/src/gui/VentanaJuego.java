package gui;

import javax.swing.*;

import logica.NaveJugador;

public class VentanaJuego extends JFrame {
	
	NaveJugador nave;
	public VentanaJuego() {
		super("jugando...");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(400, 150);
		setSize(500, 500);
		setResizable(false);
		getContentPane().setLayout(null);
		
		nave = new NaveJugador(5, 5, 5);
		
		this.add(nave.getlNave());
		nave.setPosX(250);
		nave.setPosY(250);
		
		

	}

}
