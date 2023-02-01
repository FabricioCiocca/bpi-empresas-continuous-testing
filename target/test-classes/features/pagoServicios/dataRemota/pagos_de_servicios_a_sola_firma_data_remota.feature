@pago_servicios_data_remota_a_sola_firma
Feature: A sola firma - Data Remota
#terminado
  @happy_path @pagoServicio @ESC1REMOTASF

  Scenario Outline: Valida que se pueda realizar un pago total de servicio a sola firma,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a sola firma se realiza con exito
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion |
      | DEV      | Usuario01 | SI             | Corriente Soles   | 100-7001097373 | OSLO    | VTA MERCADERI | 77250601     | test01      |
      | DEV      | Usuario02 | SI             | Corriente Dólares | 100-7001097380 | OSLO    | VTA MERCADERI | 77250601     |             |

  @happy_path @pagoServicio @ESC2REMOTASF

  Scenario Outline: Valida que se pueda realizar un nuevo pago con la misma cuenta al mismo servicio; luego de realizar el pago total de un servicio a sola firma,
  de Data Remota, de una (1) cuota, para más de un (>1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a sola firma se realiza con exito
    And intenta realizar un nuevo pago de servicios con la misma cuenta al mismo servicio - A sola firma - Remota
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta     | cuentaOrigen   | empresa | servicio      | codigoDeudor      | descripcion |
      | DEV      | Usuario01 | SI             |Corriente Soles | 100-7001097373 | OSLO    | VTA MERCADERI | 77250601-10086371 | test01      |

  @happy_path @pagoServicio @ESC3REMOTASF

  Scenario Outline: Valida que se pueda realizar un nuevo pago con diferente cuenta al mismo servicio; luego de realizar el pago total de un servicio a sola firma,
  de Data Remota, de una (1) cuota, para más de un (>1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta1>; <cuentaOrigen1>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a sola firma se realiza con exito
    And intenta realizar un nuevo pago de servicios con diferente cuenta al mismo servicio - A sola firma - Remota <tipoCuenta2>, <cuentaOrigen2>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta1       | cuentaOrigen1  | tipoCuenta2     | cuentaOrigen2  | empresa | servicio      | codigoDeudor      | descripcion |
      | DEV      | Usuario02 | SI             | Corriente Dólares | 100-7001097380 | Corriente Soles | 100-7001097373 | OSLO    | VTA MERCADERI | 77250601-10086371 |             |

  @happy_path @pagoServicio @ESC4REMOTASF
#bien
  Scenario Outline: Valida que se pueda realizar un pago parcial de servicio a sola firma,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a sola firma se realiza con exito
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto | descripcion |
      | DEV      | Usuario03 | SI             | Corriente Soles | 100-7001097373 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 191   | test01      |

  @happy_path @pagoServicio @ESC5REMOTASF
#bien
  Scenario Outline: Valida que se pueda realizar un nuevo pago con la misma cuenta a la misma empresa y diferente servicio; luego de realizar el pago parcial de un servicio a sola firma,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio1>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a sola firma se realiza con exito
    And intenta realizar un nuevo pago de servicios con la misma cuenta a diferente servicio - A sola firma - Remota <servicio2>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa  | servicio1     | servicio2 | codigoDeudor | monto | descripcion |
      | DEV      | Usuario03 | SI             | Ahorros Soles | 100-7005764266 | COSTAMAR | RECAUDO LIBRE | RESERVAS  | 0567297048   | 191   | test01      |

  @happy_path @pagoServicio @ESC6REMOTASF
#bien
  Scenario Outline: Valida que se pueda realizar un nuevo pago con diferente cuenta al mismo servicio; luego de realizar el pago parcial de un servicio a sola firma,
  de Data Remota, de una (1) cuota, para más de un (>1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta1>; <cuentaOrigen1>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a sola firma se realiza con exito
    And intenta realizar un nuevo pago de servicios con diferente cuenta al mismo servicio - A sola firma - Remota <tipoCuenta2>, <cuentaOrigen2>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta1       | cuentaOrigen1  | tipoCuenta2     | cuentaOrigen2  | empresa  | servicio      | codigoDeudor | monto | descripcion |
      | DEV      | Usuario02 | SI             | Corriente Dólares | 100-7001097380 | Corriente Soles | 100-7001097373 | COSTAMAR | RECAUDO LIBRE | 0567297048-2 | 191   |             |

  @happy_path @pagoServicio @ESC7REMOTASF
#bien
  Scenario Outline: Valida que se pueda realizar un nuevo pago con diferente cuenta a diferente empresa; luego de realizar el pago parcial de un servicio a sola firma,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta1>; <cuentaOrigen1>; <empresa1>; <servicio1>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a sola firma se realiza con exito
    And intenta realizar un nuevo pago de servicios con diferente cuenta a diferente empresa - A sola firma - Remota <tipoCuenta2>, <cuentaOrigen2>, <empresa2>, <servicio2>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta1     | cuentaOrigen1  | tipoCuenta2     | cuentaOrigen2  | empresa1 | servicio1     | empresa2    | servicio2     | codigoDeudor | monto | descripcion |
      | DEV      | Usuario03 | SI             | Ahorros Dólares | 100-7005765130 | Corriente Soles | 100-7001097373 | COSTAMAR | RECAUDO LIBRE | FOOD RETAIL | VTA MERCADERI | 0567297048   | 200   |             |
