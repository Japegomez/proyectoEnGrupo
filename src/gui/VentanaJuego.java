package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;

import javax.swing.*;

import logica.BaseDatos;
import logica.Config;
import logica.Disparo;
import logica.Main;
import logica.MeteoritoEnemigo;
import logica.NaveJugador;
import logica.ObjetoJuego;
import logica.Partida;
import logica.Usuario;
import logica.Cronometro;

public class VentanaJuego extends JFrame {

	private PanelFondo pPrincipal; // Panel de juego
	private Usuario us; // usuario de la partida
	private NaveJugador nave; // nave del usuario
	private Partida part; // partida
	private int vidaNavePartida; // Vida de la nave en la partida.
	private int maxMeteoritosEnPantalla = 6; // Número máximo de meteoritos en la pantalla de juego.
	public ArrayList<MeteoritoEnemigo> arrayMeteoritosEnPantalla = new ArrayList<>(); // ArrayList de meteoritos que
																						// estan en la pantalla.
	private ArrayList<MeteoritoEnemigo> arrayMeteoritosEliminados = new ArrayList<>(); // ArrayList de meteoritos que
																						// han sido eliminados por
																						// salirse de la ventanan de
																						// juego.
	private ArrayList<MeteoritoEnemigo> arrayMeteoritosDisparados = new ArrayList<>(); // ArrayList de meterotios que
																						// has sido eliminados por
																						// disparo.
	private ArrayList<Disparo> arrayDisparo = new ArrayList<>(); // ArrayList de disparos.
	public Cronometro cro; // Cronometro de la partida.
	public JProgressBar pbVida; // JProgessbar del porcentaje de vida de la nave.
	private Config c = new Config();
	/**
	 * Constructor de la ventanaJuego
	 * 
	 * @param usuario usuario de la partida
	 * @param v1      VentanaMenu de la que precede VentanaJuego
	 */
	public VentanaJuego(Usuario usuario, VentanaMenu v1) {

		super("jugando...");
		us = usuario;
		nave = us.getNave();

		part = new Partida();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocation(400, 150);
		int size = Integer.parseInt(c.getProp("MEDIDA_VENT"));
		setSize(size, size);
		setResizable(false);
		vidaNavePartida = nave.getVida();

		pPrincipal = new PanelFondo();
		pPrincipal.setLayout(null);
		add(pPrincipal);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			System.err.println(e);
		}

