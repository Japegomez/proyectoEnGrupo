package logica;

import javax.swing.UIManager;

import gui.*;

public class Main {
public static void main(String[] argsjonlopez) {
	try { 
		System.out.println("hola");
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		System.out.print("hola");
	} catch (Exception e) { } 
	VentanaLogin vLogin = new VentanaLogin("Login");
	vLogin.setVisible(true);
}
}
