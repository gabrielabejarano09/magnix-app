Feature: Detalle e Inscripción a un Torneo por Equipos

  Para poder competir con otros jugadores
  Como jugador interesado en un torneo por equipos
  Quiero ver sus detalles y poder inscribirme creando un grupo o uniéndome a uno aleatorio

  Scenario: Jugador crea un nuevo equipo e inscribe a sus miembros
    Given que soy un jugador que ha iniciado sesión con el ID "usr_001"
    And he navegado a la pantalla de detalles del "Torneo de Voleibol por Equipos"
    And los jugadores con IDs "usr_002", "usr_003" y "usr_004" están registrados y disponibles

    When presiono el botón "Crear e Inscribir Equipo"
    And introduzco "Los Invencibles" en el campo "Nombre del Equipo"
    And introduzco los IDs "usr_002", "usr_003", "usr_004" en el campo de "Miembros del Equipo"
    And presiono "Confirmar Inscripción"

    Then debería ver un mensaje de confirmación de la inscripción del equipo "Los Invencibles"
    And el torneo debería aparecer en mi sección de "Mis Torneos"

  Scenario: Jugador se inscribe a un torneo por equipos y se une a un grupo aleatorio
    Given que soy un jugador que ha iniciado sesión y no tengo equipo para este torneo
    And he navegado a la pantalla de detalles del "Torneo de Voleibol por Equipos"
    And otros jugadores ya se han inscrito de forma individual buscando equipo

    When presiono el botón "Unirme a un equipo aleatorio"
    
    Then debería ver un mensaje confirmando que he sido añadido a una lista de espera o a un equipo incompleto
    And debería recibir una notificación cuando mi equipo esté completo y formalmente inscrito

  Scenario: Intento de inscripción de un equipo con un ID de jugador inválido
    Given que soy un jugador que ha iniciado sesión
    And he navegado a la pantalla de detalles del "Torneo de Voleibol por Equipos"

    When presiono el botón "Crear e Inscribir Equipo"
    And introduzco "Equipo Fantasma" en el campo "Nombre del Equipo"
    And introduzco los IDs "usr_002" y "id_inexistente" en el campo de "Miembros del Equipo"
    And presiono "Confirmar Inscripción"

    Then debería ver un mensaje de error que dice "El jugador con ID 'id_inexistente' no existe"
    And el equipo "Equipo Fantasma" no debería haber sido inscrito en el torneo