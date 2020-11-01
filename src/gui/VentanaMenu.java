package gui;

import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import logica.BaseDatos;
import logica.Usuario;

public class VentanaMenu extends JFrame {
	JButton bJugar;
	JButton bMejorarNave;
	JButton bClasificacion;
	JTextArea tUsuario;
	String nombreUsuario;
	VentanaJuego vJuego;
	VentanaClasificaciones vClasi;
	VentanaMejorasNave vMej;
	
	public VentanaMenu(String titulo, Usuario usu) {
		super(titulo);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new GridLayout(4, 1));
		setSize(200, 200);
		
		
		tUsuario = new JTextArea("Has iniciado sesion como: " + usu.getNombreUsuario());
		tUsuario.setEditable(false);
		bJugar = new JButton("Jugar");
		bMejorarNave = new JButton("Mejorar Nave");
		bClasificacion = new JButton("Clasificacion");
		
		this.add(tUsuario);
		this.add(bJugar);
		this.add(bMejorarNave);
		this.add(bClasificacion);
		
		this.pack();
		
		bJugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaMenu.this.setVisible(false);
				vJuego = new VentanaJuego(usu,VentanaMenu.this);
				vJuego.setVisible(true);
				vJuego.creaNave();
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
				vMej = new VentanaMejorasNave(VentanaMenu.this);
				vMej.setVisible(true);
				
			}
		});
		
		addWindowListener(new WindowAdapter() {
			

			@Override
			public void windowClosed(WindowEvent e) {
				BaseDatos.cerrarConexion();
			}
		
		});
	}
	
}
