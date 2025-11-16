Feature: Visualización de la Pantalla de Reservas

  Para poder alquilar el equipamiento y las instalaciones del club
  Como jugador que ha iniciado sesión
  Quiero ver los espacios físicos e implementos disponibles para reservar

  Scenario: Visualización de la pantalla principal de Reservas
    Given que soy un jugador que ha iniciado sesión
    And la "Cancha de Tenis #1" está disponible para reservar
    And la "Raqueta de Tenis" está disponible para reservar

    When navego a la pantalla "Reservas"

    Then en la sección "Espacios Físicos" debería ver "Cancha de Tenis #1"
    And en la sección "Implementos" debería ver "Raqueta de Tenis Pro"

  Scenario: Navegación a la vista de detalle de un implemento
    Given que estoy en la pantalla "Reservas"
    And la "Raqueta de Tenis" está visible en la sección "Implementos"

    When hago clic en la "Raqueta de Tenis Pro"

    Then debería ser redirigido a la pantalla de detalle del implemento
    And debería ver una imagen grande de la "Raqueta de Tenis Pro"
    And debería ver la descripción: "Raqueta de alto rendimiento para jugadores avanzados."
    And debería ver un calendario y un selector de hora para hacer la reserva