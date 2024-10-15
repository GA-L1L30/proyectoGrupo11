### Qué tipo de arquitectura usaron y por qué? ¿Podría mejorarla?

Usamos el tipo de arquitectura MVVM ya que
El paquete de data representa la capa de modelo, en el que se definen los datos como Product y Category. 
Las pantallas que están en el paquete screens (por ejemplo, HomeScreen, SearchScreen) representan la capa de vista. Estas pantallas muestran los datos al usuario e interactúan con el usuario.
Los archivos que terminan con ViewModel actúan como el ViewModel, los cuales manejan la lógica de negocio y prepara los datos para ser consumidos por la vista.

Usamos la arquitectura MVVM porque sirve para separar las responsabilidades y es fácil mantener el código, especialmente cuando hay que interactuar con datos y mostrar interfaces de usuario. 
Se podría mejorarla al implementar casos de uso para reducir la carga del ViewModel, que actuarían como intermediarios entre el ViewModel y el repositorio de datos, usar inyección de dependencias con Hilt para desacoplar mejor las clases y evitar código repetitivo. 

---

### ¿Tuvieron objetos stateful y stateless? ¿Cómo definen la elección de los mismos?

Usamos objetos stateful y stateless. 

Stateful: los ViewModels son stateful porque mantienen el estado de los datos que se muestran a la vista. Si una pantalla muestra una lista de productos, el ViewModel gestiona el estado de esos productos y maneja cualquier actualización de datos.

Stateless: Los botones, por ejemplo, son stateless porque no almacenan ningún estado, sino que, mediante los parámetros definen su apariencia o comportamiento.

Por lo tanto, la elección se basaba en usar stateful si necesitábamos gestionar datos dinámicos, y en usar stateless si el componente solo necesitaba recibir datos y no tiene que gestionar un estado propio.

---

### ¿Qué mejoras detectan que podrían realizarle a la app? ¿Tendrían side effect si escala el volumen de datos? Comenten al menos 2 cuestiones a refactorizar y tener en cuenta

Algunas mejoras que detectamos son: 
- Optimización de la gestión del estado: refactorizar la lógica de negocio por repositorios para hacer los ViewModels más simples en caso de escalar con grandes volúmenes de datos.
- Paginación: Cargar datos de manera incremental en vez de toda la lista a la vez, lo que mejoraría la experiencia del usuario y reduciría el consumo de memoria.
- Optimizar las llamadas a la API: establecer estrategias de caché para mejorar el rendimiento de las llamadas a la API con el fin de reducir el consumo de datos

---

### ¿Qué manejo de errores harían? ¿dónde los contemplan a nivel código? Mencionen la estrategia de mapeo que más se adecúe. 

A nivel código contemplamos el manejo de errores en los archivos ViewModel para capturar excepciones durante la carga de datos. Esto sirve para gestionar errores de red y mapearlos a estados de UI  como por ejemplo, "Loading", "Error with data".
Se podría agregar el manejo de errores mediante notificaciones Toast, que informe al usuario sobre el estado de una acción.


---

### En el caso de uso de persistencia para Favoritos, ¿que estrategia sugieren?. 

Haríamos una lista  (como un MutableList) que tenga los elementos favoritos y que se puede actualizar cada vez que el usuario agrega o quita un elemento.
A gran escala de datos utilizariamos un repositorio que actúe como intermediario entre la vista y la capa de datos. Este repositorio puede obtener los datos de la base de datos, de la red, o de caché.

---

### Si la tendríamos que convertir a Español y conservar el Inglés, qué estrategia utilizaría para extenderla. Y si necesitamos agregar otros idiomas?

Se podría usar archivos de recursos localizados (strings.xml) que almacenan las traducciones para cada idioma y agregar nuevos idiomas creando nuevos archivos strings.xml.

También se podría utilizar Locale para permitir a los usuarios cambiar el idioma desde la app. Y guardar el idioma seleccionado en SharedPreferences para aplicarlo al inicio de la aplicación.
