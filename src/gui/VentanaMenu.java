package gui;

import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class VentanaMenu extends JFrame {
	JButton bJugar;
	JButton bMejorarNave;
	JButton bClasificacion;
	JTextArea tUsuario;
	String nombreUsuario;
	VentanaJuego vJuego;
	
	public VentanaMenu(String titulo) {
		super(titulo);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new GridLayout(4, 1));
		setSize(200, 200);
		
		
		tUsuario = new JTextArea("Hola");
		tUsuario.setEditable(false);
		bJugar = new JButton("Jugar");
		bMejorarNave = new JButton("Mejorar Nave");
		bClasificacion = new JButton("Clasificacion");
		
		this.add(tUsuario);
		this.add(bJugar);
		this.add(bMejorarNave);
		this.add(bClasificacion);
		
		bJugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaMenu.this.setVisible(false);
				vJuego = new VentanaJuego();
				vJuego.setVisible(true);
				vJuego.creaNave();
				// comentario jaajajajajajajajslj1
				
				
			}
		});
		
	}
	
}
