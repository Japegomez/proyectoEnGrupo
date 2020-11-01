package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import logica.*;

public class VentanaJuego extends JFrame {
	
	private PanelFondo pPrincipal; //Panel de juego
	private Usuario us; //usuario de la partida
	private NaveJugador nave; //nave del usuario
	private Partida part; //partida
	
	private boolean juegoAcabado = false;
	
	private ArrayList<MeteoritoEnemigo> arrayMeteoritosEnPantalla = new ArrayList<>(); //lista de meteoritos añadidos a la pantalla
	private ArrayList<MeteoritoEnemigo> arrayMeteoritosEliminados = new ArrayList<>(); //lista de meteoritos eliminados de la pantalla
	

	/**Constructor de la ventanaJuego
	 * @param usuario usuario de la partida
	 * @param v1 VentanaMenu de la que precede VentanaJuego
	 */
	public VentanaJuego(Usuario usuario, VentanaMenu v1) {
		super("jugando...");
		us = usuario;
		part = new Partida();
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
			
			addWindowListener(new WindowAdapter() {
			
				@Override
				public void windowOpened(WindowEvent e) {
					BaseDatos.abrirConexion("baseDatos.bd");
					
				}
				@Override
				public void windowClosed(WindowEvent e) {
					BaseDatos.aniadirPartida(VentanaJuego.this.part.getPuntuacion(), usuario.getNombreUsuario());
					v1.setVisible(true);
					BaseDatos.cerrarConexion();
				}
			});
			this.addKeyListener(new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent e) {
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
			 });
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

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
		 });
	}
	
	/**Crea una nave  y la posiciona en relacion al panel de juego
	 * 
	 */
	public void creaNave() {
		nave = new NaveJugador();
		nave.setPosX(this.getWidth()/2-nave.getlNave().getAnchoNave()/2);
		nave.setPosY(pPrincipal.getHeight()-nave.getlNave().getHeight());
		nave.setVelocidadX(2);
		nave.setVelocidadY(2);
		pPrincipal.add(nave.getlNave());
	}

	/**Crea un meteorito y lo posiciona en relacion al panel de juego
	 * 
	 */
	public void creaMeteorito() {
			MeteoritoEnemigo me1 = new MeteoritoEnemigo();
			double x = Math.random()*(((this.getWidth()-me1.getlMeteorito().getWidth())- 0) + 0);
			x = checkeaXMeteorito(x, me1.getlMeteorito().getWidth());
			me1.setPosX(x);
			me1.setPosY(-(me1.getlMeteorito().getHeight()));
			me1.setDanyoAJugador(10);
			pPrincipal.add(me1.getlMeteorito());
			arrayMeteoritosEnPantalla.add(me1);
			
	}
	
	/**Cierra la ventana e informa al usuario  del game over
	 * 
	 */
	public void gameOver() {
		this.dispose();
		JOptionPane.showMessageDialog(this, "Game Over","Game Over",JOptionPane.YES_NO_OPTION);
	}
	
	/**Comprueba que el meteorito no se superpone sobre otro meteorito en pantalla
	 * @param x posicionamiento en el eje x del meteorito (en pixels)
	 * @param AnchoMeteorito ancho del JLabel de meteorito (en pixels)
	 * @return
	 */
	public double checkeaXMeteorito(double x, int AnchoMeteorito) {
		boolean sigue = true;
		while(sigue) {
			int i = 0;
			for (MeteoritoEnemigo meteoritoEnemigo : arrayMeteoritosEnPantalla) {
				if(!arrayMeteoritosEliminados.contains(meteoritoEnemigo)) {
					if(!(x>meteoritoEnemigo.getPosX()-meteoritoEnemigo.getlMeteorito().getWidth() && x<meteoritoEnemigo.getPosX()+2*meteoritoEnemigo.getlMeteorito().getWidth())) {
						i ++;
					}
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
			if(!arrayMeteoritosEliminados.contains(me)) { 
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

	/**Comprueba si la nave tiene vida
	 * @return  true si no tiene vida, false si tiene.
	 */
	public boolean estaMuerto() {
		if(nave == null) return false;
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
