package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.*;

import logica.MeteoritoEnemigo;
import logica.NaveJugador;
import logica.Partida;

public class VentanaJuego extends JFrame {
	//.S
	private PanelFondo pPrincipal;
	NaveJugador nave;
	public ArrayList<MeteoritoEnemigo> arrayMeteoritos = new ArrayList<MeteoritoEnemigo>();
	public VentanaJuego() {
		super("jugando...");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(400, 150);
		setSize(500, 500);
		setResizable(false);
		
		pPrincipal = new PanelFondo();
		pPrincipal.setLayout( null );
		add(pPrincipal);
		
		creaMeteorito();
		creaMeteorito();
		
		new Thread(){
			@Override
			public void run() {
				int i = 0;
				while(true) {
					for (MeteoritoEnemigo o:arrayMeteoritos) {
						o.mover(0.040);
						System.out.println(o.getPosY());
						
						try { Thread.sleep(40); } catch (InterruptedException e) {
							System.err.println(e);
						}

					}
//					for (MeteoritoEnemigo a:arrayMeteoritos) {
//						if ( a.getPosY()>pPrincipal.getHeight()) {
//							arrayMeteoritos.remove(a);
//							VentanaJuego.this.remove(a.getlMeteorito());
//					}
//					}
				}
				
				
			}
		}.start();
	
	}
	
	public void creaNave() {
		nave = new NaveJugador();
		nave.setPosX(this.getWidth()/2-nave.getlNave().getAnchoNave()/2);
		nave.setPosY(pPrincipal.getHeight()-nave.getlNave().getHeight());
		pPrincipal.add(nave.getlNave());
	}

	public void creaMeteorito() {
			MeteoritoEnemigo me1 = new MeteoritoEnemigo();
			me1.setPosX(Math.random()*(this.getWidth()-(me1.getlMeteorito().getWidth()*1.5))+me1.getlMeteorito().getWidth());
			me1.setPosY(me1.getlMeteorito().getHeight());
			pPrincipal.add(me1.getlMeteorito());
			arrayMeteoritos.add(me1);
			
	}
	
	
}
