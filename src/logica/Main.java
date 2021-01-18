package logica;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.UIManager;

import gui.*;

public class Main {
	static Logger logger;
	public static void main(String[] args) {
		try {
			logger = Logger.getLogger("Logger");
		} catch (Exception e) { } 
		logger.log(Level.INFO, "Inicio del programa: " + new Date());
		try { 
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) { } 
		VentanaLogin vLogin = new VentanaLogin("Login");
		vLogin.setVisible(true);
	}
}
