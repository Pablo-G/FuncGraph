/**
 *Clase <code>Controlador</code>.
 *Controlador para la aplicación FuncGraph.
 *Simula el controlador del patrón de diseño MVC.
 *Conecta al modelo con la vista.
 *@author <a href="mailto:pablo.t645@hotmail.com">Pablo G.</a>
 *@version 1.0
 *Copyright 2015 Pablo G.
 */
package FuncGraph;

import java.util.LinkedList;

public class Controlador{
	
	private AnalizadorSintactico anS;
	private LinkedList<NodoArbol> funciones;

    /**
     *<code>Controlador</code> Constructor.
     *Manda una excepción en caso de que el Analizador Léxico encuentre algún problema.
     */
	public Controlador() throws ExcepcionEntradaInvalida{
		anS = new AnalizadorSintactico(new AnalizadorLexico("").generaListaTokens());
		funciones = new LinkedList<NodoArbol>();
	}

    /**
     *<code>setAnalizadorLexico</code> Crea un nuevo Analizador Léxico para la función en s.
     *@param s tipo <code>String</code>: Una función f(x) en notación infija.
     *Manda una excepción en caso de que el Analizador Léxico encuentre algún problema.
     */
	public void setAnalizadorLexico(String s) throws ExcepcionEntradaInvalida{
		anS = new AnalizadorSintactico(new AnalizadorLexico(s).generaListaTokens());
	}

    /**
     *<code>generaArbol</code> Método que pide generar el árbol sintáctico de la última funcion dada y lo agrega a la lista de funciones.
     *Manda una excepción en caso de que el Analizador Léxico encuentre algún problema.
     */
	public void generaArbol() throws ExcepcionEntradaInvalida{
		funciones.add(anS.generaArbol());
	}

    /**
     *<code>evaluaUltimo</code> Método que evalúa el mas reciente árbol sintáctico insertado en la lista de funciones.
     *@param zoom tipo <code>double</code>: Zoom que tiene la función.
     *@param despX tipo <code>double</code>: Desplazamiento en el eje X de la función.
     *@param despY tipo <code>double</code>: Desplazamiento en el eje Y de la función.
     *@param width tipo <code>int</code>: Ancho en pixeles que tendrá la función (Se restarán 330).
     *@return tipo <code>double[]<Token></code>: Arreglo con los valores en Y de la función.
     */
	public double[] evaluaUltimo(double zoom, double despX, double despY, int width){
		NodoArbol ult = funciones.getLast();
		double[] valoresY = new double[width-330];

		for (int i = 0; i < valoresY.length ; i++) {
			double a = 0;
			if (zoom == 0) {
				a = ult.evalua(i-(((width-330)/2)+despX));
			}else if (zoom > 0) {
				a = ult.evalua((i-(((width-330)/2)+despX))/zoom);
				a = a * zoom;
			}else{
				a = ult.evalua((i-(((width-330)/2)+despX))*(-zoom));
				a = a * (-zoom);
			}
			a = a + despY;
			valoresY[i] = a;
		}
		
		return valoresY;
	}

    /**
     *<code>evaluaUltimo</code> Método que evalúa todos los árboles sintácticos de la lista de funciones.
     *@param zoom tipo <code>double</code>: Zoom que tiene la función.
     *@param despX tipo <code>double</code>: Desplazamiento en el eje X de la función.
     *@param despY tipo <code>double</code>: Desplazamiento en el eje Y de la función.
     *@param width tipo <code>int</code>: Ancho en pixeles que tendrá la función (Se restarán 330).
     *@return tipo <code>LinkedList<double[]><Token></code>: Lista con arreglo con los valores en Y de la función.
     */
	public LinkedList<double[]> evaluaTodo(double zoom, double despX, double despY, int width){
		LinkedList<double[]> evaluaciones = new LinkedList<double[]>();

		for (NodoArbol nodo : funciones) {
			double[] valoresY = new double[width-330];
			for (int i = 0; i < valoresY.length ; i++) {
				double a = 0;
				if (zoom == 0) {
					a = nodo.evalua(i-(((width-330)/2)+despX));
				}else if (zoom > 0) {
					a = nodo.evalua((i-(((width-330)/2)+despX))/zoom);
					a = a * zoom;
				}else{
					a = nodo.evalua((i-(((width-330)/2)+despX))*(-zoom));
					a = a / (-zoom);
				}
				a = a + despY;
				valoresY[i] = a;
			}
			evaluaciones.add(valoresY);
		}

		return evaluaciones;
	}

    /**
     *<code>evaluaUltimo</code> Método que evalúa todos los árboles sintácticos de la lista de funciones.
     *@param zoom tipo <code>double</code>: Zoom que tiene la función.
     *@param despX tipo <code>double</code>: Desplazamiento en el eje X de la función.
     *@param despY tipo <code>double</code>: Desplazamiento en el eje Y de la función.
     *@param width tipo <code>int</code>: Ancho en pixeles que tendrá la función.
     *@param b tipo <code>boolean</code>: Diferencia de evaluaTodo(double zoom, double despX, double despY, int width).
     *Este método no restará 330 a width.
     *@return tipo <code>LinkedList<double[]><Token></code>: Lista con arreglo con los valores en Y de la función.
     */
	public LinkedList<double[]> evaluaTodo(double zoom, double despX, double despY, int width, boolean b){
		LinkedList<double[]> evaluaciones = new LinkedList<double[]>();

		for (NodoArbol nodo : funciones) {
			double[] valoresY = new double[width];
			for (int i = 0; i < valoresY.length ; i++) {
				double a = 0;
				if (zoom == 0) {
					a = nodo.evalua(i-(((width)/2)+despX));
				}else if (zoom > 0) {
					a = nodo.evalua((i-(((width)/2)+despX))/zoom);
					a = a * zoom;
				}else{
					a = nodo.evalua((i-(((width)/2)+despX))*(-zoom));
					a = a / (-zoom);
				}
				a = a + despY;
				valoresY[i] = a;
			}
			evaluaciones.add(valoresY);
		}

		return evaluaciones;
	}

    /**
     *<code>resetea</code> Método que elimina todas las funciones de la lista de funciones.
     */
	public void resetea(){
		funciones = new LinkedList<NodoArbol>();
	}

}