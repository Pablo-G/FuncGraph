/**
 *Clase <code>TestAnalizadorLexico</code>.
 *Clase para pruebas unitarias de la clase {@link AnalizadorLexico}.
 *@author <a href="mailto:pablo.t645@hotmail.com">Pablo G.</a>
 *@version 1.0
 *Copyright 2015 Pablo G.
 */
package FuncGraph.test;

import org.junit.Assert;
import org.junit.Test;
import java.util.Random;
import java.util.LinkedList;
import FuncGraph.Token;
import FuncGraph.Tipo;
import FuncGraph.Valor;
import FuncGraph.AnalizadorLexico;
import FuncGraph.ExcepcionEntradaInvalida;

public class TestAnalizadorLexico{

    /**
     * Prueba unitaria para {@link AnalizadorLexico#generaListaTokens}.
     */
	@Test public void testALVacio(){
		try{
			AnalizadorLexico al = new AnalizadorLexico("");
			LinkedList<Token> lista = new LinkedList<Token>();

			Assert.assertTrue(al.generaListaTokens().equals(lista));
		}catch(ExcepcionEntradaInvalida a){
			Assert.fail();
		}
	}

    /**
     * Prueba unitaria para {@link AnalizadorLexico#generaListaTokens}.
     */
	@Test public void testALInvalida(){
		try{
			AnalizadorLexico al = new AnalizadorLexico("lol");
			al.generaListaTokens();
			Assert.fail();
		}catch(ExcepcionEntradaInvalida a){
		}
		try{
			AnalizadorLexico al = new AnalizadorLexico("sen(x)");
			al.generaListaTokens();
			Assert.fail();
		}catch(ExcepcionEntradaInvalida a){
		}
		try{
			AnalizadorLexico al = new AnalizadorLexico("x^2+((5*4)");
			al.generaListaTokens();
			Assert.fail();
		}catch(ExcepcionEntradaInvalida a){
		}
		try{
			AnalizadorLexico al = new AnalizadorLexico("x^2+3-5a");
			al.generaListaTokens();
			Assert.fail();
		}catch(ExcepcionEntradaInvalida a){
		}
		try{
			AnalizadorLexico al = new AnalizadorLexico("(-6))");
			al.generaListaTokens();
			Assert.fail();
		}catch(ExcepcionEntradaInvalida a){
		}
	}

    /**
     * Prueba unitaria para {@link AnalizadorLexico#generaListaTokens}.
     */
	@Test public void testALValidas(){
		try{
			AnalizadorLexico al1 = new AnalizadorLexico("-1");
			LinkedList<Token> l1 = new LinkedList<Token>();
			l1.add(new Token(Tipo.OPERADOR, Valor.RESTA));
			l1.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 1));

