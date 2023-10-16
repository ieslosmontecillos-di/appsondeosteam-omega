package es.ieslosmontecillos.appsondeos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.FileWriter;
import java.io.IOException;


public class Comida extends Tab {

    // Panel
    private final GridPane root = new GridPane();


    // Fichero encuesta
    private FileWriter csvEncuesta;


    // Preguntas de la encuesta
    // Pregunta 1
    private final Label preguntaC = new Label("¿Te gusta comer?");
    private final ToggleGroup gustoComer = new ToggleGroup();
    private final RadioButton siComer = new RadioButton("Si");
    private final RadioButton noComer = new RadioButton("No");

    // Pregunta 2
    private final Label preguntaTC = new Label("¿Qué tipo de comida te gusta más?");
    private final ChoiceBox tipoComida = new ChoiceBox();

    // Pregunta 3
    private final Label preguntaCF = new Label("¿Cuál es tu comida favorita?");
    private final TextField comidaFavorita = new TextField();

    // Pregunta 4
    private final Label preguntaDE = new Label("¿Mantienes una dieta equilibrada?");
    private final ToggleGroup dietaEquilibrada = new ToggleGroup();
    private final RadioButton siDieta = new RadioButton("Si");
    private final RadioButton noDieta = new RadioButton("No");

    // Pregunta 5
    private final Label preguntaCC = new Label("¿Cuánta cantidad de comida sueles comer?");
    private final ChoiceBox cantidadComida = new ChoiceBox();

    // Pregunta 6
    private final Label preguntaIntolerancia = new Label("¿Eres intolerante a algún alimento/nutriente? (ej. lactosa o cacahuete)");
    private final ToggleGroup intolera = new ToggleGroup();
    private final RadioButton intolerancia = new RadioButton("Si");
    private final RadioButton tolerancia = new RadioButton("No");

    // Pregunta 7
    private final Label intoleranciaConcreta = new Label("¿A qué eres intolerante?");
    private final TextField intolerante = new TextField();


    // Botón para enviar la encuesta
    private final Button enviarEncuesta = new Button("Enviar");


    // Constructor
    public Comida() {
        makeGUI();
    }


    // Métodos
    private void makeGUI() {

        valoresChoiceBox();
        gruposRadioButton();
        adicionNodos();
        eventos();
        propiedades();
        
        setContent(root);
    }

    private void valoresChoiceBox() {

        ObservableList<String> tComida = FXCollections.observableArrayList("Fruta", "Verdura", "Carne", "Pescado", "Legumbres", "Frituras", "Frutos secos");
        ObservableList<String> cComida = FXCollections.observableArrayList("Mucho", "Normal", "Poco", "Casi nada");

        tipoComida.setItems(tComida);
        cantidadComida.setItems(cComida);
    }

    private void gruposRadioButton() {
        // Pregunta1
        siComer.setToggleGroup(gustoComer);
        noComer.setToggleGroup(gustoComer);

        // Pregunta 4
        siDieta.setToggleGroup(dietaEquilibrada);
        noDieta.setToggleGroup(dietaEquilibrada);

        // Pregunta 6
        intolerancia.setToggleGroup(intolera);
        tolerancia.setToggleGroup(intolera);
    }

    private void adicionNodos() {

        // Preguntas
        // 1
        root.add(preguntaC, 0, 2);
        root.add(siComer, 1, 2);
        root.add(noComer, 2, 2);

        // 2
        root.add(preguntaTC, 0, 6);
        root.add(tipoComida, 1, 6);

        // 3
        root.add(preguntaCF, 0, 12);
        root.add(comidaFavorita, 1, 12);

        // 4
        root.add(preguntaDE, 0, 18);
        root.add(siDieta, 1, 18);
        root.add(noDieta, 2, 18);

        // 5
        root.add(preguntaCC, 0, 24);
        root.add(cantidadComida, 1, 24);

        // 6
        root.add(preguntaIntolerancia, 0, 30);
        root.add(intolerancia, 1, 30);
        root.add(tolerancia, 2, 30);

        // 7
        root.add(intoleranciaConcreta, 0, 36);
        root.add(intolerante, 1, 36);


        // Botón
        root.add(enviarEncuesta, 1, 38);
    }

