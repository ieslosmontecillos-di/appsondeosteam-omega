package es.ieslosmontecillos.appsondeos;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

public class DeportesFun extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        String nameUser= "Antonio";
        final String[] strPreguntaDed = new String[]{"Tienes que dedicarte a algo...", "¿Que estudias?", "¿De que trabajas?"};

        // Crear un TabPane
        TabPane tabPane = new TabPane();

        //Tab
        Tab tab = new Tab("Deportes");
        tab.setClosable(false);

        //Grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        //Genero
        final ToggleGroup genero = new ToggleGroup();

        RadioButton fem = new RadioButton("Femenino");
        fem.setSelected(true);
        RadioButton masc = new RadioButton("Masculino");
        RadioButton no_cont = new RadioButton("Prefiero no contestar");

        no_cont.setToggleGroup(genero);
        fem.setToggleGroup(genero);
        masc.setToggleGroup(genero);

        Label lblGenero = new Label("¿Cuál es tu género " + nameUser + "?");
        VBox vbSex = new VBox();
        vbSex.getChildren().addAll(fem, masc,no_cont);

        grid.add(lblGenero,0,4);
        grid.add(vbSex,1,4);

        //Separador
        final Separator separador = new Separator(Orientation.HORIZONTAL);
        grid.add(separador,0,6);
        GridPane.setColumnSpan(separador, 5);

        //A que se dedica, si trabaja o estudia y dependiendo de lo que haga cambie la pregunta para rellenar dicho dato

        Label lblDed = new Label("¿A que te dedicas?");
        ChoiceBox cbDed = new ChoiceBox(FXCollections.observableArrayList(
                "Nada","Estudiar", "Trabajar", "Ambas")
        );

        grid.add(lblDed,0,8);
        grid.add(cbDed, 1,8);

        Label lblInteres = new Label();
        TextField txtResDed = new TextField();
        AtomicBoolean doscampos = new AtomicBoolean(false);

        Label lblInteres2 = new Label(strPreguntaDed[2]);
        TextField txtResDed2 = new TextField();

        cbDed.setOnAction((event) -> {
            int indexSeleccion = cbDed.getSelectionModel().getSelectedIndex();

            System.out.println(cbDed.getSelectionModel().getSelectedIndex());
            //Trabajar y estudiar
            if(indexSeleccion == 3){
                lblInteres.setText(strPreguntaDed[1]);
                lblInteres2.setVisible(true);
                txtResDed2.setVisible(true);
                txtResDed.setVisible(true);
                doscampos.set(true);
            }
            //No hace nada, ni estudia ni trabaja
            else if(indexSeleccion == 0){
                txtResDed.setVisible(false);
                lblInteres2.setVisible(false);
                txtResDed2.setVisible(false);
                lblInteres.setText(strPreguntaDed[indexSeleccion]);
                doscampos.set(false);
            }
            //Estudia o Trabaja
            else{
                lblInteres.setText(strPreguntaDed[indexSeleccion]);
                txtResDed.setVisible(true);

                lblInteres2.setVisible(false);
                txtResDed2.setVisible(false);
                doscampos.set(false);
            }
        });

        grid.add(txtResDed, 1, 11);
        grid.add(lblInteres, 0, 11);
        grid.add(lblInteres2, 2, 11);
        grid.add(txtResDed2, 3, 11);
        lblInteres2.setVisible(false);
        txtResDed2.setVisible(false);
        txtResDed.setVisible(false);

        //Gusto por el deporte
        Label lblDeporte = new Label("¿Cuanto te gusta el deporte?");
        Slider slDeporte = new Slider(1,10,5);
        slDeporte.setBlockIncrement(1);
        slDeporte.setShowTickLabels(true);
        slDeporte.setMajorTickUnit(1);
        slDeporte.setShowTickMarks(true);

        grid.add(lblDeporte, 0, 13);
        grid.add(slDeporte, 1, 13);

        //Separador
        final Separator separador2 = new Separator(Orientation.HORIZONTAL);
        grid.add(separador2,0,15);
        GridPane.setColumnSpan(separador2, 5);

        //Preguntas de dos respuestas
        Label lblExpl = new Label("A continuacion, varias preguntas de Si o No " + nameUser);
        grid.add(lblExpl, 0, 17);

        //Pregunta 1
        Label lblCHB1 = new Label("¿Consideras el ajedrez un deporte?");
        RadioButton rbAjSi = new RadioButton("Si");
        rbAjSi.setSelected(true);
        RadioButton rbAjNo = new RadioButton("No");

        final ToggleGroup respuesta1 = new ToggleGroup();
        rbAjNo.setToggleGroup(respuesta1);
        rbAjSi.setToggleGroup(respuesta1);

        grid.add(lblCHB1, 0, 21);
        grid.add(rbAjSi, 1, 21);
        grid.add(rbAjNo, 2, 21);

        //Pregunta 2
        Label lblCHB2 = new Label("¿Te gustan los deporte de raqueta?");
        RadioButton rbRaSi = new RadioButton("Si");
        rbRaSi.setSelected(true);
        RadioButton rbRaNo = new RadioButton("No");

        final ToggleGroup respuesta2 = new ToggleGroup();
        rbRaSi.setToggleGroup(respuesta2);
        rbRaNo.setToggleGroup(respuesta2);

        grid.add(lblCHB2, 0, 25);
        grid.add(rbRaSi, 1, 25);
        grid.add(rbRaNo, 2, 25);

        //Pregunta 3
        Label lblCHB3 = new Label("¿Consideras la F1 y Motogp un deporte?");
        RadioButton rbMoSi = new RadioButton("Si");
        rbMoSi.setSelected(true);
        RadioButton rbMoNo = new RadioButton("No");

        final ToggleGroup respuesta3 = new ToggleGroup();
        rbMoSi.setToggleGroup(respuesta3);
        rbMoNo.setToggleGroup(respuesta3);

        grid.add(lblCHB3, 0, 29);
        grid.add(rbMoSi, 1, 29);
        grid.add(rbMoNo, 2, 29);

        Button envCuest = new Button("Enviar cuestionario");
        grid.add(envCuest, 1,45);

        Label lblAviso = new Label("Debes rellenar todos los campos");
        grid.add(lblAviso, 2,45);
        lblAviso.setVisible(false);

        envCuest.setOnAction((event) -> {
            if(doscampos.get()){
                if (camposRelleno(txtResDed, txtResDed2)) {
                    lblAviso.setVisible(false);
                }else
                    lblAviso.setVisible(true);
            }
            else{
                if (camposRelleno(txtResDed)) {
                    lblAviso.setVisible(false);
                }else
                    lblAviso.setVisible(true);
            }

            System.out.println("Hola");
        });

        //Añadimos el grid al tab
        tab.setContent(grid);

        // Agregar el tab al TabPane
        tabPane.getTabs().add(tab);

        Scene scene = new Scene(tabPane, 800, 900);
        scene.getStylesheets().add(AppSondeos.class.getResource("css/Style.css").toExternalForm());
        primaryStage.setTitle("Deportes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
    }
    // Metodo para verificar si todos los campos estan rellenos
    private boolean camposRelleno(TextField... campos) {
        for (TextField campo : campos) {
            if (campo.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
