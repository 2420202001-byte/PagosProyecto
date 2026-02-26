# Concesionario

Aplicación de ejemplo en Java para gestionar automóviles (GUI Swing).

## Descripción

Proyecto educativo que muestra una pequeña arquitectura MVC con repositorio en memoria, servicios y vistas Swing.

## Requisitos

- Java 17+ (el proyecto usa `maven.compiler.release` en el `pom.xml`; ajustar según tu JDK)
- Maven 3.6+

## Compilar

Desde la raíz del proyecto ejecuta:

```bash
mvn clean package
```

Esto generará el jar en `target/concesionario-1.0-SNAPSHOT.jar`.

## Ejecutar

- Ejecutar la aplicación GUI (clase `App`):

```bash
mvn exec:java -Dexec.mainClass="com.mycompany.concesionario.App"
```

- Ejecutar la clase principal por defecto `Concesionario` (imprime "Hello World!"):

```bash
mvn exec:java -Dexec.mainClass="com.mycompany.concesionario.Concesionario"
```

- Ejecutar desde el jar empaquetado (si añades `Main-Class` al manifest):

```bash
java -jar target/concesionario-1.0-SNAPSHOT.jar
```

O bien ejecutar una clase concreta dentro del jar:

```bash
java -cp target/concesionario-1.0-SNAPSHOT.jar com.mycompany.concesionario.App
```

## Estructura del proyecto

- `src/main/java/com/mycompany/concesionario` - paquete base
  - `controller/` - `AutomovilController`
  - `model/` - modelos (`Automovil`, `AutomovilElectrico`, `AutomovilGasolina`, etc.)
  - `repo/` - repositorio e implementación en memoria
  - `service/` - lógica de negocio (`AutomovilService`)
  - `view/` - interfaces Swing (frames y paneles)
  - `App.java` - entrada para la GUI
  - `Concesionario.java` - clase con `main` de ejemplo

## Tecnologías

- Java
- Maven
- Swing (interfaz gráfica)

## Contribuir

1. Haz un fork del repositorio.
2. Crea una rama feature/bugfix.
3. Abre un pull request explicando los cambios.

## Notas

- El `pom.xml` declara `artifactId: concesionario` y `exec.mainClass` apuntando a `com.mycompany.concesionario.Concesionario`.
- Si quieres que el JAR sea ejecutable directamente, añade la configuración del plugin `maven-jar-plugin` o `maven-assembly-plugin` para incluir `Main-Class` en el manifest.

---

Si quieres, adapto el README para incluir instrucciones para ejecutar tests, un archivo `Makefile` o configurar el JAR ejecutable.

## Uso en Apache NetBeans

- Abrir el proyecto: `File > Open Project` y seleccionar la carpeta raíz del proyecto (la que contiene `pom.xml`). NetBeans detectará el proyecto Maven.
- Seleccionar plataforma Java: botón derecho sobre el proyecto > `Properties` > `Libraries` > `Java Platform` y elige tu JDK (usar Java 17+ recomendado).
- Ajustar el nivel de fuente si hace falta: `Properties` > `Sources` > `Source/Binary Format` (por ejemplo `17`) si NetBeans muestra warnings por la propiedad `maven.compiler.release`.
- Establecer la clase principal para ejecutar desde NetBeans: `Properties` > `Run` > `Main Class` e introducir `com.mycompany.concesionario.App` para iniciar la GUI.
- Ejecutar la aplicación: botón derecho sobre el proyecto > `Run` (o el botón Run Project). Para ejecutar la clase `Concesionario` en su lugar, cambia la `Main Class` a `com.mycompany.concesionario.Concesionario`.
- Construir JAR desde NetBeans: botón derecho > `Clean and Build`. El JAR resultante estará en `target/concesionario-1.0-SNAPSHOT.jar`.

### Notas y solución de problemas en NetBeans

- Si NetBeans muestra error por `maven.compiler.release` (por ejemplo un valor no reconocido), actualiza esa propiedad en `pom.xml` al número de tu JDK (por ejemplo `17`) o ajusta la plataforma del proyecto en `Properties`.
- Si la GUI no aparece al ejecutar, verifica que la `Main Class` está apuntando a `com.mycompany.concesionario.App` y que no haya excepciones en la ventana Output de NetBeans.

