package FuncGraph.test;

import org.junit.Assert;
import org.junit.Test;
import java.util.Random;
import FuncGraph.Token;
import FuncGraph.Tipo;
import FuncGraph.Valor;

public class TestToken{
	
	private Random random;

	public TestToken(){
		random = new Random();
	}

	@Test public void testTokenParentesis(){
		Token t = new Token(Tipo.PARENTESIS, Valor.ABIERTO);
		Assert.assertTrue(t.getTipo() == Tipo.PARENTESIS);
		Assert.assertTrue(t.getValor() == Valor.ABIERTO);

		t = new Token(Tipo.PARENTESIS, Valor.CERRADO);
		Assert.assertTrue(t.getTipo() == Tipo.PARENTESIS);
		Assert.assertTrue(t.getValor() == Valor.CERRADO);
	}

	@Test public void testTokenOperador(){
		Token t = new Token(Tipo.OPERADOR, Valor.SUMA);
		Assert.assertTrue(t.getTipo() == Tipo.OPERADOR);
		Assert.assertTrue(t.getValor() == Valor.SUMA);

		t = new Token(Tipo.OPERADOR, Valor.RESTA);
		Assert.assertTrue(t.getTipo() == Tipo.OPERADOR);
		Assert.assertTrue(t.getValor() == Valor.RESTA);

		t = new Token(Tipo.OPERADOR, Valor.MULTIPLICACION);
		Assert.assertTrue(t.getTipo() == Tipo.OPERADOR);
		Assert.assertTrue(t.getValor() == Valor.MULTIPLICACION);

		t = new Token(Tipo.OPERADOR, Valor.DIVISION);
		Assert.assertTrue(t.getTipo() == Tipo.OPERADOR);
		Assert.assertTrue(t.getValor() == Valor.DIVISION);

		t = new Token(Tipo.OPERADOR, Valor.POTENCIA);
		Assert.assertTrue(t.getTipo() == Tipo.OPERADOR);
		Assert.assertTrue(t.getValor() == Valor.POTENCIA);
	}

	@Test public void testTokenFuncion(){
		Token t = new Token(Tipo.FUNCION, Valor.SIN);
		Assert.assertTrue(t.getTipo() == Tipo.FUNCION);
		Assert.assertTrue(t.getValor() == Valor.SIN);

		t = new Token(Tipo.FUNCION, Valor.COS);
		Assert.assertTrue(t.getTipo() == Tipo.FUNCION);
		Assert.assertTrue(t.getValor() == Valor.COS);

		t = new Token(Tipo.FUNCION, Valor.TAN);
		Assert.assertTrue(t.getTipo() == Tipo.FUNCION);
		Assert.assertTrue(t.getValor() == Valor.TAN);

		t = new Token(Tipo.FUNCION, Valor.COT);
		Assert.assertTrue(t.getTipo() == Tipo.FUNCION);
		Assert.assertTrue(t.getValor() == Valor.COT);

		t = new Token(Tipo.FUNCION, Valor.SEC);
		Assert.assertTrue(t.getTipo() == Tipo.FUNCION);
		Assert.assertTrue(t.getValor() == Valor.SEC);

		t = new Token(Tipo.FUNCION, Valor.CSC);
		Assert.assertTrue(t.getTipo() == Tipo.FUNCION);
		Assert.assertTrue(t.getValor() == Valor.CSC);
	}

	@Test public void testTokenVariable(){
		Token t = new Token(Tipo.VARIABLE, Valor.X);
		Assert.assertTrue(t.getTipo() == Tipo.VARIABLE);
		Assert.assertTrue(t.getValor() == Valor.X);
	}

	@Test public void testTokenNumero(){

		int a = random.nextInt();
		double b = random.nextGaussian();

		Token t = new Token(Tipo.NUMERO, Valor.DIGITOS);
		t.getValor().setDigitos(a);
		Assert.assertTrue(t.getTipo() == Tipo.NUMERO);
		Assert.assertTrue(t.getValor() == Valor.DIGITOS);
		Assert.assertTrue(t.getDigitos() == a);

		t = new Token(Tipo.NUMERO, Valor.DIGITOS);
		t.getValor().setDigitos(b);
		Assert.assertTrue(t.getTipo() == Tipo.NUMERO);
		Assert.assertTrue(t.getValor() == Valor.DIGITOS);
		Assert.assertTrue(t.getDigitos() == b);
	}
}