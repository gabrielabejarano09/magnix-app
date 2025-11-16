Feature: Visualización del Historial de Reservas

  Para poder gestionar mis compromisos y consultar mi actividad pasada
  Como jugador que ha iniciado sesión
  Quiero ver una pantalla con mis reservas, separadas en "Activas" y "Anteriores"

  Background:
    Given que soy un jugador que ha iniciado sesión

  Scenario: Jugador con reservas activas y pasadas consulta su historial
    Given tengo una reserva activa para la "Cancha de Pádel #3" para mañana a las 09:00
    And tengo una reserva activa para la "Máquina lanza pelotas" para mañana a las 09:00
    And tuve una reserva para la "Cancha de Tenis #1" que fue ayer
    And tuve una reserva para la "Raqueta de Tenis Pro" que fue la semana pasada

    When navego a la pantalla "Mis Reservas"

    Then en la sección "Reservas Activas", debería ver:
      | Implemento/Espacio         | Fecha    | Hora  |
      | Cancha de Pádel #3         | Mañana   | 09:00 |
      | Máquina lanza pelotas      | Mañana   | 09:00 |
    And en la sección "Historial de Reservas", debería ver:
      | Implemento/Espacio         | Fecha         | Estado    |
      | Cancha de Tenis #1         | Ayer          | Completada|
      | Raqueta de Tenis Pro       | Semana pasada | Completada|

  Scenario: Jugador nuevo sin reservas consulta su historial
    Given no he realizado ninguna reserva todavía

    When navego a la pantalla "Mis Reservas"

    Then la sección "Reservas Activas" debería mostrar un mensaje como "No tienes próximas reservas"
    And la sección "Historial de Reservas" debería mostrar un mensaje como "Tu historial de reservas está vacío"

  Scenario: Jugador con una reserva cancelada consulta su historial
    Given tengo una reserva activa para la "Cancha de Tenis #2" para el próximo sábado
    And tuve una reserva para la "Cesta de pelotas" que fue cancelada por mí

    When navego a la pantalla "Mis Reservas"

    Then en la sección "Reservas Activas" debería ver la reserva de la "Cancha de Tenis #2"
    And en la sección "Historial de Reservas", debería ver la "Cesta de pelotas" con el estado "Cancelada"