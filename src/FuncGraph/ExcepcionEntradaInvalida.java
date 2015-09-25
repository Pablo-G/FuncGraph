/**
 *Clase <code>ExcepcionEntradaInvalida</code>.
 *Excepción para la aplicación FuncGraph.
 *@author <a href="mailto:pablo.t645@hotmail.com">Pablo G.</a>
 *@version 1.0
 *Copyright 2015 Pablo G.
 */
package FuncGraph;

public class ExcepcionEntradaInvalida extends Exception{
	
	/**
     *<code>ExcepcionEntradaInvalida</code> Constructor.
     */
	public ExcepcionEntradaInvalida(){
		super();
	}

    /**
     *<code>ExcepcionEntradaInvalida</code> Constructor.
     *@param message tipo <code>String</code>: Mensaje que verá el usuario cuando se lanze la excepción.
     */
	public ExcepcionEntradaInvalida(String message){
		super(message);
	}

    /**
     *<code>ExcepcionEntradaInvalida</code> Constructor.
     *@param message tipo <code>String</code>: Mensaje que verá el usuario cuando se lanze la excepción.
     *@param cause tipo <code>Throwable</code>: Causa.
     */
	public ExcepcionEntradaInvalida(String message, Throwable cause){
		super(message, cause);
	}

    /**
     *<code>ExcepcionEntradaInvalida</code> Constructor.
     *@param cause tipo <code>Throwable</code>: Causa.
     */
	public ExcepcionEntradaInvalida(Throwable cause){
		super(cause);
	}

}