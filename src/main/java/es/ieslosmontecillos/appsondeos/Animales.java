package es.ieslosmontecillos.appsondeos;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Animales extends Application {
    public void start(Stage primaryStage) {
        String nameUser = "Raul";
        TabPane tabPane = new TabPane();

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab tab1 = new Tab("Main");
        Tab tab2 = new Tab("Deportes");
        Tab tab3 = new Tab("Viajes");
        Tab tab4 = new Tab("Lectura");
        Tab tab5 = new Tab("Comida");
        Tab tab6 = new Tab("Animales");

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);
        tabPane.getTabs().add(tab4);
        tabPane.getTabs().add(tab5);
        tabPane.getTabs().add(tab6);


        VBox vBox = new VBox(tabPane);
//
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(0,10, 0, 10));

        //Animal
        Label lblanimal = new Label("¿Animal?");
        TextField txtanimal = new TextField("");
        gridPane.add(lblanimal, 3 ,1);
        gridPane.add(txtanimal , 4, 1);

        //Sexo del animal
        final ToggleGroup sexo = new ToggleGroup();
        Label lblsexo = new Label("Sellecione sexo");
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
        Label lblpeligro = new Label("Peligroso");
        Slider slpeligro = new Slider();
        slpeligro.setMin(0);
        slpeligro.setMax(10);
        slpeligro.setValue(5);
        slpeligro.setShowTickLabels(true);
        slpeligro.setShowTickMarks(true);
        slpeligro.setMajorTickUnit(5);
        slpeligro.setMinorTickCount(5);
        gridPane.add(lblpeligro,3,9);
        gridPane.add(slpeligro, 4,9);

        //Slider peligro de extincion del animal
        Label lblpeligroex = new Label("Peligro de extinción ");
        Slider slpeligroex = new Slider();
        slpeligroex.setMin(0);
        slpeligroex.setMax(10);
        slpeligroex.setValue(5);
        slpeligroex.setShowTickLabels(true);
        slpeligroex.setShowTickMarks(true);
        slpeligroex.setMajorTickUnit(5);
        slpeligroex.setMinorTickCount(5);
        gridPane.add(lblpeligroex,3,11);
        gridPane.add(slpeligroex, 4,11);

        //Enviar boton
        Button btnEnviar = new Button("Enviar encuesta");
        btnEnviar.setOnAction(actionEvent -> {
            try {
                String nombreAnimal = txtanimal.getText();
                String seleccionaSexo = ((RadioButton) sexo.getSelectedToggle()).getText();
                String TipodeAnimal = comboBox.getValue().toString();
                String esVenenoso = ((RadioButton) veneno.getSelectedToggle()).getText();
                String peligroso = String.format("%.2f",slpeligro.getValue());
                double peligrodeExtincion = slpeligroex.getValue();

                List<String> data = new ArrayList<>();
                data.add(nombreAnimal);
                data.add(seleccionaSexo);
                data.add(TipodeAnimal);
                data.add(esVenenoso);
                data.add(String.valueOf(peligroso));
                data.add(String.valueOf(peligrodeExtincion));

                FileWriter csvWriter = new FileWriter("Enc_Animales.csv", true);
                for (String field : data) {
                    csvWriter.append(field);
                    csvWriter.append(",");
                }
                csvWriter.append("\n");
                csvWriter.flush();
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        gridPane.add(btnEnviar, 4, 13);

        tab1.setContent(gridPane);
        tab2.setContent(gridPane);
        tab3.setContent(gridPane);
        tab4.setContent(gridPane);
        tab5.setContent(gridPane);
        tab6.setContent(gridPane);

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);
        tabPane.getTabs().add(tab4);
        tabPane.getTabs().add(tab5);
        tabPane.getTabs().add(tab6);

        Scene scene = new Scene(gridPane ,500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Animales");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}