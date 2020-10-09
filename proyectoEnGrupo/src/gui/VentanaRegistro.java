package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.SwingConstants;

public class VentanaRegistro extends JFrame {

	private JPanel pPrincipal;
	private JTextField tfUsuario;
	private JLabel lContrasenya;
	private JTextField tfContrasenya;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro frame = new VentanaRegistro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaRegistro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		pPrincipal = new JPanel();
		pPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pPrincipal);
		pPrincipal.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lUsuario = new JLabel("USUSARIO");
		lUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		pPrincipal.add(lUsuario);
		
		tfUsuario = new JTextField();
		tfUsuario.setText("INTRODUCE EL USUARIO");
		pPrincipal.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		lContrasenya = new JLabel("CONTRASEÑA");
		lContrasenya.setHorizontalAlignment(SwingConstants.CENTER);
		pPrincipal.add(lContrasenya);
		
		tfContrasenya = new JTextField();
		tfContrasenya.setText("INTRODUCE LA CONTRASEÑA");
		pPrincipal.add(tfContrasenya);
		tfContrasenya.setColumns(2);
	}

}