    private void eventos() {
        // Si es intolerante, se especifica, sino no
        intolera.selectedToggleProperty().addListener(e -> {
            if(intolera.getSelectedToggle() != null) {
                if (intolera.getSelectedToggle().equals(intolerancia)) {
                    intoleranciaConcreta.setVisible(true);
                    intolerante.setVisible(true);
                } else {
                    intolerante.setText("");
                    intoleranciaConcreta.setVisible(false);
                    intolerante.setVisible(false);
                }
            }
        });


        // Vacía la encuesta
        setOnSelectionChanged(changedSelection -> {
            vaciaEncuesta();
        });

        // Envía los datos a una encuesta nueva o ya creada
        enviarEncuesta.setOnAction(onClick -> {
            enviaEncuesta();
        });

    }

    private void propiedades() {
        // Nodos
        intoleranciaConcreta.setVisible(false);
        intolerante.setVisible(false);

        // Panel
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setHgap(10);
        root.setVgap(10);

        // Tab
        setText("Comida");
        setClosable(false);
    }

    public void setCsvEncuesta(FileWriter csv) {
        csvEncuesta = csv;
    }


    // Métodos de los eventos
    private void enviaEncuesta() {
        try {
            // Tipo de encuesta
            csvEncuesta.append(getText());
            csvEncuesta.append(",");

            // Preguntas registradas en el csv
            String preg1 = "¿Le gusta Comer?";
            String preg2 = "Tipo Favorito";
            String preg3 = "Comida Favorita";
            String preg4 = "¿Dieta equilibrada?";
            String preg5 = "¿Cuánto come?";
            String preg6 = "¿Intolerancia?";
            String preg7 = "¿A qué?";

            ObservableList<String> preguntas = FXCollections.observableArrayList(preg1, preg2, preg3, preg4, preg5, preg6, preg7);

            for (String celda : preguntas) {
                csvEncuesta.append(celda);
                csvEncuesta.append(",");
            }
            csvEncuesta.append("\n");
            csvEncuesta.append(",");


            // Respuestas registradas en el csv
            String gustaComer = gustoComer.getSelectedToggle() != null ? ((RadioButton) gustoComer.getSelectedToggle()).getText() : "";
            String tipoCom = tipoComida.getValue() != null ? tipoComida.getValue().toString() : "";
            String cFavorita = comidaFavorita.getText();
            String dieta = dietaEquilibrada.getSelectedToggle() != null ? ((RadioButton) dietaEquilibrada.getSelectedToggle()).getText() : "";
            String cantidad = cantidadComida.getValue() != null ? cantidadComida.getValue().toString() : "";
            String intol = intolera.getSelectedToggle() != null ? ((RadioButton) intolera.getSelectedToggle()).getText() : "";
            String alimentoIntol = intolerante.getText();

            ObservableList<String> respuestas = FXCollections.observableArrayList(gustaComer, tipoCom, cFavorita, dieta, cantidad, intol, alimentoIntol);

            for (String celda : respuestas) {
                csvEncuesta.append(celda);
                csvEncuesta.append(",");
            }
            csvEncuesta.append("\n\n");


            // Termina cerrando la encuesta
            System.out.println("Encuesta enviada");
            csvEncuesta.flush();
            csvEncuesta.close();
        } catch (IOException err) {
            System.err.println("ERROR");
        }
    }

    private void vaciaEncuesta() {
        gustoComer.selectToggle(null);
        tipoComida.getSelectionModel().clearSelection();
        comidaFavorita.clear();
        dietaEquilibrada.selectToggle(null);
        cantidadComida.getSelectionModel().clearSelection();
        intolera.selectToggle(null);
        intoleranciaConcreta.setVisible(false);
        intolerante.setVisible(false);
        intolerante.clear();
    }
}
