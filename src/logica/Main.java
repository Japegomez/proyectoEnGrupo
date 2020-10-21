package logica;

import javax.swing.UIManager;

import gui.*;

public class Main {
<<<<<<< HEAD
public static void main(String[] argsjavi) {
=======
public static void main(String[] args) {
>>>>>>> 243126e634acb32675850229a820b770e0271672
	try { 
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		System.out.print("hola");
	} catch (Exception e) { } 
	VentanaLogin vLogin = new VentanaLogin("Login");
	vLogin.setVisible(true);
}
}
