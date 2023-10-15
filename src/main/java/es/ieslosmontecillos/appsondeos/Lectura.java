package es.ieslosmontecillos.appsondeos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.FileWriter;
import java.io.IOException;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class Lectura extends Tab {

    // Panel principal
    private GridPane root = new GridPane();


    // Fichero csv
    private FileWriter csvEncuesta;


    // Preguntas de la encuesta
    // Pregunta 1
    private final Label preguntaFL = new Label("¿Con qué frecuencia lees actualmente?");
    private final ChoiceBox frecuenciaLectura = new ChoiceBox();

    // Pregunta 2
    private final Label preguntaTL = new Label("¿Qué tipo de lectura te gusta más?");
    private final ChoiceBox tipoLectura = new ChoiceBox();

    // Pregunta 3
    private final Label preguntaTF = new Label("¿Qué obra que hayas leído te ha gustado más?");
    private final TextField tituloFavorito = new TextField();

    // Pregunta 4
    private final Label preguntaNO = new Label("¿Cuántas obras literarias te has leído?");
    private final TextField numObras = new TextField();

    // Pregunta 5
    private final Label preguntaIL = new Label("¿Cuál fue el motivo principal de tu interés por la lectura?");
    private final ChoiceBox interesLectura = new ChoiceBox();


    // Botón
    private final Button enviarEncuesta = new Button("Enviar");


    // Constructor
    public Lectura() {
        makeGUI();
    }


    // Métodos
    private void makeGUI() {

        valoresChoiceBox();
        adicionNodos();
        eventos();
        formatoTextField();
        propiedades();

        setContent(root);
    }

    private void valoresChoiceBox() {
        // Listas para ChoiceBox
        ObservableList<String> fLectura = FXCollections.observableArrayList("No leo", "Todos los días", "Más de 4 veces a la semana", "2 o 3 veces a la semana", "1 vez a la semana");
        ObservableList<String> tLectura = FXCollections.observableArrayList("Otro", "Cómics", "Novelas", "Poemas", "Cuentos", "Enciclopedias", "Diccionarios", "Biografías", "Libros de divulgación científica", "Libros de fotografía");
        ObservableList<String> iLectura = FXCollections.observableArrayList("No estoy interesado", "No me acuerdo", "Recomendación de amigos/familiares", "Interés propio");


        // Asignación de opciones ChoiceBox
        frecuenciaLectura.setItems(fLectura);
        tipoLectura.setItems(tLectura);
        interesLectura.setItems(iLectura);
    }

    private void adicionNodos() {
        // Adición de nodos al panel
        // Panel principal
        root.add(preguntaIL, 1, 1);
        root.add(interesLectura, 3, 1);

        root.add(preguntaTL, 1, 2);
        root.add(tipoLectura, 3, 2);

        root.add(preguntaFL, 1, 3);
        root.add(frecuenciaLectura, 3, 3);

        root.add(preguntaTF, 1, 4);
        root.add(tituloFavorito, 3, 4);

        root.add(preguntaNO, 1, 5);
        root.add(numObras, 3, 5);

        root.add(enviarEncuesta, 3, 6);
    }

    private void eventos() {
        // Eventos
        enviarEncuesta.setOnAction(onClick -> {
            enviaEncuesta();
        });

        setOnSelectionChanged(changedSelection -> {
            vaciaEncuesta();
        });
    }

    private void formatoTextField() {
        // Formato para los TextField
        // Solo números
        UnaryOperator<TextFormatter.Change> filtroNumero = cambio -> {
            // Expresión regular para permitir solo dígitos
            Pattern patron = Pattern.compile("[0-9]*");

            // Verificamos si el nuevo texto cumple con la expresión regular
            if (patron.matcher(cambio.getControlNewText()).matches()) {
                return cambio;
            } else {
                // Si no se cumple con los requisitos del patrón, no se devuelve nada
                return null;
            }
        };
        TextFormatter<String> formato = new TextFormatter<>(filtroNumero);

        numObras.setTextFormatter(formato);
    }

    private void propiedades() {
        // Propiedades
        // Paneles
        root.setVgap(10);
        root.setHgap(10);
        root.setAlignment(Pos.BASELINE_CENTER);

        // Tab
        setText("Lectura");
        setClosable(false);
    }


    private void enviaEncuesta() {
        try {
            // Tipo de encuesta
            csvEncuesta.append(getText());
            csvEncuesta.append(",");

            // Preguntas registradas en el csv
            String preg1 = "Frecuencia";
            String preg2 = "Tipo Favorito";
            String preg3 = "Título Favorito";
            String preg4 = "Obras leídas";
            String preg5 = "Motivo interés";

            ObservableList<String> preguntas = FXCollections.observableArrayList(preg1, preg2, preg3, preg4, preg5);

            for (String celda : preguntas) {
                csvEncuesta.append(celda);
                csvEncuesta.append(",");
            }
            csvEncuesta.append("\n");
            csvEncuesta.append(",");

            String frecuenciaLee = "";
            String tipoLec = "";
            String interes = "";

            if (frecuenciaLectura.getValue() != null)
                frecuenciaLee = frecuenciaLectura.getValue().toString();
            if (tipoLectura.getValue() != null)
                tipoLec = tipoLectura.getValue().toString();
            if (interesLectura.getValue() != null)
                interes = interesLectura.getValue().toString();


            // Respuestas registradas en el csv
            String titFav = tituloFavorito.getText();
            String leidos = numObras.getText();


            ObservableList<String> respuestas = FXCollections.observableArrayList(frecuenciaLee, tipoLec, titFav, leidos, interes);

            for (String celda : respuestas) {
                csvEncuesta.append(celda);
                csvEncuesta.append(",");
            }
            csvEncuesta.append("\n\n");
            csvEncuesta.flush();
            csvEncuesta.close();
            System.exit(0);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    private void vaciaEncuesta() {
        frecuenciaLectura.getSelectionModel().clearSelection();
        tipoLectura.getSelectionModel().clearSelection();
        frecuenciaLectura.getSelectionModel().clearSelection();
        tituloFavorito.setText("");
        numObras.setText("");
        interesLectura.getSelectionModel().clearSelection();
    }

    public void setCsvEncuesta(FileWriter csv) {
        csvEncuesta = csv;
    }
}
