package FuncGraph;

public class ExcepcionEntradaInvalida extends Exception{
	public ExcepcionEntradaInvalida(){
		super();
	}

	public ExcepcionEntradaInvalida(String message){
		super(message);
	}

	public ExcepcionEntradaInvalida(String message, Throwable cause){
		super(message, cause);
	}
	
	public ExcepcionEntradaInvalida(Throwable cause){
		super(cause);
	}
}