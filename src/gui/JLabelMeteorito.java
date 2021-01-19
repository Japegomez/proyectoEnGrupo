package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JLabelMeteorito extends JLabel {

	final private int ANCHO_METEORITO = 50; // Ancho del meteorito (en pixels)
	final private int ALTO_METEORITO = 50;// Alto del meteorito (en pixels)

	/**
	 * @return int Ancho del jlabel del meteorito(en pixels)
	 */
	public int getAnchoMeteorito() {
		return ANCHO_METEORITO;
	}

	/**
	 * @return int Altura del jlabel de la meteorito(en pixels)
	 */
	public int getAltoMeteorito() {
		return ALTO_METEORITO;
	}

	/**
	 * Crea el JLabel y le asocia una imagen de un meteorito.
	 */
	public JLabelMeteorito() {
		ImageIcon image = new ImageIcon("./resources/meteorito.png");
		this.setIcon(image);
		setBounds(0, 0, ANCHO_METEORITO, ALTO_METEORITO);
		setPreferredSize(new Dimension(ANCHO_METEORITO, ALTO_METEORITO));
	}

	/**
	 * Ajusta la escala del JLabel del meteorito.
	 * @param g
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		java.awt.Image img = null;
		try {
			final BufferedImage dimg = ImageIO.read(new File("./resources/meteorito.png"));
			img = dimg.getScaledInstance(50, 50, 1);

		} catch (final IOException e) {
			e.printStackTrace();
		}
		this.setIcon(new ImageIcon(img));
	}

}
