package logica;

import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.UIManager;

import gui.*;

public class Main {
	static Logger logger;
	public static void main(String[] args) {
		try {
			logger = Logger.getLogger("Logger");
			Handler h = new FileHandler("spacedefense.xml",true);
			logger.setUseParentHandlers(false);
			logger.addHandler(h);
			logger.setLevel(Level.FINEST);
			h.setLevel(Level.FINER);
		} catch (Exception e) { } 
		logger.log(Level.INFO, "Inicio del programa: " + new Date());
		try { 
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) { } 
		VentanaLogin vLogin = new VentanaLogin("Login");		vLogin.setVisible(true);
	}
	public static Logger getLogger() {
		return logger;
	}
}
