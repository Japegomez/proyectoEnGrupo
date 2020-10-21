package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JLabelMeteorito extends JLabel {
	
	final private int ANCHO_METEORITO = 50;
	final private int ALTO_METEORITO = 50;
	
	public JLabelMeteorito() {
		ImageIcon image = new ImageIcon("./resources/meteorito.png");
		this.setIcon(image);
		setBounds( 0, 0, ANCHO_METEORITO, ALTO_METEORITO );
		setPreferredSize(new Dimension(ANCHO_METEORITO, ALTO_METEORITO));
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		java.awt.Image img = null;
		try { 
            final BufferedImage dimg = ImageIO
                    .read(new File("./resources/meteorito.png"));
           img = dimg.getScaledInstance(50, 50, 1);
            

        } catch (final IOException e) {
            e.printStackTrace();
        }
		this.setIcon(new ImageIcon(img));
	}
	

}
