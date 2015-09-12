package FuncGraph;

public class Token{
	
	private Tipo t;
	private Valor v;

	public Token(Tipo tipo, Valor valor){
		this.t = tipo;
		this.v = valor;
	}

	public Tipo getTipo(){
		return this.t;
	}

	public Valor getValor(){
		return this.v;
	}

	public double getDigitos(){
		return this.v.getDigitos();
	}

	public boolean equals(Token t){
		if (this.getTipo() == t.getTipo()) {
			if (this.getValor() == t.getValor()) {
				return this.getValor().getDigitos() == t.getValor().getDigitos();
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}