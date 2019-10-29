package logic;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase con el código y el array de términos que tiene
 * 
 * @author Viviana Guerrero
 * @author Juan David Romero
 * @author Carlos Quiñones
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
	 * Accesor del código del término
	 * 
	 * @return código del término
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Modificador del código del término
	 * 
	 * @param codigo código con el que se va a modificar el término
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Accesor del array de términos
	 * 
	 * @return array con los términos de cada código
	 */
	public ArrayList<String> getTerminos() {
		return terminos;
	}

	/**
	 * Modificador del array de términos
	 * 
	 * @param terminos array de términos con los que se va a modificar la clase
	 */
	public void setTerminos(ArrayList<String> terminos) {
		this.terminos = terminos;
	}

	/**
	 * Permite agregar todos los términos que vienen enl parámetro
	 * 
	 * @param codigo código del término
	 * @param a      array con el que se va a modificar la clase
	 */
	public void agregar(String codigo, ArrayList<String> a) {

		terminos.addAll(a);

	}

}
