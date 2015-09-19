package FuncGraph;

public class Controlador{
	
	private AnalizadorSintactico anS;

	public Controlador() throws ExcepcionEntradaInvalida{
		anS = new AnalizadorSintactico(new AnalizadorLexico("").generaListaTokens());
	}

	public void setAnalizadorLexico(String s) throws ExcepcionEntradaInvalida{
		anS = new AnalizadorSintactico(new AnalizadorLexico(s).generaListaTokens());
	}

	public NodoArbol pideArbol() throws ExcepcionEntradaInvalida{
		return anS.generaArbol();
	}

}