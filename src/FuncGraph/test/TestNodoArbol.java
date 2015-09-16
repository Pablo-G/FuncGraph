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

	@Test public void testDigitoyX(){
		Assert.assertTrue(valorA.evalua(0) == valorAI);
		Assert.assertTrue(valorB.evalua(valorBI) == valorBI);
	}

	@Test public void testSuma(){
		NodoArbol sum = new NodoArbol(new Token(Tipo.OPERADOR, Valor.SUMA));
		sum.agregaHI(valorA);
		sum.agregaHD(valorB);
		Assert.assertTrue(sum.evalua(valorBI) == (valorAI + valorBI));
	}

	@Test public void testResta(){
		NodoArbol res = new NodoArbol(new Token(Tipo.OPERADOR, Valor.RESTA));
		res.agregaHI(valorA);
		res.agregaHD(valorB);
		Assert.assertTrue(res.evalua(valorBI) == (valorAI - valorBI));
	}

	@Test public void testMultiplicacion(){
		NodoArbol mul = new NodoArbol(new Token(Tipo.OPERADOR, Valor.MULTIPLICACION));
		mul.agregaHI(valorA);
		mul.agregaHD(valorB);
		Assert.assertTrue(mul.evalua(valorBI) == (valorAI * valorBI));
	}

	@Test public void testDivision(){
		this.valorAI = 30;
		this.valorBI = 5;
		this.valorA = new NodoArbol(new Token(Tipo.NUMERO, Valor.DIGITOS, valorAI));
		this.valorB = new NodoArbol(new Token(Tipo.VARIABLE, Valor.X));

		NodoArbol div = new NodoArbol(new Token(Tipo.OPERADOR, Valor.DIVISION));
		div.agregaHI(valorA);
		div.agregaHD(valorB);
		Assert.assertTrue(div.evalua(valorBI) == (valorAI / valorBI));
	}

	@Test public void testPotencia(){
		this.valorAI = random.nextInt(10);
		this.valorBI = random.nextInt(5);
		this.valorA = new NodoArbol(new Token(Tipo.NUMERO, Valor.DIGITOS, valorAI));
		this.valorB = new NodoArbol(new Token(Tipo.VARIABLE, Valor.X));
		
		NodoArbol pot = new NodoArbol(new Token(Tipo.OPERADOR, Valor.POTENCIA));
		pot.agregaHI(valorA);
		pot.agregaHD(valorB);
		Assert.assertTrue(pot.evalua(valorBI) == (Math.pow(valorAI, valorBI)));
	}

	@Test public void testSin(){
		this.valorAI = 0;
		this.valorA = new NodoArbol(new Token(Tipo.NUMERO, Valor.DIGITOS, valorAI));

		NodoArbol ar1 = new NodoArbol(new Token(Tipo.FUNCION, Valor.SIN));
		ar1.agregaHD(valorA);

		Assert.assertTrue(ar1.evalua(0) == 0);
	}

	@Test public void testCos(){
		this.valorAI = 0;
		this.valorA = new NodoArbol(new Token(Tipo.NUMERO, Valor.DIGITOS, valorAI));

		NodoArbol ar1 = new NodoArbol(new Token(Tipo.FUNCION, Valor.COS));
		ar1.agregaHD(valorA);

		Assert.assertTrue(ar1.evalua(0) == 1);
	}

	@Test public void testTan(){
		this.valorAI = 0;
		this.valorA = new NodoArbol(new Token(Tipo.NUMERO, Valor.DIGITOS, valorAI));

		NodoArbol ar1 = new NodoArbol(new Token(Tipo.FUNCION, Valor.TAN));
		ar1.agregaHD(valorA);

		Assert.assertTrue(ar1.evalua(0) == 0);
	}

	@Test public void testSqr(){
		this.valorAI = 25;
		this.valorA = new NodoArbol(new Token(Tipo.NUMERO, Valor.DIGITOS, valorAI));

		NodoArbol ar1 = new NodoArbol(new Token(Tipo.FUNCION, Valor.SQR));
		ar1.agregaHD(valorA);

		Assert.assertTrue(ar1.evalua(0) == 5);
	}
}