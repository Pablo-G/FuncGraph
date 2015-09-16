package FuncGraph;

public class NodoArbol{
	
	private Token elemento;
	private NodoArbol hijoIzq;
	private NodoArbol hijoDer;

	public NodoArbol(Token elemento){
		this.elemento = elemento;
		hijoIzq = null;
		hijoDer = null;
	}

	public void agregaHI(NodoArbol nvo){
		hijoIzq = nvo;
	}

	public void agregaHD(NodoArbol nvo){
		hijoDer = nvo;
	}

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

}