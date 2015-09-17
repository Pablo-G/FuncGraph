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

	public Token getElemento(){
		return this.elemento;
	}

	public NodoArbol getHI(){
		return this.hijoIzq;
	}

	public NodoArbol getHD(){
		return this.hijoDer;
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