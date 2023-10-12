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
    private Label preguntaNO = new Label("¿Cuántaas obras literarias te has leído?");
    private TextField numObras = new TextField();
    private Label preguntaIL = new Label("¿Cuál fue el motivo principal de tu interés por la lectura?");
    private ChoiceBox interesLectura = new ChoiceBox();


    public Lectura(){
        makeGUI();
    }

    private void makeGUI(){
        ObservableList<String> fLectura = FXCollections.observableArrayList("Todos los días", "Más de 4 veces a la semana", "2 o 3 veces a la semana", "1 vez a la semana");
        ObservableList<String> tLectura = FXCollections.observableArrayList("Cómics", "Novelas", "Poemas", "Cuentos", "Enciclopedias", "Diccionarios", "Biografías", "Libros de divulgación científica", "Libros de fotografía");
        ObservableList<String> iLectura = FXCollections.observableArrayList("Recomendación de amigos/familiares", "Interés propio");

        frecuenciaLectura.setValue("Ninguna vez");
        tipoLectura.setValue("Otro");
        interesLectura.setValue("No me acuerdo");

        frecuenciaLectura.setItems(fLectura);
        tipoLectura.setItems(tLectura);
        interesLectura.setItems(iLectura);

        root.add(preguntaFL,1,1);
        root.add(frecuenciaLectura,3,1);
        root.add(preguntaTL,1,2);
        root.add(tipoLectura,3,2);
        root.add(preguntaIL,1,3);
        root.add(interesLectura,3,3);
        root.setVgap(10);
        root.setHgap(10);
        root.setAlignment(Pos.BASELINE_CENTER);

        preguntaTL.setVisible(false);
        tipoLectura.setVisible(false);
        preguntaIL.setVisible(false);
        interesLectura.setVisible(false);


        setText("Lectura");
        setClosable(false);
        setContent(root);
    }

}
