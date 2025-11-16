Feature: Reserva de un Implemento Deportivo

  Para asegurar la disponibilidad del equipamiento que necesito para jugar
  Como jugador
  Quiero poder reservar un implemento para una fecha y hora específicas

  Scenario: Reserva exitosa de un implemento en un horario disponible
    Given que soy un jugador que ha iniciado sesión
    And estoy en la pantalla de detalle de la "Raqueta de Tenis"
    And la raqueta está disponible para mañana a las 15:00

    When selecciono la fecha de mañana y la hora "15:00"
    And presiono el botón "Confirmar Reserva"

    Then debería ver un mensaje de "Reserva realizada con éxito"
    And la "Raqueta de Tenis Pro" para mañana a las 15:00 debería aparecer en mi sección de "Mis Próximas Reservas"

  Scenario: Intento de reserva de un implemento en un horario ya ocupado
    Given que soy un jugador que ha iniciado sesión
    And estoy en la pantalla de detalle de la "Raqueta de Tenis"
    And otro jugador ya ha reservado la raqueta para mañana a las 10:00

    When selecciono la fecha de mañana y la hora "10:00"
    And presiono el botón "Confirmar Reserva"

    Then debería ver un mensaje de error que dice "Este horario ya no está disponible. Por favor, elige otro."
    And no debería tener una nueva reserva para esa raqueta

  Scenario: Intento de reserva de un implemento en una fecha pasada
    Given que soy un jugador en la pantalla de detalle de la "Raqueta de Tenis Pro"

    When selecciono la fecha de ayer y la hora "14:00"

    Then el botón "Confirmar Reserva" debería estar deshabilitado
    And podría ver un mensaje de ayuda que dice "No puedes realizar reservas en fechas pasadas"

  Scenario: Intento de reserva de un implemento que entra en conflicto con otra reserva mía
    Given que soy un jugador que ha iniciado sesión
    And estoy en la pantalla de detalle de la "Canasta de Balones de Tenis"
    And ya tengo una reserva para la "Cancha de Tenis #2" para mañana de 11:00 a 12:00

    When selecciono la fecha de mañana y la hora "11:00" para la "Canasta de Balones de Tenis"
    And presiono el botón "Confirmar Reserva"

    Then debería ver un mensaje de error que dice "Ya tienes otra reserva a esta misma hora."