/**
 *Clase <code>AnalizadorSintactico</code>.
 *Analizador Sintáctico para la aplicación FuncGraph.
 *Contiene umétodos para verificar la gramatica de una lista de Tokens,
 * y generar un arbol sintáctico utlizando una implementación del algoritmo
 * Shunting Yard.
 *Creada: Sep 2015
 *@author <a href="mailto:pablo.t645@hotmail.com">Pablo G.</a>
 *@version 1.0
 *Copyright 2015 Pablo G.
 */
package FuncGraph;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

public class AnalizadorSintactico{
	
	private LinkedList<Token> listaTokensInf;

    /**
     *<code>AnalizadorSintactico</code> Constructor.
     *@param listaTokens tipo <code>LinkedList<Token></code>: Lista de Tokens.
     */
	public AnalizadorSintactico(LinkedList<Token> listaTokens){
		this.listaTokensInf = listaTokens;
	}

    /**
     *<code>verificaGramatica</code> Método que verifica la grámatica de la lista de Tokens.
     *Manda una excepción en caso de encontrar algún error en la gramática.
     */
	public void verificaGramatica() throws ExcepcionEntradaInvalida{
		Token[] arreglo = new Token[listaTokensInf.size()];
		listaTokensInf.toArray(arreglo);

		for (int i = 0; i < arreglo.length ; i++) {
			switch (arreglo[i].getValor()) {
				case ABIERTO:{
					if (i != 0) {
						if (arreglo[i-1].getValor() == Valor.CERRADO || arreglo[i-1].getValor() == Valor.X || arreglo[i-1].getValor() == Valor.DIGITOS) {
							throw new ExcepcionEntradaInvalida("E01-" + i + " | Operador esperado una posición antes.");
						}
						if (i == arreglo.length-1) {
							throw new ExcepcionEntradaInvalida("E02-" + i + " | Expresión esperada.");
						}
					}
					break;
				}
				case CERRADO:{
					if (i == 0) {
						throw new ExcepcionEntradaInvalida("E03-" + i + " | Expresión esperada.");
					}
					if (arreglo[i-1].getValor() == Valor.ABIERTO || arreglo[i-1].getTipo() == Tipo.OPERADOR) {
						throw new ExcepcionEntradaInvalida("E04-" + i + " | Expresión esperada.");
					}
					if (arreglo[i-1].getTipo() == Tipo.FUNCION) {
						throw new ExcepcionEntradaInvalida("E05-" + i + " | '(' esperado.");
					}
					break;
				}
				case SUMA: case MULTIPLICACION: case DIVISION: case POTENCIA:{
					if (i == 0) {
						throw new ExcepcionEntradaInvalida("E06-" + i + " | Expresión esperada.");
					}
					if (i == arreglo.length-1) {
						throw new ExcepcionEntradaInvalida("E07-" + i + " | Expresión esperada.");
					}
					if (arreglo[i-1].getValor() == Valor.ABIERTO || arreglo[i-1].getTipo() == Tipo.OPERADOR) {
						throw new ExcepcionEntradaInvalida("E08-" + i + " | Expresión esperada.");
					}
					if (arreglo[i-1].getTipo() == Tipo.FUNCION) {
						throw new ExcepcionEntradaInvalida("E09-" + i + " | '(' esperado.");
					}
					break;
				}
				case RESTA:{
					if (i == arreglo.length-1) {
						throw new ExcepcionEntradaInvalida("E10-" + i + " | Expresión esperada.");
					}
					if (i != 0 && arreglo[i-1].getTipo() == Tipo.FUNCION) {
						throw new ExcepcionEntradaInvalida("E11-" + i + " | '(' esperado.");
					}
					if (i == 0 || arreglo[i-1].getTipo() == Tipo.OPERADOR || arreglo[i-1].getValor() == Valor.ABIERTO) {
						int pos = this.listaTokensInf.indexOf(arreglo[i]);
						this.listaTokensInf.get(pos).setValor(Valor.MULTIPLICACION);
						this.listaTokensInf.add(pos, new Token(Tipo.NUMERO, Valor.DIGITOS, -1));
					}
					break;
				}
				case X: case DIGITOS:{
					if (i != 0) {
						if (arreglo[i-1].getValor() == Valor.CERRADO || arreglo[i-1].getValor() == Valor.X || arreglo[i-1].getValor() == Valor.DIGITOS) {
							throw new ExcepcionEntradaInvalida("E12-" + i + " | Operador esperado una posición antes.");
						}
						if (arreglo[i-1].getTipo() == Tipo.FUNCION) {
							throw new ExcepcionEntradaInvalida("E14-" + i + " | '(' esperado.");
						}
					}
					break;
				}
				case SIN: case COS: case TAN: case COT: case SEC: case CSC: case SQR:{
					if (i != 0) {
						if (arreglo[i-1].getValor() == Valor.CERRADO || arreglo[i-1].getValor() == Valor.X || arreglo[i-1].getValor() == Valor.DIGITOS) {
							throw new ExcepcionEntradaInvalida("E15-" + i + " | Operador esperado una posición antes.");
						}
						if (i == arreglo.length-1 || arreglo[i-1].getTipo() == Tipo.FUNCION) {
							throw new ExcepcionEntradaInvalida("E16-" + i + " | '(' esperado.");
						}		
					}
					break;
				}
			}
		}
	}

