package FuncGraph.test;

import org.junit.Assert;
import org.junit.Test;
import java.util.Random;
import FuncGraph.NodoArbol;
import FuncGraph.Token;
import FuncGraph.Tipo;
import FuncGraph.Valor;

public class TestNodoArbol{
	
	private Random random;
	private double valorAI;
	private double valorBI;
	private NodoArbol valorA;
	private NodoArbol valorB;

	public TestNodoArbol(){
		this.random = new Random();
		this.valorAI = random.nextInt(1000);
		this.valorBI = random.nextInt(1000);
		this.valorA = new NodoArbol(new Token(Tipo.NUMERO, Valor.DIGITOS, valorAI));
		this.valorB = new NodoArbol(new Token(Tipo.VARIABLE, Valor.X));
	}

	@Test public void testGetHI(){
		valorA.agregaHI(valorB);
		Assert.assertTrue(valorA.getHI() == valorB);
	}

	@Test public void testGetHD(){
		valorA.agregaHD(valorB);
		Assert.assertTrue(valorA.getHD() == valorB);
	}

	@Test public void testAgergaHI(){
		valorA.agregaHI(valorB);
		Assert.assertTrue(valorA.getHI() == valorB);
	}

	@Test public void testAgergaHD(){
		valorA.agregaHD(valorB);
		Assert.assertTrue(valorA.getHD() == valorB);
	}

	@Test public void testGetElemento(){
		valorA.getElemento().equals(new Token(Tipo.NUMERO, Valor.DIGITOS, valorAI));
		valorB.getElemento().equals(new Token(Tipo.VARIABLE, Valor.X));
	}
	@Test public void testEvaluaDigitoyX(){
		Assert.assertTrue(valorA.evalua(0) == valorAI);
		Assert.assertTrue(valorB.evalua(valorBI) == valorBI);
	}

	@Test public void testEvaluaSuma(){
		NodoArbol sum = new NodoArbol(new Token(Tipo.OPERADOR, Valor.SUMA));
		sum.agregaHI(valorA);
		sum.agregaHD(valorB);
		Assert.assertTrue(sum.evalua(valorBI) == (valorAI + valorBI));
	}

	@Test public void testEvaluaResta(){
		NodoArbol res = new NodoArbol(new Token(Tipo.OPERADOR, Valor.RESTA));
		res.agregaHI(valorA);
		res.agregaHD(valorB);
		Assert.assertTrue(res.evalua(valorBI) == (valorAI - valorBI));
	}

	@Test public void testEvaluaMultiplicacion(){
		NodoArbol mul = new NodoArbol(new Token(Tipo.OPERADOR, Valor.MULTIPLICACION));
		mul.agregaHI(valorA);
		mul.agregaHD(valorB);
		Assert.assertTrue(mul.evalua(valorBI) == (valorAI * valorBI));
	}

	@Test public void testEvaluaDivision(){
		this.valorAI = 30;
		this.valorBI = 5;
		this.valorA = new NodoArbol(new Token(Tipo.NUMERO, Valor.DIGITOS, valorAI));
		this.valorB = new NodoArbol(new Token(Tipo.VARIABLE, Valor.X));

		NodoArbol div = new NodoArbol(new Token(Tipo.OPERADOR, Valor.DIVISION));
		div.agregaHI(valorA);
		div.agregaHD(valorB);
		Assert.assertTrue(div.evalua(valorBI) == (valorAI / valorBI));
	}

	@Test public void testEvaluaPotencia(){
		this.valorAI = random.nextInt(10);
		this.valorBI = random.nextInt(5);
		this.valorA = new NodoArbol(new Token(Tipo.NUMERO, Valor.DIGITOS, valorAI));
		this.valorB = new NodoArbol(new Token(Tipo.VARIABLE, Valor.X));
		
		NodoArbol pot = new NodoArbol(new Token(Tipo.OPERADOR, Valor.POTENCIA));
		pot.agregaHI(valorA);
		pot.agregaHD(valorB);
		Assert.assertTrue(pot.evalua(valorBI) == (Math.pow(valorAI, valorBI)));
	}

	@Test public void testEvaluaSin(){
		this.valorAI = 0;
		this.valorA = new NodoArbol(new Token(Tipo.NUMERO, Valor.DIGITOS, valorAI));

		NodoArbol ar1 = new NodoArbol(new Token(Tipo.FUNCION, Valor.SIN));
		ar1.agregaHD(valorA);

		Assert.assertTrue(ar1.evalua(0) == 0);
	}

	@Test public void testEvaluaCos(){
		this.valorAI = 0;
		this.valorA = new NodoArbol(new Token(Tipo.NUMERO, Valor.DIGITOS, valorAI));

		NodoArbol ar1 = new NodoArbol(new Token(Tipo.FUNCION, Valor.COS));
		ar1.agregaHD(valorA);

		Assert.assertTrue(ar1.evalua(0) == 1);
	}

	@Test public void testEvaluaTan(){
		this.valorAI = 0;
		this.valorA = new NodoArbol(new Token(Tipo.NUMERO, Valor.DIGITOS, valorAI));

		NodoArbol ar1 = new NodoArbol(new Token(Tipo.FUNCION, Valor.TAN));
		ar1.agregaHD(valorA);

		Assert.assertTrue(ar1.evalua(0) == 0);
	}

	@Test public void testEvaluaSqr(){
		this.valorAI = 25;
		this.valorA = new NodoArbol(new Token(Tipo.NUMERO, Valor.DIGITOS, valorAI));

		NodoArbol ar1 = new NodoArbol(new Token(Tipo.FUNCION, Valor.SQR));
		ar1.agregaHD(valorA);

		Assert.assertTrue(ar1.evalua(0) == 5);
	}

	@Test public void testEquals(){
		NodoArbol a = new NodoArbol(new Token(Tipo.FUNCION, Valor.SIN));
		NodoArbol b = new NodoArbol(new Token(Tipo.FUNCION, Valor.SIN));
		NodoArbol c = new NodoArbol(new Token(Tipo.OPERADOR, Valor.MULTIPLICACION));
		NodoArbol d = new NodoArbol(new Token(Tipo.OPERADOR, Valor.MULTIPLICACION));
		NodoArbol e = new NodoArbol(new Token(Tipo.VARIABLE, Valor.X));
		NodoArbol f = new NodoArbol(new Token(Tipo.VARIABLE, Valor.X));

		Assert.assertTrue(a.equals(b));

		c.agregaHD(e);
		d.agregaHD(f);

		Assert.assertTrue(c.equals(d));

		c.agregaHI(a);
		d.agregaHI(b);

		Assert.assertTrue(c.equals(d));

		NodoArbol g = new NodoArbol(new Token(Tipo.NUMERO, Valor.DIGITOS));
		NodoArbol h = new NodoArbol(new Token(Tipo.NUMERO, Valor.DIGITOS));
		e.agregaHI(g);
		f.agregaHD(h);

		Assert.assertFalse(c.equals(d));
		Assert.assertFalse(c.equals(f));
		Assert.assertFalse(d.equals(c));
		Assert.assertFalse(d.equals(e));
		Assert.assertFalse(e.equals(f));

	}
}