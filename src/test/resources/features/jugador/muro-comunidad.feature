Feature: Visualización del Muro de la Comunidad

  Para estar al día de las conversaciones e interactuar con otros deportistas
  Como jugador que ha iniciado sesión
  Quiero ver un muro con los mensajes publicados por la comunidad

  Background:
    Given que soy un jugador que ha iniciado sesión

  Scenario: Visualización del muro con mensajes existentes
    Given otros deportistas han publicado mensajes
    And "Ana" publicó "¡Gran torneo el fin de semana! Felicitaciones a los ganadores."
    And "Carlos" publicó "¿Alguien sabe si mañana abren la cancha #1 a primera hora?"

    When navego a la pestaña "Comunidad"

    Then debería ver los mensajes en orden cronológico inverso (el más nuevo primero)
    And el primer mensaje en la lista debería ser de "Ana" con el texto "¡Gran torneo el fin de semana! Felicitaciones a los ganadores."
    And también debería ver el mensaje de "Carlos"

  Scenario: Visualización del muro cuando no hay mensajes
    Given no se ha publicado ningún mensaje en la comunidad

    When navego a la pestaña "Comunidad"

    Then debería ver un mensaje como "¡Sé el primero en publicar! El muro de la comunidad está vacío."
    And debería ver un campo de texto para escribir un nuevo mensaje