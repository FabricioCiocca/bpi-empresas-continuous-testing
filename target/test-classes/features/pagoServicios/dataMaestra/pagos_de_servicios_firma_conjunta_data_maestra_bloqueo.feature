@pago_servicios_data_maestra_a_firma_conjunta_bloqueo
Feature: Firma Conjunta - Data Maestra - Bloqueo

  # FIRMA CONJUNTA - USUARIO 1 Y USUARIO 2

  @happy_path @pagoServicio @ESC1FIRMACONJUNTADATAMAESTRABLOQUEO
  Scenario Outline: Valida que se pueda realizar un bloqueo por el Firmante 1; despues se realiza un pago total de servicio a firman conjunta
    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente - Firma Conjunta - Data Maestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And bloquea el pago a firma conjunta
    And se valida que no se visualice el pago a firma conjunta <firmante2>
    And se valida que no se visualice el pago a firma conjunta <firmante3>

    Examples:
      | ambiente | firmante1 | password   | tipoCuenta        | cuentaOrigen   | empresa | servicio        | codigoDeudor | monto | descripcion | firmante2 | firmante3 | credencialesOK |
      | DEV      | AUTO13   | Interbank1* | Ahorros Dólares   | 100-7005769291 | PERUSAT | PREPAGO - SOLES | 244369       | 8000  |             | AUTO14    | AUTO15    | SI             |


  @happy_path @pagoServicio @ESC2FIRMACONJUNTADATAMAESTRABLOQUEO
  Scenario Outline: Valida que se pueda realizar un bloqueo por el Firmante 2; despues se realiza un pago total de servicio a firman conjunta
    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente - Firma Conjunta - Data Maestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And se bloquea el pago a firma conjunta <firmante2>
    And se valida que no se visualice el pago a firma conjunta <firmante3>

    Examples:
      | ambiente | firmante1 | password    | tipoCuenta        | cuentaOrigen   | empresa        | servicio | codigoDeudor | monto | descripcion | firmante2 | firmante3 | credencialesOK |
      | DEV      | AUTO13    | Interbank1* | Ahorros Dólares   | 100-7005769291 | CASUARINAS SAC | PAGOS    | 110516081    | 8000  |             | AUTO14    | AUTO15    | SI             |


  # FIRMA CONJUNTA - USUARIO 3, USUARIO 4 Y USUARIO 5

  @happy_path @pagoServicio @ESC3FIRMACONJUNTADATAMAESTRABLOQUEO
  Scenario Outline: Valida que se pueda realizar un bloqueo por el Firmante 2; despues se realiza un pago total de servicio a firman conjunta
    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente - Firma Conjunta - Data Maestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And se bloquea el pago a firma conjunta <firmante2>
    And se valida que no se visualice el pago a firma conjunta <firmante3>

    Examples:
      | ambiente | firmante1 | password    | tipoCuenta        | cuentaOrigen   | empresa        | servicio | codigoDeudor | monto | descripcion | firmante2 | firmante3 |credencialesOK |
      | DEV      | AUTO13    | Interbank1* | Corriente Dólares | 100-7001101400 | CASUARINAS SAC | PAGOS    | 110516081    | 8000  |             | AUTO14    | AUTO15    | SI            |


  @happy_path @pagoServicio @ESC4FIRMACONJUNTADATAMAESTRABLOQUEO
  Scenario Outline: Valida que se pueda realizar un bloqueo por el Firmante 1; despues se realiza un pago total de servicio a firman conjunta
    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente - Firma Conjunta - Data Maestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And bloquea el pago a firma conjunta
    And se valida que no se visualice el pago a firma conjunta <firmante2>
    And se valida que no se visualice el pago a firma conjunta <firmante3>

    Examples:
      | ambiente | firmante1 | password    | tipoCuenta   | cuentaOrigen   | empresa        | servicio | codigoDeudor | monto | descripcion | firmante2 | firmante3 | credencialesOK |
      | DEV      | AUTO13    | Interbank1* | Ahorro Soles | 100-7005769276 | CASUARINAS SAC | PAGOS    | 110516081    | 8000  | DESCP       | AUTO14    | AUTO15    | SI             |


  @happy_path @pagoServicio @ESC5FIRMACONJUNTADATAMAESTRABLOQUEO
  Scenario Outline: Valida que se pueda realizar un bloqueo por el Firmante 3; despues se realiza un pago total de servicio a firman conjunta
    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente - Firma Conjunta - Data Maestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante2>
    And se bloquea el pago a firma conjunta <firmante3>

    Examples:
      | ambiente | firmante1 | password    | tipoCuenta        | cuentaOrigen   | empresa | servicio        | codigoDeudor | monto | descripcion | firmante2 | firmante3 | credencialesOK |
      | DEV      | AUTO13    | Interbank1* | Ahorros Dólares   | 100-7005769291 | PERUSAT | PREPAGO - SOLES | 244369       | 8000  |             | AUTO14    | AUTO15    | SI             |


  # FIRMA CONJUNTA - OPERADOR, USUARIO 3, USUARIO 4 Y USUARIO 5


  @happy_path @pagoServicio @ESC6FIRMACONJUNTADATAMAESTRABLOQUEO
  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 2; despues de realizar un pago a firma conjunta
    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And se bloquea el pago a firma conjunta <firmante2>
    And se valida que no se visualice el pago a firma conjunta <firmante3>

    Examples:
      | ambiente | operador | password    | tipoCuenta        | cuentaOrigen   | empresa | servicio        | codigoDeudor | monto | descripcion | firmante2 | firmante3 | credencialesOK |
      | DEV      | AUTO13   | Interbank1* | Ahorros Dólares   | 100-7005769291 | PERUSAT | PREPAGO - SOLES | 244369       | 8000  |             | AUTO14    | AUTO15    | SI             |



  @happy_path @pagoServicio @ESC7FIRMACONJUNTADATAMAESTRABLOQUEO
  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 3 despues de realizar un pago a firma conjunta
    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante2>
    And se bloquea el pago a firma conjunta <firmante3>

    Examples:
      | ambiente | operador | password    | tipoCuenta        | cuentaOrigen   | empresa        | servicio | codigoDeudor | monto | descripcion | firmante2 | firmante3 | credencialesOK |
      | DEV      | AUTO13   | Interbank1* | Corriente Dólares | 100-7001101400 | CASUARINAS SAC | PAGOS    | 110516081    | 8000  |             | AUTO14    | AUTO15    | SI             |