			al1.generaListaTokens().equals(l1);
		}catch(ExcepcionEntradaInvalida a){
			Assert.fail();
		}
		try{
			AnalizadorLexico al2 = new AnalizadorLexico("-2+3*5-3/2.32x");
			LinkedList<Token> l2 = new LinkedList<Token>();
			l2.add(new Token(Tipo.OPERADOR, Valor.RESTA));
			l2.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 2));
			l2.add(new Token(Tipo.OPERADOR, Valor.SUMA));
			l2.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 3));
			l2.add(new Token(Tipo.OPERADOR, Valor.MULTIPLICACION));
			l2.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 5));
			l2.add(new Token(Tipo.OPERADOR, Valor.RESTA));
			l2.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 3));
			l2.add(new Token(Tipo.OPERADOR, Valor.DIVISION));
			l2.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 2.32));
			l2.add(new Token(Tipo.VARIABLE, Valor.X));

			Assert.assertTrue(al2.generaListaTokens().equals(l2));
		}catch(ExcepcionEntradaInvalida a){
			Assert.fail();
		}
		try{
			AnalizadorLexico al3 = new AnalizadorLexico("sin(x)");
			LinkedList<Token> l3 = new LinkedList<Token>();
			l3.add(new Token(Tipo.FUNCION, Valor.SIN));
			l3.add(new Token(Tipo.PARENTESIS, Valor.ABIERTO));
			l3.add(new Token(Tipo.VARIABLE, Valor.X));
			l3.add(new Token(Tipo.PARENTESIS, Valor.CERRADO));

			Assert.assertTrue(al3.generaListaTokens().equals(l3));
		}catch(ExcepcionEntradaInvalida a){
			Assert.fail();
		}
		try{
			AnalizadorLexico al4 = new AnalizadorLexico("x^2+(3/x)");
			LinkedList<Token> l4 = new LinkedList<Token>();
			l4.add(new Token(Tipo.VARIABLE, Valor.X));
			l4.add(new Token(Tipo.OPERADOR, Valor.POTENCIA));
			l4.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 2));
			l4.add(new Token(Tipo.OPERADOR, Valor.SUMA));
			l4.add(new Token(Tipo.PARENTESIS, Valor.ABIERTO));
			l4.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 3));
			l4.add(new Token(Tipo.OPERADOR, Valor.DIVISION));
			l4.add(new Token(Tipo.VARIABLE, Valor.X));
			l4.add(new Token(Tipo.PARENTESIS, Valor.CERRADO));

			Assert.assertTrue(al4.generaListaTokens().equals(l4));
		}catch(ExcepcionEntradaInvalida a){
			Assert.fail();
		}
		try{
			AnalizadorLexico al5 = new AnalizadorLexico("x^2+3*sin(x)");
			LinkedList<Token> l5 = new LinkedList<Token>();
			l5.add(new Token(Tipo.VARIABLE, Valor.X));
			l5.add(new Token(Tipo.OPERADOR, Valor.POTENCIA));
			l5.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 2));
			l5.add(new Token(Tipo.OPERADOR, Valor.SUMA));
			l5.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 3));
			l5.add(new Token(Tipo.OPERADOR, Valor.MULTIPLICACION));
			l5.add(new Token(Tipo.FUNCION, Valor.SIN));
			l5.add(new Token(Tipo.PARENTESIS, Valor.ABIERTO));
			l5.add(new Token(Tipo.VARIABLE, Valor.X));
			l5.add(new Token(Tipo.PARENTESIS, Valor.CERRADO));

			Assert.assertTrue(al5.generaListaTokens().equals(l5));
		}catch(ExcepcionEntradaInvalida a){
			Assert.fail();
		}
		try{
			AnalizadorLexico al6 = new AnalizadorLexico("sin(cos(tan(csc(sec(cot(x))))))*7543");
			LinkedList<Token> l6 = new LinkedList<Token>();
			l6.add(new Token(Tipo.FUNCION, Valor.SIN));
			l6.add(new Token(Tipo.PARENTESIS, Valor.ABIERTO));
			l6.add(new Token(Tipo.FUNCION, Valor.COS));
			l6.add(new Token(Tipo.PARENTESIS, Valor.ABIERTO));
			l6.add(new Token(Tipo.FUNCION, Valor.TAN));
			l6.add(new Token(Tipo.PARENTESIS, Valor.ABIERTO));
			l6.add(new Token(Tipo.FUNCION, Valor.CSC));
			l6.add(new Token(Tipo.PARENTESIS, Valor.ABIERTO));
			l6.add(new Token(Tipo.FUNCION, Valor.SEC));
			l6.add(new Token(Tipo.PARENTESIS, Valor.ABIERTO));
			l6.add(new Token(Tipo.FUNCION, Valor.COT));
			l6.add(new Token(Tipo.PARENTESIS, Valor.ABIERTO));
			l6.add(new Token(Tipo.VARIABLE, Valor.X));
			l6.add(new Token(Tipo.PARENTESIS, Valor.CERRADO));
			l6.add(new Token(Tipo.PARENTESIS, Valor.CERRADO));
			l6.add(new Token(Tipo.PARENTESIS, Valor.CERRADO));
			l6.add(new Token(Tipo.PARENTESIS, Valor.CERRADO));
			l6.add(new Token(Tipo.PARENTESIS, Valor.CERRADO));
			l6.add(new Token(Tipo.PARENTESIS, Valor.CERRADO));
			l6.add(new Token(Tipo.OPERADOR, Valor.MULTIPLICACION));
			l6.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 7543));

			Assert.assertTrue(al6.generaListaTokens().equals(l6));
		}catch(ExcepcionEntradaInvalida a){
			Assert.fail();
		}
	}

}