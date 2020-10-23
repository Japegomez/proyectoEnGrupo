package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import logica.MeteoritoEnemigo;
import logica.NaveJugador;
import logica.Partida;

public class VentanaJuego extends JFrame {
	//.S
	private PanelFondo pPrincipal;
	NaveJugador nave;
	public ArrayList<MeteoritoEnemigo> arrayMeteoritosEnPantalla = new ArrayList<>();
	public ArrayList<MeteoritoEnemigo> arrayMeteoritosEliminados = new ArrayList<>();
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
				boolean funciona = true;
				while(funciona) {
					for (MeteoritoEnemigo o:arrayMeteoritosEnPantalla) {
						if (!arrayMeteoritosEliminados.contains(o)) {
							o.mover(0.01);
						}
						if(o.getPosY()>pPrincipal.getHeight()) {
							arrayMeteoritosEliminados.add(o);
						}
						
						try { Thread.sleep(20); } catch (InterruptedException e) {
							System.err.println(e);
						}

					}
				}
				
				
			}
		}.start();
		
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				//System.out.println("Presionada");
				int c = e.getKeyCode();
				System.out.println(nave.getPosX() + ", " + nave.getPosY());
				if (c==38) {
					if(nave.getPosY()>225) {
						nave.setPosY(nave.getPosY()-nave.getVelocidadY());
						System.out.println("arriba");
					}
					else {
						nave.setPosY(nave.getPosY());
					}
						
				}
				else if (c==40) {
					if(nave.getPosY()<pPrincipal.getHeight()-nave.getlNave().getHeight()) {
						nave.setPosY(nave.getPosY()+nave.getVelocidadY());
						System.out.println("abajo");
					}
					else {
						nave.setPosY(nave.getPosY());
					}	
				}
				else if (c==37) {
					if(nave.getPosX()> 0) {
						nave.setPosX(nave.getPosX()-nave.getVelocidadX());
						System.out.println("izquierda");
					}
					else {
						nave.setPosX(nave.getPosX());
					}	
				}
				else if(c==39) {
					if(nave.getPosX()<pPrincipal.getWidth()-(nave.getlNave().getAnchoNave())) {
						nave.setPosX(nave.getPosX()+nave.getVelocidadX());
						System.out.println("derecha");
					}
					else {
						nave.setPosX(nave.getPosX());
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
//		nave.getlNave().setBorder(new LineBorder(Color.YELLOW));
		nave.setPosX(this.getWidth()/2-nave.getlNave().getAnchoNave()/2);
		nave.setPosY(pPrincipal.getHeight()-nave.getlNave().getHeight());
		nave.setVelocidadX(2);
		nave.setVelocidadY(2);
		pPrincipal.add(nave.getlNave());
	}

	public void creaMeteorito() {
			MeteoritoEnemigo me1 = new MeteoritoEnemigo();
			double x = Math.random()*(((this.getWidth()-me1.getlMeteorito().getWidth())- 0) + 0);
			x = checkeaXMeteorito(x, me1.getlMeteorito().getWidth());
			me1.setPosX(x);
			me1.setPosY(-(me1.getlMeteorito().getHeight()));
			me1.setVelocidadY(9);
			pPrincipal.add(me1.getlMeteorito());
			arrayMeteoritosEnPantalla.add(me1);
			
	}
	
	public double checkeaXMeteorito(double x, int AnchoMeteorito) {
		boolean sigue = true;
		while(sigue) {
			int i = 0;
			for (MeteoritoEnemigo meteoritoEnemigo : arrayMeteoritosEnPantalla) {
				if(!(x>meteoritoEnemigo.getPosX()-meteoritoEnemigo.getlMeteorito().getWidth() && x<meteoritoEnemigo.getPosX()+2*meteoritoEnemigo.getlMeteorito().getWidth())) {
				i ++;
				}
			}
			if (i == arrayMeteoritosEnPantalla.size()) sigue = false;
	
			else {
				 x = Math.random()*(((this.getWidth()-AnchoMeteorito)- 0) + 0);
			}
		}
		return x;
	}
	
}
