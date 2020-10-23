package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
		creaMeteorito();
		
		new Thread(){
			@Override
			public void run() {
				int i = 0;
				while(true) {
					for (MeteoritoEnemigo o:arrayMeteoritos) {
						o.mover(0.1);
						//System.out.println(o.getPosY());
						
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
		
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				//System.out.println("Presionada");
				int c = e.getKeyCode();
				if (c==38) {
					if(nave.getPosY()>200) {
						nave.setPosY(nave.getPosY()-nave.getVelocidadY());
						System.out.println("arriba");
					}
						
				}
				else if (c==40) {
					if(nave.getPosY()<pPrincipal.getHeight()) {
						nave.setPosY(nave.getPosY()+nave.getVelocidadY());
						System.out.println("abajo");
					}
						
				}
				else if (c==37) {
					if(nave.getPosX()>0) {
						nave.setPosX(nave.getPosX()-nave.getVelocidadX());
						System.out.println("izquierda");
					}
						
				}
				else if(c==39) {
					if(nave.getPosX()>pPrincipal.getWidth()) {
						nave.setPosX(nave.getPosX()+nave.getVelocidadX());
						System.out.println("derecha");
					}
						
				}			
			}

			@Override
			public void keyReleased(KeyEvent e) {
				//System.out.println("Dejar de presionar");

			}

			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("PRESIONADA");

			}
		 });
	}
	
	public void creaNave() {
		nave = new NaveJugador();
		nave.setPosX(this.getWidth()/2-nave.getlNave().getAnchoNave()/2);
		nave.setPosY(pPrincipal.getHeight()-nave.getlNave().getHeight());
		nave.setVelocidadX(2);
		nave.setVelocidadY(2);
		pPrincipal.add(nave.getlNave());
	}

	public void creaMeteorito() {
			MeteoritoEnemigo me1 = new MeteoritoEnemigo();
			me1.setPosX(Math.random()*(this.getWidth()-(me1.getlMeteorito().getWidth()*1.5))+me1.getlMeteorito().getWidth());
			me1.setPosY(me1.getlMeteorito().getHeight());
			me1.setVelocidadY(3);
			pPrincipal.add(me1.getlMeteorito());
			arrayMeteoritos.add(me1);
			
	}
	
	
}
