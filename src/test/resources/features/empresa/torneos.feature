Feature: Gestión de Torneos por la Empresa

  Para poder ofrecer y administrar eventos competitivos
  Como administrador de la empresa
  Quiero poder crear, ver, modificar y eliminar torneos

  Background:
    Given que he iniciado sesión como administrador de la empresa

  Scenario: Creación de un nuevo torneo
    When navego a la pestaña "Torneos"
    And presiono el botón "Crear Nuevo Torneo"
    And relleno el formulario con los siguientes datos:
      | Campo           | Valor                           |
      | Nombre          | Torneo de Apertura 2026         |
      | Descripción     | El primer gran torneo del año.  |
      | Fecha de Inicio | 2026-02-01                      |
      | Fecha de Fin    | 2026-02-15                      |
      | Tipo            | Individual                      |
    And presiono "Guardar Torneo"
    Then el "Torneo de Apertura 2026" debería aparecer en mi lista de torneos

  Scenario: Modificación de los detalles de un torneo existente
    Given que existe el "Torneo de Apertura 2026"
    When navego a la pestaña "Torneos"
    And hago clic en "Editar" para el "Torneo de Apertura 2026"
    And cambio la "Fecha de Fin" a "2026-02-20"
    And presiono "Guardar Cambios"
    Then la fecha de fin del "Torneo de Apertura 2026" en la lista debería ser "2026-02-20"

  Scenario: Eliminación de un torneo
    Given que existe el "Torneo de Invierno" sin inscritos
    When navego a la pestaña "Torneos"
    And presiono "Eliminar" para el "Torneo de Invierno"
    And confirmo la eliminación
    Then el "Torneo de Invierno" ya no debería aparecer en la lista de torneos

  Scenario: Visualización de los inscritos a un torneo
    Given que existe el "Torneo de Apertura 2026"
    And los jugadores "Carlos" y "Ana" se han inscrito
    When navego a la pestaña "Torneos"
    And hago clic en "Ver Inscritos" para el "Torneo de Apertura 2026"
    Then debería ver una lista que contiene a "Carlos" y "Ana"