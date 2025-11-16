Feature: Visualización de la Pantalla de Torneos

  Para poder participar en competencias
  Como jugador que ha iniciado sesión
  Quiero ver una lista de los torneos abiertos y los torneos en los que estoy inscrito

  Scenario: Jugador inscrito en torneos visualiza la pantalla de Torneos
    Given que soy un jugador que ha iniciado sesión
    And estoy inscrito en el "Torneo de Verano 2025"
    And el "Torneo de Otoño 2025" y el "Torneo Relámpago" están abiertos para inscripción

    When navego a la pestaña "Torneos"

    Then en la sección "Mis Torneos" debería ver "Torneo de Verano 2025"
    And en la sección "Torneos Abiertos" debería ver el "Torneo de Otoño 2025" y el "Torneo Relámpago"

  Scenario: Jugador nuevo sin torneos visualiza la pantalla de Torneos
    Given que soy un jugador que ha iniciado sesión y no estoy inscrito en ningún torneo
    And existen los torneos abiertos "Torneo de Pádel para Principiantes" y "Torneo de Tenis Avanzado"

    When navego a la pestaña "Torneos"

    Then la sección "Mis Torneos" debería mostrar un mensaje como "Aún no te has inscrito a ningún torneo"
    And la sección "Torneos Abiertos" debería mostrar el "Torneo de Pádel para Principiantes" y el "Torneo de Tenis Avanzado"