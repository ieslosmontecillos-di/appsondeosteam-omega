**AppSondeos\_teamName:**

Cada equipo desarrollará una App con un escenario principal que incluya en un área central una imagen que sugiera encuestas (imagen\_1). En el área superior, top, se incluya una barra de menú con un menú en la parte izquierda con dos menúitems uno de los cuales lanzará otro escenario que incluirá un TabPane con cinco Tab, uno por cada uno de los tipos de encuesta que se indicará después (imagen\_2). Otro menú en la parte derecha del top con los menuitem de ayuda y acerca de. Se supone un interfaz similar a:

![AppSondeos1](imagen1.jpg)

imagen\_1

Para el comportamiento del menú, se registrarán los manejadores de eventos correspondientes: Al hacer clic sobre el MenuItem &quot;Realizar encuestas&quot; se debe mostrar la escena con los diferentes Tabs para realizar las diferentes encuestas, que son sobre Deporte, Animales, Comidas, Viajes, Lecturas. Al hacer clic sobre el MenuItem salir se cierra la aplicación.

Además se crearán por tanto varias UI (escenas gráficas) incorporando los controles necesarios para que un usuario pueda rellenar los distintos tipos de encuesta, al final quedará una interfaz similar a la de la imagen en la que se muestra el Tab para la encuesta sobre Deportes. Las escenas que se mostrarán al seleccionar los Tabs serán cada unas pequeñas encuestas en la que se preguntará al usuario algunos datos personales, como la profesión, edad, o número de hermanos, y otros datos relacionados con los siguientes temas (un tema para cada escena):

1. **Deportes** , hábitos de ocio (ejemplo en la imagen adjunta)

2. **Animales** , mascotas, hábitos con las mascotas.

3. **Comidas** , tipos de comidas, hábitos alimenticios.

4. **Viajes** , medios de transporte, hábitos de viajar.

5. **Lecturas** , tipo de lecturas, comics, novelas, ensayos, hábitos de lectura.

Ejemplo de escenario con el Tab sobre de **Deportes** activado:

![AppSondeos2](imagen2.jpg)

imagen\_2

En este ejemplo necesitarás los siguientes componentes: etiquetas ( **Labels** ), un campo de texto para la profesión (TextField), para el número de hermanos incluirás una **ChoiceBox** que contendrán los valores: &quot;Ninguno&quot;, &quot;Uno&quot;, &quot;Dos&quot;, &quot;Más de dos&quot;, para la edad otra **ChoiceBox** en la que se deberá elegir entre: &quot;Menos de 15&quot;, &quot;Entre 15 y 18&quot;, &quot;Entre 19 y 35&quot;, &quot;Entre 36 y 60&quot;, &quot;Más de 60&quot;, un **RadioButtons** para el sexo, una **CheckBox** para preguntar al usuario si le gusta el deporte, una **ChoiceBox** para los deportes (Tenis, Fútbol, Balonmano, Atletismo y Natación), para las aficiones tres deslizadores, Sliders. Por último un Button para enviar la encuesta.

En cuanto al comportamiento de los **Sliders** deberá mostrar en la etiqueta de la derecha el valor correspondiente a la posición seleccionada entre 1 y 10 a medida que el usuario lo desliza. El manejador de selector de sexo, **Toggle** , que mostrará la imagen correspondiente al sexo seleccionado. El manejador de eventos del botón &quot;Enviar&quot; que validará que todos los campos estén rellenos, enviará el mensaje de error correspondiente si alguno de los campos no está relleno y por el contrario el mensaje &quot;Encuesta enviada con éxito&quot; cuando todo vaya bien. En este último caso **se grabará** la información de la encuesta en un **fichero** llamado **Enc\_Deporte.csv** , **Enc\_Comidas.csv** , etc., con la fecha y hora del sistema y todos los datos introducidos en una línea. Cada campo de la línea se separará por &quot;;&quot;.

Se deberá establecer una relación **modal** de aplicación en el que el Stage principal, el menú, abra el Stage de realizar encuestas. Por lo que no se podrá cerrar la aplicación hasta realizadas las encuestas y se cierre la ventana de las encuestas abierta inicialmente.




