# AndroidTestMovies


## Capas de la Aplicación

* La aplicación usa una arquitectura MVP donde se segmentan las vistas, Presenters y la capa de Modelo.
No todas las clases correspondientes a una capa se encuentran en un mismo paquete es decir: no todas las clases correspondientes 
a la capa de datos esta en un paquete *.data.

* Dentro de la capa de Datos podemos destacar todos los Interactors como lo son: CategoryInteractor, FilmListInteractor, DetailFilmInteractor. Tambien encontramos dentro de la capa de datos una subcapa llamada db que es donde se encuentra el orm (helper class) y las entitidades de base de datos.
* La capa de Vista encontramos todos los Fragments, Activities y Adapters.
* Los presenters (ubicados en el paquete Features) son: CategoryPresenter, FilmListPresenter y DetailFilmPresenter; Estos son los responsables de interactura entre la vista y la capa de datos, obtienen las acciones del usuario y le notifican a la vista que debe hacer despues de procesar los datos.
* La capa de Red (network) encontramos los servicios y DTOS



## PREGUNTAS

### 1. En qué consiste el principio de responsabilidad única? Cuál es su propósito?

R:// (SRP) Este principio nos indica que cada clase debe tener una sola razón para existir, es decir debe encargarse de responsabilidades especificas de cada módulo de acuerdo al objectivo de su creación. SRP nos permite tener un proyecto mas desacoplado.

### 2. Qué características tiene, según su opinión, un “buen” código o código limpio?

R:// 
* Debe ser escrito en prosa.
* Tener pocos o ceros comentarios en los metodos porque los mismos deben ser descriptivos.
* Debe ser desacoplado.
* Cumplir con los principios SOLID.
* Ser Testeables.
* No debe tener codigo redundante y duplicado.
* Fácil de mantener.
