package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

import logica.MeteoritoEnemigo;
import logica.NaveJugador;

public class VentanaJuego extends JFrame {
	private PanelFondo pPrincipal;
	NaveJugador nave;
	public VentanaJuego() {
		super("jugando...");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(400, 150);
		setSize(500, 500);
		setResizable(false);
		
		pPrincipal = new PanelFondo();
		pPrincipal.setLayout( null );
		add(pPrincipal);
	

	}
	
	public void creaNave() {
		nave = new NaveJugador();
		nave.setPosX(this.getWidth()/2-nave.getlNave().getAnchoNave()/2);
		nave.setPosY(pPrincipal.getHeight()-nave.getlNave().getHeight());
		pPrincipal.add(nave.getlNave());
	}

	public void creaMeteorito() {
		MeteoritoEnemigo me1 = new MeteoritoEnemigo();
		me1.setPosX(Math.random()*(this.getWidth()-me1.getlMeteorito().getWidth())+me1.getlMeteorito().getWidth());
		me1.setPosY(me1.getlMeteorito().getHeight());
		pPrincipal.add(me1.getlMeteorito());

	}
}
