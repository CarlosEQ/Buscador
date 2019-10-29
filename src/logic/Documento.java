package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase en la que se tiene un mapa, con un término y la cantidad de veces que
 * se repite en el documento
 * 
 * @author Viviana Guerrero
 * @author Juan David Romero
 * @author Carlos Quiñones
 * 
 */
public class Documento {

	private String ruta;

	private ArrayList<TerminoOnologico> terminosOntologicos;

	HashMap<String, Integer> ocurrencias = new HashMap<String, Integer>();

	/**
	 * Constructor de la clase
	 */
	public Documento() {

	}

	/**
	 * Modificador de la ruta
	 * 
	 * @param ruta ruta para ser modificada
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	/**
	 * Modificador del array de términos ontológicos
	 * 
	 * @param terminosOntologicos
	 */
	public void setTerminosOntologicos(ArrayList<TerminoOnologico> terminosOntologicos) {
		this.terminosOntologicos = terminosOntologicos;
	}

	/**
	 * Accesor del array de términos ontológicos
	 * 
	 * @return array de términos ontológicos
	 */
	public ArrayList<TerminoOnologico> getTerminosOntologicos() {
		return terminosOntologicos;
	}

	/**
	 * Accesor de la ruta
	 * 
	 * @return la ruta del documento
	 */
	public String getRuta() {
		return ruta;
	}

	/**
	 * Llama al método de contar, para enviar como parámetro cada término ontológico
	 */
	public void contar() {

		for (int i = 0; i < terminosOntologicos.size(); i++) {

			realizarCuenta(terminosOntologicos.get(i));

		}
	}

	/**
	 * hace la cuenta de cuántas veces se repite un término en el array de términos
	 * 
	 * @param t término ontológico a ser analizado
	 */
	public void realizarCuenta(TerminoOnologico t) {

		ArrayList<String> array = t.getTerminos();

		for (int i = 0; i < array.size(); i++) {

			if (!ocurrencias.containsKey(array.get(i))) {
				ocurrencias.put(array.get(i), 1);
			} else {
				int n = ocurrencias.get(array.get(i));

				ocurrencias.put(array.get(i), ++n);
			}

		}

	}

	/**
	 * Guarda en un array la ruta del archivo y lcantidad de veces que se repite o hay una palabra
	 * similar a la ingresada como parámetro
	 * 
	 * @param palabra palabra a ser analizada
	 * @return array con la 
	 */
	public ArrayList<String> contadorOcurrencias(String palabra) {

		int contador = 0;
		palabra = palabra.toLowerCase();

		for (Map.Entry<String, Integer> entry : ocurrencias.entrySet()) {

			String clave = entry.getKey();
			Integer repeticiones = entry.getValue();

			clave = clave.toLowerCase();

			if (clave.matches(palabra + ".*")) {

				contador += repeticiones;

			}

		}

		ArrayList<String> resultado = null;
		if (contador != 0) {
			resultado = new ArrayList<String>();
			resultado.add(ruta);
			resultado.add(contador + "");
		}
		return resultado;
	}

}
