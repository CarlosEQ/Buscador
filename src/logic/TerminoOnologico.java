package logic;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase con el c�digo y el array de t�rminos que tiene
 * 
 * @author Viviana Guerrero
 * @author Juan David Romero
 * @author Carlos Qui�ones
 */

public class TerminoOnologico {

	private String codigo;
	private ArrayList<String> terminos;

	/**
	 * Constructor de la clase
	 * 
	 * @param codigo
	 */
	public TerminoOnologico(String codigo) {
		super();
		terminos = new ArrayList<String>();
		this.codigo = codigo;
	}

	/**
	 * Accesor del c�digo del t�rmino
	 * 
	 * @return c�digo del t�rmino
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Modificador del c�digo del t�rmino
	 * 
	 * @param codigo c�digo con el que se va a modificar el t�rmino
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Accesor del array de t�rminos
	 * 
	 * @return array con los t�rminos de cada c�digo
	 */
	public ArrayList<String> getTerminos() {
		return terminos;
	}

	/**
	 * Modificador del array de t�rminos
	 * 
	 * @param terminos array de t�rminos con los que se va a modificar la clase
	 */
	public void setTerminos(ArrayList<String> terminos) {
		this.terminos = terminos;
	}

	/**
	 * Permite agregar todos los t�rminos que vienen enl par�metro
	 * 
	 * @param codigo c�digo del t�rmino
	 * @param a      array con el que se va a modificar la clase
	 */
	public void agregar(String codigo, ArrayList<String> a) {

		terminos.addAll(a);

	}

}
