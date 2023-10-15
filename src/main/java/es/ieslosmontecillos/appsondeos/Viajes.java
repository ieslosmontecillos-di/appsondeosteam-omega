package es.ieslosmontecillos.appsondeos;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Viajes extends Application {


    @Override
    public void start(Stage stage) throws Exception {


        TabPane tabPane = new TabPane();
        VBox vBox = new VBox(tabPane);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(30);
        gridPane.setPadding(new Insets(0,10, 0, 10));

        //Pregunta sobre si ha viajado
        final ToggleGroup ViajeSN = new ToggleGroup();
        Label lblViaje = new Label("¿Ha viajado en los dos ultimos años?");
        RadioButton rbSi = new RadioButton();
        rbSi.setText("Si");
        rbSi.setToggleGroup(ViajeSN);
        RadioButton rbNo = new RadioButton();
        rbNo.setText("No");
        rbNo.setToggleGroup(ViajeSN);


        gridPane.add(lblViaje, 3 ,1);
        gridPane.add(rbSi , 4, 1);
        gridPane.add(rbNo, 5, 1);


        //Pregunta sobre si tiene previsto viajar proximamente
        final ToggleGroup previstoSN = new ToggleGroup();
        Label lblprevisto = new Label("¿Tiene previsto viajar próximamente?");
        RadioButton previstoSi = new RadioButton();
        previstoSi.setText("Si");
        previstoSi.setToggleGroup(previstoSN);
        RadioButton previstoNo = new RadioButton();
        previstoNo.setText("No");
        previstoNo.setToggleGroup(previstoSN);


        gridPane.add(lblprevisto, 3, 4);
        gridPane.add(previstoSi, 4, 4);
        gridPane.add(previstoNo, 5,4);


        //Pregunta sobre cuantos dias de promedio duran sus viajes
        Label lblpromedio = new Label("¿Cuantos días de promedio dura sin viajes?");
        ObservableList<String> opcionesDias =
                FXCollections.observableArrayList(
                        "Entre 2 a 3 días",
                        "Entre 4 a 7 dias",
                        "Entre 1 y 2 semanas"
                );
        final ComboBox comboBoxpromedio = new ComboBox(opcionesDias);


        gridPane.add(lblpromedio, 3, 7);
        gridPane.add(comboBoxpromedio, 4, 7);


        //Precio del viaje
        Label lblprecio = new Label("Precio que suele gastar en todo el viaje");
        TextField txtPrecio = new TextField("€");


        gridPane.add(lblprecio, 3, 10);
        gridPane.add(txtPrecio, 4 , 10);


        //Continentes
        Label lblContinentes = new Label("Continente al que le gustaria viajar");
        ObservableList<String> opcionesContinentes =
                FXCollections.observableArrayList(
                        "Europa",
                        "Asia",
                        "America",
                        "Oceanía",
                        "Africa"
                );
        final ComboBox comboBoxContientes = new ComboBox(opcionesContinentes);


        gridPane.add(lblContinentes, 3, 13);
        gridPane.add(comboBoxContientes, 4, 13);

        //Enviar boton
        Button btnEnviar = new Button("Enviar encuesta");
        btnEnviar.setOnAction(actionEvent -> {
            try {
                String seleccionaViajeSn = ((RadioButton) ViajeSN.getSelectedToggle()).getText();
                String seleccionaPrevistoSn = ((RadioButton) previstoSN.getSelectedToggle()).getText();
                String promedioViaje = comboBoxpromedio.getValue().toString();
                String precioViaje = txtPrecio.getText();
                String continentes = comboBoxContientes.getValue().toString();

                List<String> data = new ArrayList<>();
                data.add(seleccionaViajeSn);
                data.add(seleccionaPrevistoSn);
                data.add(promedioViaje);
                data.add(precioViaje);
                data.add(String.valueOf(continentes));


                FileWriter csvEncuesta = new FileWriter("Enc_Viajes.csv", true);
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

        gridPane.add(btnEnviar, 4, 16);


        //Escena Final
        Scene scene = new Scene(gridPane, 800, 900);
        String cssFile = Viajes.class.getResource("css/Style.css").toExternalForm();
        scene.getStylesheets().add(cssFile);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Viajes");
        stage.show();

    }
}
