package es.ieslosmontecillos.appsondeos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Comida extends Tab {

    private VBox root = new VBox();
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

    ObservableList<String> fLectura = FXCollections.observableArrayList("Todos los días", "Más de 4 veces a la semana", "2 o 3 veces a la semana", "1 vez a la semana");
    ObservableList<String> tLectura = FXCollections.observableArrayList("Cómics", "Novelas", "Poemas", "Cuentos", "Enciclopedias", "Diccionarios", "Biografías", "Libros de divulgación científica", "Libros de fotografía");
    ObservableList<String> iLectura = FXCollections.observableArrayList("Recomendación de amigos/familiares", "Interés propio");

    public Comida(){
        makeGUI();
    }

    private void makeGUI(){
        frecuenciaLectura.setValue("Ninguna vez");
        tipoLectura.setValue("Otro");
        interesLectura.setValue("No me acuerdo");

        frecuenciaLectura.setItems(fLectura);
        root.getChildren().add(preguntaFL);
        root.getChildren().add(frecuenciaLectura);

        setText("Comida");
        setClosable(false);
        setContent(root);
    }
}
