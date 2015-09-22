package FuncGraph;

import java.util.LinkedList;

public class Controlador{
	
	private AnalizadorSintactico anS;
	private LinkedList<NodoArbol> funciones;

	public Controlador() throws ExcepcionEntradaInvalida{
		anS = new AnalizadorSintactico(new AnalizadorLexico("").generaListaTokens());
		funciones = new LinkedList<NodoArbol>();
	}

	public void setAnalizadorLexico(String s) throws ExcepcionEntradaInvalida{
		anS = new AnalizadorSintactico(new AnalizadorLexico(s).generaListaTokens());
	}

	public void generaArbol() throws ExcepcionEntradaInvalida{
		funciones.add(anS.generaArbol());
	}

	public double[] evaluaUltimo(double zoom, double despX, double despY){
		NodoArbol ult = funciones.getLast();
		double[] valoresY = new double[1130];
		for (int i = 0; i < valoresY.length ; i++) {
			double a = 0;
			if (zoom == 0) {
				a = ult.evalua(i-((565)+despX));
			}else if (zoom > 0) {
				a = ult.evalua((i-((565)+despX))/zoom);
				a = a * zoom;
			}else{
				a = ult.evalua((i-((565)+despX))*(-zoom));
				a = a * (-zoom);
			}
			a = a + despY;
			if (a < -315) {
				a = -315;
			}else if (a > 315) {
				a = 315;
			}
			valoresY[i] = a;
		}
		return valoresY;
	}

	public LinkedList<double[]> evaluaTodo(double zoom, double despX, double despY){
		LinkedList<double[]> evaluaciones = new LinkedList<double[]>();
		for (NodoArbol nodo : funciones) {
			double[] valoresY = new double[1130];
			for (int i = 0; i < valoresY.length ; i++) {
				double a = 0;
				if (zoom == 0) {
					a = nodo.evalua(i-((565)+despX));
				}else if (zoom > 0) {
					a = nodo.evalua((i-((565)+despX))/zoom);
					a = a * zoom;
				}else{
					a = nodo.evalua((i-((565)+despX))*(-zoom));
					a = a * (-zoom);
				}
				a = a + despY;
				if (a < -315) {
					a = -315;
				}else if (a > 315) {
					a = 315;
				}
				valoresY[i] = a;
			}
			evaluaciones.add(valoresY);
		}
		return evaluaciones;
	}

	public void resetea(){
		funciones = new LinkedList<NodoArbol>();
	}

}