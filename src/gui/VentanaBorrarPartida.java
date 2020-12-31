package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logica.BaseDatos;
import logica.Partida;
import logica.Usuario;

public class VentanaBorrarPartida extends JFrame{
	
	private JLabel lBorrar;
	
	private JButton bConfirmar;
	
	private JButton bDeshacer;
	
	private JComboBox<Object> cbObjetosAEliminar;

	private DefaultComboBoxModel<Object> mObjetosAEliminar;
	
	private HashSet<Partida> SPartidas = new HashSet<>();
	
	
	
	public VentanaBorrarPartida(VentanaMenu vMenu, Usuario usu) {
		super("Borrando Partida...");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(700, 200);
		
		
		JPanel pCentral = new JPanel();
		JPanel pBotonera = new JPanel();
		bConfirmar = new JButton("Confirmar");
		bDeshacer = new JButton("Deshacer cambios");
		pCentral.setLayout(new GridLayout(1, 2));
		pBotonera.setLayout(new GridLayout(1, 2));
		mObjetosAEliminar = new DefaultComboBoxModel<Object>();
		cbObjetosAEliminar = new JComboBox<>(mObjetosAEliminar);
		
		lBorrar = new JLabel("Que partida desea borrar?");
		
		pCentral.add(lBorrar);
		pCentral.add(cbObjetosAEliminar);
		pBotonera.add(bDeshacer);
		pBotonera.add(bConfirmar);
		add(pBotonera,BorderLayout.SOUTH);
		add(pCentral,BorderLayout.NORTH);
		
		initCombo(usu);
		

		bDeshacer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SPartidas.clear();
				mObjetosAEliminar.removeAllElements();
				initCombo(usu);
			}
		});
		bConfirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbObjetosAEliminar.getSelectedItem()!=null) {
					SPartidas.add((Partida) cbObjetosAEliminar.getSelectedItem());
					mObjetosAEliminar.removeElement(cbObjetosAEliminar.getSelectedItem());		
				}
			}
		});
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent e) {
				for (Partida partida : SPartidas) {
					BaseDatos.borrarPartida(partida);
				}
				vMenu.setVisible(true);
			}
			
			@Override
			public void windowOpened(WindowEvent e) {
				if(mObjetosAEliminar.getSize()==0) {
					JOptionPane.showMessageDialog(VentanaBorrarPartida.this, "No hay ninguna partida registrado para este jugador", "Error", JOptionPane.ERROR_MESSAGE);
					VentanaBorrarPartida.this.dispose();
				}
			}
		});
	}

	private void initCombo(Usuario usu) {
		ArrayList<Partida> aPartidas = BaseDatos.getPartidos(usu);		
		Collections.sort(aPartidas);
		mObjetosAEliminar.addAll(aPartidas);
		
	}
}
