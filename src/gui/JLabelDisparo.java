package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class JLabelDisparo extends JLabel {
	final private int ANCHO_DISP = 50;
	final private int ALTO_DISP = 50;

	/**
	 * @return int Ancho del jlabel de la nave (en pixels)
	 */
	public int getAnchoDisparo() {
		return ANCHO_DISP;
	}

	/**
	 * @return int Altura del jlabel de la nave (en pixels)
	 */
	public int getAltoDisparo() {
		return ALTO_DISP;
	}

	/**
	 * Crea el JLabel y le asocia una imagen de un disparo.
	 */
	public JLabelDisparo() {
		ImageIcon imageIcon = new ImageIcon("./resources/disparo.png");

		this.setIcon(imageIcon);
		setBounds(0, 0, ANCHO_DISP, ALTO_DISP);
		setPreferredSize(new Dimension(ANCHO_DISP, ALTO_DISP));

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
			img = dimg.getScaledInstance(ANCHO_DISP, ALTO_DISP, 1);
			// super.paintComponent(g);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		this.setIcon(new ImageIcon(img));

	}
}
