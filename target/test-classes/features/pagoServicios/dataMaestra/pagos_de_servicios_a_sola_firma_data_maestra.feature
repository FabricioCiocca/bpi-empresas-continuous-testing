@pago_servicios_data_maestra_sola_firma
Feature: Resolucion de trámite de producto Tarjeta de crédito

  @happy_path @pagoServicio @ESC1DATAMAESTRA
  Scenario Outline: Valida que se pueda realizar un pago de servicio a sola firma, data maestra, de una (1) cuota, para un (1) documento, para la empresa PERUSAT
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Data Maestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And valida los Saldos y Movimientos

    Examples:
      | ambiente | usuario | password    | tipoCuenta        | cuentaOrigen   | empresa | servicio        | codigoDeudor | monto | descripcion | credencialesOK |
#      | DEV      | AUTO11  | Interbank1* | Corriente Soles   | 100-7001101389 | PERUSAT | PREPAGO - SOLES | 10440873800  | 10    | DESCP1      | SI             |
#      | DEV      | AUTO12  | Interbank1* | Ahorros Soles     | 100-7005769276 | PERUSAT | PREPAGO - SOLES | 10304034527  | 20    |             | SI             |
#      | DEV      | AUTO13  | Interbank1* | Corriente Dólares | 100-7001101400 | PERUSAT | PREPAGO - SOLES | 201077       | 30    | DESCP2      | SI             |
      | DEV      | AUTO14  | Interbank1* | Ahorros Dólares   | 100-7005769291 | PERUSAT | PREPAGO - SOLES | 244369       | 40    |             | SI             |


  @happy_path @pagoServicio @ESC2DATAMAESTRA
  Scenario Outline: Valida que se pueda realizar un nuevo pago con la misma cuenta al mismo servicio; luego de realizar el pago de un servicio a sola firma, data maestra, de una (1) cuota, para un (1) codigo deudor, para la empresa PERUSAT (codigo deudor > 800)
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Data Maestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And intenta realizar un nuevo pago de tipo Pagos - De servicios - A sola firma - Data Maestra <cuentaOrigen>
    And valida los Saldos y Movimientos

    Examples:
      | ambiente | usuario | password    | tipoCuenta        | cuentaOrigen   | empresa | servicio        | codigoDeudor | monto | descripcion | credencialesOK |
      | DEV      | AUTO11  | Interbank1* | Corriente Dólares | 100-7001101400 | PERUSAT | PREPAGO - SOLES | 177677       | 10    |             | SI             |


  @happy_path @pagoServicio @ESC3DATAMAESTRA
  Scenario Outline: Valida que se pueda realizar un nuevo pago con la misma cuenta a una nueva empresa con un nuevo servicio; luego de realizar el pago de un servicio a sola firma, data maestra, de una (1) cuota, para un (1) codigo deudor, para la empresa PERUSAT y ORIFLAME (codigo deudor > 800)
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Sin Data <tipoCuenta>, <cuentaOrigen>, <empresa1>, <servicio>, <codigoDeudor>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And intenta realizar un nuevo pago de tipo Pagos a una nueva empresa con nuevo servicio - De servicios - A sola firma - Data Maestra <empresa2>, <servicio2>
    And validos los Saldos y Movimientos

    Examples:
      | ambiente | usuario | password    | tipoCuenta   | cuentaOrigen   | empresa1 | empresa2 | servicio        | servicio2 |codigoDeudor | monto | descripcion | credencialesOK |
      | DEV      | AUTO11  | Interbank1* | Ahorro Soles | 100-7005769276 | PERUSAT  | ORIFLAME | PREPAGO - SOLES | ORIFLAME  |177677       | 10    | DESC        | SI             |


  @happy_path @pagoServicio @ESC4DATAMAESTRA
  Scenario Outline: Valida que se pueda realizar un nuevo pago con diferente cuenta al mismo servicio; luego de realizar el pago de un servicio a sola firma, data maestra, de una (1) cuota, para un (1) codigo deudor, para la empresa PERUSAT (codigo deudor > 800)
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Sin Data <tipoCuenta1>, <cuentaOrigen1>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And intenta realizar un nuevo pago de tipo Pagos - De servicios - A sola firma - Sin Data <cuentaOrigen2>
    And valida los Saldos y Movimientos

    Examples:
      | ambiente | usuario | password    | tipoCuenta1    | cuentaOrigen1  | tipoCuenta2     | cuentaOrigen2  | empresa | servicio        | codigoDeudor | monto | descripcion | credencialesOK |
      | DEV      | AUTO11  | Interbank1* | Ahorro Dólares | 100-7005769291 | Corriente Soles | 100-7001101389 | PERUSAT | PREPAGO - SOLES | 177677       | 25    |             | SI             |


  @happy_path @pagoServicio @ESC5DATAMAESTRA
  Scenario Outline: Valida que se pueda realizar un pago de servicio a sola firma, data maestra, de una (1) cuota, para un (1) documento, para la empresa ORIFLAME
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Data Maestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoSocio>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And valida los Saldos y Movimientos

    Examples:
      | ambiente | usuario | password    | tipoCuenta        | cuentaOrigen   | empresa        | servicio | codigoSocio | monto | descripcion | credencialesOK |
      | DEV      | AUTO11  | Interbank1* | Corriente Soles   | 100-7001101389 | CASUARINAS SAC | PAGOS    | 110516081   | 10    | DESCP1      | SI             |