    /**
     *<code>shuntingYard</code> Implementación del algoritmo Shunting Yard que cambia el orden de la lista de Tokens, de notación infica a postfija.
     *@return tipo <code>LinkedList<Token><Token></code>: Lista de Tokens en notación postfija.
     *Manda una excepción en caso de encontrar un identificador no válido (Excepción mandada por verificaGramatica()).
     */
	public LinkedList<Token> shuntingYard() throws ExcepcionEntradaInvalida{
		verificaGramatica();

		ListIterator<Token> it = this.listaTokensInf.listIterator(0);
		LinkedList<Token> nlistaTokens = new LinkedList<Token>();
		Stack<Token> stack = new Stack<Token>();

		while(it.hasNext()){
			Token actual = it.next();
			if (actual.getTipo() == Tipo.NUMERO || actual.getTipo() == Tipo.VARIABLE) {
				nlistaTokens.add(actual);
			}else if (actual.getTipo() == Tipo.FUNCION) {
				stack.push(actual);
			}else if (actual.getTipo() == Tipo.OPERADOR) {
				while(!stack.empty() && stack.peek().getTipo() == Tipo.OPERADOR && actual.esMayorPres(stack.peek())){
					nlistaTokens.add(stack.pop());
				}
				stack.push(actual);
			}else if (actual.getValor() == Valor.ABIERTO) {
				stack.push(actual);
			}else if (actual.getValor() == Valor.CERRADO) {
				while(!stack.empty() && stack.peek().getValor() != Valor.ABIERTO){
					nlistaTokens.add(stack.pop());
				}
				stack.pop();
				if (!stack.empty() && stack.peek().getTipo() == Tipo.FUNCION) {
					nlistaTokens.add(stack.pop());
				}
			}
		}
		
		while(!stack.empty()){
			nlistaTokens.add(stack.pop());
		}

		return nlistaTokens;
	}

    /**
     *<code>generaArbol</code> Método que genera el árbol sintáctico.
     *@return tipo <code>NodoArbol<Token></code>: Raiz del árbol sintáctico.
     *Manda una excepción en caso de encontrar un identificador no válido (Excepción mandada por verificaGramatica()).
     */
	public NodoArbol generaArbol() throws ExcepcionEntradaInvalida{
		ListIterator<Token> it = shuntingYard().listIterator(0);
		Stack<NodoArbol> stack = new Stack<NodoArbol>();

		while(it.hasNext()){
			Token actual = it.next();
			switch(actual.getValor()){
				case X: case DIGITOS:{
					stack.push(new NodoArbol(actual));
					break;
				}
				case SUMA: case RESTA: case MULTIPLICACION: case DIVISION: case POTENCIA:{
					NodoArbol actualN = new NodoArbol(actual);
					actualN.agregaHD(stack.pop());
					actualN.agregaHI(stack.pop());
					stack.push(actualN);
					break;
				}
				case SIN: case COS: case TAN: case CSC: case SEC: case COT: case SQR:{
					NodoArbol actualN = new NodoArbol(actual);
					actualN.agregaHD(stack.pop());
					stack.push(actualN);
					break;
				}
			}
		}

		return stack.pop();
	}

}