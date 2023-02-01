@pago_servicios_data_pendiente_a_sola_firma
Feature: A sola firma - Data Pendiente

  @happy_path @pagoServicio @ESC1PENDIENTESF
#bien
  Scenario Outline: Valida que se pueda realizar un pago total de servicio a sola firma,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Luz del Sur)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a sola firma se realiza con exito
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa     | servicio | codigoDeudor | descripcion |
      | DEV      | Usuario04 | SI             | Corriente Soles   | 100-7001097373 | LUZ DEL SUR | LUZ      | SX898051     | test01      |
      | DEV      | Usuario02 | SI             | Corriente Dólares | 100-7001097380 | LUZ DEL SUR | LUZ      | SX898036     |             |

  @happy_path @pagoServicio @ESC2PENDIENTESF
#bien
  Scenario Outline: Valida que se pueda realizar un nuevo pago con la misma cuenta al mismo servicio; luego de realizar el pago total de un servicio a sola firma,
  de Data Pendiente, de una (1) cuota, para más de un (>1) codigo deudor, para una Empresa Uniservicio (Luz del Sur)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a sola firma se realiza con exito
    And intenta realizar un nuevo pago de servicios con la misma cuenta al mismo servicio - A sola firma - Pendiente
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa     | servicio | codigoDeudor | descripcion |
      | DEV      | Usuario02 | SI             | Corriente Soles | 100-7001097373 | LUZ DEL SUR | LUZ      | SX898036     | test01      |

  @happy_path @pagoServicio @ESC3PENDIENTESF
#bien
  Scenario Outline: Valida que se pueda realizar un nuevo pago con diferente cuenta a diferente empresa; luego de realizar el pago total de un servicio a sola firma,
  de Data Pendiente, de una (1) cuota, para más de un (>1) codigo deudor, para una Empresa Uniservicio (Luz del Sur)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta1>; <cuentaOrigen1>; <empresa1>; <servicio1>; <codigoDeudor>; <descripcion>
    Then el pago a sola firma se realiza con exito
    And intenta realizar un nuevo pago de servicios con diferente cuenta a diferente empresa - A sola firma - Pendiente <tipoCuenta2>, <cuentaOrigen2>, <empresa2>, <servicio2>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta1       | cuentaOrigen1  | tipoCuenta2     | cuentaOrigen2  | empresa1    | servicio1 | empresa2      | servicio2 | codigoDeudor      | descripcion |
      | DEV      | Usuario02 | SI             | Corriente Dólares | 100-7001097380 | Corriente Soles | 100-7001097373 | LUZ DEL SUR | LUZ       | SAN SILVESTRE | PENSIONES | SX898036-SX898030 |             |

  @happy_path @pagoServicio @ESC4PENDIENTESF
#bien
  Scenario Outline: Valida que se pueda realizar un pago parcial de servicio a sola firma,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (San Silvestre)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a sola firma se realiza con exito
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa       | servicio  | codigoDeudor | monto | descripcion |
      | DEV      | Usuario02 | SI             | Corriente Soles   | 100-7001097373 | SAN SILVESTRE | PENSIONES | 0082059      | 200   | test01      |
      | DEV      | Usuario02 | SI             | Corriente Dólares | 100-7001097380 | SAN SILVESTRE | PENSIONES | 0082059      | 800   |             |

  @happy_path @pagoServicio @ESC5PENDIENTESF
#bien
  Scenario Outline: Valida que se pueda realizar un nuevo pago con la misma cuenta a la misma empresa y diferente servicio; luego de realizar el pago parcial de un servicio a sola firma,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (San Silvestre)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio1>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a sola firma se realiza con exito
    And intenta realizar un nuevo pago de servicios con la misma cuenta a diferente servicio - A sola firma - Pendiente <servicio2>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa       | servicio1 | servicio2 | codigoDeudor | monto | descripcion |
      | DEV      | Usuario02 | SI             | Ahorros Soles | 100-7005764266 | SAN SILVESTRE | PENSIONES | VARIOS    | 0082059      | 200   | test01      |

  @happy_path @pagoServicio @ESC6PENDIENTESF
