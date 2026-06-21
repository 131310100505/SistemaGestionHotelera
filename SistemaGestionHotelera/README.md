Sistema de Gestión Hotelera

Integrantes del grupo:
- Tomás Delia
- Tomás Szkarlatiuk
- German Rocha

Descripción del sistema:
Sistema de Gestión Hotelera desarrollado en Java que permite administrar huéspedes, habitaciones, reservas, pagos y servicios adicionales.
El sistema cuenta con una interfaz gráfica (Swing) y una versión de demostración por consola. El proyecto fue diseñado aplicando patrones de diseño, principios SOLID y GRASP para lograr una arquitectura modular, extensible y de bajo acoplamiento.

Instrucciones para ejecutar el proyecto:

1. Clonar el repositorio:

   https://github.com/131310100505/SistemaGestionHotelera.git

2. Abrir el proyecto en IntelliJ IDEA

3. Ejecutar alguna de las siguientes clases:

   SistemaGestionHotelera.main.Main
   o
   SistemaGestionHotelera.ui.SistemaHotelGUI
4. Al ejecutar SistemaHotelGUI se mostrará un menú inicial que permite ingresar como:
    - Administrador
    - Huésped
Cada rol dispone de funcionalidades específicas para la gestión de reservas.

Patrones aplicados:

Facade
Centraliza el acceso a los subsistemas mediante la clase `SistemaHotelFacade`.

Singleton
Garantiza una única instancia de los componentes compartidos.

Factory Method
Permite crear distintos tipos de habitaciones sin acoplar el código cliente a implementaciones concretas.

Strategy
Implementa diferentes estrategias de descuento aplicables a las reservas.

Decorator
Permite agregar servicios adicionales a una reserva de forma dinámica.

State
Modela los distintos estados de una reserva durante su ciclo de vida.

Principios SOLID aplicados:

SRP (Single Responsibility Principle)
Cada clase posee una única responsabilidad.

OCP (Open/Closed Principle)
El sistema puede extenderse mediante nuevos descuentos, servicios o habitaciones sin modificar código existente.

LSP (Liskov Substitution Principle)
Las implementaciones concretas pueden reemplazar a sus abstracciones.

ISP (Interface Segregation Principle)
Las interfaces contienen únicamente los métodos necesarios.

DIP (Dependency Inversion Principle)
Las clases dependen de abstracciones y no de implementaciones concretas.

Patrones GRASP aplicados:

Controller
Los controladores gestionan los casos de uso del sistema.

Creator
Las entidades responsables crean los objetos que administran.

Information Expert
Cada objeto maneja la información que le corresponde.

Low Coupling
Se minimiza el acoplamiento entre componentes.

High Cohesion
Cada clase concentra responsabilidades relacionadas.

Distribución de tareas:

German Rocha
- Diseño UML
- Implementación de patrones de diseño

Tomas Szkarlatiuk
- Desarrollo de controladores y repositorios

Tomas Delia
- Implementación de la interfaz gráfica
- Documentación y pruebas


Repositorio:

https://github.com/131310100505/SistemaGestionHotelera