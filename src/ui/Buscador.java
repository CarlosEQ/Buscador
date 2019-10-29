package ui;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logic.Metodos;
import logic.ModeloTabla;
import logic.Termino;
/*
 * Modelo de la interfaz de usuario
 * 
 * @author Viviana Guerrero
 * @author Juan David Romero
 * @author Carlos Quiñones
 */
public class Buscador extends JFrame {

	private JPanel contentPane;
	private JTextField txtPalabra;
	private Metodos m = new Metodos();
	private ArrayList<Termino> terminos;

	private final ModeloTabla tableModel = new ModeloTabla();

	private final JTable table = new JTable() {
		@Override
		public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
			if (columnIndex == 1)
				super.changeSelection(rowIndex, columnIndex - 1, toggle, extend);
			else
				super.changeSelection(rowIndex, columnIndex, toggle, extend);
		}
	};

	private final JScrollPane scrollPane = new JScrollPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscador frame = new Buscador();
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
	public Buscador() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Layout null para ubicar el componente por coordenadas.
		getContentPane().setLayout(null);
		// Título de la ventana.
		this.setTitle("Buscador Ontol\u00F3gico");
		// Tamaño de la ventana.
		this.setSize(450, 500);

		// Configuramos como se mostrara la tabla.
		configurarTabla();

		// Agregamos el ScrollPane a la ventana.
		getContentPane().add(this.scrollPane);

		// Indicamos que la tabla no permite ajustar el tamaño.
		this.setResizable(false);
		// // Indicamos que la aplicación finaliza al cerrar la ventana.
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Ubicamos la ventana en el centro de la pantalla.
		this.setLocationRelativeTo(null);

		txtPalabra = new JTextField();
		txtPalabra.setBounds(10, 11, 296, 36);
		contentPane.add(txtPalabra);
		txtPalabra.setColumns(10);

		terminos = new ArrayList<Termino>();

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String palabra = txtPalabra.getText();

				terminos = m.buscar(palabra);

				if (terminos.size() == 0) {
					agregarFilas(terminos);
				} else {
					m.getA().clear();
					limpiar();
					agregarFilas(terminos);
				}

			}
		});
		btnBuscar.setBounds(331, 18, 89, 23);

		contentPane.add(btnBuscar);
		getRootPane().setDefaultButton(btnBuscar);

	}

	/**
	 * Agrega las filas de la tabla, según los términos del parámetro
	 * @param terminos términos con los que se llena la tabla
	 */
	public void agregarFilas(ArrayList<Termino> terminos) {

		Object datos[] = new Object[terminos.size()];

		Object m[][] = new Object[terminos.size()][2];

		int x = 0;

		for (int i = 0; i < terminos.size(); i++) {
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					m[i][j] = terminos.get(x).getNombre();
				}
				if (j == 1) {
					m[i][j] = terminos.get(x).getRepeticiones();
				}

			}
			x++;
		}

		x = 0;
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				datos[x] = m[i][j];
				x++;
			}
			this.tableModel.addRow(datos);
			x = 0;

		}
	}

	/**
	 * Método en el cual se configuran aspectos de la tabla, como
	 * tamanio, ubicacion y columnas.
	 */
	private void configurarTabla() {
		// Colocamos tamaño al ScrollPane.
		this.scrollPane.setSize(400, 400);

		// Agregamos las columnas al modelo de la tabla.
		tableModel.addColumn("Ruta");
		tableModel.addColumn("Repeticiones");

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				int fila = table.getSelectedRow();
				int columna = table.getSelectedColumn();

				String ruta = table.getValueAt(fila, columna).toString();

				abrirArchivo(ruta);

			}
		});

		// Agregamos el modelo a la tabla.
		this.table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setMaxWidth(200);
		table.getColumnModel().getColumn(1).setMaxWidth(100);

		// Agregamos la tabla al ScrollPane.
		this.scrollPane.setViewportView(this.table);
		// Indicamos donde se ubicará el ScrollPane y con que tamaño.
		// Parámetros: x, y, ancho, largo.
		this.scrollPane.setBounds(10, 72, 300, 318);
	}

	/**
	 * Abre el archivo según la ruta
	 * @param ruta ruta en la que se va a buscar el archivo
	 */
	public void abrirArchivo(String ruta) {
		File objetofile = new File(ruta);
		try {
			Desktop.getDesktop().open(objetofile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Limpia la tabla para nuevos usos
	 */
	public void limpiar() {
		for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
	}
}
