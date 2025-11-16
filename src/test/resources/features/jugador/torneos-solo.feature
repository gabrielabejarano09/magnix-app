Feature: Detalle e Inscripción a un Torneo Individual

  Para poder competir individualmente
  Como jugador interesado en un torneo
  Quiero ver sus detalles y poder inscribirme

  Scenario: Jugador visualiza los detalles de un torneo individual y se inscribe
    Given que soy un jugador que ha iniciado sesión
    And he navegado a la pantalla "Torneos"
    And el "Torneo de Tenis Individual 2025" está en la lista de "Torneos Abiertos"
    
    When hago clic en el "Torneo de Tenis Individual 2025"

    Then debería ser redirigido a la pantalla de detalles del torneo
    And debería ver la descripción del "Torneo de Tenis Individual 2025"
    And debería ver que hay "15 de 32" jugadores inscritos
    And debería ver que la fecha de inicio es "01/03/2026" y la fecha de fin es "15/03/2026"
    And debería ver un botón con el texto "Inscribirme"

    When presiono el botón "Inscribirme"

    Then debería ver un mensaje de confirmación de inscripción exitosa
    And el botón "Inscribirme" debería cambiar a "Anular Inscripción" o desaparecer