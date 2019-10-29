package logic;

/**
 * Clase con el nombre del término y la cantidad de veces que se repite
 * 
 * 
 * @author Viviana Guerrero
 * @author Juan David Romero
 * @author Carlos Quiñones
 */

public class Termino {
	private String nombre;
	private int repeticiones;

	/**
	 * Constructor de la clase
	 * 
	 * @param nombre nombre que va a tener la clase
	 */
	public Termino(String nombre) {
		super();
		this.nombre = nombre;
	}

	/**
	 * Accesor del nombre
	 * 
	 * @return nombre de la clase
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Modificador del nombre
	 * 
	 * @param nombre nombre con el que se va a modificar modificada la clase
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * accesor de las repeticiones de la clase
	 * 
	 * @return cantidad de repeticiones
	 */
	public int getRepeticiones() {
		return repeticiones;
	}

	/**
	 * Modificador de repeticiones
	 * 
	 * @param repeticiones repeticiones con las que se va a modificar la clase
	 */
	public void setRepeticiones(int repeticiones) {
		this.repeticiones = repeticiones;
	}

}
