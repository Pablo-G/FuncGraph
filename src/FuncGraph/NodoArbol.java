/**
 *Clase <code>NodoArbol</code>.
 *Arbol Binario para la aplicación FuncGraph.
 *Nodos que se utilizan en la aplicación para generar el arbol sintáctico.
 *Cada uno de estos contiene un Token y dos hijos.
 *@author <a href="mailto:pablo.t645@hotmail.com">Pablo G.</a>
 *@version 1.0
 *Copyright 2015 Pablo G.
 */
package FuncGraph;

public class NodoArbol{
	
	private Token elemento;
	private NodoArbol hijoIzq;
	private NodoArbol hijoDer;

    /**
     *<code>NodoArbol</code> Constructor.
     *@param elemento tipo <code>NodoArbol</code>: Elemento que contendrá este nodo.
     */
	public NodoArbol(Token elemento){
		this.elemento = elemento;
		hijoIzq = null;
		hijoDer = null;
	}

    /**
     *<code>agregaHI</code> Método que agrega un hijo izquierdo.
     *@param nvo tipo <code>NodoArbol</code>: Nodo a agregar.
     */
	public void agregaHI(NodoArbol nvo){
		hijoIzq = nvo;
	}

    /**
     *<code>agregaHD</code> Método que agrega un hijo derecho.
     *@param nvo tipo <code>NodoArbol</code>: Nodo a agregar.
     */
	public void agregaHD(NodoArbol nvo){
		hijoDer = nvo;
	}

    /**
     *<code>getElemento</code> Método que regresa el eemento del nodo.
     *@return tipo <code>Token</code>: Elemento del nodo.
     */
	public Token getElemento(){
		return this.elemento;
	}

    /**
     *<code>getHI</code> Método que regresa el hijo izquierdo del nodo.
     *@return tipo <code>NodoArbol</code>: Hijo izquierdo
     */
	public NodoArbol getHI(){
		return this.hijoIzq;
	}

    /**
     *<code>getHD</code> Método que regresa el hijo derecho del nodo.
     *@return tipo <code>NodoArbol</code>: Hijo derecho
     */
	public NodoArbol getHD(){
		return this.hijoDer;
	}

    /**
     *<code>evalua</code> Método recursivo que evalua el arbol a partir del nodo donde se llama.
     *@param x tipo <code>double</code>: Variable independiente.
     *@return tipo <code>double</code>: Variable dependiente.
     */
	public double evalua(double x){
		switch(elemento.getValor()){
			case SUMA:{
				return hijoIzq.evalua(x) + hijoDer.evalua(x);
			}
			case RESTA:{
				return hijoIzq.evalua(x) - hijoDer.evalua(x);
			}
			case MULTIPLICACION:{
				return hijoIzq.evalua(x) * hijoDer.evalua(x);
			}
			case DIVISION:{
				return hijoIzq.evalua(x) / hijoDer.evalua(x);
			}
			case POTENCIA:{
				return Math.pow(hijoIzq.evalua(x), hijoDer.evalua(x));
			}
			case SIN:{
				return Math.sin(hijoDer.evalua(x));
			}
			case COS:{
				return Math.cos(hijoDer.evalua(x));
			}
			case TAN:{
				return Math.tan(hijoDer.evalua(x));
			}
			case CSC:{
				return 1 / Math.sin(hijoDer.evalua(x));
			}
			case SEC:{
				return 1 / Math.cos(hijoDer.evalua(x));
			}
			case COT:{
				return 1 / Math.tan(hijoDer.evalua(x));
			}
			case SQR:{
				return Math.sqrt(hijoDer.evalua(x));
			}
			case X:{
				return x;
			}
			case DIGITOS:{
				return this.elemento.getDigitos();
			}
		}
		return -1;
	}

    /**
     *<code>equals</code> Método recursivo que determina si dos nodos son iguales.
     *@param o tipo <code>Object</code>: Nodo a comparar.
     *@return tipo <code>boolean</code>: True si el elemento y los hijos son iguales recursivamente; False en otro caso.
     */
	@Override public boolean equals(Object o) {
            if (o == null)
                return false;
            if (getClass() != o.getClass())
                return false;
            @SuppressWarnings("unchecked") NodoArbol t = (NodoArbol)o;
            if (!this.elemento.equals(t.elemento)) {
            	return false;
            }
            if (this.getHI() == null && t.getHI() == null && this.getHD() == null && t.getHD() == null ) {
            	return true;
            }
            if (this.getHI() != null && this.getHD() != null) {
            	return this.getHI().equals(t.getHI()) && this.getHD().equals(t.getHD());
            }
            if (this.getHI() != null) {
            	return this.getHI().equals(t.getHI());
            }
            if (this.getHD() != null) {
            	return this.getHD().equals(t.getHD());
            }
            return false;
    }

}