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
import java.util.concurrent.atomic.AtomicBoolean;

public class Deportes extends Tab {
    private FileWriter csvEncuesta;
    private String nameUser;
    private final GridPane grid = new GridPane();
    private final ToggleGroup genero = new ToggleGroup();
    private final String[] strPreguntaDed = new String[]{"Tienes que dedicarte a algo...", "¿Que estudias?", "¿De que trabajas?"};
    private RadioButton fem = new RadioButton("Femenino");
    private RadioButton masc = new RadioButton("Masculino");
    private RadioButton no_cont = new RadioButton("Prefiero no contestar");
    private Label lblGenero = new Label("¿Cuál es tu género " + nameUser + "?");
    private VBox vbSex = new VBox();
    private final Separator separador = new Separator(Orientation.HORIZONTAL);
    private Label lblDed = new Label("¿A que te dedicas?");
    private ChoiceBox cbDed = new ChoiceBox(FXCollections.observableArrayList(
            "Nada", "Estudiar", "Trabajar", "Ambas")
    );
    private Label lblInteres = new Label();
    private TextField txtResDed = new TextField();
    private AtomicBoolean doscampos = new AtomicBoolean(false);
    private Label lblInteres2 = new Label(strPreguntaDed[2]);
    private TextField txtResDed2 = new TextField();
    private Label lblDeporte = new Label("¿Cuanto te gusta el deporte?");
    private Slider slDeporte = new Slider(1, 10, 5);
    private final Separator separador2 = new Separator(Orientation.HORIZONTAL);
    private Label lblExpl = new Label("A continuacion, varias preguntas de Si o No ");
    private Label lblCHB1 = new Label("¿Consideras el ajedrez un deporte?");
    private RadioButton rbAjSi = new RadioButton("Si");
    private RadioButton rbAjNo = new RadioButton("No");
    private final ToggleGroup respuesta1 = new ToggleGroup();
    private Label lblCHB2 = new Label("¿Te gustan los deporte de raqueta?");
    private RadioButton rbRaSi = new RadioButton("Si");
    private RadioButton rbRaNo = new RadioButton("No");
    private final ToggleGroup respuesta2 = new ToggleGroup();
    private Label lblCHB3 = new Label("¿Consideras la F1 y Motogp un deporte?");
    private RadioButton rbMoSi = new RadioButton("Si");
    private RadioButton rbMoNo = new RadioButton("No");
    private final ToggleGroup respuesta3 = new ToggleGroup();
    private Button envCuest = new Button("Enviar");
    private Label lblAviso = new Label("Debes rellenar todos los campos");

    public Deportes() {
        makeGUI();
    }

    private void makeGUI() {
        gridAdd();
        //Genero
        fem.setSelected(true);
        no_cont.setToggleGroup(genero);
        fem.setToggleGroup(genero);
        masc.setToggleGroup(genero);

        vbSex.getChildren().addAll(fem, masc, no_cont);

        cbDed.setOnAction((event) -> {
            int indexSeleccion = cbDed.getSelectionModel().getSelectedIndex();

            System.out.println(cbDed.getSelectionModel().getSelectedIndex());
            //Trabajar y estudiar
            if (indexSeleccion == 3) {
                lblInteres.setText(strPreguntaDed[1]);
                lblInteres2.setVisible(true);
                txtResDed2.setVisible(true);
                txtResDed.setVisible(true);
                doscampos.set(true);
            }
            //No hace nada, ni estudia ni trabaja
            else if (indexSeleccion == 0) {
                txtResDed.setVisible(false);
                lblInteres2.setVisible(false);
                txtResDed2.setVisible(false);
                lblInteres.setText(strPreguntaDed[indexSeleccion]);
                doscampos.set(false);
            }
            //Estudia o Trabaja
            else {
                lblInteres.setText(strPreguntaDed[indexSeleccion]);
                txtResDed.setVisible(true);

                lblInteres2.setVisible(false);
                txtResDed2.setVisible(false);
                doscampos.set(false);
            }
        });

        lblInteres2.setVisible(false);
        txtResDed2.setVisible(false);
        txtResDed.setVisible(false);

        //Gusto por el deporte
        slDeporte.setBlockIncrement(1);
        slDeporte.setShowTickLabels(true);
        slDeporte.setMajorTickUnit(1);
        slDeporte.setShowTickMarks(true);

        //Pregunta 1
        rbAjSi.setSelected(true);
        rbAjNo.setToggleGroup(respuesta1);
        rbAjSi.setToggleGroup(respuesta1);

        //Pregunta 2
        rbRaSi.setSelected(true);
        rbRaSi.setToggleGroup(respuesta2);
        rbRaNo.setToggleGroup(respuesta2);


        //Pregunta 3
        rbMoSi.setSelected(true);
        rbMoSi.setToggleGroup(respuesta3);
        rbMoNo.setToggleGroup(respuesta3);

        lblAviso.setVisible(false);

        envCuest.setOnAction((event) -> {
            if (doscampos.get()) {
                if (camposRelleno(txtResDed, txtResDed2)) {
                    lblAviso.setVisible(false);
                    enviaEncuesta();
                } else
                    lblAviso.setVisible(true);
            } else {
                if (camposRelleno(txtResDed)) {
                    lblAviso.setVisible(false);
                    enviaEncuesta();
                } else
                    lblAviso.setVisible(true);
            }

            System.out.println("Hola");
        });

        lblAviso.setId("errorlbl");

        setText("Deporte");
        setClosable(false);
        setContent(grid);
    }

