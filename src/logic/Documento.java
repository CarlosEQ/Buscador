package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase en la que se tiene un mapa, con un t�rmino y la cantidad de veces que
 * se repite en el documento
 * 
 * @author Viviana Guerrero
 * @author Juan David Romero
 * @author Carlos Qui�ones
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
	 * Modificador del array de t�rminos ontol�gicos
	 * 
	 * @param terminosOntologicos
	 */
	public void setTerminosOntologicos(ArrayList<TerminoOnologico> terminosOntologicos) {
		this.terminosOntologicos = terminosOntologicos;
	}

	/**
	 * Accesor del array de t�rminos ontol�gicos
	 * 
	 * @return array de t�rminos ontol�gicos
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
	 * Llama al m�todo de contar, para enviar como par�metro cada t�rmino ontol�gico
	 */
	public void contar() {

		for (int i = 0; i < terminosOntologicos.size(); i++) {

			realizarCuenta(terminosOntologicos.get(i));

		}
	}

	/**
	 * hace la cuenta de cu�ntas veces se repite un t�rmino en el array de t�rminos
	 * 
	 * @param t t�rmino ontol�gico a ser analizado
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
	 * similar a la ingresada como par�metro
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
