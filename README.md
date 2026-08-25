Análisis del Dominio.
1.	Elementos del Dominio
Elemento / Clase candidata	Responsabilidad	Información Relevante
	Reglas / Colaboraciones
Usuario	Cualquier persona que usa el sistema	idUsuario, nombre, correo, estado, password	Clase base de Estudiante, Tutor. Debe autenticarse para ingresar al sistema
Estudiante	Revisar, solicitar, cancelar tutorías	Carrera, semestre, modalidad	Hereda de usuario. Puede revisar, realizar y cancelar tutorías.
Tutor	Publicar horarios, atender tutorías	Especialidad, facultad, disponibilidad	Hereda de usuario. Recibe solicitudes y confirmaciones
Horario	Representa una hora disponible para tutoría 	Fecha, hora, estado	Un horario solo puede estar asociado a una reserva activa
Reserva	Gestión de la sesión entre estudiante y tutor.	idReserva, fechaCreacion, estado	Relaciona Estudiante, Tutor y Horario. Debe validar disponibilidad antes de crearse.
Tutoría	Representa la sesión académica programada o ejecutada.	idTutoria, fecha, tema, estado	Se genera a partir de una reserva confirmada. 
Notificación	Informar eventos relevantes a estudiantes y tutores	Mensaje, fechaEnvio, tipo	Se genera cuando se crea o cancela una reserva. 
ServicioReservas	Proceso de reserva y cancelación	Reglas de negocio de reservas	Colabora con Horario, Reserva y Notificación
RepositorioReservas	Almacenar y recuperar datos de reservas	Colección de reservas	Separa la tecnología de almacenamiento del resto del sistema
HistorialTutorias	Mantener registro de tutorías realizadas	Tutorías completadas, fechas, estudiante, tutor	Permite consultas

2.	Relaciones
                Usuario
                   │
        ┌──────────┴──────────┐
        │                     │
   Estudiante             Tutor
        │                     │
        └────── Reserva ──────┘
                   │
               	Horario
                   │
                Tutoría
                   │
          HistorialTutorías

Reserva ───────► Notificación

ServicioReservas
      │
      ├── Reserva
      ├── Horario
      ├── Notificación
      └── RepositorioReservas

3.	Reglas de negocio
R1: Un estudiante debe estar registrado para reservar una tutoría
R2: Un tutor únicamente puede publicar horarios disponibles de su agenda
R3: Un horario no puede reservarse por más de un estudiante al mismo tiempo
R4: La disponibilidad se valida antes de crear una reserva
R5: La cancelación de reserva libera automáticamente el horario
R6: Cada reserva genera notificación para tutor y estudiante
R7: Una tutoría realizada debe almacenarse en el historial

4.	Diseño orientado a objetos

A partir del análisis de dominio realizado, se definen las clases principales identificando sus responsabilidades, información encapsulada y colaboraciones. Se debe mantener alta cohesión, bajo acoplamiento, aplicar encapsulación, utilizar herencia solo cuando exista una relación “es-un” válida y se preferirá composición para colaborar entre objetos.

Clase	Responsabilidad	Atributos	Comportamientos
Usuario	Información común de los usuarios del sistema	idUsuario, nombre, correo, password, estado	iniciarSesion(),
actualizarPerfil()
Estudiante	Gestionar las acciones relacionadas con un estudiante	Carrera	solicitarReserva(),
cancelarReserva()
Tutor	Gestionar horarios y tutorías asignadas	Especialidad, Facultad	publicarHorario(),
consultarTutorias()
Horario	Espacio de tiempo disponible para tutorías	Fecha, horaInicio, horaFin, disponible	marcarDisponible(),
marcarReservado(),
validarDisponibilidad()
Reserva	Mantener la información de una reserva realizada	idReserva, fechaCreacion, estado	Confirmar(),
Cancelar(),
ObtenerEstado()
Tutoría	Representa la sesión académica programada o realizada	Fecha, tema, observaciones, estado	Iniciar(),
Finalizar(),
registrarObservaciones()
Notificación	Gestionar mensajes informativos de eventos del sistema	idNotificacion, mensaje, fechaEnvio, tipo	generarMensaje()
ServicioReservas	Coordinar proceso de reservas y cancelaciones	Reglas de negocio	crearReserva(),
cancelarReserva(),
validarDisponibilidad()
ServicioNotificacion	Gestionar el envío de notificaciones	Configuración de envío	enviarConfirmacion(),
enviarCancelacion()
RepositorioReservas	Guardar  recuperar información de reservas	Colección de reservas	Guardar(),
Buscar(),
Eliminar()
HistorialTutorias	Mantener el registro de ejecución de tutorías	listaTutorias	registroTutoria(),
consultarHistorial()

Información Encapsulada

Usuario:
-	password
-	Estado de la cuenta

Horario:
-	Disponibilidad
-	Fecha y Hora asignada

Reserva:
-	Estado de la reserva
-	idReserva

