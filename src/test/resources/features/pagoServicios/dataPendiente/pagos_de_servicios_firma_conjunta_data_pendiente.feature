@pago_servicios_data_pendiente_a_firma_conjunta
Feature: Firma conjunta - Data Pendiente

  @happy_path @pagoServicio @ESC1PENDIENTEFC
#bien
  Scenario Outline: Valida que se pueda realizar un pago total de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Luz del Sur)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante2>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa     | servicio | codigoDeudor | descripcion | firmante2 |
      | DEV      | Usuario02 | SI             | Corriente Dólares | 100-7001097380 | LUZ DEL SUR | LUZ      | SX898030     |             | Usuario01 |

  @happy_path @pagoServicio @ESC2PENDIENTEFC
#bien
  Scenario Outline: Valida que se pueda realizar un pago parcial de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (San Silvestre)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante2>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa       | servicio | codigoDeudor | monto | descripcion | firmante2 |
      | DEV      | Usuario02 | SI             | Corriente Dólares | 100-7001097380 | SAN SILVESTRE | VARIOS   | 0000P7110005 | 400   |             | Usuario01 |

  @happy_path @pagoServicio @ESC3PENDIENTEFC
#bien
  Scenario Outline: Valida que se pueda realizar un pago parcial de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Multiservicio (Mall Proxim)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante2>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa     | servicio            | codigoDeudor | monto | descripcion | firmante2 |
      | DEV      | Usuario02 | SI             | Ahorros Dólares | 100-7005765130 | MALL PROXIM | TODOS MIS SERVICIOS | EMP11111124  | 400   |             | Usuario01 |

  @happy_path @pagoServicio @ESC4PENDIENTEFC
#bien
  Scenario Outline: Valida que se pueda realizar un pago total de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Luz del Sur)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante2>
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante3>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa     | servicio | codigoDeudor | descripcion | firmante2 | firmante3 |
      | DEV      | Usuario03 | SI             | Corriente Soles | 100-7001097373 | LUZ DEL SUR | LUZ      | SX898030     | test01      | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC5PENDIENTEFC
#bien
  Scenario Outline: Valida que se pueda realizar un pago parcial de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (San Silvestre)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante2>
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante3>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa       | servicio  | codigoDeudor | monto | descripcion | firmante2 | firmante3 |
      | DEV      | Usuario03 | SI             | Corriente Dólares | 100-7001097380 | SAN SILVESTRE | PENSIONES | 0082059      | 160   |             | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC6PENDIENTEFC
#bien
  Scenario Outline: Valida que se pueda realizar un pago total de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (San Silvestre)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante2>
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante3>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa       | servicio | codigoDeudor | descripcion | firmante2 | firmante3 |
      | DEV      | Usuario03 | SI             | Ahorros Soles | 100-7005764266 | SAN SILVESTRE | VARIOS   | 0000P7110009 | test01      | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC7PENDIENTEFC
#bien
  Scenario Outline: Valida que se pueda realizar un pago total de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Multiservicio (Mall Proxim)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante2>
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante3>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa     | servicio            | codigoDeudor | descripcion | firmante2 | firmante3 |
      | DEV      | Usuario03 | SI             | Ahorros Dólares | 100-7005765130 | MALL PROXIM | TODOS MIS SERVICIOS | EMP11111124  |             | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC8PENDIENTEFC
#bien
  Scenario Outline: Valida que se pueda realizar un pago total de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Luz del Sur)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante1>
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante2>
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante3>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa     | servicio | codigoDeudor | descripcion | firmante1 | firmante2 | firmante3 |
      | DEV      | Usuario01 | SI             | Corriente Soles | 100-7001097373 | LUZ DEL SUR | LUZ      | SX898051     | test01      | Usuario03 | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC9PENDIENTEFC
#bien
  Scenario Outline: Valida que se pueda realizar un pago total de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (San Silvestre)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante1>
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante2>
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante3>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa       | servicio  | codigoDeudor | descripcion | firmante1 | firmante2 | firmante3 |
      | DEV      | Usuario01 | SI             | Corriente Dólares | 100-7001097380 | SAN SILVESTRE | PENSIONES | 0082059      |             | Usuario03 | Usuario04 | Usuario05 |