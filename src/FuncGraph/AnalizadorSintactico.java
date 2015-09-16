package FuncGraph;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

public class AnalizadorSintactico{
	
	private LinkedList<Token> listaTokensInf;

	public AnalizadorSintactico(LinkedList<Token> listaTokens){
		this.listaTokensInf = listaTokens;
	}

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
					if (arreglo[i-1].getTipo() == Tipo.FUNCION) {
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
				if (stack.peek().getTipo() == Tipo.FUNCION) {
					nlistaTokens.add(stack.pop());
				}
			}
		}
		while(!stack.empty()){
			nlistaTokens.add(stack.pop());
		}

		return nlistaTokens;
	}
}