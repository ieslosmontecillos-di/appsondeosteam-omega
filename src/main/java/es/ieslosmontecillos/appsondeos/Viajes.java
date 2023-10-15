package es.ieslosmontecillos.appsondeos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.FileWriter;
import java.io.IOException;

public class Viajes extends Tab {

    // Panel
    private final GridPane root = new GridPane();


    // Fichero encuesta
    FileWriter csvEncuesta;


    // Preguntas encuesta
    // Pregunta 1
    private final Label lblViaje = new Label("¿Ha viajado en los dos ultimos años?");
    private final ToggleGroup viajeSN = new ToggleGroup();
    private final RadioButton rbSi = new RadioButton("Si");
    private final RadioButton rbNo = new RadioButton("No");

    // Pregunta 2
    private final Label lblprevisto = new Label("¿Tiene previsto viajar próximamente?");
    private final ToggleGroup previstoSN = new ToggleGroup();
    private final RadioButton previstoSi = new RadioButton("Si");
    private final RadioButton previstoNo = new RadioButton("No");

    // Pregunta 3
    private final Label lblpromedio = new Label("¿Cuantos días de promedio duran sus viajes?");
    private final ChoiceBox promedio = new ChoiceBox();

    // Pregunta 4
    private final Label lblprecio = new Label("Precio que suele gastar en todo el viaje");
    private final TextField txtPrecio = new TextField();

    // Pregunta 5
    private final Label lblContinentes = new Label("Continente al que le gustaría viajar");
    private final ChoiceBox continents = new ChoiceBox();


    // Botón para enviar la encuesta
    private final Button btnEnviar = new Button("Enviar encuesta");


    // Constructor
    public Viajes(){makeGUI();}


    // Métodos
    private void makeGUI(){

        valoresChoiceBox();
        gruposRadioButton();
        adicionNodos();
        eventos();
        propiedades();

        setContent(root);
    }

    private void valoresChoiceBox(){
        // Pregunta 3
        ObservableList<String> opcionesDias = FXCollections.observableArrayList("Entre 2 a 3 dias", "Entre 4 a 7 dias", "Entre 1 y 2 semanas");
        promedio.setItems(opcionesDias);

        // Pregunta 5
        ObservableList<String> opcionesContinentes = FXCollections.observableArrayList("Europa", "Asia", "America", "Oceania", "Africa");
        continents.setItems(opcionesContinentes);
    }

    private void gruposRadioButton(){
        // Pregunta 1
        rbSi.setToggleGroup(viajeSN);
        rbNo.setToggleGroup(viajeSN);

        // Pregunta 2
        previstoSi.setToggleGroup(previstoSN);
        previstoNo.setToggleGroup(previstoSN);
    }

    private void adicionNodos(){

        // Preguntas
        // 1
        root.add(lblViaje, 3 ,1);
        root.add(rbSi , 4, 1);
        root.add(rbNo, 5, 1);

        // 2
        root.add(lblprevisto, 3, 4);
        root.add(previstoSi, 4, 4);
        root.add(previstoNo, 5,4);

        // 3
        root.add(lblpromedio, 3, 7);
        root.add(promedio, 4, 7);

        // 4
        root.add(lblprecio, 3, 10);
        root.add(txtPrecio, 4 , 10);

        // 5
        root.add(lblContinentes, 3, 13);
        root.add(continents, 4, 13);


        // Botón enviar encuesta
        root.add(btnEnviar, 4, 16);
    }

    private void eventos(){
        // Vacía las respuestas
        setOnSelectionChanged(changedSelection -> {
            vaciaEncuesta();
        });

        // Envía la encuesta
        btnEnviar.setOnAction(actionEvent -> {
            enviaEncuesta();
        });
    }

    private void propiedades(){
        // Panel
        root.setHgap(10);
        root.setVgap(30);
        root.setPadding(new Insets(0,10, 0, 10));

        // Tab
        setClosable(false);
        setText("Viajes");
    }

    public void setCsvEncuesta(FileWriter csvEnc){
        csvEncuesta = csvEnc;
    }


    // Métodos de los eventos
    private void enviaEncuesta(){
        try {
            csvEncuesta.append(getText());
            csvEncuesta.append(",");

            // Registra las preguntas
            String preg1 = "Ha viajado";
            String preg2 = "Viajara";
            String preg3 = "Duracion";
            String preg4 = "Precio medio gastado";
            String preg5 = "Continente al que le gustaria viajar";

            ObservableList<String> preguntas = FXCollections.observableArrayList(preg1, preg2, preg3, preg4, preg5);

            for (String celda : preguntas) {
                csvEncuesta.append(celda);
                csvEncuesta.append(",");
            }
            csvEncuesta.append("\n");
            csvEncuesta.append(",");


            // Registra las respuestas
            String seleccionaViajeSn = viajeSN.getSelectedToggle() != null ? ((RadioButton) viajeSN.getSelectedToggle()).getText() : "";
            String seleccionaPrevistoSn = previstoSN.getSelectedToggle() != null ? ((RadioButton) previstoSN.getSelectedToggle()).getText() : "";
            String promedioViaje = promedio.getValue() != null ? promedio.getValue().toString() : "";
            String precioViaje = txtPrecio.getText();
            String continentes = continents.getValue() != null ? continents.getValue().toString() : "";

            ObservableList<String> respuestas = FXCollections.observableArrayList(seleccionaViajeSn, seleccionaPrevistoSn, promedioViaje, precioViaje, continentes);

            for (String celda : respuestas) {
                csvEncuesta.append(celda);
                csvEncuesta.append(",");
            }
            csvEncuesta.append("\n");


            // Termina cerrando la encuesta
            System.out.println("Encuesta enviada");
            csvEncuesta.flush();
            csvEncuesta.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void vaciaEncuesta(){
        viajeSN.selectToggle(null);
        previstoSN.selectToggle(null);
        promedio.getSelectionModel().clearSelection();
        txtPrecio.clear();
        continents.getSelectionModel().clearSelection();
    }

}
