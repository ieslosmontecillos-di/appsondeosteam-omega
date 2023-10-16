package es.ieslosmontecillos.appsondeos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.FileWriter;
import java.io.IOException;

public class Deportes extends Tab {

    // Panel
    private final GridPane grid = new GridPane();


    // Fichero encuesta
    private FileWriter csvEncuesta;


    // Preguntas
    // Pregunta 1
    private final VBox vbSex = new VBox();
    private final Label lblGenero = new Label("¿Cuál es tu género?");
    private final ToggleGroup genero = new ToggleGroup();
    private final RadioButton fem = new RadioButton("Femenino");
    private final RadioButton masc = new RadioButton("Masculino");
    private final RadioButton no_cont = new RadioButton("Prefiero no contestar");

    // Separador de preguntas
    private final Separator separador = new Separator(Orientation.HORIZONTAL);

    // Pregunta 2
    private final Label lblDed = new Label("¿A qué te dedicas?");
    private final ChoiceBox cbDed = new ChoiceBox(FXCollections.observableArrayList("Nada", "Estudiar", "Trabajar"));
    private final String[] strPreguntaDed = new String[]{"Que poca vergüenza...", "¿Qué estudias?", "¿De qué trabajas?"};
    // Opción 1 choiceBox (Estudiar)
    private final Label lblInteres = new Label();
    private final TextField txtResDed = new TextField();
    // Opción 2 ChoiceBox (Trabajar)
    private final Label lblInteres2 = new Label(strPreguntaDed[2]);

    // Separador de preguntas 2
    private final Separator separador2 = new Separator(Orientation.HORIZONTAL);

    //Pregunta 3
    private final Label lblDeporte = new Label("¿Cuánto te gusta el deporte?");
    private final Slider slDeporte = new Slider(1, 10, 5);
    private final Label lblExpl = new Label("A continuación, varias preguntas de Si o No ");

    // Pregunta 4
    private final Label lblCHB1 = new Label("¿Consideras el ajedrez un deporte?");
    private final RadioButton rbAjSi = new RadioButton("Si");
    private final RadioButton rbAjNo = new RadioButton("No");
    private final ToggleGroup respuesta1 = new ToggleGroup();

    // Pregunta 5
    private final Label lblCHB2 = new Label("¿Te gustan los deportes de raqueta?");
    private final RadioButton rbRaSi = new RadioButton("Si");
    private final RadioButton rbRaNo = new RadioButton("No");
    private final ToggleGroup respuesta2 = new ToggleGroup();

    // Pregunta 6
    private final Label lblCHB3 = new Label("¿Consideras la F1 y Motogp un deporte?");
    private final RadioButton rbMoSi = new RadioButton("Si");
    private final RadioButton rbMoNo = new RadioButton("No");
    private final ToggleGroup respuesta3 = new ToggleGroup();

    // Botón de enviar encuesta
    private final Button envCuest = new Button("Enviar");


    // Constructor
    public Deportes() {
        makeGUI();
    }


    // Métodos
    private void makeGUI() {

        gruposRadioButton();
        adicionNodos();
        eventos();
        propiedades();

        setContent(grid);
    }

    private void adicionNodos(){


        // Genero
        grid.add(lblGenero, 0, 4);
        grid.add(vbSex, 1, 4);

        // Separador 1
        grid.add(separador, 0, 6);
        GridPane.setColumnSpan(separador, 5);

        // Dedica
        grid.add(lblDed, 0, 8);
        grid.add(cbDed, 1, 8);

        // Interes
        grid.add(txtResDed, 1, 11);
        grid.add(lblInteres, 0, 11);
        grid.add(lblInteres2, 2, 11);

        // Deporte
        grid.add(lblDeporte, 0, 13);
        grid.add(slDeporte, 1, 13);

        // Separador 2
        grid.add(separador2, 0, 15);
        GridPane.setColumnSpan(separador2, 5);

        // Preguntas de dos respuestas
        grid.add(lblExpl, 0, 17);

        // Pregunta 1
        grid.add(lblCHB1, 0, 21);
        grid.add(rbAjSi, 1, 21);
        grid.add(rbAjNo, 2, 21);

        // Pregunta 2
        grid.add(lblCHB2, 0, 25);
        grid.add(rbRaSi, 1, 25);
        grid.add(rbRaNo, 2, 25);

        // Pregunta 3
        grid.add(lblCHB3, 0, 29);
        grid.add(rbMoSi, 1, 29);
        grid.add(rbMoNo, 2, 29);

        // Enviar encuesta
        grid.add(envCuest, 1, 30);



        // Panel para el sexo
        vbSex.getChildren().addAll(fem, masc, no_cont);
    }

