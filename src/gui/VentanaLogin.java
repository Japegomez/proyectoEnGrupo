package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class VentanaLogin extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lNombreUsuario;
	private JTextField tfNombreUsuario;
	private JLabel lContrasenya;
	private JTextField tfContrasenya;
	private JButton bLogin;
	private JButton bRegistro;
	private static Logger logger = Logger.getLogger( "MiniPracticaBD" );
	private static Connection con;
	private static Statement s;
	private static ResultSet rs;
	
	public VentanaLogin(String titulo) {		
		super(titulo);
		String com = "";
	try {
		Class.forName( "org.sqlite.JDBC" );
		con = DriverManager.getConnection( "jdbc:sqlite:test.db" );
		s = con.createStatement();
		try {
			com = "create table Usuario( usuario STRING, contrasenya STRING )";
			logger.log( Level.INFO, "BD: " + com );
			s.executeUpdate( com );
		} catch (SQLException e) {} 
	} catch (SQLException|ClassNotFoundException e) {}

		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		JPanel pNombre = new JPanel();
		JPanel pContrasenia = new JPanel();
		JPanel pBotonera = new JPanel();
		
		
		lNombreUsuario = new JLabel("Usuario");
		tfNombreUsuario = new JTextField(15);
		
		lContrasenya = new JLabel("Contrasenya");
		tfContrasenya = new JTextField(15);
		
		bLogin = new JButton("Login");
		bRegistro = new JButton("Registrarse");
		
		pNombre.add(lNombreUsuario);
		pNombre.add(tfNombreUsuario);
		pContrasenia.add(lContrasenya);
		pContrasenia.add(tfContrasenya);
		pBotonera.add(bLogin);
		pBotonera.add(bRegistro);
		this.add(pNombre);
		this.add(pContrasenia);
		this.add(pBotonera);
		
		this.pack();
		
		
		bLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaMenu vMenu = new VentanaMenu("menu");
				vMenu.setVisible(true);
				VentanaLogin.this.setVisible(false);
			}
		});
		
		
	}
	
	
}
