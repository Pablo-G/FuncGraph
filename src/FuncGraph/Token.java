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
}