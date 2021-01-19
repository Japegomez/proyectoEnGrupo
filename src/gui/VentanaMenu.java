package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import logica.Usuario;
	/**Clase de la ventana del menu principal 
	 */
public class VentanaMenu extends JFrame {
	JButton bJugar;
	JButton bMejorarNave;
	JButton bClasificacion;
	JButton bBorrarPartidas;
	JButton bCerrarSesion;
	JTextArea tUsuario;
	String nombreUsuario;
	VentanaJuego vJuego;
	VentanaBorrarPartida vBorrarP;
	VentanaClasificaciones vClasi;
	VentanaMejorasNave vMej;
	
	/**Crea una ventana que muestra el menu con sus listeners de los botones
	 * @param titulo titulo de la ventana
	 * @param usu usuario que ha iniciado sesion
	 */
	public VentanaMenu(String titulo, Usuario usu, VentanaLogin v1) {
		super(titulo);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new GridLayout(4, 1));
		setSize(200, 200);
		
		
		tUsuario = new JTextArea("Has iniciado sesion como: " + usu.getNombreUsuario());
		tUsuario.setEditable(false);
		bJugar = new JButton("Jugar");
		bMejorarNave = new JButton("Mejorar Nave");
		bClasificacion = new JButton("Clasificacion");
		bBorrarPartidas = new JButton("Borrar partidas");
		bCerrarSesion = new JButton("Cerrar Sesion");
		
		this.add(tUsuario);
		this.add(bJugar);
		this.add(bMejorarNave);
		this.add(bClasificacion);
		this.add(bBorrarPartidas);
		this.add(bCerrarSesion);
		
		this.pack();
		
		bBorrarPartidas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaMenu.this.setVisible(false);
				vBorrarP = new VentanaBorrarPartida(VentanaMenu.this, usu);
				vBorrarP.setVisible(true);
			}
		});
		bCerrarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaMenu.this.dispose();
				v1.setVisible(true);
			}
		});
		
		bJugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaMenu.this.setVisible(false);
				vJuego = new VentanaJuego(usu,VentanaMenu.this);
				vJuego.setVisible(true);
				vJuego.creaNave();
				vJuego.creaCronometro();
				vJuego.crearPbVida();
			}
		});
		
		bClasificacion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaMenu.this.setVisible(false);
				vClasi = new VentanaClasificaciones(VentanaMenu.this);
				vClasi.setVisible(true);
				
			}
		});
		
		bMejorarNave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaMenu.this.setVisible(false);
				vMej = new VentanaMejorasNave(VentanaMenu.this, usu);
				vMej.setVisible(true);
				
			}
		});

	}
	
}
