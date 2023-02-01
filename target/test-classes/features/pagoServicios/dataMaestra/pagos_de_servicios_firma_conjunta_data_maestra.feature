@pago_servicios_data_maestra_a_firma_conjunta
Feature: Firma Conjunta - Data Maestra

  # FIRMA CONJUNTA - USUARIO 1 Y USUARIO 2

  @happy_path @pagoServicio @ESC1FIRMACONJUNTADATAMAESTRA
  Scenario Outline: Valida que se pueda realizar un pago de servicio a firma conjunta, de Data Maestra, de una (1) cuota, para un (1) codigo deudor, para la empresa PERUSAT
    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente - Firma Conjunta - Data Maestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante2>
    And validos los Saldos y Movimientos

    Examples:
      | ambiente | firmante1 | password    | tipoCuenta        | cuentaOrigen   | empresa | servicio      | codigoDeudor | monto | descripcion | firmante2 | credencialesOK |
      | DEV      | AUTO11    | Interbank1* | Corriente Dólares | 100-7001101400 | PERUSAT | PREPAGO SOLES | 01234567     | 3000  |             | AUTO12    | SI             |



  @happy_path @pagoServicio @ESC2FIRMACONJUNTADATAMAESTRA
  Scenario Outline: Valida que se pueda realizar un pago de servicio a firma conjunta, de Data Maestra, de una (1) cuota, para un (1) codigo deudor, para la empresa CASUARINAS SAC
    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente - Firma Conjunta - Data Maestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante2>
    And validos los Saldos y Movimientos

    Examples:
      | ambiente | firmante1 | password    | tipoCuenta      | cuentaOrigen   | empresa        | servicio | codigoDeudor | monto | descripcion | firmante2 | credencialesOK |
      | DEV      | AUTO11    | Interbank1* | Corriente Soles | 100-7001101389 | CASUARINAS SAC | PAGOS    | 110516081    | 200   | DESCP01     | AUTO12    | SI             |


  # FIRMA CONJUNTA - USUARIO 3, USUARIO 4 Y USUARIO 5


  @happy_path @pagoServicio @ESC3FIRMACONJUNTADATAMAESTRA
  Scenario Outline: Valida que se pueda realizar un pago de servicio a firma conjunta, de Data Maestra, de una (1) cuota, para un (1) codigo deudor, para la empresa
    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza de manera exitosa <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante1>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante2>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante3>
    And validos los Saldos y Movimientos

    Examples:
      | ambiente | firmante1 | password    | tipoCuenta      | cuentaOrigen   | empresa        | servicio | codigoDeudor |monto | descripcion | firmante2 | firmante3 | credencialesOK |
      | DEV      | AUTO13    | Interbank1* | Corriente Soles | 100-7001101389 | CASUARINAS SAC | PAGOS    | 110516081    | 200  | DESCP       | AUTO14    | AUTO15    | SI             |



  @happy_path @pagoServicio @ESC4FIRMACONJUNTADATAMAESTRA
  Scenario Outline: Valida que se pueda realizar un pago de servicio a firma conjunta, de Data Maestra, de una (1) cuota, para un (1) codigo deudor, para la empresa
    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza de manera exitosa <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante1>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante2>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante3>
    And validos los Saldos y Movimientos

    Examples:
      | ambiente | firmante1 | password    | tipoCuenta      | cuentaOrigen   | empresa        | servicio | codigoDeudor | monto | descripcion | firmante2 | firmante3 | credencialesOK |
      | DEV      | AUTO13    | Interbank1* | Ahorros Dólares | 100-7005769291 | CASUARINAS SAC | PAGOS    | 110516081    | 8000  | test01      | AUTO14    | AUTO15    | SI             |


    # FIRMA CONJUNTA - OPERADOR, USUARIO 3, USUARIO 4 Y USUARIO 5


  @happy_path @pagoServicio @ESC5FIRMACONJUNTADATAMAESTRA
  Scenario Outline: Valida que se pueda realizar un pago de servicio a firma conjunta, de Data Maestra, de una (1) cuota, para un (1) codigo deudor, para la empresa
    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza de manera exitosa <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante1>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante2>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante3>
    And validos los Saldos y Movimientos

    Examples:
      | ambiente | operador | password    | tipoCuenta      | cuentaOrigen   | empresa | servicio      | codigoDeudor | monto | descripcion | firmante1 | firmante2 | firmante3 | credencialesOK |
      | DEV      | AUTO12    | Interbank1* | Corriente Soles | 100-7001101389 | PERUSAT | PREPAGO SOLES | 01234567     | 50000 | test01      | AUTO13    | AUTO14    | AUTO15    | SI             |

  @happy_path @pagoServicio @ESC6FIRMACONJUNTADATAMAESTRA
  Scenario Outline: Valida que se pueda realizar un pago de servicio a firma conjunta, de Data Maestra, de una (1) cuota, para un (1) codigo deudor, para la empresa
    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza de manera exitosa <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante1>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante2>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante3>
    And validos los Saldos y Movimientos

    Examples:
      | ambiente | operador | password    | tipoCuenta        | cuentaOrigen   | empresa        | servicio | codigoDeudor | monto | descripcion | firmante1 | firmante2 | firmante3 | credencialesOK |
      | DEV      | AUTO12   | Interbank1* | Corriente Dólares | 100-7001101400 | CASUARINAS SAC | PAGOS    | 110516081    | 50000 |             | AUTO13    | AUTO14    | AUTO15    | SI             |



  @happy_path @pagoServicio @ESC7FIRMACONJUNTADATAMAESTRA
  Scenario Outline: Valida que se pueda realizar un pago de servicio a firma conjunta, de Data Maestra, de una (1) cuota, para un (1) codigo deudor, para la empresa
    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza de manera exitosa <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante1>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante2>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante3>
    And validos los Saldos y Movimientos

    Examples:
      | ambiente | operador | password    | tipoCuenta      | cuentaOrigen   | empresa        | servicio | codigoDeudor | monto | descripcion | firmante1 | firmante2 | firmante3 | credencialesOK |
      | DEV      | AUTO12   | Interbank1* | Corriente Soles | 100-7001101389 | CASUARINAS SAC | PAGOS    | 110516081    | 50000 | DESCP       | AUTO13    | AUTO14    | AUTO15    | SI             |



  @happy_path @pagoServicio @ESC8FIRMACONJUNTADATAMAESTRA
  Scenario Outline: Valida que se pueda realizar un pago de servicio a firma conjunta, de Data Maestra, de una (1) cuota, para un (1) codigo deudor, para la empresa
    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza de manera exitosa <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante1>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante2>
    And se autoriza el pago a Firma Conjunta - Data Maestra <firmante3>
    And validos los Saldos y Movimientos

    Examples:
      | ambiente | operador | password     | tipoCuenta      | cuentaOrigen   | empresa | servicio      | codigoDeudor | monto | descripcion | firmante1 | firmante2 | firmante3 | credencialesOK |
      | DEV      | AUTO12    | Interbank1* | Corriente Soles | 100-7001101389 | PERUSAT | PREPAGO SOLES | 01234567     | 50000 | DESCP       | AUTO13    | AUTO14    | AUTO15    | SI             |


