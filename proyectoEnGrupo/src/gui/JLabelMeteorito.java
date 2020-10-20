package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JLabelMeteorito extends JLabel {
	
	public JLabelMeteorito() {
		
		ImageIcon image = new ImageIcon("./resources/meteorito.jpg");
		this.setIcon(image);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		java.awt.Image img = null;
		try { 
            final BufferedImage dimg = ImageIO
                    .read(new File("./resources/meteorito.jpg"));
           img = dimg.getScaledInstance(50, 50, 1);
            

        } catch (final IOException e) {
            e.printStackTrace();
        }
		this.setIcon(new ImageIcon(img));
	}
	

}
