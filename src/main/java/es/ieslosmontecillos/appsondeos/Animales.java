package es.ieslosmontecillos.appsondeos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.FileWriter;
import java.io.IOException;


public class Animales extends Tab {

    // Panel
    private final GridPane root = new GridPane();

    // Fichero encuesta
    FileWriter csvEncuesta;

    // Preguntas encuesta
    // Pregunta 1
    private final Label lblanimal = new Label("¿Animal?");
    private final TextField txtanimal = new TextField("");

    // Pregunta 2
    private final Label lblsexo = new Label("Seleccione sexo");
    private final ToggleGroup sexo = new ToggleGroup();
    private final RadioButton rbmacho = new RadioButton("Macho");
    private final RadioButton rbhembra = new RadioButton("Hembra");

    // Pregunta 3
    private final Label lbltipoanimal = new Label("Tipo de animales: ");
    private final ChoiceBox tipoAnimal = new ChoiceBox();

    // Pregunta 4
    private final Label lblvenenoso = new Label("¿Animal venenoso?");
    private final ToggleGroup veneno = new ToggleGroup();
    private final RadioButton rbvenenoSi = new RadioButton("Si");
    private final RadioButton rbvenenoNo = new RadioButton("No");

    // Pregunta 5
    private final Label lblPeligro = new Label("Peligrosidad");
    private final Slider slPeligro = new Slider();

    // Pregunta 6
    private final Label lblPeligroEx = new Label("Peligro de extinción ");
    private final Slider slPeligroEx = new Slider();


    // Botón para enviar la encuesta
    private final Button btnEnviar = new Button("Enviar");


    public Animales(){makeGUI();}

    private void makeGUI(){

        valoresChoiceBox();
        gruposRadioButton();
        adicionNodos();
        eventos();
        propiedades();

        setContent(root);

    }

    private void valoresChoiceBox(){
        ObservableList<String> tAnimal = FXCollections.observableArrayList("Pez", "Anfibio", "Reptil", "Ave", "Mamífero");

        tipoAnimal.setItems(tAnimal);
    }

    private void gruposRadioButton(){

        rbmacho.setToggleGroup(sexo);
        rbhembra.setToggleGroup(sexo);

        rbvenenoSi.setToggleGroup(veneno);
        rbvenenoNo.setToggleGroup(veneno);

    }

    private void adicionNodos(){

        root.add(lblanimal, 3 ,1);
        root.add(txtanimal , 4, 1);

        root.add(lblsexo, 3, 3);
        root.add(rbmacho, 4, 3);
        root.add(rbhembra, 5, 3);

        root.add(lbltipoanimal, 3, 5);
        root.add(tipoAnimal, 4, 5);

        root.add(lblvenenoso, 3, 7);
        root.add(rbvenenoSi, 4, 7);
        root.add(rbvenenoNo, 5, 7);

        root.add(lblPeligro,3,9);
        root.add(slPeligro, 4,9);

        root.add(lblPeligroEx,3,11);
        root.add(slPeligroEx, 4,11);

        root.add(btnEnviar, 4, 13);

    }

    private void eventos(){

        // Vacía la encuesta
        setOnSelectionChanged( changedSelection ->{
            vaciaEncuesta();
        });

        // Envía la encuesta
        btnEnviar.setOnAction(actionEvent -> {
            enviaEncuesta();
        });
    }

    private void propiedades(){

        // Barra de peligrosidad
        slPeligro.setMin(0);
        slPeligro.setMax(10);
        slPeligro.setValue(5);
        slPeligro.setShowTickLabels(true);
        slPeligro.setShowTickMarks(true);
        slPeligro.setMajorTickUnit(5);
        slPeligro.setMinorTickCount(5);

        // Barra de peligro de extinción
        slPeligroEx.setMin(0);
        slPeligroEx.setMax(10);
        slPeligroEx.setValue(5);
        slPeligroEx.setShowTickLabels(true);
        slPeligroEx.setShowTickMarks(true);
        slPeligroEx.setMajorTickUnit(5);
        slPeligroEx.setMinorTickCount(5);


        // Panel
        root.setHgap(28);
        root.setVgap(25);
        root.setPadding(new Insets(0,10, 0, 10));
        // Tab
        setText("Animales");
        setClosable(false);


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
            String preg1 = "Nombre";
            String preg2 = "Sexo";
            String preg3 = "Tipo";
            String preg4 = "Venenosidad";
            String preg5 = "Peligrosidad";
            String preg6 = "Nivel peligro de extinción";

            ObservableList<String> preguntas = FXCollections.observableArrayList(preg1, preg2, preg3, preg4, preg5, preg6);

            for (String celda : preguntas) {
                csvEncuesta.append(celda);
                csvEncuesta.append(",");
            }
            csvEncuesta.append("\n");
            csvEncuesta.append(",");


            // Registra las respuestas
            String nombreAnimal = txtanimal.getText();
            String seleccionaSexo =  sexo.getSelectedToggle() != null ? ((RadioButton) sexo.getSelectedToggle()).getText() : "";
            String tipodeAnimal = tipoAnimal.getValue() != null ? tipoAnimal.getValue().toString() : "";
            String esVenenoso =  veneno.getSelectedToggle() != null ? ((RadioButton) veneno.getSelectedToggle()).getText() : "";
            String peligroso = String.format("%.2f",slPeligro.getValue());
            String peligrodeExtincion = String.format("%.2f",slPeligroEx.getValue());

            ObservableList<String> data = FXCollections.observableArrayList(nombreAnimal, seleccionaSexo, tipodeAnimal, esVenenoso, peligroso, peligroso, peligrodeExtincion);

            for (String celda : data) {
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

    private void vaciaEncuesta() {
        txtanimal.clear();
        sexo.selectToggle(null);
        tipoAnimal.getSelectionModel().clearSelection();
        veneno.selectToggle(null);
        slPeligro.setValue(5);
        slPeligroEx.setValue(5);
    }
}