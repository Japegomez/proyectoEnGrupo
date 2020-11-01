package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PanelFondo extends JPanel{
	
	/**Configura un fondo de una imagen de una galaxia
	 *
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img = null;
			try { 
	            final BufferedImage dimg = ImageIO
	                    .read(new File("./resources/galaxia.jpg"));
	           img = dimg.getScaledInstance(500, 500, 1);
	            

	        } catch (final IOException e) {
	            e.printStackTrace();
	        }
			 g.drawImage(img, 0, 0, null);
	}
}
