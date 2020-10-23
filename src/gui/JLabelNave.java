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
	final private int ANCHO_NAVE = 38;
	final private int ALTO_NAVE = 75;
	public JLabelNave() {
		 ImageIcon imageIcon = new ImageIcon("./resources/nave.png");
		 this.setIcon(imageIcon);
		setBounds( 0, 0, ANCHO_NAVE, ALTO_NAVE );
		setPreferredSize(new Dimension(ANCHO_NAVE, ALTO_NAVE));
	}
	public int getAnchoNave() {
		return ANCHO_NAVE;
	}
	public int getAltoNave() {
		return ALTO_NAVE;
	}
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