    private void gruposRadioButton(){

        no_cont.setToggleGroup(genero);
        fem.setToggleGroup(genero);
        masc.setToggleGroup(genero);

        rbAjNo.setToggleGroup(respuesta1);
        rbAjSi.setToggleGroup(respuesta1);

        rbRaSi.setToggleGroup(respuesta2);
        rbRaNo.setToggleGroup(respuesta2);

        rbMoSi.setToggleGroup(respuesta3);
        rbMoNo.setToggleGroup(respuesta3);
    }

    private void eventos(){
        envCuest.setOnAction( actionEvent -> {
            enviaEncuesta();
        });

        setOnSelectionChanged( changedSelection -> {
            vaciaEncuesta();
        });
        cbDed.setOnAction( event -> {
            int indexSeleccion = cbDed.getSelectionModel().getSelectedIndex();

            //No hace nada, ni estudia ni trabaja
            if (indexSeleccion == 0) {
                txtResDed.setVisible(false);
                lblInteres2.setVisible(false);
                lblInteres.setText(strPreguntaDed[indexSeleccion]);
            }
            //Estudia o Trabaja
            else {
                lblInteres.setText(strPreguntaDed[indexSeleccion]);
                txtResDed.setVisible(true);

                lblInteres2.setVisible(false);
            }
        });
    }
    private void propiedades (){

        // Panel principal
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        // Trabajo/Estudio
        lblInteres2.setVisible(false);
        txtResDed.setVisible(false);

        // Gusto deporte
        slDeporte.setBlockIncrement(1);
        slDeporte.setShowTickLabels(true);
        slDeporte.setMajorTickUnit(1);
        slDeporte.setShowTickMarks(true);


        // Tab
        setText("Deporte");
        setClosable(false);
    }
    public void setCsvEncuesta(FileWriter csv) {
        csvEncuesta = csv;
    }


    // Métodos de eventos
    private void enviaEncuesta() {
        try {
            // Tipo de encuesta
            csvEncuesta.append(getText());
            csvEncuesta.append(",");

            // Preguntas registradas en el csv
            String preg1 = "¿Género?";
            String preg2 = "¿A qué se dedica?";
            String preg3 = "¿Concretamente?";
            String preg4 = "¿Gusto por el deporte?";
            String preg5 = "¿Considera ajedrez un deporte?";
            String preg6 = "¿Le gustan los deportes de raqueta?";
            String preg7 = "¿Considera moto gp y F1 un deporte?";

            ObservableList<String> preguntas = FXCollections.observableArrayList(preg1, preg2, preg3, preg4, preg5, preg6,preg7);

            for (String celda : preguntas) {
                csvEncuesta.append(celda);
                csvEncuesta.append(",");
            }
            csvEncuesta.append("\n");
            csvEncuesta.append(",");

            // Respuestas registradas en el csv

            String generoUsu = genero.getSelectedToggle() != null ? ((RadioButton) genero.getSelectedToggle()).getText() : "";
            String dedica = cbDed.getValue() != null ? cbDed.getValue().toString() : "";
            String dedicaConcreto = txtResDed.getText();
            String gustoDep = String.valueOf (slDeporte.getValue());
            String respuestaSN1 = respuesta1.getSelectedToggle() != null ? ((RadioButton) respuesta1.getSelectedToggle()).getText() : "";
            String respuestaSN2 = respuesta2.getSelectedToggle() != null ? ((RadioButton) respuesta2.getSelectedToggle()).getText() : "";
            String respuestaSN3 = respuesta3.getSelectedToggle() != null ? ((RadioButton) respuesta3.getSelectedToggle()).getText() : "";

            ObservableList<String> respuestas = FXCollections.observableArrayList(generoUsu, dedica, dedicaConcreto, gustoDep, respuestaSN1, respuestaSN2, respuestaSN3);

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

    private void vaciaEncuesta(){
        genero.selectToggle(null);
        cbDed.getSelectionModel().select(0);
        txtResDed.clear();
        slDeporte.setValue(5);
        respuesta1.selectToggle(null);
        respuesta2.selectToggle(null);
        respuesta3.selectToggle(null);
    }

}
