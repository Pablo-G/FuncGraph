package FuncGraph;

public class Token{
	
	private Tipo t;
	private Valor v;
	private double d;

	public Token(Tipo tipo, Valor valor){
		this.t = tipo;
		this.v = valor;
		this.d = 0;
	}

	public Token(Tipo tipo, Valor valor, double digitos){
		this.t = tipo;
		this.v = valor;
		this.d = digitos;
	}

	public Tipo getTipo(){
		return this.t;
	}

	public Valor getValor(){
		return this.v;
	}

	public void setValor(Valor valor){
		this.v = valor;
	}

	public double getDigitos(){
		return this.d;
	}

	public boolean esMayorPres(Token m){
		switch(this.getValor()){
			case SUMA: case RESTA:{
				return true;
			}
			case MULTIPLICACION: case DIVISION:{
				if (m.getValor() == Valor.SUMA || m.getValor() == Valor.RESTA){
					return false;
				}else{
					return true;
				}
			}
			case POTENCIA:{
				return false;
			}
		}
		return false;
	}

	@Override public boolean equals(Object o) {
            if (o == null)
                return false;
            if (getClass() != o.getClass())
                return false;
            @SuppressWarnings("unchecked") Token t = (Token)o;
	        if (this.getTipo() == t.getTipo()) {
				if (this.getValor() == t.getValor()) {
					return this.getDigitos() == t.getDigitos();
				}else{
					return false;
				}
			}else{
				return false;
			}
    }

	@Override public String toString(){
		if (this.v == Valor.DIGITOS) {
			return "[" + t + " , " + v + "-|" + d + "|-" + "]";
		}else{
			return "[" + t + " , " + v + "]"; 
		}
	}
}