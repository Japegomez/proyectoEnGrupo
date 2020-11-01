package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import logica.MeteoritoEnemigo;
import logica.NaveJugador;
import logica.ObjetoJuego;
import logica.Partida;

public class VentanaJuego extends JFrame {
	//.S
	private PanelFondo pPrincipal;
	NaveJugador nave;
	public ArrayList<MeteoritoEnemigo> arrayMeteoritos = new ArrayList<MeteoritoEnemigo>();
	
	private boolean juegoAcabado = false;
	
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
							o.mover(0.1);	
							
						}
						if(o.getPosY()>pPrincipal.getHeight()) {
							arrayMeteoritosEliminados.add(o);
						}
						try { Thread.sleep(20); } catch (InterruptedException e) {
							System.err.println(e);
						}
					}
					choqueConMeterorito();
					if (estaMuerto()) {
						funciona = false;
						gameOver();
					}
				}

				
				repaint();
				
			}}.start();
			

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
						if(nave.getPosY()<pPrincipal.getHeight()) {
							nave.setPosY(nave.getPosY()+nave.getVelocidadY());
							System.out.println("abajo");
						}
						else {
							nave.setPosY(nave.getPosY());
						}	
					}
					else if (c==37) {
						if(nave.getPosX()>0) {
							nave.setPosX(nave.getPosX()-nave.getVelocidadX());
							System.out.println("izquierda");
						}
						else {
							nave.setPosX(nave.getPosX());
						}	
					}
					else if(c==39) {
						if(nave.getPosX()<pPrincipal.getWidth()-(nave.getlNave().getAnchoNave()/2)) {
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
					//System.out.println("PRESIONADA");
				}
			 });
		
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
			me1.setDanyoAJugador(10);
			// me1.setVelocidadY(me1.getVelocidadY());
			pPrincipal.add(me1.getlMeteorito());
			arrayMeteoritosEnPantalla.add(me1);
			
	}
	
	/**Cuando la vida de la nave es menor o igual que cero, se acaba el juego.
	 * 
	 */
	public void gameOver() {
		this.dispose();
		JOptionPane.showMessageDialog(this, "Game Over","Game Over",JOptionPane.YES_NO_OPTION);
		VentanaMenu v3 = new VentanaMenu("menu");
		v3.setVisible(true);
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

	/**Comprueba si la nave y un meteorito han chocado.
	 * 
	 */
	public void choqueConMeterorito() {
		if (nave == null) return;
		ArrayList<MeteoritoEnemigo> aEliminar = new ArrayList<MeteoritoEnemigo>();
		for (MeteoritoEnemigo me : arrayMeteoritosEnPantalla) {
			if(!arrayMeteoritosEliminados.contains(me)) { // sobra si esta bien programado
				if (me.getBounds().intersects(nave.getBounds())) {
					
					aEliminar.add(me);
					nave.setVida(nave.getVida()- (int)me.getDanyoAJugador());
					System.out.println("Han chocado!!");
					System.out.println(nave.getVida());
				}
			}

		}
		for (MeteoritoEnemigo me : aEliminar) {
			arrayMeteoritosEliminados.add(me);
			arrayMeteoritosEnPantalla.remove(me);
			pPrincipal.remove(me.getlMeteorito());
		}

	}
	/**Devuelve true si la vida de la nave es menor o igual que cero.
	 * @return  
	 */
	private boolean estaMuerto() {
		if (nave == null) return false;
		if(nave.getVida()<=0) {
			return true;
		}
		return false;
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		//nave.paint(g2d);
		for (MeteoritoEnemigo meteoritoEnemigo : arrayMeteoritosEnPantalla) {
		//	meteoritoEnemigo.paint(g2d);
		}
		
	}
	
}
