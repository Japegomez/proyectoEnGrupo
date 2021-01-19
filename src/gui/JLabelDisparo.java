package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import logica.Config;

import javax.imageio.ImageIO;
import javax.swing.*;

public class JLabelDisparo extends JLabel {
	private Config c = new Config();
	/**
	 * @return int Ancho del jlabel de la nave (en pixels)
	 */
	public int getAnchoDisparo() {
		return Integer.parseInt(c.getProp("ANCHO_DISTP"));
	}

	/**
	 * @return int Altura del jlabel de la nave (en pixels)
	 */
	public int getAltoDisparo() {
		return Integer.parseInt(c.getProp("ALTO_DISTP"));
	}

	/**
	 * Crea el JLabel y le asocia una imagen de un disparo.
	 */
	public JLabelDisparo() {
		ImageIcon imageIcon = new ImageIcon("./resources/disparo.png");

		this.setIcon(imageIcon);
		setBounds(0, 0, getAnchoDisparo(), getAltoDisparo());
		setPreferredSize(new Dimension(getAnchoDisparo(), getAltoDisparo()));

	}

	/**
	 * Ajusta la escala del JLabel del disparo.
	 * @param g
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		java.awt.Image img = null;
		try {
			final BufferedImage dimg = ImageIO.read(new File("./resources/disparo.png"));
			((Graphics2D) g).rotate(Math.toRadians(180), dimg.getWidth() / 2, dimg.getHeight() / 2);
			img = dimg.getScaledInstance(getAnchoDisparo(), getAltoDisparo(), 1);
			// super.paintComponent(g);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		this.setIcon(new ImageIcon(img));

	}
}