    private void gridAdd(){
        //Grid
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        //Genero
        grid.add(lblGenero, 0, 4);
        grid.add(vbSex, 1, 4);

        //Separador 1
        grid.add(separador, 0, 6);
        GridPane.setColumnSpan(separador, 5);

        //Dedica
        grid.add(lblDed, 0, 8);
        grid.add(cbDed, 1, 8);

        //Interes
        grid.add(txtResDed, 1, 11);
        grid.add(lblInteres, 0, 11);
        grid.add(lblInteres2, 2, 11);
        grid.add(txtResDed2, 3, 11);

        //Deporte
        grid.add(lblDeporte, 0, 13);
        grid.add(slDeporte, 1, 13);

        //Separador 2
        grid.add(separador2, 0, 15);
        GridPane.setColumnSpan(separador2, 5);

        //Preguntas de dos respuestas
        grid.add(lblExpl, 0, 17);

        //Pregunta 1
        grid.add(lblCHB1, 0, 21);
        grid.add(rbAjSi, 1, 21);
        grid.add(rbAjNo, 2, 21);

        //Pregunta 2
        grid.add(lblCHB2, 0, 25);
        grid.add(rbRaSi, 1, 25);
        grid.add(rbRaNo, 2, 25);

        //Pregunta 3
        grid.add(lblCHB3, 0, 29);
        grid.add(rbMoSi, 1, 29);
        grid.add(rbMoNo, 2, 29);


        grid.add(envCuest, 1, 32);

        grid.add(lblAviso, 2, 32);
    }
    private void enviaEncuesta() {
        try {
            // Tipo de encuesta
            csvEncuesta.append(getText());
            csvEncuesta.append(",");

            // Preguntas registradas en el csv
            String preg1 = "¿Cual es tu genero?";
            String preg2 = "A que se dedica";
            String preg3 = "Gusto por el deporte";
            String preg4 = "Considera ajedrez un deporte";
            String preg5 = "¿Le gustan los deporte de raqueta?";
            String preg6 = "Considera moto gp y F1 un deporte";

            ObservableList<String> preguntas = FXCollections.observableArrayList(preg1, preg2, preg3, preg4, preg5, preg6);

            for (String celda : preguntas) {
                csvEncuesta.append(celda);
                csvEncuesta.append(",");
            }
            csvEncuesta.append("\n");
            csvEncuesta.append(",");

            // Respuestas registradas en el csv

            String generoUsu = genero.getSelectedToggle() != null ? ((RadioButton) genero.getSelectedToggle()).getText() : "";

            String dedica = cbDed.getValue() != null ? cbDed.getValue().toString() : "";

            String ded1 = txtResDed.getText();

            String ded2 = txtResDed2.getText();

            String gustoDep = String.valueOf(slDeporte.getValue());

            String respuestaSN1 = respuesta1.getSelectedToggle() != null ? ((RadioButton) respuesta1.getSelectedToggle()).getText() : "";
            String respuestaSN2 = respuesta2.getSelectedToggle() != null ? ((RadioButton) respuesta2.getSelectedToggle()).getText() : "";
            String respuestaSN3 = respuesta3.getSelectedToggle() != null ? ((RadioButton) respuesta3.getSelectedToggle()).getText() : "";

            ObservableList<String> respuestas = FXCollections.observableArrayList(generoUsu, dedica, ded1, ded2, gustoDep, respuestaSN1, respuestaSN2, respuestaSN3);

            for (String celda : respuestas) {
                csvEncuesta.append(celda);
                csvEncuesta.append(",");
            }
            csvEncuesta.append("\n\n");
            csvEncuesta.flush();
            csvEncuesta.close();
            System.exit(0);
        } catch (IOException err) {
            System.err.println("ERROR");
        }
    }
    private boolean camposRelleno(TextField... campos) {
        for (TextField campo : campos) {
            if (campo.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void setCsvEncuesta(FileWriter csv) {
        csvEncuesta = csv;
    }
}
