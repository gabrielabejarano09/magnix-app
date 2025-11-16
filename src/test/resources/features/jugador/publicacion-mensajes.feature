Feature: Publicación de Mensajes en la Comunidad

  Para poder compartir información, hacer preguntas o comunicarme con otros
  Como jugador que ha iniciado sesión
  Quiero poder escribir y publicar mis propios mensajes en el muro de la comunidad

  Background:
    Given que soy un jugador que ha iniciado sesión
    And estoy en la pestaña "Comunidad"

  Scenario: Jugador publica un nuevo mensaje exitosamente
    When introduzco el texto "¿Alguien para jugar un partido de pádel hoy a las 18:00?" en el campo de nuevo mensaje
    And presiono el botón "Publicar"

    Then debería ver mi mensaje "¿Alguien para jugar un partido de pádel hoy a las 18:00?" al principio de la lista de mensajes
    And el campo de texto para un nuevo mensaje debería estar vacío

  Scenario: Intento de publicar un mensaje vacío
    When dejo el campo de nuevo mensaje vacío
    And presiono el botón "Publicar"

    Then debería ver un mensaje de error que dice "El mensaje no puede estar vacío"
    And no debería aparecer un nuevo mensaje en blanco en el muro

  Scenario: Intento de publicar un mensaje que excede el límite de caracteres
    Given el límite de caracteres para un mensaje es de 280
    When introduzco un texto con más de 280 caracteres en el campo de nuevo mensaje

    Then el botón "Publicar" debería estar deshabilitado
    And debería ver un indicador visual de que he excedido el límite (ej. contador en rojo)