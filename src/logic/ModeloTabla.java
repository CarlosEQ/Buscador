package logic;

import javax.swing.table.DefaultTableModel;

/**
 * Modelo de tabla propio.
 * 
 * @author Viviana Guerrero
 * @author Juan David Romero
 * @author Carlos Quiñones
 */
public class ModeloTabla extends DefaultTableModel {

	/**
	 * Define la posibilidad de editar de una columna.
	 */
	private final boolean[] tableColums = { false, false };

	@Override
	public final boolean isCellEditable(int row, int column) {
		if (column == 0) {
			return false;
		}
		return this.tableColums[column];

	}

}