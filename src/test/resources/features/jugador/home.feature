Feature: Panel Principal del Jugador (Home)

  Para tener un resumen rápido de mi actividad y de las novedades
  Como jugador que ha iniciado sesión
  Quiero ver la información más relevante en mi pantalla de Home

  Scenario: Visualización del Home para un jugador con actividad reciente
   
    Given que soy un jugador que ha iniciado sesión
    And estoy inscrito en el "Torneo de Tenis"
    And tengo una reserva para la "Cancha de Tenis #1" para mañana a las 10:00
    
    When navego a la pestaña "Home"

    Then en la sección "Mis Próximos Eventos", debería ver mi inscripción al "Torneo de Tenis 2025"
    And en la sección "Mis Próximas Reservas", debería ver la reserva de la "Cancha de Tenis #1"

  Scenario: Visualización del Home para un jugador nuevo sin actividad
    Given que soy un jugador nuevo que acaba de registrarse y ha iniciado sesión
    And no estoy inscrito en ningún torneo
    And no tengo ninguna reserva

    When navego a la pestaña "Home"

    Then la sección "Mis Próximos Eventos" debería mostrar un mensaje como "Aún no te has inscrito a ningún torneo"
    And la sección "Mis Próximas Reservas" debería estar vacía o mostrar un mensaje similar
    And la sección "Torneos Abiertos" debería mostrar una lista de torneos disponibles en la plataforma