Feature: Gestión de Espacios Físicos por la Empresa

  Para poder administrar los activos que los jugadores pueden reservar
  Como administrador de la empresa
  Quiero poder añadir, modificar y eliminar espacios físicos

  Background:
    Given que he iniciado sesión como administrador de la empresa

  Scenario: Creación de un nuevo espacio físico
    When navego a la pestaña "Espacios Físicos"
    And presiono el botón "Añadir Espacio"
    And relleno el formulario con los datos:
      | Campo       | Valor                   |
      | Nombre      | Cancha de Pádel Central |
      | Tipo        | Pádel                   |
      | Descripción | Nuestra mejor cancha.   |
    And presiono "Guardar Espacio"
    Then "Cancha de Pádel Central" debería aparecer en la lista de espacios físicos

  Scenario: Intento de eliminar un espacio con reservas activas
    Given que la "Cancha de Tenis #1" tiene una reserva para mañana
    When navego a la pestaña "Espacios Físicos"
    And intento eliminar la "Cancha de Tenis #1"
    Then debería ver un mensaje de error que dice "No se puede eliminar un espacio con reservas futuras"
    And la "Cancha de Tenis #1" debería permanecer en la lista