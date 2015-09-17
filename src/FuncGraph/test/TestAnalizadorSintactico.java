package FuncGraph.test;

import org.junit.Assert;
import org.junit.Test;
import java.util.LinkedList;
import FuncGraph.Token;
import FuncGraph.Tipo;
import FuncGraph.Valor;
import FuncGraph.NodoArbol;
import FuncGraph.AnalizadorLexico;
import FuncGraph.AnalizadorSintactico;
import FuncGraph.ExcepcionEntradaInvalida;

public class TestAnalizadorSintactico{
	
	@Test public void testASInvalida(){
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("3(5+x)").generaListaTokens());
			an.verificaGramatica();
			Assert.fail();
		}catch(ExcepcionEntradaInvalida a){
		}
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("(5+1)(2)").generaListaTokens());
			an.verificaGramatica();
			Assert.fail();
		}catch(ExcepcionEntradaInvalida a){
		}
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("()+2").generaListaTokens());
			an.verificaGramatica();
			Assert.fail();
		}catch(ExcepcionEntradaInvalida a){
		}
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("(5+sin)").generaListaTokens());
			an.verificaGramatica();
			Assert.fail();
		}catch(ExcepcionEntradaInvalida a){
		}
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("2-cos+").generaListaTokens());
			an.verificaGramatica();
			Assert.fail();
		}catch(ExcepcionEntradaInvalida a){
		}
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("tan-").generaListaTokens());
			an.verificaGramatica();
			Assert.fail();
		}catch(ExcepcionEntradaInvalida a){
		}
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("csc90").generaListaTokens());
			an.verificaGramatica();
			Assert.fail();
		}catch(ExcepcionEntradaInvalida a){
		}
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("costan").generaListaTokens());
			an.verificaGramatica();
			Assert.fail();
		}catch(ExcepcionEntradaInvalida a){
		}
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("cossqr").generaListaTokens());
			an.verificaGramatica();
			Assert.fail();
		}catch(ExcepcionEntradaInvalida a){
		}
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("2*+30").generaListaTokens());
			an.verificaGramatica();
			Assert.fail();
		}catch(ExcepcionEntradaInvalida a){
		}
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("+1").generaListaTokens());
			an.verificaGramatica();
			Assert.fail();
		}catch(ExcepcionEntradaInvalida a){
		}
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("5+").generaListaTokens());
			an.verificaGramatica();
			Assert.fail();
		}catch(ExcepcionEntradaInvalida a){
		}
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("(+)").generaListaTokens());
			an.verificaGramatica();
			Assert.fail();
		}catch(ExcepcionEntradaInvalida a){
		}
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("(^2)").generaListaTokens());
			an.verificaGramatica();
			Assert.fail();
		}catch(ExcepcionEntradaInvalida a){
		}
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("x5").generaListaTokens());
			an.verificaGramatica();
			Assert.fail();
		}catch(ExcepcionEntradaInvalida a){
		}
	}
	
	@Test public void testASValida(){
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("sin(x-20)").generaListaTokens());
			an.verificaGramatica();
		}catch(ExcepcionEntradaInvalida a){
			Assert.fail();
		}
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("20+50").generaListaTokens());
			an.verificaGramatica();
		}catch(ExcepcionEntradaInvalida a){
			Assert.fail();
		}
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("sqr(5^x^2)").generaListaTokens());
			an.verificaGramatica();
		}catch(ExcepcionEntradaInvalida a){
			Assert.fail();
		}
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("30*x/5").generaListaTokens());
			an.verificaGramatica();
		}catch(ExcepcionEntradaInvalida a){
			Assert.fail();
		}
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("tan(1/x)").generaListaTokens());
			an.verificaGramatica();
		}catch(ExcepcionEntradaInvalida a){
			Assert.fail();
		}
	}

	@Test public void testASShuntingYard(){
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("5+30*x-24").generaListaTokens());
			LinkedList<Token> listaPos = new LinkedList<Token>();
			listaPos.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 5));
			listaPos.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 30));
			listaPos.add(new Token(Tipo.VARIABLE, Valor.X));
			listaPos.add(new Token(Tipo.OPERADOR, Valor.MULTIPLICACION));
			listaPos.add(new Token(Tipo.OPERADOR, Valor.SUMA));
			listaPos.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 24));
			listaPos.add(new Token(Tipo.OPERADOR, Valor.RESTA));

			Assert.assertTrue(listaPos.equals(an.shuntingYard()));
		}catch(ExcepcionEntradaInvalida a){
			Assert.fail();
		}
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("3+4*2.141580/(1-x)^2^3").generaListaTokens());
			LinkedList<Token> listaPos = new LinkedList<Token>();
			listaPos.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 3));
			listaPos.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 4));
			listaPos.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 2.141580));
			listaPos.add(new Token(Tipo.OPERADOR, Valor.MULTIPLICACION));
			listaPos.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 1));
			listaPos.add(new Token(Tipo.VARIABLE, Valor.X));
			listaPos.add(new Token(Tipo.OPERADOR, Valor.RESTA));
			listaPos.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 2));
			listaPos.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 3));
			listaPos.add(new Token(Tipo.OPERADOR, Valor.POTENCIA));
			listaPos.add(new Token(Tipo.OPERADOR, Valor.POTENCIA));
			listaPos.add(new Token(Tipo.OPERADOR, Valor.DIVISION));
			listaPos.add(new Token(Tipo.OPERADOR, Valor.SUMA));

			Assert.assertTrue(listaPos.equals(an.shuntingYard()));
		}catch(ExcepcionEntradaInvalida a){
			Assert.fail();
		}
		try{
			AnalizadorSintactico an = new AnalizadorSintactico(new AnalizadorLexico("sin(sqr(x)/3*3.1415)").generaListaTokens());
			LinkedList<Token> listaPos = new LinkedList<Token>();
			listaPos.add(new Token(Tipo.VARIABLE, Valor.X));
			listaPos.add(new Token(Tipo.FUNCION, Valor.SQR));
			listaPos.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 3));
			listaPos.add(new Token(Tipo.OPERADOR, Valor.DIVISION));
			listaPos.add(new Token(Tipo.NUMERO, Valor.DIGITOS, 3.1415));
			listaPos.add(new Token(Tipo.OPERADOR, Valor.MULTIPLICACION));
			listaPos.add(new Token(Tipo.FUNCION, Valor.SIN));

			Assert.assertTrue(listaPos.equals(an.shuntingYard()));
		}catch(ExcepcionEntradaInvalida a){
			Assert.fail();
		}
	}

	@Test public void testASGeneraArbol(){
		AnalizadorSintactico an;

		NodoArbol sum = new NodoArbol(new Token(Tipo.OPERADOR, Valor.SUMA));
		NodoArbol pot = new NodoArbol(new Token(Tipo.OPERADOR, Valor.POTENCIA));
		pot.agregaHI(new NodoArbol(new Token(Tipo.VARIABLE, Valor.X)));
		pot.agregaHD(new NodoArbol(new Token(Tipo.NUMERO, Valor.DIGITOS, 2)));
		sum.agregaHI(new NodoArbol(new Token(Tipo.NUMERO, Valor.DIGITOS, 5)));
		sum.agregaHD(pot);
		try{
			an = new AnalizadorSintactico(new AnalizadorLexico("5+x^2").generaListaTokens());
			Assert.assertTrue(sum.equals(an.generaArbol()));
		}catch(ExcepcionEntradaInvalida a){
			Assert.fail();
		}

		NodoArbol sin = new NodoArbol(new Token(Tipo.FUNCION, Valor.SIN));
		NodoArbol sums = new NodoArbol(new Token(Tipo.OPERADOR, Valor.SUMA));
		sums.agregaHI(new NodoArbol(new Token(Tipo.NUMERO, Valor.DIGITOS, 3)));
		sums.agregaHD(new NodoArbol(new Token(Tipo.VARIABLE, Valor.X)));
		sin.agregaHD(sums);
		try{
			an = new AnalizadorSintactico(new AnalizadorLexico("sin(3+x)").generaListaTokens());
			Assert.assertTrue(sin.equals(an.generaArbol()));
		}catch(ExcepcionEntradaInvalida a){
			Assert.fail();
		}

		NodoArbol par = new NodoArbol(new Token(Tipo.OPERADOR, Valor.RESTA));
		par.agregaHI(new NodoArbol(new Token(Tipo.NUMERO, Valor.DIGITOS, 8)));
		par.agregaHD(new NodoArbol(new Token(Tipo.NUMERO, Valor.DIGITOS, 3)));
		try{
			an = new AnalizadorSintactico(new AnalizadorLexico("(8)-(3)").generaListaTokens());
			Assert.assertTrue(par.equals(an.generaArbol()));
		}catch(ExcepcionEntradaInvalida a){
			Assert.fail();
		}

	}
}