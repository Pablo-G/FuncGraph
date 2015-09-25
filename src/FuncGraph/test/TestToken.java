/**
 *Clase <code>TestToken</code>.
 *Clase para pruebas unitarias de la clase {@link Token}.
 *@author <a href="mailto:pablo.t645@hotmail.com">Pablo G.</a>
 *@version 1.0
 *Copyright 2015 Pablo G.
 */
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
		this.random = new Random();
	}

    /**
     * Prueba unitaria para {@link Token#getTipo} y {@link Token#getValor}.
     */	
	@Test public void testTokenParentesis(){
		Token t = new Token(Tipo.PARENTESIS, Valor.ABIERTO);
		Assert.assertTrue(t.getTipo() == Tipo.PARENTESIS);
		Assert.assertTrue(t.getValor() == Valor.ABIERTO);

		t = new Token(Tipo.PARENTESIS, Valor.CERRADO);
		Assert.assertTrue(t.getTipo() == Tipo.PARENTESIS);
		Assert.assertTrue(t.getValor() == Valor.CERRADO);
	}

    /**
     * Prueba unitaria para {@link Token#getTipo} y {@link Token#getValor}.
     */	
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

    /**
     * Prueba unitaria para {@link Token#getTipo} y {@link Token#getValor}.
     */	
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

    /**
     * Prueba unitaria para {@link Token#getTipo} y {@link Token#getValor}.
     */	
	@Test public void testTokenVariable(){
		Token t = new Token(Tipo.VARIABLE, Valor.X);
		Assert.assertTrue(t.getTipo() == Tipo.VARIABLE);
		Assert.assertTrue(t.getValor() == Valor.X);
	}

    /**
     * Prueba unitaria para {@link Token#getTipo}, {@link Token#getValor} y {@link Token#getDigitos}.
     */	
	@Test public void testTokenNumero(){

		int a = random.nextInt();
		double b = random.nextGaussian();

		Token t = new Token(Tipo.NUMERO, Valor.DIGITOS, a);
		Assert.assertTrue(t.getTipo() == Tipo.NUMERO);
		Assert.assertTrue(t.getValor() == Valor.DIGITOS);
		Assert.assertTrue(t.getDigitos() == a);

		t = new Token(Tipo.NUMERO, Valor.DIGITOS, b);
		Assert.assertTrue(t.getTipo() == Tipo.NUMERO);
		Assert.assertTrue(t.getValor() == Valor.DIGITOS);
		Assert.assertTrue(t.getDigitos() == b);
	}

    /**
     * Prueba unitaria para {@link Token#equals}.
     */	
	@Test public void testTokenEquals(){

		int na = random.nextInt();
		double nb = random.nextGaussian();

		Token a = new Token(Tipo.OPERADOR, Valor.SUMA);
		Token b = new Token(Tipo.OPERADOR, Valor.SUMA);

		Token c = new Token(Tipo.OPERADOR, Valor.RESTA);
		Token d = new Token(Tipo.OPERADOR, Valor.RESTA);

		Token e = new Token(Tipo.NUMERO, Valor.DIGITOS, na);
		Token f = new Token(Tipo.NUMERO, Valor.DIGITOS, na);

		Token g = new Token(Tipo.OPERADOR, Valor.DIGITOS, nb);
		Token h = new Token(Tipo.OPERADOR, Valor.DIGITOS, nb);

		Assert.assertTrue(a.equals(b));
		Assert.assertTrue(c.equals(d));
		Assert.assertTrue(e.equals(f));
		Assert.assertTrue(g.equals(h));

		Assert.assertFalse(a.equals(c));
		Assert.assertFalse(e.equals(h));

		Assert.assertFalse(a.equals(e));
	}

    /**
     * Prueba unitaria para {@link Token#setValor}.
     */	
	@Test public void testSetValor(){
		Token a = new Token(Tipo.OPERADOR, Valor.SUMA);
		Token b = new Token(Tipo.OPERADOR, Valor.RESTA);

		a.setValor(Valor.RESTA);
		b.setValor(Valor.MULTIPLICACION);

		Assert.assertTrue(a.getValor() == Valor.RESTA);
		Assert.assertTrue(b.getValor() == Valor.MULTIPLICACION);
	}

    /**
     * Prueba unitaria para {@link Token#toString}.
     */	
	@Test public void testToString(){
		Token a = new Token(Tipo.OPERADOR, Valor.SUMA);
		Token b = new Token(Tipo.NUMERO, Valor.DIGITOS, 5.156);

		Assert.assertTrue(a.toString().equals("[OPERADOR , SUMA]"));
		Assert.assertTrue(b.toString().equals("[NUMERO , DIGITOS-|5.156|-]"));
	}

    /**
     * Prueba unitaria para {@link Token#esMayorPres}.
     */	
	@Test public void testEsMayorPres(){
		Token a = new Token(Tipo.OPERADOR, Valor.SUMA);
		Token b = new Token(Tipo.OPERADOR, Valor.RESTA);
		Token c = new Token(Tipo.OPERADOR, Valor.MULTIPLICACION);
		Token d = new Token(Tipo.OPERADOR, Valor.POTENCIA);

		Assert.assertTrue(a.esMayorPres(b));
		Assert.assertTrue(a.esMayorPres(c));
		Assert.assertTrue(a.esMayorPres(d));
		Assert.assertFalse(c.esMayorPres(b));
		Assert.assertTrue(c.esMayorPres(d));
		Assert.assertFalse(d.esMayorPres(a));
		Assert.assertFalse(d.esMayorPres(c));
	}

}