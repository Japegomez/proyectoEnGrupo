package logica;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.Properties;

import logica.Main;

public class Config {
	private Properties prop;

	private final String FILE_NAME = "config.properties";

	public Config() {
		prop = new Properties();

		try {
			FileInputStream ip = new FileInputStream(FILE_NAME);
			prop.load(ip);
		} catch (IOException e) {
			Main.getLogger().log(Level.SEVERE, "Error cargando configuracion", e);
		}
	}
	
	public String getProp(String propName) {
		return prop.getProperty(propName);
	}
	public void setProp(String propName, String value) {
		prop.setProperty(propName, value);
	}
}
