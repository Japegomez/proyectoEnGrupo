package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class JLabelNave extends JLabel{

	public JLabelNave() {
		 ImageIcon imageIcon = new ImageIcon("./resources/nave.jpg");
		 this.setIcon(imageIcon);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		java.awt.Image img = null;
		try { 
            final BufferedImage dimg = ImageIO
                    .read(new File("./resources/nave.jpg"));
           img = dimg.getScaledInstance(100, 100, 1);
            

        } catch (final IOException e) {
            e.printStackTrace();
        }
		this.setIcon(new ImageIcon(img));
	}
}
