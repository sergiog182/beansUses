Proyecto manejo beans 
=====================

El proyecto mueve los archivos de la carpeta files/incoming a la carpeta files/outgoing

Se manejan los beans como componentes camel, como predicados, utilizando la funcion bean(Class<t>) desde Java DSL y utilizando la funcion Bean("@named") que funciona con CDI 

Para correr el proyecto, desde consola ejecutar:

    mvn celan camel:run

Desde JBoss developer studio, crear un perfil de ejecución:

	clean camel:run