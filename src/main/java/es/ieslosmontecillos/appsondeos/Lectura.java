package es.ieslosmontecillos.appsondeos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class Lectura extends Tab {

    private GridPane root = new GridPane();
    private Label preguntaFL = new Label("¿Con qué frecuencia lees?");
    private ChoiceBox frecuenciaLectura = new ChoiceBox();
    private Label preguntaTL = new Label("¿Qué tipo de lectura te gusta más?");
    private ChoiceBox tipoLectura = new ChoiceBox();
    private Label preguntaTF = new Label("¿Qué obra que hayas leído te ha gustado más?");
    private TextField tituloFavorito = new TextField();
    private Label preguntaNO = new Label("¿Cuántas obras literarias te has leído?");
    private TextField numObras = new TextField();
    private Label preguntaIL = new Label("¿Cuál fue el motivo principal de tu interés por la lectura?");
    private ChoiceBox interesLectura = new ChoiceBox();
    private Button cancelaEncuesta = new Button("Cancelar");
    private Button enviarEncuesta = new Button("Enviar");


    public Lectura(){
        makeGUI();
    }

    private void makeGUI(){
        ObservableList<String> fLectura = FXCollections.observableArrayList("Ninguna vez", "Todos los días", "Más de 4 veces a la semana", "2 o 3 veces a la semana", "1 vez a la semana");
        ObservableList<String> tLectura = FXCollections.observableArrayList("Otro", "Cómics", "Novelas", "Poemas", "Cuentos", "Enciclopedias", "Diccionarios", "Biografías", "Libros de divulgación científica", "Libros de fotografía");
        ObservableList<String> iLectura = FXCollections.observableArrayList("No estoy interesado", "No me acuerdo","Recomendación de amigos/familiares", "Interés propio");

        frecuenciaLectura.setItems(fLectura);
        tipoLectura.setItems(tLectura);
        interesLectura.setItems(iLectura);

        root.add(preguntaIL,1,1);
        root.add(interesLectura,3,1);
        root.add(preguntaTL,1,2);
        root.add(tipoLectura,3,2);
        root.add(preguntaFL,1,3);
        root.add(frecuenciaLectura,3,3);
        root.add(preguntaTF,1,4);
        root.add(tituloFavorito,3,4);
        root.add(preguntaNO,1,5);
        root.add(numObras,3,5);
        root.add(cancelaEncuesta,2,6);
        root.add(enviarEncuesta,3,6);

        root.setVgap(10);
        root.setHgap(10);
        root.setAlignment(Pos.BASELINE_CENTER);

        preguntaTL.setVisible(false);
        tipoLectura.setVisible(false);
        preguntaFL.setVisible(false);
        frecuenciaLectura.setVisible(false);
        preguntaTF.setVisible(false);
        tituloFavorito.setVisible(false);
        preguntaNO.setVisible(false);
        numObras.setVisible(false);

        interesLectura.setOnAction(e->{

            if(interesLectura.getValue().toString().equals("No estoy interesado" )){
                preguntaTL.setVisible(false);
                tipoLectura.setVisible(false);
                preguntaFL.setVisible(false);
                frecuenciaLectura.setVisible(false);
                preguntaTF.setVisible(false);
                tituloFavorito.setVisible(false);
                preguntaNO.setVisible(false);
                numObras.setVisible(false);
            }else{
                preguntaFL.setVisible(true);
                frecuenciaLectura.setVisible(true);
                preguntaTL.setVisible(true);
                tipoLectura.setVisible(true);
                preguntaTF.setVisible(true);
                tituloFavorito.setVisible(true);
                preguntaNO.setVisible(true);
                numObras.setVisible(true);
            }
        });


        setText("Lectura");
        setClosable(false);
        setContent(root);
    }

}
