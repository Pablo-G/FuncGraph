package FuncGraph;

public enum Valor{
	ABIERTO,
	CERRADO,
	SUMA,
	RESTA,
	MULTIPLICACION,
	DIVISION,
	POTENCIA,
	SIN,
	COS,
	TAN,
	COT,
	SEC,
	CSC,
	X,
	DIGITOS;

	private double digitos;

	public void setDigitos(double d){
		this.digitos = d;
	}

	public double getDigitos(){
		return this.digitos;
	}


}