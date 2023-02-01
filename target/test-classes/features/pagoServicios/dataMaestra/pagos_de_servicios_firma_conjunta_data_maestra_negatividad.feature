@pago_servicios_data_maestra_firma_conjunta_negatividad
Feature: Firma Conjunta - Data Maestra - Negatividad

  # FIRMA CONJUNTA - USUARIO 1 Y USUARIO 2

  @unhappy_path @pagoServicio @ESC1FIRMACONJUNTADATAMAESTRANEGATIVIDAD
  Scenario Outline: Valida que se pueda rechazar un pago si el monto es mayor que el saldo disponible
    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente - Firma Conjunta - Data Maestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante2>
    And validos los Saldos y Movimientos

    Examples:
      | ambiente | firmante1 | password    | tipoCuenta        | cuentaOrigen   | empresa | servicio        | codigoDeudor | monto | descripcion | credencialesOK |
      | DEV      | AUTO11    | Interbank1* | Ahorros Soles     | 100-7005769276 | PERUSAT | PREPAGO - SOLES | 10304034527  | 60000 |             | SI             |


  @unhappy_path @pagoServicio @ESC2FIRMACONJUNTADATAMAESTRANEGATIVIDAD
  Scenario Outline: Valida que se pueda rechazar un pago si las credenciales son incorrectas
    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente - Firma Conjunta - Data Maestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante2>
    And validos los Saldos y Movimientos

    Examples:
      | ambiente | firmante1 | password    | tipoCuenta    | cuentaOrigen   | empresa        | servicio | codigoDeudor | monto | descripcion | credencialesOK |
      | DEV      | AUTO11    | Interbank11 | Ahorros Soles | 100-7005769276 | CASUARINAS SAC | PAGOS    | 10304034527  | 850   | DESCP       | SI             |


  @unhappy_path @pagoServicio @ESC3FIRMACONJUNTADATAMAESTRANEGATIVIDAD
  Scenario Outline: Valida que se pueda rechazar un pago si las credenciales son incorrectas
    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente - Firma Conjunta - Data Maestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante2>
    And validos los Saldos y Movimientos

    Examples:
      | ambiente | firmante1 | password    | tipoCuenta        | cuentaOrigen   | empresa | servicio        | codigoDeudor | monto | descripcion | firmante2 | credencialesOK |
      | DEV      | AUTO11    | Interbank1* | Corriente Soles   | 100-7001101389 | PERUSAT | PREPAGO - SOLES | 10440873800  | 850   | DESCP1      | AUTO12    | SI             |


  @unhappy_path @pagoServicio @ESC4FIRMACONJUNTADATAMAESTRANEGATIVIDAD
  Scenario Outline: Valida que se pueda rechazar un pago si el saldo es insuficiente
    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente - Firma Conjunta - Data Maestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante2>
    And validos los Saldos y Movimientos

    Examples:
      | ambiente | firmante1 | password    | tipoCuenta        | cuentaOrigen   | empresa | servicio        | codigoDeudor | monto   | descripcion | firmante2 | credencialesOK |
      | DEV      | AUTO11    | Interbank1* | Ahorros Soles     | 100-7005769276 | PERUSAT | PREPAGO - SOLES | 10304034527  | 9999000 |             | AUTO12    | SI             |


  # FIRMA CONJUNTA - USUARIO 3, USUARIO 4 Y USUARIO 5


  @unhappy_path @pagoServicio @ESC5FIRMACONJUNTADATAMAESTRANEGATIVIDAD
  Scenario Outline: Valida que se pueda rechazar un pago si las credenciales del Firmante 3 son incorrectas
    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza de manera exitosa <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante2>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante3>
    And validamos el Saldo y Movimiento


    Examples:
      | ambiente | firmante1 | password    | tipoCuenta        | cuentaOrigen   | empresa | servicio        | codigoDeudor | monto | descripcion | firmante2 | firmante3 | credencialesOK |
      | DEV      | AUTO13    | Interbank1* | Corriente Dólares | 100-7001101400 | PERUSAT | PREPAGO - SOLES | 201077       | 8000  |             | AUTO14    |  AUTO15   | SI             |



  @unhappy_path @pagoServicio @ESC6FIRMACONJUNTADATAMAESTRANEGATIVIDAD
  Scenario Outline: Valida que se pueda rechazar un pago si las credenciales del Firmante 3 son incorrectas
    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza de manera exitosa <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante2>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante3>
    And validamos el Saldo y Movimiento


    Examples:
      | ambiente | firmante1 | password    | tipoCuenta        | cuentaOrigen   | empresa | servicio        | codigoDeudor | monto | descripcion | firmante2 | firmante3 | credencialesOK |
      | DEV      | AUTO11    | Interbank1* | Ahorros Soles     | 100-7005769276 | PERUSAT | PREPAGO - SOLES | 201077       | 8000  | DESCP       | AUTO14    |  AUTO15   | SI             |


  @unhappy_path @pagoServicio @ESC7FIRMACONJUNTADATAMAESTRANEGATIVIDAD
  Scenario Outline: Valida que se pueda rechazar un pago si las credenciales del Firmante 1 son incorrectas
    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza de manera exitosa <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante2>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante3>
    And validamos el Saldo y Movimiento


    Examples:
      | ambiente | firmante1 | password    | tipoCuenta        | cuentaOrigen   | empresa        | servicio | codigoDeudor | monto | descripcion | firmante2 | firmante3 | credencialesOK |
      | DEV      | AUTO13    | Interbank1* | Corriente Soles   | 100-7001101389 | CASUARINAS SAC | PAGOS    | 10440873800  | 60000 |             | AUTO14    | AUTO15    | SI             |


  @unhappy_path @pagoServicio @ESC8FIRMACONJUNTADATAMAESTRANEGATIVIDAD
  Scenario Outline: Valida que se pueda rechazar un pago si el monto es mayor que el saldo de la cuenta
    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza de manera exitosa <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante2>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante3>
    And validamos el Saldo y Movimiento


    Examples:
      | ambiente | firmante1 | password    | tipoCuenta        | cuentaOrigen   | empresa        | servicio | codigoDeudor | monto   | descripcion | firmante2 | firmante3 | credencialesOK |
      | DEV      | AUTO13    | Interbank1* | Corriente Dólares | 100-7001101400 | CASUARINAS SAC | PAGOS    | 10440873800  | 9999000 |             | AUTO14    |  AUTO15   | SI             |


  # FIRMA CONJUNTA - OPERADOR, USUARIO 3, USUARIO 4 Y USUARIO 5


  @happy_path @pagoServicio @ESC9FIRMACONJUNTADATAMAESTRANEGATIVIDAD
  Scenario Outline: Valida que se pueda rechazar un pago si el monto es mayor que el monto limite permitido
    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza de manera exitosa <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante1>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante2>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante3>
    And validamos el Saldo y Movimiento

    Examples:
      | ambiente | operador | password    | tipoCuenta    | cuentaOrigen   | empresa        | servicio | codigoDeudor | monto | descripcion | firmante1 | firmante2 | firmante3 | credencialesOK |
      | DEV      | AUTO12   | Interbank1* | Ahorros Soles | 100-7005769276 | CASUARINAS SAC | PAGOS    | 10440873800  | 70000 |             | AUTO13    | AUTO14    | AUTO15    | SI             |


  @happy_path @pagoServicio @ESC10FIRMACONJUNTADATAMAESTRANEGATIVIDAD
  Scenario Outline: Valida que se pueda rechazar un pago si las credenciañes del Firmante 1 son incorrectas
    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza de manera exitosa <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante1>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante2>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante3>
    And validamos el Saldo y Movimiento

    Examples:
      | ambiente | operador | password    | tipoCuenta      | cuentaOrigen   | empresa        | servicio | codigoDeudor | monto | descripcion | firmante1 | firmante2 | firmante3 | credencialesOK |
      | DEV      | AUTO12   | Interbank1* | Ahorros Dólares | 100-7005769291 | CASUARINAS SAC | PAGOS    | 10440873800  | 60000 |             | AUTO13    | AUTO14    | AUTO15    | SI             |


  @happy_path @pagoServicio @ESC11FIRMACONJUNTADATAMAESTRANEGATIVIDAD
  Scenario Outline: Valida que se pueda rechazar un pago si las credenciañes del Firmante 2 son incorrectas
    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza de manera exitosa <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante1>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante2>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante3>
    And validamos el Saldo y Movimiento

    Examples:
      | ambiente | operador | password    | tipoCuenta    | cuentaOrigen   | empresa        | servicio | codigoDeudor | monto | descripcion | firmante1 | firmante2 | firmante3 | credencialesOK |
      | DEV      | AUTO12   | Interbank1* | Ahorros Soles | 100-7005769276 | CASUARINAS SAC | PAGOS    | 10440873800  | 60000 | DESCP       | AUTO13    | AUTO14    | AUTO15    | SI             |


  @happy_path @pagoServicio @ESC12FIRMACONJUNTADATAMAESTRANEGATIVIDAD
  Scenario Outline: Valida que se pueda rechazar un pago si el monto es mayor que el saldo de la cuenta
    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza de manera exitosa <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante1>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante2>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante3>
    And validamos el Saldo y Movimiento

    Examples:
      | ambiente | operador | password    | tipoCuenta    | cuentaOrigen   | empresa        | servicio | codigoDeudor | monto   | descripcion | firmante1 | firmante2 | firmante3 | credencialesOK |
      | DEV      | AUTO12   | Interbank1* | Ahorros Soles | 100-7005769276 | CASUARINAS SAC | PAGOS    | 10440873800  | 9999000 | DESCP       | AUTO13    | AUTO14    | AUTO15    | SI             |
