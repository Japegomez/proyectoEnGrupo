package logica;

import javax.swing.UIManager;

import gui.*;

public class Main {
	public static void main(String[] args) {
		try { 
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) { } 
		VentanaLogin vLogin = new VentanaLogin("Login");
		vLogin.setVisible(true);
	}
	
	//javi es un subnormal de atar
}
