Feature: Gestión y Moderación de la Comunidad por la Empresa

  Para mantener una comunicación fluida, promocionar eventos y asegurar un ambiente respetuoso
  Como administrador de la empresa
  Quiero poder ver, publicar, fijar y eliminar mensajes en la pestaña de Comunidad

  Background:
    Given que he iniciado sesión como administrador de la empresa
    And estoy en la pestaña "Comunidad"

  Scenario: Empresa visualiza la actividad de la comunidad
    Given el jugador "Juan Pérez" ha publicado: "¿Alguien para un partido mañana a las 10?"
    And la jugadora "Isabel Solano" ha publicado: "¡Busco equipo para el torneo de pádel!"
    When la página de la Comunidad se carga
    Then debería ver el mensaje de "Juan Pérez"
    And debería ver el mensaje de "Isabel Solano" en el muro

  Scenario: Empresa publica un anuncio exitosamente
    When introduzco el texto "¡Recuerden que las inscripciones para el Torneo de Apertura cierran este viernes!" en el campo de nuevo mensaje
    And presiono el botón "Publicar"
    Then mi mensaje debería aparecer al principio del muro
    And el mensaje debería estar claramente identificado como una publicación de la empresa (ej. con un color de fondo diferente o una etiqueta de "Anuncio")
    And el campo de texto para un nuevo mensaje debería quedar vacío

  Scenario: Empresa fija un anuncio importante en la parte superior del muro
    Given he publicado un anuncio que dice "Mantenimiento de canchas este sábado. Cierre de 14:00 a 16:00."
    When hago clic en la opción "Fijar Mensaje" en mi anuncio
    Then el anuncio "Mantenimiento de canchas este sábado..." debería permanecer en la parte superior del muro, incluso si se publican nuevos mensajes
    And el mensaje debería mostrar un ícono o etiqueta de "Fijado"

  Scenario: Empresa elimina un mensaje inapropiado de un jugador
    Given el jugador "UsuarioMolesto" ha publicado un mensaje con contenido ofensivo: "Este es un mensaje con spam y lenguaje inapropiado."
    When encuentro el mensaje de "UsuarioMolesto"
    And hago clic en la opción "Eliminar Mensaje"
    And confirmo la acción
    Then el mensaje "Este es un mensaje con spam y lenguaje inapropiado." ya no debería ser visible en el muro de la comunidad

  Scenario: Intento de publicar un anuncio vacío
    When dejo el campo de nuevo mensaje vacío
    And presiono el botón "Publicar"
    Then debería ver un mensaje de error que dice "El mensaje no puede estar vacío"
    And no se debería haber publicado ningún mensaje nuevo