Tutoría:
-	Observaciones
-	Estado de realización

HistorialTutorías
-	Registros académicos almacenados

Con esto los atributos se modifican mediante métodos controlados y no desde otras clases.

Operaciones que protegen las reglas del objeto.
Horario
Debe controlar:
-	Reservar un horario ya ocupado.
-	Modificar horarios reservados.
Métodos:
-	validarDisponibilidad()
-	marcarReservado()

Reserva
Debe controlar:
-	No confirmar reservas duplicadas
-	No cancelar reservas finalizadas
Métodos:
-	Confirmar()
-	Cancelar()
Tutoría
Debe controlar:
-	No marcar como realizada una tutoría inexistente
-	No registrar observaciones antes de iniciar la sesión
Métodos:
-	Iniciar()
-	Finalizar()

Composición:
	Reserva – tiene un horario
	Tutoría – tiene una reserva
	ServicioReservas – utiliza ServicioNotificaciones

5. Principios SOLID

Aplicamos SRP. 

- Reserva: administra el estado y los datos de una reserva.
- ServicioReservas: coordina el proceso de creación y cancelación de reservas.
- ServicioNotificaciones: envía confirmaciones y avisos.
- RepositorioReservas: almacena y recupera información.

Con esto evitamos el problema de la clase monolítica. Gracias a esta separación, si el establecimiento educativo cambia el mecanismo de notificación o modifica las reglas de reserva, solo será necesario actualizar la clase responsable de esa funcionalidad sin afectar el resto del sistema. 

Aplicamos DIP.

En el componente ServicioReservas no depende directamente de tecnologías concretas, sino de interfaces o contratos: IRepositorioReservas, INotificador. Con esto la lógica del negocio permanece estable mientras las implementaciones pueden cambiar. 

Usando SRP y DIP permiten diseñar un sistema de alta cohesión, bajo acoplamiento, mejor testabilidad y facilidad para incorporar cambios futuros. 

6. Diagrama UML

@startuml

'=====================
' Clases base
'=====================

abstract class Usuario {
    -idUsuario: String
    -nombre: String
    -correo: String
    -password: String
    -estado: Boolean

    +iniciarSesion()
    +actualizarPerfil()
}

class Estudiante {
    -matricula: String
    -carrera: String
    -semestre: Integer

    +solicitarReserva()
    +cancelarReserva()
}

class Tutor {
    -especialidad: String
    -facultad: String

    +publicarHorario()
    +consultarTutorias()
}


Usuario <|-- Estudiante
Usuario <|-- Tutor

'=====================
' Entidades del dominio
'=====================

class Horario {
    -fecha: Date
    -horaInicio: Time
    -horaFin: Time
    -disponible: Boolean

    +validarDisponibilidad()
    +marcarReservado()
    +marcarDisponible()
}

class Reserva {
    -idReserva: String
    -fechaCreacion: Date
    -estado: String

    +confirmar()
    +cancelar()
    +obtenerEstado()
}

class Tutoria {
    -fecha: Date
    -tema: String
    -observaciones: String
    -estado: String

    +iniciar()
    +finalizar()
    +registrarObservaciones()
}

class Notificacion {
    -idNotificacion: String
    -mensaje: String
    -fechaEnvio: Date
    -tipo: String

    +generarMensaje()
}

class HistorialTutorias {
    -tutorias: List

    +registrarTutoria()
    +consultarHistorial()
}

'=====================
' Interfaces
'=====================

interface IRepositorioReservas {
    +guardar(reserva: Reserva)
    +buscar(id: String)
    +eliminar(id: String)
}

interface INotificador {
    +enviarConfirmacion(reserva: Reserva)
    +enviarCancelacion(reserva: Reserva)
}


'=====================
' Servicios
'=====================

class ServicioReservas {
    +crearReserva()
    +cancelarReserva()
    +validarDisponibilidad()
}

class ServicioNotificaciones {
    +enviarConfirmacion()
    +enviarCancelacion()
}

'=====================
' Persistencia
'=====================

class RepositorioReservas {
    +guardar()
    +buscar()
    +eliminar()
}

RepositorioReservas ..|> IRepositorioReservas

'=====================
' Notificaciones
'=====================

class NotificadorEmail {
    +enviarConfirmacion()
    +enviarCancelacion()
}

NotificadorEmail ..|> INotificador

'=====================
' Relaciones
'=====================

Estudiante "1" --> "0..*" Reserva : realiza
Tutor "1" --> "0..*" Horario : publica

Reserva "1" --> "1" Horario
Reserva "1" --> "1" Estudiante
Reserva "1" --> "1" Tutor

Tutoria "1" --> "1" Reserva
HistorialTutorias "1" o-- "0..*" Tutoria

ServicioReservas ..> IRepositorioReservas
ServicioReservas ..> INotificador

ServicioReservas ..> Reserva
ServicioReservas ..> Horario

ServicioNotificaciones ..> INotificador
Notificacion ..> Reserva

@enduml
