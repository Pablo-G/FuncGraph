/**
 *Clase <code>Token</code>.
 *Tokens para la aplicación FuncGraph.
 *Cada uno de estos contine un Tipo y Valor. En caso de ser un número, tambien lo contendrá.
 *@author <a href="mailto:pablo.t645@hotmail.com">Pablo G.</a>
 *@version 1.0
 *Copyright 2015 Pablo G.
 */
package FuncGraph;

public class Token{
	
	private Tipo t;
	private Valor v;
	private double d;

    /**
     *<code>generaListaTokens</code> Constructor.
     *@param tipo tipo <code>Tipo</code>: Tipo que tendrá el Token.
     *@param valor tipo <code>Valor</code>: Valor que tendrá el Token.
     */
	public Token(Tipo tipo, Valor valor){
		this.t = tipo;
		this.v = valor;
		this.d = 0;
	}

    /**
     *<code>generaListaTokens</code> Constructor para digitos.
     *@param tipo tipo <code>Tipo</code>: Tipo que tendrá el Token.
     *@param valor tipo <code>Valor</code>: Valor que tendrá el Token.
     *@param digitos tipo <code>double</code>: Dígitos que tendrá el Token.
     */
	public Token(Tipo tipo, Valor valor, double digitos){
		this.t = tipo;
		this.v = valor;
		this.d = digitos;
	}

    /**
     *<code>getTipo</code> Método que regresa el tipo del Token.
     *@return tipo <code>Tipo</code>: Tipo.
     */
	public Tipo getTipo(){
		return this.t;
	}

    /**
     *<code>getValor</code> Método que regresa el valor del Token.
     *@return tipo <code>Valor</code>: Valor.
     */
	public Valor getValor(){
		return this.v;
	}

    /**
     *<code>setValor</code> Método que cambia el valor del Token.
     *@param valor tipo <code>Valor</code>: Nuevo valor.
     */
	public void setValor(Valor valor){
		this.v = valor;
	}

    /**
     *<code>getDigitos</code> Método que regresa los dígitos del Token.
     *@return tipo <code>double</code>: Dígitos.
     */
	public double getDigitos(){
		return this.d;
	}

    /**
     *<code>esMayorPres</code> Método que nos dice si el Token m tiene mayor presedencia que este.
     *USAR SOLO CON OPERADORES.
     *@param m tipo <code>Token</code>: Token a comparar.
     *@return tipo <code>boolean</code>: True si m tiene mayor presedencia; False en otro caso.
     */
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

    /**
     *<code>equals</code> Método que determina si dos Tokens son iguales.
     *@param o tipo <code>Object</code>: Token a comparar.
     *@return tipo <code>boolean</code>: True si Tipo y Valor son iguales; False en otro caso.
     */
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

    /**
     *<code>toString</code> Método que genera una representación en cadena del Token.
     *@return tipo <code>String</code>: Representación en cadena del Token.
     */
	@Override public String toString(){
		if (this.v == Valor.DIGITOS) {
			return "[" + t + " , " + v + "-|" + d + "|-" + "]";
		}else{
			return "[" + t + " , " + v + "]"; 
		}
	}

}