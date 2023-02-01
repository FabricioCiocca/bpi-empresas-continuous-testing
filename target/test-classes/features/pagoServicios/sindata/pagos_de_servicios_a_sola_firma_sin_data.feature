@pago_servicios_sin_data_sola_firma
Feature: A sola firma - Sin Data

  @happy_path @pagoServicio @ESC1ASINDATA
  Scenario Outline: Valida que se pueda realizar un pago de servicio a sola firma, sin data, de una (1) cuota, para un (1) documento, para la empresa Fe y Alegria
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Sin data <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <dniPagador>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And validos los Saldos y Movimientos

    Examples:

      | ambiente | usuario   | password    | tipoCuenta        | cuentaOrigen   | empresa      | servicio   | dniPagador | monto | descripcion | credencialesOK |
      | DEV      | Usuario06 | Interbank*1 | Corriente Soles   | 100-7001095125 | FE Y ALEGRÍA | DONACIONES | 01234567   | 700   | CP01        | SI             |
#      | DEV      | Usuario07 | Interbank*1 | Ahorros Soles     | 100-7005760848 | FE Y ALEGRÍA | DONACIONES | 01234568   | 500   |             | SI             |
#      | DEV      | Usuario08 | Interbank*1 | Corriente Dólares | 100-7001097364 | FE Y ALEGRÍA | DONACIONES | 01234569   | 1999  | CP03        | SI             |
#      | DEV      | Usuario09 | Interbank*1 | Ahorros Dólares   | 100-7005763919 | FE Y ALEGRÍA | DONACIONES | 01234510   | 5000  |             | SI             |
#

  @happy_path @pagoServicio @ESC2ASINDATA
  Scenario Outline: Valida que se pueda realizar un pago de servicio a sola firma, sin data, para más de una cuota (>1) y para más de un documento  (>1), para la empresa FE Y ALEGRÍA
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente>  <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Sin data <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <dniPagador>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And validos los Saldos y Movimientos


    Examples:
      | ambiente | usuario   | password    | tipoCuenta        | cuentaOrigen   | empresa      | servicio   | dniPagador                                                                                | monto | descripcion | credencialesOK |
     # | DEV      | Usuario06 | Interbank*1 | Corriente Soles   | 100-7001095125 | FE Y ALEGRÍA | DONACIONES | 01234567-12345678                                                                         | 30    | CP1         | SI             |
      | DEV      | Usuario07 | Interbank*1 | Corriente Dólares | 100-7001097364 | FE Y ALEGRÍA | DONACIONES | 01234567-12345678-23456789-34567890-45678901-56789012-67890123-78901234-89012345-90123456 | 30    |             | SI             |

  @happy_path @pagoServicio @ESC3ASINDATA
  Scenario Outline: Valida que se pueda realizar un nuevo pago con diferente cuenta al mismo servicio; luego de realizar el pago de un servicio a sola firma, sin data, de una (1) cuota, para un (1) codigo deudor, para la empresa Fe y Alegría
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Sin data <tipoCuenta1>, <cuentaOrigen1>, <empresa>, <servicio>, <dniPagador>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And intenta realizar un nuevo pago de tipo Pagos - De servicios - A sola firma - Sin data <cuentaOrigen2>
    And validos los Saldos y Movimientos

    Examples:
      | ambiente | usuario   | password    | tipoCuenta1       | cuentaOrigen1  | cuentaOrigen2  | empresa      | servicio   | dniPagador | monto | descripcion | credencialesOK |
      | DEV      | Usuario06 | Interbank*1 | Corriente Soles   | 100-7001095125 | 100-7005760848 | FE Y ALEGRÍA | DONACIONES | 01234567   | 10    | test01      | SI             |
      | DEV      | Usuario07 | Interbank*1 | Ahorros Soles     | 100-7005760848 | 100-7005763919 | FE Y ALEGRÍA | DONACIONES | 01234567   | 40    |             | SI             |
      | DEV      | Usuario08 | Interbank*1 | Corriente Dólares | 100-7001097364 | 100-7001095125 | FE Y ALEGRÍA | DONACIONES | 01234567   | 50    | test01      | SI             |
      | DEV      | Usuario09 | Interbank*1 | Ahorros Dólares   | 100-7005763919 | 100-7001097364 | FE Y ALEGRÍA | DONACIONES | 01234567   | 40    |             | SI             |

  @happy_path @pagoServicio @ESC4ASINDATA
  Scenario Outline: Valida que se pueda realizar un pago de servicio a sola firma, sin data, de una (1) cuota, para un (1) documento, para la empresa Sembrando
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente>  <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Sin data <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <dniPagador>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And validos los Saldos y Movimientos

    Examples:
      | ambiente | usuario   | password    | tipoCuenta        | cuentaOrigen   | empresa   | servicio           | dniPagador | monto | descripcion | credencialesOK |
      | DEV      | Usuario06 | Interbank*1 | Corriente Soles   | 100-7001095125 | SEMBRANDO | DONACIONES - SOLES | 01234567   | 80    |             | SI             |
      | DEV      | Usuario07 | Interbank*1 | Ahorros Soles     | 100-7005760848 | SEMBRANDO | DONACIONES DOLARES | 01234567   | 19    | test 01     | SI             |
      | DEV      | Usuario08 | Interbank*1 | Corriente Dólares | 100-7001097364 | SEMBRANDO | DONACIONES - SOLES | 01234567   | 80    |             | SI             |
      | DEV      | Usuario09 | Interbank*1 | Ahorros Dólares   | 100-7005763919 | SEMBRANDO | DONACIONES DOLARES | 01234567   | 70    | test 02     | SI             |

  @happy_path @pagoServicio @ESC5ASINDATA
  Scenario Outline: Valida que se pueda realizar un pago de servicio a sola firma, sin data, de una (1) cuota, para más de un (>1) documento, para la empresa Sembrando
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente>  <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Sin data <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <dniPagador>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And validos los Saldos y Movimientos


    Examples:
      | ambiente | usuario   | password    | tipoCuenta        | cuentaOrigen   | empresa   | servicio           | dniPagador                                                                                | monto | descripcion | credencialesOK |
      | DEV      | Usuario06 | Interbank*1 | Corriente Soles   | 100-7001095125 | SEMBRANDO | DONACIONES - SOLES | 01234567-12345678                                                                         | 80    |             | SI             |
      | DEV      | Usuario07 | Interbank*1 | Corriente Dólares | 100-7001097364 | SEMBRANDO | DONACIONES - SOLES | 01234567-12345678-23456789-34567890-45678901-56789012-67890123-78901234-89012345-90123456 | 80    | test01      | SI             |


  @happy_path @pagoServicio @ESC6ASINDATA
  Scenario Outline: Valida que se pueda realizar un nuevo pago con la misma cuenta a la misma empresa y diferente servicio; luego de realizar el pago de un servicio a sola firma, Sin Data, de una (1) cuota, para un (1) codigo deudor, para la empresa Sembrando (codigo deudor > 800)
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente>  <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Sin data <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio1>, <dniPagador>
    Then el pago se realiza satisfactoriamente <monto>, <descripcion>, <password>
    And intenta realizar un nuevo pago  con el mismo servicio de tipo Pagos - De servicios - A sola firma - Sin data <servicio2>
    And validos los Saldos y Movimientos

    Examples:
      | ambiente | usuario   | password    | tipoCuenta      | cuentaOrigen   | empresa   | servicio1          | servicio2          | dniPagador | monto | descripcion | credencialesOK |

      | DEV      | Usuario06 | Interbank*1 | Ahorros Soles   | 100-7005760848 | SEMBRANDO | DONACIONES - SOLES | DONACIONES DOLARES | 01234567   | 80    |             | SI             |
      | DEV      | Usuario07 | Interbank*1 | Ahorros Dólares | 100-7005763919 | SEMBRANDO | DONACIONES DOLARES | DONACIONES - SOLES | 01234567   | 80    | test01      | SI             |










