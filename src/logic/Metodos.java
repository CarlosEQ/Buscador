package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/*
 * @author Viviana Guerrero
 * @author Juan David Romero
 * @author Carlos Quiñones
 */
public class Metodos {

	private ArrayList<TerminoOnologico> terms;

	private ArrayList<Documento> documentos = new ArrayList<>();

	private ArrayList<Termino> ocurrencias = new ArrayList<Termino>();

	public Metodos() {
		leerDirectorioCompleto();
	}

	/**
	 * Lee un archivo según la ruta
	 * 
	 * @return cadena con todas las palabras
	 */
	public String leerArchivos(String ruta) {

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String salida = "";

		try {
			archivo = new File(ruta);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;

			while ((linea = br.readLine()) != null) {
				salida += linea + "\n";

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		salida = salida.replace('<', ' ');
		salida = salida.replace('>', ' ');
		return salida;
	}

	/**
	 * Lee todos los ficheros que hay en el directorio
	 */
	public void leerDirectorioCompleto() {

		String ruta = ("./src/resources/");

		String[] archivos = obtenerArchivosDirectorio(ruta);

		if (archivos != null) {

			int tamanio = archivos.length;

			for (int i = 0; i < tamanio; i++) {

				String cad = leerArchivos(archivos[i]);

				conrtarPalabras(cad, archivos[i]);

			}
		}

	}

	/**
	 * Obtiene el código de cada palabra
	 * 
	 * @param palabra palabra para obtener el código
	 * @return código de la palabra
	 */
	public String obtenerCodigo(String palabra) {
		String codigo = "";
		for (int i = 0; i < palabra.length(); i++) {
			if (Character.isDigit(palabra.charAt(i))) {
				codigo += palabra.charAt(i);
			}
		}
		return codigo;
	}

	/**
	 * Lista un arreglo de strings con todas las rutas del directorio
	 * 
	 * @param rutaDirectorio directorio a analizar
	 * @return arreglo de rutas
	 */
	public String[] obtenerArchivosDirectorio(String rutaDirectorio) {

		String[] arr_res = null;

		File f = new File(rutaDirectorio);

		if (f.isDirectory()) {

			ArrayList<String> res = new ArrayList<>();
			File[] arr_content = f.listFiles();

			int size = arr_content.length;

			for (int i = 0; i < size; i++) {

				if (arr_content[i].isFile())
					res.add(arr_content[i].toString());
			}

			arr_res = res.toArray(new String[0]);

		} else
			System.err.println("¡ Path NO válido !");

		return arr_res;
	}

	/**
	 * Accesor de términos ontológicos
	 * 
	 * @return array de términos ontológicos
	 */
	public ArrayList<TerminoOnologico> getTerms() {
		return terms;
	}

	/**
	 * Corta la palabras cada que encuenta un código y su lista de términos, de un
	 * archivo guardado como cadena
	 * 
	 * @param cad  cadena donde se leyó el archivo
	 * @param ruta ruta del archivo
	 */
	public void conrtarPalabras(String cad, String ruta) {
		ArrayList<String> terminos;
		terms = new ArrayList<TerminoOnologico>();

		for (int i = 0; i < cad.length(); i++) {

			if (cad.charAt(i) == 'C') {

				if (cad.charAt(i + 1) == 'L') {

					if (cad.charAt(i + 2) == ':') {

						if (Character.isLetter(cad.charAt(i + 12))) {

							terminos = devolverTerminos(cad, i);

							TerminoOnologico t = new TerminoOnologico(devolverCodigo(cad, i));

							t.agregar(t.getCodigo(), terminos);

							terms.add(t);

						}

					}

				}

			}

		}
		Documento d = new Documento();
		d.setRuta(ruta);
		d.setTerminosOntologicos(terms);
		d.contar();
		documentos.add(d);

	}

	/**
	 * Permite obtener el código de los términos de una cadena
	 * 
	 * @param cad cadena para analizar
	 * @param i   posición desde donde se empieza a analizar
	 * @return código de los términos correspondientes
	 */
	public String devolverCodigo(String cad, int i) {
		String aux = "";

		for (int j = i + 3; j < cad.length(); j++) {
			if (Character.isDigit(cad.charAt(j))) {
				aux += cad.charAt(j);
			} else {
				break;

			}

		}
		return aux;
	}

	/**
	 * Permite obtener la lista de lotérminos de una cadena
	 * 
	 * @param cad cadena para analizar
	 * @param i   posición desde donde se empieza a analizar
	 * @return lista de términos correspondientes
	 */
	public ArrayList<String> devolverTerminos(String cad, int i) {
		String aux2 = "";

		ArrayList<String> terminos = new ArrayList<String>();

		for (int j = i + 12; j < cad.length(); j++) {

			if (Character.isLetter(cad.charAt(j))) {
				aux2 += cad.charAt(j);

			} else if (cad.charAt(j) == ' ') {
				terminos.add(aux2);
				aux2 = "";
			} else {

				break;
			}

		}

		return terminos;
	}

	/**
	 * Obtiene un array ordenado, según las repeticiones de una palabra
	 * 
	 * @param palabra Palabra a ser analizada
	 * @return array con la palbra y las veces que se repitió en cada documento
	 */
	public ArrayList<Termino> buscar(String palabra) {

		for (int i = 0; i < documentos.size(); i++) {

			ArrayList<String> terminos = documentos.get(i).contadorOcurrencias(palabra);

			if (terminos != null) {
				String ruta = terminos.get(0);
				int repeticiones = Integer.parseInt(terminos.get(1));

				Termino t = new Termino(ruta);
				t.setRepeticiones(repeticiones);

				ocurrencias.add(t);

			}

		}

		ArrayList<Termino> ordenado = quicksort(ocurrencias);

		return ordenado;

	}

	/**
	 * Accesor del array de documentos
	 * 
	 * @return array de documentos
	 */
	public ArrayList<Documento> getDocumentos() {
		return documentos;
	}

	/**
	 * método para ordenar un array
	 * 
	 * @param entrada array a ordenar
	 * @return el array ordenado
	 */
	private ArrayList<Termino> quicksort(ArrayList<Termino> entrada) {

		if (entrada.size() <= 1) {
			return entrada;
		}

		int medio = (int) Math.ceil((double) entrada.size() / 2);

		int pivote = entrada.get(medio).getRepeticiones();

		Termino aux = entrada.get(medio);

		ArrayList<Termino> menor = new ArrayList<Termino>();
		ArrayList<Termino> mayor = new ArrayList<Termino>();

		for (int i = 0; i < entrada.size(); i++) {
			if (entrada.get(i).getRepeticiones() >= pivote) {
				if (i == medio) {
					continue;
				}
				menor.add(entrada.get(i));
			} else {
				mayor.add(entrada.get(i));
			}
		}

		return concatenar(quicksort(menor), aux, quicksort(mayor));
	}

	private ArrayList<Termino> concatenar(ArrayList<Termino> menor, Termino aux, ArrayList<Termino> mayor) {

		ArrayList<Termino> lista = new ArrayList<Termino>();

		for (int i = 0; i < menor.size(); i++) {
			lista.add(menor.get(i));
		}

		lista.add(aux);

		for (int i = 0; i < mayor.size(); i++) {
			lista.add(mayor.get(i));
		}

		return lista;
	}

	public ArrayList<Termino> getA() {
		return ocurrencias;
	}

}
