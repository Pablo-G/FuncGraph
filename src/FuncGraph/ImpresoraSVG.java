/**
 *Clase <code>ImpresoraSVG</code>.
 *Impresora de SVG para la aplicación FuncGraph.
 *Genera un y guarda en el equipo un archivo SVG con una representación de una función.
 *Esta se crea a partir de valores dados.
 *En el patrón de diseño MVC esta clase es otra vista.
 *@author <a href="mailto:pablo.t645@hotmail.com">Pablo G.</a>
 *@version 1.0
 *Copyright 2015 Pablo G.
 */
package FuncGraph;

import java.io.*;
import java.util.Random;
import java.util.LinkedList;

public class ImpresoraSVG{
	
	private Controlador controlador;
	private Random random;

    /**
     *<code>ImpresoraSVG</code> Constructor.
     */
	public ImpresoraSVG(){
		try{
			controlador = new Controlador();
			random = new Random();
		}catch(ExcepcionEntradaInvalida e){
			//Inalcanzable
		}
	}

    /**
     *<code>generaListaTokens</code> Constructor.
     *@param controlador tipo <code>Controlador</code>: Controlador.
     */
	public ImpresoraSVG(Controlador controlador){
		this.controlador = controlador;
		this.random = new Random();
	}

    /**
     *<code>imprimeSVG</code> Método que crea el archivo SVG con la representación grafica de la función.
     *@param s tipo <code>String</code>: Ruta del archivo a crear.
     *@param zoom tipo <code>double</code>: Zoom que tiene la función.
     *@param despX tipo <code>double</code>: Desplazamiento en el eje X de la función.
     *@param despY tipo <code>double</code>: Desplazamiento en el eje Y de la función.
     *Manda una excepción en caso de no poder crear el archivo.
     */
	public void imprimeSVG(String s, double zoom, double despXd, double despYd) throws Exception{
		File temp = new File(s);
		try{
			temp.createNewFile();
        	PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(temp)),true);
			PrintStream stdout = System.out;
	        System.setOut(ps);

			System.out.println("<?xml version=\"1.0\" standalone=\"yes\"?>");
			System.out.println("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"100%\" height=\"100%\">");
			System.out.println("<g xmlns=\"http://www.w3.org/2000/svg\">");
			if (despYd >= -540 && despYd <= 540) {
			System.out.println("<line x1=\"0\" y1=\"" + (540-despYd) + "\" x2=\"1920\" y2=\"" + (540-despYd) + "\" style=\"stroke:black; stroke-width:2\" />");
			System.out.println("<line x1=\"1915\" y1=\"" + (535-despYd) + "\" x2=\"1920\" y2=\"" + (540-despYd) + "\" style=\"stroke:black; stroke-width:2\" />");
			System.out.println("<line x1=\"1915\" y1=\"" + (545-despYd) + "\" x2=\"1920\" y2=\"" + (540-despYd) + "\" style=\"stroke:black; stroke-width:2\" />");
			System.out.println("<text x=\"1905\" y=\"" + (555-despYd) + "\" fill=\"black\">x</text>");
			}		
			if (despXd >= -960 && despXd <= 960) {
			System.out.println("<line x1=\"" + (960+despXd) + "\" y1=\"0\" x2=\"" + (960+despXd) + "\" y2=\"1080\" style=\"stroke:black; stroke-width:2\" />");
			System.out.println("<line x1=\"" + (955+despXd) + "\" y1=\"5\" x2=\"" + (960+despXd) + "\" y2=\"0\" style=\"stroke:black; stroke-width:2\" />");
			System.out.println("<line x1=\"" + (965+despXd) + "\" y1=\"5\" x2=\"" + (960+despXd) + "\" y2=\"0\" style=\"stroke:black; stroke-width:2\" />");
			System.out.println("<text x=\"" + (975+despXd) + "\" y=\"20\" fill=\"black\">y</text>");
			}
			
            LinkedList<double[]> funciones = controlador.evaluaTodo(zoom, despXd, despYd, 1920, true);
            for (double[] valoresY: funciones) {
            	int r = random.nextInt(255);
            	int g = random.nextInt(255);
            	int b = random.nextInt(255);
		        for (int i = 0; i < valoresY.length-1 ; i++) {
		    		if (valoresY[i] >= -1080 && valoresY[i] <= 1080 && valoresY[i+1] >= -1080 && valoresY[i+1] <= 1080) {
		    			System.out.println("<line x1=\"" + i + "\" y1=\"" + (1080-(540+valoresY[i])) + "\" x2=\"" + (i+1) + "\" y2=\"" + (1080-(540+valoresY[i+1])) + "\" style=\"stroke:rgb(" + r + "," + g + "," + b + "); stroke-width:2\" />");
		    		}
		    	}
            }

			System.out.println("</g>");
			System.out.println("</svg>");

			ps.close();
			System.setOut(stdout);
        }catch(Exception e){
        	throw new Exception("E17--| Error al guardar.");
        }
	}

}