		new Thread() {
			@Override
			public void run() {
				boolean funciona = true;

				while (funciona) {

					if (cro != null) {
						int segundosPasados = cro.getSegundos();
						MeteoritoEnemigo ultimoMeteorito = arrayMeteoritosEnPantalla.size() == 0 ? null
								: arrayMeteoritosEnPantalla.get(arrayMeteoritosEnPantalla.size() - 1);
						if (ultimoMeteorito != null) {
							int segundosPartidaLanzado = ultimoMeteorito.getCurrentSegundosPartida();
							if (segundosPasados <= 10) {
								// existe uno anterior
								if (segundosPartidaLanzado + 3 <= segundosPasados) {
									Main.getLogger().log(Level.FINEST, "Creando meteorito en rango 1 (<=10). Segundos pasados: " + 
											+ segundosPasados);
									creaMeteorito(10, 150);
								}

							} else if (segundosPasados <= 20) {
								if (segundosPartidaLanzado + 2 <= segundosPasados) {
									Main.getLogger().log(Level.FINEST, "Creando meteorito en rango 2 (<=20). Segundos pasados: " + 
											+ segundosPasados);
									creaMeteorito(20, 200);
								}
							} else {
								if (segundosPartidaLanzado + 1 <= segundosPasados) {
									Main.getLogger().log(Level.FINEST, "Creando meteorito en rango 3 (>=20). Segundos pasados: " + 
											+ segundosPasados);
									creaMeteorito(50, 300);
								}
							}

						} else {
							// no existen meteoritos --> se crea el primer meteorito
							Main.getLogger().log(Level.FINEST, "Creando primer meteorito");
							creaMeteorito(10, 150);
						}
					}
					ArrayList<MeteoritoEnemigo> aEliminar = new ArrayList<>();
					for (MeteoritoEnemigo o : arrayMeteoritosEnPantalla) {

						if (!arrayMeteoritosEliminados.contains(o)) {
							o.mover(1);

						}
						if (o.getPosY() > pPrincipal.getHeight()) {
							aEliminar.add(o);
							arrayMeteoritosEliminados.add(o);
						}
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							System.err.println(e);
						}
					}
					for (MeteoritoEnemigo maEliminar : aEliminar) {
						arrayMeteoritosEnPantalla.remove(maEliminar);
						aEliminarObjetos(maEliminar);
					}
					try {
						for (Disparo disparo : arrayDisparo) {
							disparo.mover(2);
						}
					} catch (Exception e) {
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

			}
		}.start();

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent e) {
				BaseDatos.aniadirPartida(VentanaJuego.this.part.getPuntuacion(), usuario.getNombreUsuario());
				v1.setVisible(true);
			}
		});
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				int c = e.getKeyCode();
				if (c == 38) {
					if (nave.getPosY() > 225) {
						nave.setPosY(nave.getPosY() - nave.getVelocidadY());
					} else {
						nave.setPosY(nave.getPosY());
					}

				} else if (c == 40) {
					if (nave.getPosY() < pPrincipal.getHeight()) {
						nave.setPosY(nave.getPosY() + nave.getVelocidadY());
					} else {
						nave.setPosY(nave.getPosY());
					}
				} else if (c == 37) {
					if (nave.getPosX() > 0) {
						nave.setPosX(nave.getPosX() - nave.getVelocidadX());
					} else {
						nave.setPosX(nave.getPosX());
					}
				} else if (c == 32) {
					crearDisparo();

				} else if (c == 39) {
					if (nave.getPosX() < pPrincipal.getWidth() - (nave.getlNave().getAnchoNave() / 2)) {
						nave.setPosX(nave.getPosX() + nave.getVelocidadX());
					} else {
						nave.setPosX(nave.getPosX());
					}
				}
			}
		});

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				int c = e.getKeyCode();
				if (c == 38) {
					if (nave.getPosY() > 225) {
						nave.setPosY(nave.getPosY() - nave.getVelocidadY());
					} else {
						nave.setPosY(nave.getPosY());
					}

				} else if (c == 40) {
					if (nave.getPosY() < pPrincipal.getHeight() - nave.getlNave().getHeight()) {
						nave.setPosY(nave.getPosY() + nave.getVelocidadY());
					} else {
						nave.setPosY(nave.getPosY());
					}
				} else if (c == 37) {
					if (nave.getPosX() > 0) {
						nave.setPosX(nave.getPosX() - nave.getVelocidadX());
					} else {
						nave.setPosX(nave.getPosX());
					}
				} else if (c == 39) {
					if (nave.getPosX() < pPrincipal.getWidth() - (nave.getlNave().getAnchoNave())) {
						nave.setPosX(nave.getPosX() + nave.getVelocidadX());
					} else {
						nave.setPosX(nave.getPosX());
					}
				}
			}
		});
	}

	/**
	 * Crea una nave y la posiciona en relacion al panel de juego
	 */
	public void creaNave() {
		nave = us.getNave();
		nave.setPosX(this.getWidth() / 2 - nave.getlNave().getAnchoNave() / 2);
		nave.setPosY(pPrincipal.getHeight() - nave.getlNave().getHeight());
		nave.setVelocidadX(2);
		nave.setVelocidadY(2);
		pPrincipal.add(nave.getlNave());
	}

	public void crearDisparo() {
		Disparo ultimoDisparo = arrayDisparo.size() == 0 ? null : arrayDisparo.get(arrayDisparo.size() - 1);
		Disparo nuevoDis = new Disparo(nave.getPosX(), nave.getPosY() - nave.getlNave().getAltoNave() / 2, 1, 0, 5);
		if (ultimoDisparo != null) {
			// existe un disparo ultimo => comparar tiempos
			long diff = nuevoDis.getTime() - ultimoDisparo.getTime();
			if (diff >= nave.getVelocidadAtaque()) {
				pPrincipal.add(nuevoDis.getlDisparo());
				arrayDisparo.add(nuevoDis);
			} else {
				System.out.println("Tiempo de disparo excedido");
				System.out.println("Han pasado " + diff + "/" + nave.getVelocidadAtaque());
			}
		} else {
			// no existe ningun disparo
			pPrincipal.add(nuevoDis.getlDisparo());
			arrayDisparo.add(nuevoDis);
		}

	}

	/**
	 * Crea un meteorito y lo posiciona en relacion al panel de juego (fuera de la
	 * pantalla)
	 * @param DanyoMeteorito Danio de meteorito
	 * @param vidaMeteorito Vida del meterorio
	 */
	public void creaMeteorito(int DanyoMeteorito, int vidaMeteorito) {
		if (arrayMeteoritosEnPantalla.size() + 1 >= maxMeteoritosEnPantalla) {
			return;
		}
		MeteoritoEnemigo me1 = new MeteoritoEnemigo(cro.getSegundos());
		double x = getRandom(0, this.getWidth() - me1.getlMeteorito().getWidth());
		while (!nuevoMeteoritoCorrecto(x)) {
			x = getRandom(0, this.getWidth() - me1.getlMeteorito().getWidth());
		}
		me1.setPosX(x);
		me1.setPosY(-(me1.getlMeteorito().getHeight()));
		me1.setDanyoAJugador(DanyoMeteorito);
		me1.setVida(vidaMeteorito);
		pPrincipal.add(me1.getlMeteorito());
		arrayMeteoritosEnPantalla.add(me1);
	}

	/**
	 * Cierra la ventana y actualiza los creditos del jugador
	 */
	public void gameOver() {
		cro.pausarCrono();
		puntosTiempo();
		puntosMeteoritos();
		creditosPartida();
		BaseDatos.setCreditosUsuario(us);
		this.dispose();
		JOptionPane.showMessageDialog(this,
				"Game Over" + "\n" + "Puntuacion : " + part.getPuntuacion() + "\n " + "Tiempo: " + cro.getMinutos()
						+ " : " + cro.getSegundos() + "\n" + "creditos obtenidos: " + part.getPuntuacion(),
				"Game Over", JOptionPane.YES_NO_OPTION);
	}

	/**
	 * Comprueba que el meteorito no se superpone sobre otro meteorito en pantalla
	 * 
	 * @param x posicionamiento en el eje x del meteorito (en pixels)
	 * @return true si x correcta y no se sobrepone, false si se sobrepone
	 */
	public boolean nuevoMeteoritoCorrecto(double x) {
		for (MeteoritoEnemigo meteoritoEnemigo : arrayMeteoritosEnPantalla) {
			double miPosX = meteoritoEnemigo.getPosX();
			double miPosY = meteoritoEnemigo.getPosY();

			double mfPosX = miPosX + meteoritoEnemigo.getlMeteorito().getWidth();
			double mfPosY = miPosY + meteoritoEnemigo.getlMeteorito().getHeight();

			double principioNuevo = x;
			double finalNuevo = x + meteoritoEnemigo.getlMeteorito().getWidth(); // teniendo en cuenta que el width de
																					// todos los meteoritos es igual

			if (!(!(miPosX < principioNuevo && principioNuevo < mfPosX)
					&& !(miPosX < finalNuevo && finalNuevo < mfPosX))) {
				if (!(miPosY > meteoritoEnemigo.getlMeteorito().getHeight())) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Comprueba si la nave y un meteorito han chocado.
	 * 
	 */
	public void choqueConMeteorito() {
		if (nave == null)
			return;
		ArrayList<MeteoritoEnemigo> aEliminar = new ArrayList<MeteoritoEnemigo>();
		for (MeteoritoEnemigo me : arrayMeteoritosEnPantalla) {
			if (!arrayMeteoritosEliminados.contains(me)) {
				if (me.getBounds().intersects(nave.getBounds())) {
					aEliminar.add(me);
					Main.getLogger().log(Level.FINER, "la nave ha chocado con un meteorito \n"
							+ "vida de la nave antes: " + vidaNavePartida + " -- " + "danio del meteorito: " + me.getDanyoAJugador());
					vidaNavePartida = vidaNavePartida - (int) me.getDanyoAJugador();
					Main.getLogger().log(Level.FINER, "vida de la nave despues: " + vidaNavePartida);
					int porcentajeVida = 100 * vidaNavePartida / nave.getVida();
					pbVida.setValue(porcentajeVida);
				}
			}
		}
		for (MeteoritoEnemigo me : aEliminar) {
			arrayMeteoritosEliminados.add(me);
			arrayMeteoritosEnPantalla.remove(me);
			aEliminarObjetos(me);
		}

	}

	/**
	 * Comprueba si un disparo y la nave han chocado.
	 * 
	 */
	public void DisparoChoqueMeteorito() {
		ArrayList<MeteoritoEnemigo> aEliminarMeteortitos = new ArrayList<MeteoritoEnemigo>();
		ArrayList<Disparo> aEliminarDisparo = new ArrayList<Disparo>();
		if (arrayDisparo.size() > 0) {
			for (Disparo dis : arrayDisparo) {
				for (MeteoritoEnemigo me : arrayMeteoritosEnPantalla) {
					if (!arrayMeteoritosEliminados.contains(me)) {
						if (me.getBounds().intersects(dis.getBounds())) {
							if (me.getVida() - dis.getDanyo() <= 0) {
								me.setDanyoAJugador(0);
								aEliminarMeteortitos.add(me);
								aEliminarDisparo.add(dis);
								Main.getLogger().log(Level.FINER, "un meteorito ha sido destruido");

							} else {
								me.setVida(me.getVida() - dis.getDanyo());
								Main.getLogger().log(Level.FINER, "un meteorito ha sido impactado \n"
										+ "Vida del meteorito tras el choque " + me.getVida());
								aEliminarDisparo.add(dis);
							}

						}
					}
				}
			}

		}
		for (MeteoritoEnemigo me : aEliminarMeteortitos) {
			arrayMeteoritosEliminados.add(me);
			aEliminarObjetos(me);
			arrayMeteoritosDisparados.add(me);
			arrayMeteoritosEnPantalla.remove(me);
		}
		for (Disparo dis : aEliminarDisparo) {
			aEliminarObjetos(dis);
			arrayDisparo.remove(dis);
		}
	}

	/**
	 * Elimina el JLabel del objeto en la pantalla
	 * 
	 * @param obj
	 */
	public void aEliminarObjetos(ObjetoJuego obj) {
		if (obj instanceof Disparo) {
			Disparo dis = (Disparo) obj;
			pPrincipal.remove(dis.getlDisparo());
		} else if (obj instanceof MeteoritoEnemigo) {
			MeteoritoEnemigo mete = (MeteoritoEnemigo) obj;
			pPrincipal.remove(mete.getlMeteorito());
		}
	}

	/**
	 * Comprueba si la nave tiene vida
	 * 
	 * @return true si no tiene vida, false si tiene.
	 */
	public boolean estaMuerto() {
		if (nave == null)
			return false;
		if (vidaNavePartida <= 0) {
			return true;
		}
		return false;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	/**
	 * Suma la puntuacion por el tiempo a la puntuacion de la partida.
	 */
	public void puntosTiempo() {
		double puntuacion = part.getPuntuacion();
		puntuacion += (60 * cro.getMinutos()) + cro.getSegundos();
		part.setPuntuacion(puntuacion);
	}

	/**
	 * Suma la puntuacion por matar a un meteorito a la puntuacion de la partida.
	 */
	public void puntosMeteoritos() {
		double puntuacion = part.getPuntuacion();
		for (MeteoritoEnemigo meteorito : arrayMeteoritosDisparados) {
			puntuacion += meteorito.getVALOR_PUNTUACION();
		}
		part.setPuntuacion(puntuacion);
	}

	/**
	 * Le otorga al jugador los creditos que le corresponden tras haber jugado la
	 * partida.
	 * 
	 */
	public void creditosPartida() {
		us.setCreditos(us.getCreditos() + part.getPuntuacion());
	}

	/**
	 * Crea el JProgessBar con el porcentaje de la vida de la nave en la partida.
	 * 
	 */
	public void crearPbVida() {
		pbVida = new JProgressBar();
		pbVida.setValue(100);
		pbVida.setBounds(0, (int) (pPrincipal.getHeight() - pbVida.getPreferredSize().getHeight() - 80), 50, 25);
		pPrincipal.add(pbVida);

	}

	/**
	 * Crea el Cronometro de la partida en minutos,segundos,milisegundos.
	 * 
	 */
	public void creaCronometro() {
		cro = new Cronometro();
		// System.out.println(pPrincipal.getHeight() -
		// cro.getPreferredSize().getHeight());
		cro.setBounds(0, (int) (pPrincipal.getHeight() - cro.getPreferredSize().getHeight() - 100), 100, 100);
		cro.setForeground(Color.white);
		cro.setBackground(Color.white);
		pPrincipal.add(cro);
	}

	private double getRandom(double lowerBound, double upperBound) {
		Random random = new Random();
		double randomValue = lowerBound + (upperBound - lowerBound) * random.nextDouble();
		return randomValue;
	}

	/**
	 * @return La vida de la nave en la partida.
	 */
	public int getVidaNavePartida() {
		return vidaNavePartida;
	}

	/**
	 * Modifica el vida de la nave en la partida.
	 * 
	 * @param vidaNavePartida Nueva vida de la nave en la partida.
	 */
	public void setVidaNavePartida(int vidaNavePartida) {
		this.vidaNavePartida = vidaNavePartida;
	}

	/**
	 * @return El JProgressBar de la vida de la nave en la partida.
	 */
	public JProgressBar getPbVida() {
		return pbVida;
	}

	/**
	 * Modifica el JProgressBar de la vida de la nave en la partida.
	 * 
	 * @param pbVida Nueva JProgressBar de la vida de la nave en la partida.
	 */
	public void setPbVida(JProgressBar pbVida) {
		this.pbVida = pbVida;
	}

	/**
	 * @return El usuario.
	 */
	public Usuario getUs() {
		return us;
	}

	/**
	 * Modifica el usuario.
	 * 
	 * @param pbVida Nueva usuario.
	 */
	public void setUs(Usuario us) {
		this.us = us;
	}

	/**
	 * @return La nave del usuario.
	 */
	public NaveJugador getNave() {
		return nave;
	}

	/**
	 * Modifica la nave del usuario.
	 * 
	 * @param nave Nueva nave del usuario..
	 */
	public void setNave(NaveJugador nave) {
		this.nave = nave;
	}
}
