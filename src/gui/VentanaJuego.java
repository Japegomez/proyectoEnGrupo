package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import logica.BaseDatos;
import logica.Disparo;
import logica.MeteoritoEnemigo;
import logica.NaveJugador;
import logica.ObjetoJuego;
import logica.Partida;
import logica.Usuario;
import logica.Cronometro;
import logica.*;

public class VentanaJuego extends JFrame {

	private PanelFondo pPrincipal; //Panel de juego
	private Usuario us; //usuario de la partida
	private NaveJugador nave; //nave del usuario
	private Partida part; //partida
	private int vidaNavePartida;
	
	public ArrayList<MeteoritoEnemigo> arrayMeteoritosEnPantalla = new ArrayList<>();
	private ArrayList<MeteoritoEnemigo> arrayMeteoritosEliminados = new ArrayList<>();
	private ArrayList<MeteoritoEnemigo> arrayMeteoritosEvitados = new ArrayList<>();
	private ArrayList<MeteoritoEnemigo> arrayMeteoritosDisparados = new ArrayList<>();
	private ArrayList<Disparo> arrayDisparo = new ArrayList<>();
	private Cronometro cro;


	/**Constructor de la ventanaJuego
	 * @param usuario usuario de la partida
	 * @param v1 VentanaMenu de la que precede VentanaJuego
	 */
	public VentanaJuego(Usuario usuario, VentanaMenu v1) {

		super("jugando...");
		us = usuario;
		nave = us.getNave();
		vidaNavePartida = nave.getVida();
		part = new Partida();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocation(400, 150);
		setSize(500, 500);
		setResizable(false);
		
		
		pPrincipal = new PanelFondo();
		pPrincipal.setLayout( null );
		add(pPrincipal);
		cro = new Cronometro();
		//pPrincipal.add(cro);
		

		
		
		try { Thread.sleep(20); } catch (InterruptedException e) {
			System.err.println(e);
		}
		
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
							arrayMeteoritosEvitados.add(o);
							arrayMeteoritosEliminados.add(o);
						}
						try { Thread.sleep(20); } catch (InterruptedException e) {
							System.err.println(e);
						}
					}
					try {
						for(Disparo disparo : arrayDisparo) {
							disparo.mover(0.1);
						}
					}catch (Exception e) {
						e.printStackTrace();
					}
					
					choqueConMeteorito();
					DisparoChoqueMeteorito();
					
					
					if (estaMuerto()) {
						funciona = false;
						cro.pausarCrono();
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
					if (c==38) {
						if(nave.getPosY()>225) {
							nave.setPosY(nave.getPosY()-nave.getVelocidadY());
						}
						else {
							nave.setPosY(nave.getPosY());
						}
							
					}
					else if (c==40) {
						if(nave.getPosY()<pPrincipal.getHeight()) {
							nave.setPosY(nave.getPosY()+nave.getVelocidadY());
						}
						else {
							nave.setPosY(nave.getPosY());
						}	
					}
					else if (c==37) {
						if(nave.getPosX()>0) {
							nave.setPosX(nave.getPosX()-nave.getVelocidadX());
						}
						else {
							nave.setPosX(nave.getPosX());
						}	
					}
					else if(c==32){
						crearDisparo();
						
					}
					else if(c==39) {
						if(nave.getPosX()<pPrincipal.getWidth()-(nave.getlNave().getAnchoNave()/2)) {
							nave.setPosX(nave.getPosX()+nave.getVelocidadX());
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
				if (c==38) {
					if(nave.getPosY()>225) {
						nave.setPosY(nave.getPosY()-nave.getVelocidadY());
					}
					else {
						nave.setPosY(nave.getPosY());
					}
						
				}
				else if (c==40) {
					if(nave.getPosY()<pPrincipal.getHeight()-nave.getlNave().getHeight()) {
						nave.setPosY(nave.getPosY()+nave.getVelocidadY());
					}
					else {
						nave.setPosY(nave.getPosY());
					}	
				}
				else if (c==37) {
					if(nave.getPosX()> 0) {
						nave.setPosX(nave.getPosX()-nave.getVelocidadX());
					}
					else {
						nave.setPosX(nave.getPosX());
					}	
				}
				else if(c==39) {
					if(nave.getPosX()<pPrincipal.getWidth()-(nave.getlNave().getAnchoNave())) {
						nave.setPosX(nave.getPosX()+nave.getVelocidadX());
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
		nave = us.getNave(); 
		nave.setPosX(this.getWidth()/2-nave.getlNave().getAnchoNave()/2);
		nave.setPosY(pPrincipal.getHeight()-nave.getlNave().getHeight());
		nave.setVelocidadX(2);
		nave.setVelocidadY(2);
		pPrincipal.add(nave.getlNave());
	}
	
	public void crearDisparo() {
		Disparo dis = new Disparo(nave.getPosX()-nave.getlNave().getAnchoNave()/2,nave.getPosY() - nave.getlNave().getAltoNave()/2,1,0,5);
		pPrincipal.add(dis.getlDisparo());
		arrayDisparo.add(dis);
	}

	/**Crea un meteorito y lo posiciona en relacion al panel de juego(fuera de la pantalla)
	 * 
	 */
	public void creaMeteorito() {
			MeteoritoEnemigo me1 = new MeteoritoEnemigo();
			double x = Math.random()*(((this.getWidth()-me1.getlMeteorito().getWidth())- 0) + 0);
			while(!checkeaXMeteorito(x)) {
				x = Math.random()*(((this.getWidth()-me1.getlMeteorito().getWidth())- 0) + 0);
			}
			me1.setPosX(x);
			me1.setPosY(-(me1.getlMeteorito().getHeight()));
			me1.setDanyoAJugador(10);
			pPrincipal.add(me1.getlMeteorito());
			arrayMeteoritosEnPantalla.add(me1);
			
	}
	
	/**Cierra la ventana y actualiza los creditos del jugador
	 * 
	 */
	public void gameOver() {
		cro.pausarCrono();
		puntosTiempo();
		puntosMeteoritos();
		creditosPartida();
		BaseDatos.actualizarCreditosUsuario(us, us.getCreditos());
		this.dispose();
		JOptionPane.showMessageDialog(this, "Game Over" +"\n" + "Puntuacion : " + part.getPuntuacion() +"\n " +"Tiempo: " + cro.getMinutos() +" : "+ cro.getSegundos() + "\n"+ "creditos obtenidos: " + part.getPuntuacion(),"Game Over",JOptionPane.YES_NO_OPTION);
	}
	
	/**Comprueba que el meteorito no se superpone sobre otro meteorito en pantalla
	 * @param x posicionamiento en el eje x del meteorito (en pixels)
	 * @return true si x correcta y no se sobrepone, false si se sobrepone
	 */
	public boolean checkeaXMeteorito(double x) {
			int i = 0;
			for (MeteoritoEnemigo meteoritoEnemigo : arrayMeteoritosEnPantalla) {
				if(!arrayMeteoritosEliminados.contains(meteoritoEnemigo)) {
					if(!(x>meteoritoEnemigo.getPosX()-meteoritoEnemigo.getlMeteorito().getWidth() && x<meteoritoEnemigo.getPosX()+2*meteoritoEnemigo.getlMeteorito().getWidth())) {
						i ++;
					}
				}
			}
			if (i == arrayMeteoritosEnPantalla.size()) return true;
	
			else {
				return false;
			}
	}

	/**Comprueba si la nave y un meteorito han chocado.
	 * 
	 */
	public void choqueConMeteorito() {
		if (nave == null) return;
		ArrayList<MeteoritoEnemigo> aEliminar = new ArrayList<MeteoritoEnemigo>();
		for (MeteoritoEnemigo me : arrayMeteoritosEnPantalla) {
			if(!arrayMeteoritosEliminados.contains(me)) {
				if (me.getBounds().intersects(nave.getBounds())) {
					aEliminarObjetos(me);
					aEliminar.add(me);
					vidaNavePartida = vidaNavePartida - (int) me.getDanyoAJugador();
					System.out.println("Ha chocado !!");
					System.out.println(vidaNavePartida);
				}
			}
		}
		for (MeteoritoEnemigo me : aEliminar) {
			arrayMeteoritosEliminados.add(me);
			arrayMeteoritosEnPantalla.remove(me);
		}
		
	}
	

	
	/**Comprueba si un disparo y la nave han chocado
	 * 
	 */
	public void DisparoChoqueMeteorito() {
		ArrayList<MeteoritoEnemigo> aEliminarMeteortitos = new ArrayList<MeteoritoEnemigo>();
		ArrayList<Disparo> aEliminarDisparo = new ArrayList<Disparo>();
		if(arrayDisparo.size()>0) {
			for(Disparo dis : arrayDisparo) {
				for (MeteoritoEnemigo me : arrayMeteoritosEnPantalla) {
					if(!arrayMeteoritosEliminados.contains(me)) {
						if (me.getBounds().intersects(dis.getBounds())) {
							me.setDanyoAJugador(0);
							aEliminarObjetos(me);
							aEliminarObjetos(dis);
							aEliminarMeteortitos.add(me);
							aEliminarDisparo.add(dis);
							System.out.println("Han chocado !!");
						}
					}
				}
			}
			
		}
		for (MeteoritoEnemigo me : aEliminarMeteortitos) {
			arrayMeteoritosEliminados.add(me);
			arrayMeteoritosDisparados.add(me);
			arrayMeteoritosEnPantalla.remove(me);
		}
		for(Disparo dis : aEliminarDisparo) {
			arrayDisparo.remove(dis);
		}
	}
	
	/**Elimina el JLabel del objeto en la pantalla
	 * @param obj
	 */
	public void aEliminarObjetos(ObjetoJuego obj) {
		if (obj instanceof Disparo) {
			Disparo dis = (Disparo) obj;
			pPrincipal.remove(dis.getlDisparo());
		}else if(obj instanceof MeteoritoEnemigo) {
			MeteoritoEnemigo mete = (MeteoritoEnemigo) obj;
			pPrincipal.remove(mete.getlMeteorito());
		}
	}

	/**Comprueba si la nave tiene vida
	 * @return  true si no tiene vida, false si tiene.
	 */
	public boolean estaMuerto() {
		if(nave == null) return false;
		if(vidaNavePartida<=0) {
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
		//for (MeteoritoEnemigo meteoritoEnemigo : arrayMeteoritosEnPantalla) {
		//	meteoritoEnemigo.paint(g2d);
		}
	public void puntosTiempo() {
		double puntuacion = part.getPuntuacion();
		puntuacion+= (60*cro.getMinutos())+cro.getSegundos();
		part.setPuntuacion(puntuacion);
	}

	public void puntosMeteoritos() {
		double puntuacion = part.getPuntuacion();
		for (MeteoritoEnemigo meteorito: arrayMeteoritosDisparados) {
			puntuacion += meteorito.getVALOR_PUNTUACION();
		}
		part.setPuntuacion(puntuacion);
	}
	
	/**Le otorga al jugador los creditos que le corresponden tras haber jugado la partida
	 * 
	 */
	public void creditosPartida() {
		us.setCreditos(us.getCreditos()+ part.getPuntuacion());
		

	}
	
	public Usuario getUs() {
		return us;
	}

	public void setUs(Usuario us) {
		this.us = us;
	}

	public NaveJugador getNave() {
		return nave;
	}

	public void setNave(NaveJugador nave) {
		this.nave = nave;
	}

	public Cronometro getCro() {
		return cro;
	}

	public void setCro(Cronometro cro) {
		this.cro = cro;
	}

	public int getVidaNavePartida() {
		return vidaNavePartida;
	}



	public void setVidaNavePartida(int vidaNavePartida) {
		this.vidaNavePartida = vidaNavePartida;
	}
	
}
	

