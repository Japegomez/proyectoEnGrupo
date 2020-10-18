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
	final private int ANCHO_NAVE = 50;
	final private int ALTO_NAVE = 100;
	public JLabelNave() {
		 ImageIcon imageIcon = new ImageIcon("./resources/nave.jpg");
		 this.setIcon(imageIcon);
		setBounds( 0, 0, ANCHO_NAVE, ALTO_NAVE );
		setBorder( BorderFactory.createLineBorder( Color.yellow, 4 ));
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
                    .read(new File("./resources/nave.jpg"));
           img = dimg.getScaledInstance(ANCHO_NAVE, ALTO_NAVE, 1);
            

        } catch (final IOException e) {
            e.printStackTrace();
        }
		this.setIcon(new ImageIcon(img));
	}
}
