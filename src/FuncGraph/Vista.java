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
	private Button pdf;
	private Button jpg;
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

	@Override public void start(Stage stage) {

		try{
			controlador = new Controlador();
		}catch(ExcepcionEntradaInvalida e){
			//Inalcanzable
		}

        Group root = new Group();
        Scene scene = new Scene(root, 1440, 710);
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
		pdf = new Button("Guardar en PDF");
		HBox hpdf = new HBox(10);
		hpdf.setAlignment(Pos.BOTTOM_CENTER);
		hpdf.getChildren().add(pdf);
		//gridMH.add(hpdf, 1, 0);
		jpg = new Button("Guardar en JPG");
		HBox hjpg = new HBox(10);
		hjpg.setAlignment(Pos.BOTTOM_CENTER);
		hjpg.getChildren().add(jpg);
		//gridMH.add(hjpg, 0, 0);
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

		Line ejeX = new Line(0, 315, 1130, 315);
		Line ejeY = new Line(565, 0, 565, 630);
		Line fXA = new Line(1125, 310, 1130, 315);
		Line fXB = new Line(1125, 320, 1130, 315);
		Line fYA = new Line(560, 5, 565, 0);
		Line fYB = new Line(570, 5, 565, 0);
		Text teX = new Text(1120, 335, "x");
		Text teY = new Text(580, 10, "y");

		ejes.getChildren().add(ejeX);
		ejes.getChildren().add(ejeY);
		ejes.getChildren().add(fXA);
		ejes.getChildren().add(fXB);
		ejes.getChildren().add(fYA);
		ejes.getChildren().add(fYB);
		ejes.getChildren().add(teX);
		ejes.getChildren().add(teY);

		bordes.setCenter(ejes);

		LinkedList<Paint> colofunciones = new LinkedList<Paint>();
		zoom = 0;
		despXd = 0;
		despYd = 0;
		
		scene.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override public void handle(ScrollEvent e) {
            	if (e.getDeltaY() > 0) {
            		zoom = zoom + 1;
            	}else{
            		zoom = zoom - 1;
            	}
            	reseteaPant(despXd, despYd);
            	LinkedList<double[]> funciones = controlador.evaluaTodo(zoom, despXd, despYd);
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
						agregaFuncion(controlador.evaluaUltimo(zoom, despXd, despYd), color.getValue());
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
		            	LinkedList<double[]> funciones = controlador.evaluaTodo(zoom, despXd, despYd);
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
		            	LinkedList<double[]> funciones = controlador.evaluaTodo(zoom, despXd, despYd);
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
					agregaFuncion(controlador.evaluaUltimo(zoom, despXd, despYd), color.getValue());
					colofunciones.add(color.getValue());
				}catch(ExcepcionEntradaInvalida e){
					excepciones.setFill(Color.FIREBRICK);
					excepciones.setText(e.getMessage());
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

		Line ejeX = new Line(0, 315 - despYd, 1130, 315 - despYd);
		Line ejeY = new Line(565 + despXd, 0, 565 + despXd, 630);
		Line fXA = new Line(1125, 310 - despYd, 1130, 315 - despYd);
		Line fXB = new Line(1125, 320 - despYd, 1130, 315 - despYd);
		Line fYA = new Line(560 + despXd, 5, 565 + despXd, 0);
		Line fYB = new Line(570 + despXd, 5, 565 + despXd, 0);
		Text teX = new Text(1120, 335 - despYd, "x");
		Text teY = new Text(580 + despXd, 10, "y");

		if (despYd >= -290 && despYd <= 315) {
			ejesN.getChildren().add(ejeX);
			ejesN.getChildren().add(fXA);
			ejesN.getChildren().add(fXB);
			ejesN.getChildren().add(teX);		
		}else{
			ejeX = new Line(0, 0, 1130, 0);
			ejeX.setStroke(Color.WHITE);
			ejesN.getChildren().add(ejeX);
		}
		
		if (despXd >= -565 && despXd <= 565) {
			ejesN.getChildren().add(ejeY);
			ejesN.getChildren().add(fYA);
			ejesN.getChildren().add(fYB);
			ejesN.getChildren().add(teY);	
		}else{
			ejeY = new Line(0, 0, 0, 630);
			ejeY.setStroke(Color.WHITE);
			ejesN.getChildren().add(ejeY);
		}

		this.ejes = ejesN;
		bordes.setCenter(ejes);
    }

    public void agregaFuncion(double[] valoresY, Paint color){
    	for (int i = 0; i < valoresY.length-1 ; i++) {
    		if (valoresY[i] == -315 || valoresY[i] == 315) {
    			
    		}else{
    			Line a = new Line(i, 630-(315+valoresY[i]), i+1, 630-(315+valoresY[i+1]));
    			a.setStroke(color);
    			this.ejes.getChildren().add(a);
    		}
    	}
    	bordes.setCenter(ejes);
    }

}