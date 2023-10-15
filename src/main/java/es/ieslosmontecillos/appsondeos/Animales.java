package es.ieslosmontecillos.appsondeos;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Animales extends Application {
    public void start(Stage stage) {
//
        GridPane gridPane = new GridPane();
        gridPane.setHgap(28);
        gridPane.setVgap(25);
        gridPane.setPadding(new Insets(0,10, 0, 10));

        //Animal
        Label lblanimal = new Label("¿Animal?");
        TextField txtanimal = new TextField("");
        gridPane.add(lblanimal, 3 ,1);
        gridPane.add(txtanimal , 4, 1);

        //Sexo del animal
        final ToggleGroup sexo = new ToggleGroup();
        Label lblsexo = new Label("Seleccione sexo");
        RadioButton rbmacho = new RadioButton();
        rbmacho.setText("MACHO");
        rbmacho.setToggleGroup(sexo);
        RadioButton rbhembra = new RadioButton();
        rbhembra.setText("HEMBRA");
        rbhembra.setToggleGroup(sexo);
        gridPane.add(lblsexo, 3, 3);
        gridPane.add(rbmacho, 4, 3);
        gridPane.add(rbhembra, 5, 3);

        //Tipo de animal
        Label lbltipoanimal = new Label("Tipo de animales: ");
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Pez",
                        "Anfibio",
                        "Reptil",
                        "Ave",
                        "Mamífero"
                );
        final ComboBox comboBox = new ComboBox(options);
        gridPane.add(lbltipoanimal, 3, 5);
        gridPane.add(comboBox, 4, 5);

        //Animal venenoso
        final ToggleGroup veneno = new ToggleGroup();
        Label lblvenenoso = new Label("¿Animal venenoso?");
        RadioButton rbvenenoSi = new RadioButton("Si");
        rbvenenoSi.setToggleGroup(veneno);
        RadioButton rbvenenoNo = new RadioButton("No");
        rbvenenoNo.setToggleGroup(veneno);
        gridPane.add(lblvenenoso, 3, 7);
        gridPane.add(rbvenenoSi, 4, 7);
        gridPane.add(rbvenenoNo, 5, 7);

        //Slider peligro del animal
        Label lblPeligro = new Label("Peligrosidad");
        Slider slPeligro = new Slider();
        slPeligro.setMin(0);
        slPeligro.setMax(10);
        slPeligro.setValue(5);
        slPeligro.setShowTickLabels(true);
        slPeligro.setShowTickMarks(true);
        slPeligro.setMajorTickUnit(5);
        slPeligro.setMinorTickCount(5);
        gridPane.add(lblPeligro,3,9);
        gridPane.add(slPeligro, 4,9);

        //Slider peligro de extincion del animal
        Label lblPeligroEx = new Label("Peligro de extinción ");
        Slider slPeligroEx = new Slider();
        slPeligroEx.setMin(0);
        slPeligroEx.setMax(10);
        slPeligroEx.setValue(5);
        slPeligroEx.setShowTickLabels(true);
        slPeligroEx.setShowTickMarks(true);
        slPeligroEx.setMajorTickUnit(5);
        slPeligroEx.setMinorTickCount(5);
        gridPane.add(lblPeligroEx,3,11);
        gridPane.add(slPeligroEx, 4,11);

        //Enviar boton
        Button btnEnviar = new Button("Enviar encuesta");
        btnEnviar.setOnAction(actionEvent -> {
            try {
                String nombreAnimal = txtanimal.getText();
                String seleccionaSexo =  sexo.getSelectedToggle() != null ? ((RadioButton) sexo.getSelectedToggle()).getText() : "";
                String TipodeAnimal = comboBox.getValue().toString();
                String esVenenoso =  veneno.getSelectedToggle() != null ? ((RadioButton) veneno.getSelectedToggle()).getText() : "";
                String peligroso = String.format("%.2f",slPeligro.getValue());
                double peligrodeExtincion = slPeligroEx.getValue();

                List<String> data = new ArrayList<>();
                data.add(nombreAnimal);
                data.add(seleccionaSexo);
                data.add(TipodeAnimal);
                data.add(esVenenoso);
                data.add(String.valueOf(peligroso));
                data.add(String.valueOf(peligrodeExtincion));

                FileWriter csvEncuesta = new FileWriter("Enc_Animales.csv", true);
                System.out.println("Encuesta enviada");
                for (String celda : data) {
                    csvEncuesta.append(celda);
                    csvEncuesta.append(",");
                }
                csvEncuesta.append("\n");
                csvEncuesta.flush();
                csvEncuesta.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        gridPane.add(btnEnviar, 4, 13);



        Scene scene = new Scene(gridPane ,800, 900);
        String cssFile = Animales.class.getResource("css/Style.css").toExternalForm();
        scene.getStylesheets().add(cssFile);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Animales");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}