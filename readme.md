**AppSondeos**

**Equipo Omega**

|-Mario Gallego González|
| :- |
|-Antonio Gómez Rodrigo|
|-Raúl Plaza Gálvez|


**Diseño y Arquitectura de la aplicación:**

`	`-SplashScreen para añadir una pantalla de carga.

-Establecer diseño para todas las encuestas con un css común e icono para la app.

`	`-Background igual para todas las ventanas, fondo logo del equipo

`	`-Tamaño predeterminado de la ventana de la aplicación (800x900).

**Funcionamiento:**

`	`-***Fichero csv***: se creará un fichero csv de la encuesta realizada en la

ruta Documents del usuario, dentro de un directorio creado llamado

Encuestas.

-***Página de bienvenida*** con introducción del nombre del usuario y selección de primera encuesta a realizar, guardando dicho nombre en la variable (**String nameUser**) para preguntar al usuario en las encuestas posteriores por su nombre.



-***Página de encuesta*** creada con un TabPane que dividirá las distintas secciones en Tabs. Al final de cada Tab existen dos botones: uno para cancelar la encuesta y se vacía el fichero.

-***Deportes***: Radiobuttons acerca del género, pregunta sobre la edad usando el nombre guardado del usuario. ChoiceBox preguntando a qué se dedica el usuario y en función de la selección aparece un TextField con un label u otro. Slider para responder a ¿Cuanto te gusta el deporte? Seleccionando del 1 al 10. Por último tres preguntas de RadioButton de si o no, sobre si considera el ajedrez un deporte, si le gustan los deportes de raqueta y si considera MotoGp y F1 un deporte.

-***Viajes***: Preguntar  ¿Ha viajado en los últimos dos años?, respuesta en RadioButton(si , no) , Preguntar ¿Tiene previsto viajar próximamente? RadioButton (Si o no), ¿Cuantos días de promedio duran sus viajes? ComboBox ( entre 2 a 3 días , entre 4 a 7 días, entre 1 y 2 semanas, más de 2 semanas.) , Precio qué decide gastar en el viaje TextField .

Seleccionar el continente al que le gustaría viajar con ComboBox.

-***Lectura***: ChoiceBox que pregunta la frecuencia con la que lee a la semana, siendo “Ninguna vez” por defecto, si se selecciona un valor que no es por defecto aparece un choicebox sobre el tipo de lectura que le gusta, si está vacío se registrará como “otro” tipo de lectura. TextField que preguntará por el título favorito de las obras que ha leído. Un TextField qu pregunta por su autor favorito,y otro TextField que preguntará cuántas obras se ha leído. Finalmente un ChoiceBox que preguntará cómo cogió interés por el mundo de la lectura, cuyo valor por defecto será “Nunca he estado interesado”.

-***Comida***: Dos RadioButtons que contestará si al usuario le gusta la comida. Un ChoiceBox que elija el tipo de comida que le guste el usuario (fruta,verdura,carne,...). Un TextField donde pregunte cuál es su comida favorita. Dos RadioButtons que contestarán si el usuario mantiene una dieta equilibrada. Un ChoiceBox que pregunte la forma en la que come el usuario(mucho, poco, normal…). Dos RadioButtons que pregunten si es intolerante a algún nutriente o alimento, como la lactosa. En caso de que la respuesta sea sí aparecerá un TextField donde indique a qué nutrientes/alimentos es intolerante.

-***Animales***:Preguntara por el animal(perro, gato, leon…), un radiobutton para indicar el sexo del animal con una foto del tipo de sexo,un list view donde aparezca todos los tipos de animales (vertebrados, invertebrados…). Una pregunta sobre si su animal es venenoso usando un label y un checkBox, sliders para indicar del uno al diez el peligro del animal, slider para indicar del uno al diez el peligro de extinción de ese animal.


