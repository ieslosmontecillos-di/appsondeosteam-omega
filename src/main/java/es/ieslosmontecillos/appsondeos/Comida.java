package es.ieslosmontecillos.appsondeos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.FileWriter;

public class Comida extends Tab {

    private GridPane root = new GridPane();
    private FileWriter csvEncuesta;
    private Label preguntaC = new Label("¿Te gusta comer?");
    private ToggleGroup gustoComer = new ToggleGroup();
    private RadioButton siComer = new RadioButton("Si");
    private RadioButton noComer = new RadioButton("No");
    private Label preguntaTC = new Label("¿Qué tipo de comida te gusta más?");
    private ChoiceBox tipoComida = new ChoiceBox();
    private Label preguntaCF = new Label("¿Cuál es tu comida favorita?");
    private TextField comidaFavorita = new TextField();
    private Label preguntaDE = new Label("¿Mantienes una dieta equilibrada?");
    private ToggleGroup dietaEquilibrada = new ToggleGroup();
    private RadioButton siDieta = new RadioButton("Si");
    private RadioButton noDieta = new RadioButton("No");
    private Label preguntaCC = new Label("¿Cuánta cantidad de comida sueles comer?");
    private ChoiceBox cantidadComida = new ChoiceBox();
    private Label preguntaIntolerancia = new Label("¿Eres intolerante a algún alimento/nutriente? (ej. lactosa o cacahuete)");
    private ToggleGroup intolera = new ToggleGroup();
    private RadioButton intolerancia = new RadioButton("Si");
    private RadioButton tolerancia = new RadioButton("No");
    private Label intoleranciaConcreta = new Label("¿A qué eres intolerante?");
    private TextField intolerante = new TextField();
    private Button enviarEncuesta = new Button("Enviar");

    ObservableList<String> tComida = FXCollections.observableArrayList("Fruta", "Verdura", "Carne", "Pescado", "Legumbres", "Frituras", "Frutos secos");
    ObservableList<String> cComida = FXCollections.observableArrayList("Mucho", "Normal", "Poco", "Casi nada");

    public Comida() {
        makeGUI();
    }

    private void makeGUI() {

        tipoComida.setItems(tComida);
        cantidadComida.setItems(cComida);

        siComer.setToggleGroup(gustoComer);
        noComer.setToggleGroup(gustoComer);

        siDieta.setToggleGroup(dietaEquilibrada);
        noDieta.setToggleGroup(dietaEquilibrada);

        intolerancia.setToggleGroup(intolera);
        tolerancia.setToggleGroup(intolera);

        root.add(preguntaC, 0, 0);
        root.add(siComer, 1, 0);
        root.add(noComer, 2, 0);
        root.add(preguntaTC, 0, 1);
        root.add(tipoComida, 1, 1);
        root.add(preguntaCF, 0, 2);
        root.add(comidaFavorita, 1, 2);
        root.add(preguntaDE, 0, 3);
        root.add(siDieta, 1, 3);
        root.add(noDieta, 2, 3);
        root.add(preguntaCC, 0, 4);
        root.add(cantidadComida, 1, 4);
        root.add(preguntaIntolerancia, 0, 5);
        root.add(intolerancia, 1, 5);
        root.add(tolerancia, 2, 5);
        root.add(intoleranciaConcreta, 0, 6);
        root.add(intolerante, 1, 6);
        root.add(enviarEncuesta, 1, 7);

        intolera.selectedToggleProperty().addListener(e -> {
            if (intolera.getSelectedToggle().equals(intolerancia)) {
                intoleranciaConcreta.setVisible(true);
                intolerante.setVisible(true);
            } else {
                intoleranciaConcreta.setVisible(false);
                intolerante.setVisible(false);
            }
        });

        intoleranciaConcreta.setVisible(false);
        intolerante.setVisible(false);

        root.setAlignment(Pos.BASELINE_CENTER);
        root.setHgap(10);
        root.setVgap(10);

        setText("Comida");
        setClosable(false);
        setContent(root);
    }

    public void setCsvEncuesta(FileWriter csv) {
        csvEncuesta = csv;
    }
}
