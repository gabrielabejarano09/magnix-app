Feature: Autenticación de Usuarios

  Para poder acceder a las funcionalidades personalizadas de la aplicación
  Como usuario registrado
  Quiero poder iniciar sesión con mi correo y contraseña

  Scenario: Inicio de sesión exitoso como Jugador
    Given que soy un jugador registrado con el correo "jugador1@test.com" y contraseña "Magnix123"
    When introduzco mis credenciales en la página de login y presiono "Ingresar"
    Then debería ser redirigido a la pantalla de "Home" del jugador
    And debería ver las pestañas: "Home", "Torneos", "Espacios/Implementos", "Reservas" y "Comunidad"

  Scenario: Inicio de sesión exitoso como Empresa
    Given que soy una empresa registrada con el correo "empresa@test.com" y contraseña "Magnix123"
    When introduzco mis credenciales en la página de login y presiono "Ingresar"
    Then debería ser redirigido al panel de administración de la empresa
    And no debería ver las pestañas de navegación del jugador

  Scenario: Intento de inicio de sesión con contraseña incorrecta
    Given que soy un jugador registrado con el correo "jugador1@test.com"
    When introduzco el correo "jugador1@test.com" y la contraseña incorrecta "password_malo"
    Then debería ver un mensaje de error que dice "Correo o contraseña incorrectos"
    And debería permanecer en la página de login