package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

/**
 * Esta clase contiene la ventana del borrado de una partida.
 */
public class VentanaBorrarPartida extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel lBorrar; // JLabel del borrado.

	private JButton bConfirmar; // JButton para confimar borrar las partidas.

	private JButton bDeshacer; // JButton para deshacer lo borrado anteriormente.

	private JComboBox<Object> cbObjetosAEliminar; // JComboBox de los objetos que se va a eliminar.

	private DefaultComboBoxModel<Object> mObjetosAEliminar;

	private HashSet<Partida> SPartidas = new HashSet<>(); // HashSet de las partidas de un usuario.

	/**
	 * Crea la ventana del borrado de una partida.
	 * @param vMenu vMenu desde donde se ha llamado a esta clase.
	 * @param usu Usuario que quiere borrar sus partidas.
	 */
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
		add(pBotonera, BorderLayout.SOUTH);
		add(pCentral, BorderLayout.NORTH);

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
				if (cbObjetosAEliminar.getSelectedItem() != null) {
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
				if (mObjetosAEliminar.getSize() == 0) {
					JOptionPane.showMessageDialog(VentanaBorrarPartida.this,
							"No hay ninguna partida registrado para este jugador", "Error", JOptionPane.ERROR_MESSAGE);
					VentanaBorrarPartida.this.dispose();
				}
			}
		});
	}
	/**
	 * Inicia el combo, cargando las partidas del usuario ordenadas por puntuaciones.
	 * @param usu Usuario al que se le van a cargar las partidas.
	 */
	private void initCombo(Usuario usu) {
		ArrayList<Partida> aPartidas = BaseDatos.getPartidas(usu);
		Collections.sort(aPartidas);
		mObjetosAEliminar.addAll(aPartidas);

	}
}
