/**
 *Clase <code>AnalizadorLexico</code>.
 *Analizador Lexico para la aplicación FuncGraph.
 *Contiene un método para transformar una String en una lista de Tokens.
 *Creada: Sep 2015
 *@author <a href="mailto:pablo.t645@hotmail.com">Pablo G.</a>
 *@version 1.0
 *Copyright 2015 Pablo G.
 */
package FuncGraph;

import java.util.LinkedList;

public class AnalizadorLexico{
	
	String entrada;

    /**
     *<code>AnalizadorLexico</code> Constructor.
     *@param entrada tipo <code>String</code>: Una función f(x) en notación infija.
     */
	public AnalizadorLexico(String entrada){
		this.entrada = entrada;
	}

    /**
     *<code>generaListaTokens</code> Método que genera la lista de Tokens de la función insertada en el constructor.
     *@return tipo <code>LinkedList<Token></code>: Lista de Tokens.
     *Manda una excepción en caso de encontrar un identificador no válido.
     */
	public LinkedList<Token> generaListaTokens() throws ExcepcionEntradaInvalida{

		LinkedList<Token> lista = new LinkedList<Token>();
		int contadorPa = 0;
		int i = 0;

		while(i < this.entrada.length()){
			char actual = entrada.charAt(i);

			switch(actual){
				case '(': {
							lista.add(new Token(Tipo.PARENTESIS, Valor.ABIERTO));
							contadorPa = contadorPa + 1;
							i++;
							break;
				}
				case ')': {
							lista.add(new Token(Tipo.PARENTESIS, Valor.CERRADO));
							contadorPa = contadorPa - 1;
							i++;
							break;
				}
				case '+': {
							lista.add(new Token(Tipo.OPERADOR, Valor.SUMA));
							i++;
							break;
				}
				case '-': {
							lista.add(new Token(Tipo.OPERADOR, Valor.RESTA));
							i++;
							break;
				}
				case '*': {
							lista.add(new Token(Tipo.OPERADOR, Valor.MULTIPLICACION));
							i++;
							break;
				}
				case '/': {
							lista.add(new Token(Tipo.OPERADOR, Valor.DIVISION));
							i++;
							break;
				}
				case '^': {
							lista.add(new Token(Tipo.OPERADOR, Valor.POTENCIA));
							i++;
							break;
				}
				case 's': {
							try{
								char a = entrada.charAt(i + 1);
								char b = entrada.charAt(i + 2);

								if (a == 'i' && b == 'n') {
									lista.add(new Token(Tipo.FUNCION, Valor.SIN));
									i++;
									i++;
									i++;
									break;
								}else if (a == 'e' && b == 'c') {
									lista.add(new Token(Tipo.FUNCION, Valor.SEC));
									i++;
									i++;
									i++;
									break;
								}else if (a == 'q' && b == 'r') {
									lista.add(new Token(Tipo.FUNCION, Valor.SQR));
									i++;
									i++;
									i++;
									break;
								}else{
									if (a != 'i' || a != 'e' || a != 'q') {
										throw new ExcepcionEntradaInvalida("E00-" + i+1 + " | Identificador Desconocido.");
									}else{
										throw new ExcepcionEntradaInvalida("E00-" + i+2 + " | Identificador Desconocido.");
									}
								}

							}catch(IndexOutOfBoundsException a){
								throw new ExcepcionEntradaInvalida("E00-" + i + " | Identificador Desconocido.");
							}
				}
				case 'c': {
							try{
								char a = entrada.charAt(i + 1);
								char b = entrada.charAt(i + 2);

								if (a == 'o' && b == 's') {
									lista.add(new Token(Tipo.FUNCION, Valor.COS));
									i++;
									i++;
									i++;
									break;
								}else if (a == 'o' && b == 't') {
									lista.add(new Token(Tipo.FUNCION, Valor.COT));
									i++;
									i++;
									i++;
									break;									
								}else if (a == 's' && b == 'c') {
									lista.add(new Token(Tipo.FUNCION, Valor.CSC));
									i++;
									i++;
									i++;
									break;
								}else{
									if (a != 'o' || a != 's') {
										throw new ExcepcionEntradaInvalida("E00-" + i+1 + " | Identificador Desconocido.");
									}else{
										throw new ExcepcionEntradaInvalida("E00-" + i+2 + " | Identificador Desconocido.");
									}
								}
							}catch(IndexOutOfBoundsException a){
								throw new ExcepcionEntradaInvalida("E00-" + i + " | Identificador Desconocido.");
							}
				}
				case 't': {
							try{
								char a = entrada.charAt(i + 1);
								char b = entrada.charAt(i + 2);

								if (a == 'a' && b == 'n') {
									lista.add(new Token(Tipo.FUNCION, Valor.TAN));
									i++;
									i++;
									i++;
									break;
								}else{
									if (a != 'a') {
										throw new ExcepcionEntradaInvalida("E00-" + i+1 + " | Identificador Desconocido.");
									}else{
										throw new ExcepcionEntradaInvalida("E00-" + i+2 + " | Identificador Desconocido.");
									}
								}
							}catch(IndexOutOfBoundsException a){
								throw new ExcepcionEntradaInvalida("E00-" + i + " | Identificador Desconocido.");
							}
				}
				case 'x': {
							lista.add(new Token(Tipo.VARIABLE, Valor.X));
							i++;
							break;
				}
				case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': case '.': {
							boolean punto = false;
							if (actual == '.') {
								punto = true;
							}

							i++;

							String num = "" + actual;
							boolean agregado = false;

							while(i < entrada.length()){
								char a = entrada.charAt(i);

								if (a == '.') {
									if (punto) {
										throw new ExcepcionEntradaInvalida("E00-" + i + " | '.' Inesperado.");
									}else{
										num = num + a;
										punto = true;
										i++;
									}
								}else if (esDigito(a)) {
									num = num + a;
									i++;
								}else{
									lista.add(new Token(Tipo.NUMERO, Valor.DIGITOS, Double.parseDouble(num)));
									agregado = true;
									break;
								}
							}
							if (!agregado) {
								lista.add(new Token(Tipo.NUMERO, Valor.DIGITOS, Double.parseDouble(num)));
							}
							break;
				}
				default: {
					throw new ExcepcionEntradaInvalida("E00-" + i + " | Identificador Desconocido.");
				}
			}

		}
		
		if (contadorPa == 0) {
			return lista;
		}else{
			throw new ExcepcionEntradaInvalida("E00-0 | Parentesis Incompletos.");
		}
	}

    /**
     *<code>esDigito</code> Método que determina si un caracter es un dígito o no.
     *@param a tipo <code>char</code>: Dígito.
     *@return tipo <code>boolean<Token></code>: True si es un dígito; False en otro caso.
     */
	private boolean esDigito(char a){
		if (a == '0' || a == '1' || a == '2' || a == '3' || a == '4' || a == '5' || a == '6' || a == '7' || a == '8' || a == '9') {
			return true;		
		}else{
			return false;
		}
	}

}