#bien
  Scenario Outline: Valida que se pueda realizar un nuevo pago con diferente cuenta a la misma empresa y diferente servicio; luego de realizar el pago parcial de un servicio a sola firma,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (San Silvestre)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta1>; <cuentaOrigen1>; <empresa>; <servicio1>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a sola firma se realiza con exito
    And intenta realizar un nuevo pago de servicios con diferente cuenta a diferente servicio - A sola firma - Pendiente <tipoCuenta2>, <cuentaOrigen2>, <servicio2>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta1     | cuentaOrigen1  | tipoCuenta2   | cuentaOrigen2  | empresa       | servicio1 | servicio2 | codigoDeudor | monto | descripcion |
      | DEV      | Usuario02 | SI             | Ahorros Dólares | 100-7005765130 | Ahorros Soles | 100-7005764266 | SAN SILVESTRE | PENSIONES | VARIOS    | 0082059      | 400   |             |

  @happy_path @pagoServicio @ESC7PENDIENTESF
#bien
  Scenario Outline: Valida que se pueda realizar un pago parcial de servicio a sola firma,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (San Silvestre)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a sola firma se realiza con exito
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa       | servicio | codigoDeudor | monto | descripcion |
      | DEV      | Usuario02 | SI             | Corriente Soles | 100-7001097373 | SAN SILVESTRE | VARIOS   | 0000P7110005 | 200   | test01      |

  @happy_path @pagoServicio @ESC8PENDIENTESF
#bien
  Scenario Outline: Valida que se pueda realizar un nuevo pago con la misma cuenta a la misma empresa y diferente servicio; luego de realizar el pago parcial de un servicio a sola firma,
  de Data Pendiente, de una (1) cuota, para más de un (>1) codigo deudor, para una Empresa Uniservicio (San Silvestre)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio1>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a sola firma se realiza con exito
    And intenta realizar un nuevo pago de servicios con la misma cuenta a diferente servicio - A sola firma - Pendiente <servicio2>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa       | servicio1 | servicio2 | codigoDeudor              | monto | descripcion |
      | DEV      | Usuario02 | SI             | Corriente Dólares | 100-7001097380 | SAN SILVESTRE | VARIOS    | PENSIONES | 0000P7110005-0000P7110006 | 100   |             |

  @happy_path @pagoServicio @ESC9PENDIENTESF
#bien
  Scenario Outline: Valida que se pueda realizar un nuevo pago con la misma cuenta al mismo servicio; luego de realizar el pago total de un servicio a sola firma,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Multiservicio (Mall Proxim)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a sola firma se realiza con exito
    And intenta realizar un nuevo pago de servicios con la misma cuenta al mismo servicio - A sola firma - Pendiente
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa     | servicio            | codigoDeudor | descripcion |
      | DEV      | Usuario04 | SI             | Corriente Dólares | 100-7001097380 | MALL PROXIM | TODOS MIS SERVICIOS | EMP11111125  |             |

  @happy_path @pagoServicio @ESC10PENDIENTESF
#bien
  Scenario Outline: Valida que se pueda realizar un nuevo pago con diferente cuenta al mismo servicio; luego de realizar el pago total de un servicio a sola firma,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Multiservicio (Mall Proxim)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta1>; <cuentaOrigen1>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a sola firma se realiza con exito
    And intenta realizar un nuevo pago de servicios con diferente cuenta al mismo servicio - A sola firma - Pendiente <tipoCuenta2>, <cuentaOrigen2>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta1   | cuentaOrigen1  | tipoCuenta2     | cuentaOrigen2  | empresa     | servicio            | codigoDeudor | descripcion |
      | DEV      | Usuario03 | SI             | Ahorros Soles | 100-7005764266 | Ahorros Dólares | 100-7005765130 | MALL PROXIM | TODOS MIS SERVICIOS | EMP11111124  | test01      |
