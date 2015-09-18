package FuncGraph;

import java.util.LinkedList;

public class AnalizadorLexico{
	
	String entrada;

	public AnalizadorLexico(String entrada){
		this.entrada = entrada;
	}

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

	private boolean esDigito(char a){
		if (a == '0' || a == '1' || a == '2' || a == '3' || a == '4' || a == '5' || a == '6' || a == '7' || a == '8' || a == '9') {
			return true;		
		}else{
			return false;
		}
	}

}