package FuncGraph;

import java.util.LinkedList;
import java.util.ListIterator;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.shape.Circle;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Paint;
import javafx.scene.input.ScrollEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

public class Vista extends Application{

	private Label f;
	private Label ldespX;
	private Label ldespY;
	private TextField entradaFuncion;
	private TextField despX;
	private TextField despY;
	private Button generaGraf;
	private ColorPicker color;
	private Button svg;
	private Button limpia;
	private Button soporte;
	private BorderPane bordes;
	private Text excepciones;
	private Group ejes;
	private int anchoCentro;
	private int altoCentro;
	private Controlador controlador;
	private double despXd;
	private double despYd;
	private double zoom;
	private double width;
	private double height;
	private double iniMX;
	private double iniMY;

	@Override public void start(Stage stage) {

		try{
			controlador = new Controlador();
		}catch(ExcepcionEntradaInvalida e){
			//Inalcanzable
		}

        Group root = new Group();
        Scene scene = new Scene(root, 1440, 710);

        /*Inicializando valores*/
        LinkedList<Paint> colofunciones = new LinkedList<Paint>();
		zoom = 0;
		despXd = 0;
		despYd = 0;
        width = 1440;
        height = 710;
        iniMX = 0;
        iniMY = 0;
		bordes = new BorderPane();

		/* - MENU ENTRADA - */
		GridPane gridME = new GridPane();
		gridME.setAlignment(Pos.TOP_CENTER);
		/*TEMPORAL*/
		Label temp = new Label("Aqui va la función.");
		gridME.add(temp, 0, 0);
		f = new Label("f(x)=");
		gridME.add(f, 0, 1);
		entradaFuncion = new TextField();
		gridME.add(entradaFuncion, 1, 1);
		generaGraf = new Button("Grafica!");
		HBox hgeneraGraf = new HBox(10);
		hgeneraGraf.setAlignment(Pos.BOTTOM_CENTER);
		hgeneraGraf.getChildren().add(generaGraf);
		gridME.add(hgeneraGraf, 0, 2);
		color = new ColorPicker();
        color.setValue(Color.CORAL);
		gridME.add(color, 1, 4);
		excepciones = new Text();
	    gridME.add(excepciones, 1, 5);

		bordes.setLeft(gridME);

		/* - MENU HERRAMIENTAS - */
		GridPane gridMH = new GridPane();
		gridMH.setAlignment(Pos.BOTTOM_CENTER);

		svg = new Button("Guardar en SVG");
		HBox hsvg = new HBox(10);
		hsvg.setAlignment(Pos.BOTTOM_CENTER);
		hsvg.getChildren().add(svg);
		gridMH.add(hsvg, 2, 0);
		limpia = new Button("Reset");
		HBox hlimpia = new HBox(10);
		hlimpia.setAlignment(Pos.BOTTOM_CENTER);
		hlimpia.getChildren().add(limpia);
		gridMH.add(hlimpia, 3, 0);
		soporte = new Button("Ayuda");
		HBox hsoporte = new HBox(10);
		hsoporte.setAlignment(Pos.BOTTOM_CENTER);
		hsoporte.getChildren().add(soporte);
		gridMH.add(hsoporte, 4, 0);
		ldespX = new Label("Desplazamiento en X:");
		gridMH.add(ldespX, 1, 1);
		despX = new TextField();
		gridMH.add(despX, 2, 1);
		ldespY = new Label("Desplazamiento en Y:");
		gridMH.add(ldespY, 3, 1);
		despY = new TextField();
		gridMH.add(despY, 4, 1);

		bordes.setBottom(gridMH);

		/* - Vista Graficas - */
		ejes = new Group();

		reseteaPant(despXd, despYd);
		
		scene.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override public void handle(ScrollEvent e) {
            	if (e.getDeltaY() > 0) {
            		zoom = zoom + 1;
            	}else{
            		zoom = zoom - 1;
            	}
            	reseteaPant(despXd, despYd);
            	LinkedList<double[]> funciones = controlador.evaluaTodo(zoom, despXd, despYd, (int)width);
            	ListIterator<Paint> itcol = colofunciones.listIterator(0);

            	for (double[] a: funciones) {
            		agregaFuncion(a, itcol.next());
            	}
            }
        });

		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override public void handle(MouseEvent mouseEvent) {
		    	scene.setCursor(Cursor.MOVE);
		    	iniMX = mouseEvent.getSceneX();
		  		iniMY = mouseEvent.getSceneY();
		    }
		});

		scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
		    @Override public void handle(MouseEvent mouseEvent) {
		    	scene.setCursor(Cursor.DEFAULT);
		    	despXd = despXd - (iniMX - mouseEvent.getSceneX());
		    	despYd = despYd + (iniMY - mouseEvent.getSceneY());

				reseteaPant(despXd, despYd);
		        LinkedList<double[]> funciones = controlador.evaluaTodo(zoom, despXd, despYd, (int)width);
		        ListIterator<Paint> itcol = colofunciones.listIterator(0);
		        for (double[] a: funciones) {
		          	agregaFuncion(a, itcol.next());
		        }
		    }
		});

        scene.widthProperty().addListener(new ChangeListener<Number>(){
        	@Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
        		width = stage.getWidth();
        		height = stage.getHeight();
        		reseteaPant(despXd, despYd);
            	LinkedList<double[]> funciones = controlador.evaluaTodo(zoom, despXd, despYd, (int)width);
            	ListIterator<Paint> itcol = colofunciones.listIterator(0);

            	for (double[] a: funciones) {
            		agregaFuncion(a, itcol.next());
            	}
        	}
        });

        scene.heightProperty().addListener(new ChangeListener<Number>(){
        	@Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
        		width = stage.getWidth();
        		height = stage.getHeight();
        		reseteaPant(despXd, despYd);
            	LinkedList<double[]> funciones = controlador.evaluaTodo(zoom, despXd, despYd, (int)width);
            	ListIterator<Paint> itcol = colofunciones.listIterator(0);

            	for (double[] a: funciones) {
            		agregaFuncion(a, itcol.next());
            	}
        	}
        });

		entradaFuncion.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override public void handle(final KeyEvent kE){
				if (kE.getCode() == KeyCode.ENTER) {
					try{
						excepciones.setText("");
						controlador.setAnalizadorLexico(entradaFuncion.getText());
						controlador.generaArbol();
						agregaFuncion(controlador.evaluaUltimo(zoom, despXd, despYd, (int)width), color.getValue());
						colofunciones.add(color.getValue());
					}catch(ExcepcionEntradaInvalida e){
						excepciones.setFill(Color.FIREBRICK);
						excepciones.setText(e.getMessage());
					}
				}
			}
		});

		despX.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override public void handle(final KeyEvent kE){
				if (kE.getCode() == KeyCode.ENTER) {
					try{
						excepciones.setText("");
						despXd = Double.parseDouble(despX.getText());
						reseteaPant(despXd, despYd);
		            	LinkedList<double[]> funciones = controlador.evaluaTodo(zoom, despXd, despYd, (int)width);
		            	ListIterator<Paint> itcol = colofunciones.listIterator(0);

		            	for (double[] a: funciones) {
		            		agregaFuncion(a, itcol.next());
		            	}
					}catch(Exception e){
						excepciones.setFill(Color.FIREBRICK);
						excepciones.setText("Debes poner un número!");
					}
				}
			}
		});

		despY.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override public void handle(final KeyEvent kE){
				if (kE.getCode() == KeyCode.ENTER) {
					try{
						excepciones.setText("");
						despYd = Double.parseDouble(despY.getText());
						reseteaPant(despXd, despYd);
		            	LinkedList<double[]> funciones = controlador.evaluaTodo(zoom, despXd, despYd, (int)width);
		            	ListIterator<Paint> itcol = colofunciones.listIterator(0);

		            	for (double[] a: funciones) {
		            		agregaFuncion(a, itcol.next());
		            	}
					}catch(Exception e){
						excepciones.setFill(Color.FIREBRICK);
						excepciones.setText("Debes poner un número!");
					}
				}
			}
		});

		generaGraf.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent ev){
				try{
					excepciones.setText("");
					controlador.setAnalizadorLexico(entradaFuncion.getText());
					controlador.generaArbol();
					agregaFuncion(controlador.evaluaUltimo(zoom, despXd, despYd, (int)width), color.getValue());
					colofunciones.add(color.getValue());
				}catch(ExcepcionEntradaInvalida e){
					excepciones.setFill(Color.FIREBRICK);
					excepciones.setText(e.getMessage());
				}
			}
		});

		svg.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent ev){
				ImpresoraSVG iSVG = new ImpresoraSVG(controlador);
				FileChooser fc = new FileChooser();

	          	fc.setInitialFileName("NuevaGrafica.svg");
	           	fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("SVG", "*.svg"));
				fc.setTitle("Guardar Como...");
			 	File arch = fc.showSaveDialog(stage);
			    if (arch != null) {
			    	try{
			        	iSVG.imprimeSVG(arch.toString(), zoom, despXd, despYd);
			    	}catch(Exception e){
			    		//Inalcanzable
			    	}
			    }
			}
		});

		limpia.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent ev){
				reseteaPant(despXd, despYd);
				controlador.resetea();
				colofunciones.clear();
				zoom = 0;
				despXd = 0;
				despYd = 0;
			}
		});

		soporte.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent ev){
			   try {         
			     java.awt.Desktop.getDesktop().browse(java.net.URI.create("mailto:pablo.t645@hotmail.com?Subject=Problemas%20con%20FuncGraph%20:("));
			   }
			   catch (java.io.IOException e) {
			       System.out.println(e.getMessage());
			   }
			}
		});


		root.getChildren().add(bordes);
        stage.setTitle("FuncGraph");
        stage.setScene(scene);
        stage.show();

    }

    public void reseteaPant(double despXd, double despYd){

    	Group ejesN = new Group();


		Line ejeX = new Line(0, ((height-120)/2)-despYd, (width-330), ((height-120)/2)-despYd);
		Line ejeY = new Line(((width-330)/2)+despXd, 0, ((width-330)/2)+despXd, (height-120));
		Line fXA = new Line((width-330)-5, (((height-120)/2)-5)-despYd, (width-330), ((height-120)/2)-despYd);
		Line fXB = new Line((width-330)-5, (((height-120)/2)+5)-despYd, (width-330), ((height-120)/2)-despYd);
		Line fYA = new Line((((width-330)/2)-5)+despXd, 5, ((width-330)/2)+despXd, 0);
		Line fYB = new Line((((width-330)/2)+5)+despXd, 5, ((width-330)/2)+despXd, 0);
		Text teX = new Text(width-345, (((height-120)/2)+15)-despYd, "x");
		Text teY = new Text((((width-330)/2)+15)+despXd, 10, "y");

		if (despYd >= -(((height-120)/2)-25) && despYd <= (height-120)/2) {
			ejesN.getChildren().add(ejeX);
			ejesN.getChildren().add(fXA);
			ejesN.getChildren().add(fXB);
			ejesN.getChildren().add(teX);		
		}else{
			ejeX = new Line(0, 0, width-330, 0);
			ejeX.setStroke(Color.WHITE);
			ejesN.getChildren().add(ejeX);
		}
		
		if (despXd >= -(width-330)/2 && despXd <= (width-330)/2) {
			ejesN.getChildren().add(ejeY);
			ejesN.getChildren().add(fYA);
			ejesN.getChildren().add(fYB);
			ejesN.getChildren().add(teY);	
		}else{
			ejeY = new Line(0, 0, 0, height-120);
			ejeY.setStroke(Color.WHITE);
			ejesN.getChildren().add(ejeY);
		}

		this.ejes = ejesN;
		bordes.setCenter(ejes);
    }

    public void agregaFuncion(double[] valoresY, Paint color){
    	for (int i = 0; i < valoresY.length-1 ; i++) {
    		if (valoresY[i] >= -(height-120)/2 && valoresY[i] <= (height-120)/2 && valoresY[i+1] >= -(height-120)/2 && valoresY[i+1] <= (height-120)/2) {
    			Line a = new Line(i, (height-120)-((height-120)/2+valoresY[i]), i+1, (height-120)-((height-120)/2+valoresY[i+1]));
    			a.setStroke(color);
    			this.ejes.getChildren().add(a);		
    		}
    	}
    	bordes.setCenter(ejes);
    }

}