package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class JLabelNave extends JLabel{

	final private int ANCHO_NAVE = 50;//Ancho de la nave(en pixels)
	final private int ALTO_NAVE = 100;//Alto de la nave(en pixels)
	
	/**
	 * @return int Ancho del jlabel de la nave(en pixels)
	 */
	public int getAnchoNave() {
		return ANCHO_NAVE;
	}
	/**
	 * @return int Altura del jlabel de la nave(en pixels)
	 */
	public int getAltoNave() {
		return ALTO_NAVE;
	}
	
	/**Crea el JLabel y le asocia una imagen de una nave
	 * 
	 */
	public JLabelNave() {
		 ImageIcon imageIcon = new ImageIcon("./resources/nave.png");
		 this.setIcon(imageIcon);
		setBounds( 0, 0, ANCHO_NAVE, ALTO_NAVE );
		setPreferredSize(new Dimension(ANCHO_NAVE, ALTO_NAVE));
	}

	/**Ajusta la escala del JLabel
	 *
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		java.awt.Image img = null;
		try { 
            final BufferedImage dimg = ImageIO
                    .read(new File("./resources/nave.png"));
           img = dimg.getScaledInstance(ANCHO_NAVE, ALTO_NAVE, 1);
        } catch (final IOException e) {
            e.printStackTrace();
        }
		this.setIcon(new ImageIcon(img));
	}
}