#      | DEV      | AUTO12  | Interbank1* | Ahorros Soles     | 100-7005769276 | CASUARINAS SAC | PAGOS    | 110516082   | 20    | DESCP2      | SI             |
#      | DEV      | AUTO13  | Interbank1* | Corriente Dólares | 100-7001101400 | CASUARINAS SAC | PAGOS    | 110516083   | 30    |             | SI             |
#      | DEV      | AUTO14  | Interbank1* | Ahorros Dólares   | 100-7005769291 | CASUARINAS SAC | PAGOS    | 110516085   | 40    |             | SI             |


  @happy_path @pagoServicio @ESC6DATAMAESTRA
  Scenario Outline: Valida que se pueda realizar un nuevo pago con la misma cuenta al mismo servicio; luego de realizar el pago de un servicio a sola firma, data maestra, de una (1) cuota, para un (1) codigo deudor, para la empresa ORIFLAME (codigo socio > 800)
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Data Mestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoSocio>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Data Maestra <tipoCuenta>, <cuentaOrigen>
    And validos los Saldos y Movimientos

    Examples:
      | ambiente | usuario | password    | tipoCuenta      | cuentaOrigen   | empresa  | servicio  | codigoSocio | monto | descripcion | credencialesOK |
      | DEV      | AUTO11  | Interbank1* | Corriente Soles | 100-7001101389 | ORIFLAME | ORIFLAME2 | 646884      | 10    |             | SI             |


  @happy_path @pagoServicio @ESC7DATAMAESTRA
  Scenario Outline: Valida que se pueda realizar un nuevo pago con diferente cuenta al mismo servicio; luego de realizar el pago de un servicio a sola firma, data maestra, de una (1) cuota, para un (1) codigo deudor, para la empresa ORIFLAME (codigo socio > 800)
    Given que el usuario <usuario> accede a la aplicacion  BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Data Mestra <tipoCuenta1>, <cuentaOrigen1>, <empresa>, <servicio>, <codigoSocio>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Data Maestra <tipoCuenta2>, <cuentaOrigen2>
    And validos los Saldos y Movimientos

    Examples:
      | ambiente | usuario | password    | tipoCuenta1  | cuentaOrigen1  | tipoCuenta2     | cuentaOrigen2  | empresa  | servicio  | codigoSocio | monto | descripcion | credencialesOK |
      | DEV      | AUTO11  | Interbank1* | Ahorro Soles | 100-7005769276 | Corriente Soles | 100-7001101389 | ORIFLAME | ORIFLAME2 | 706044      | 10    |             | SI             |

