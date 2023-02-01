@pago_servicios_data_pendiente_a_firma_conjunta_bloqueo
Feature: Firma conjunta bloqueo - Data Pendiente

  @happy_path @pagoServicio @ESC1PENDIENTEBLOQUEO
#bien
  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 1; despues que se realizo un pago total de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una empresa Uniservicio (San Silvestre)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And bloquea el pago a fima conjunta - Pendiente
    And se valida que no se visualice el pago a firma conjunta <firmante2>

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa       | servicio  | codigoDeudor | descripcion | firmante2 |
      | DEV      | Usuario01 | SI             | Ahorros Dólares | 100-7005765130 | SAN SILVESTRE | PENSIONES | 0082059      |             | Usuario02 |

  @happy_path @pagoServicio @ESC2PENDIENTEBLOQUEO
#bien
  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 2; despues que se realizo un pago parcial de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una empresa Uniservicio (San Silvestre)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se bloquea el pago a firma conjunta - Pendiente <firmante2>

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa       | servicio  | codigoDeudor | monto | descripcion | firmante2 |
      | DEV      | Usuario01 | SI             | Corriente Soles | 100-7001097373 | SAN SILVESTRE | PENSIONES | 0082059      | 500   | test01      | Usuario02 |

  @happy_path @pagoServicio @ESC3PENDIENTEBLOQUEO
#bien
  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 1; despues que se realizo un pago parcial de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una empresa Uniservicio (San Silvestre)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And bloquea el pago a fima conjunta - Pendiente
    And se valida que no se visualice el pago a firma conjunta <firmante2>
    And se valida que no se visualice el pago a firma conjunta <firmante3>

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa       | servicio | codigoDeudor | monto | descripcion | firmante2 | firmante3 |
      | DEV      | Usuario03 | SI             | Ahorros Dólares | 100-7005765130 | SAN SILVESTRE | VARIOS   | 0000P7110005 | 2200  |             | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC4PENDIENTEBLOQUEO
#bien
  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 2; despues que se realizo un pago parcial de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Multiservicio (Mall Proxim)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se bloquea el pago a firma conjunta - Pendiente <firmante2>
    And se valida que no se visualice el pago a firma conjunta <firmante3>

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa     | servicio            | codigoDeudor | monto | descripcion | firmante2 | firmante3 |
      | DEV      | Usuario03 | SI             | Corriente Soles | 100-7001097373 | MALL PROXIM | TODOS MIS SERVICIOS | EMP11111124  | 500   | test01      | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC5PENDIENTEBLOQUEO
#bien
  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 3; despues que se realizo un pago total de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Multiservicio (Mall Proxim)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante2>
    And se bloquea el pago a firma conjunta - Pendiente <firmante3>

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa     | servicio            | codigoDeudor |  descripcion | firmante2 | firmante3 |
      | DEV      | Usuario03 | SI             | Ahorros Soles | 100-7005764266 | MALL PROXIM | TODOS MIS SERVICIOS | EMP00000006  |  test01      | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC6PENDIENTEBLOQUEO
#bien
  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 1; despues que se realizo un pago parcial de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Multiservicio (Mall Proxim)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se bloquea el pago a firma conjunta - Pendiente <firmante1>
    And se valida que no se visualice el pago a firma conjunta <firmante2>
    And se valida que no se visualice el pago a firma conjunta <firmante3>

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa     | servicio            | codigoDeudor | monto | descripcion | firmante1 | firmante2 | firmante3 |
      | DEV      | Usuario01 | SI             | Ahorros Dólares | 100-7005765130 | MALL PROXIM | TODOS MIS SERVICIOS | EMP00000006  | 3000  |             | Usuario03 | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC7PENDIENTEBLOQUEO
#bien
  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 2; despues que se realizo un pago parcial de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Multiservicio (Mall Proxim)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante1>
    And se bloquea el pago a firma conjunta - Pendiente <firmante2>
    And se valida que no se visualice el pago a firma conjunta <firmante3>

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa     | servicio            | codigoDeudor | monto | descripcion | firmante1 | firmante2 | firmante3 |
      | DEV      | Usuario01 | SI             | Corriente Soles | 100-7001097373 | MALL PROXIM | TODOS MIS SERVICIOS | EMP00000006  | 815   | test01      | Usuario03 | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC8PENDIENTEBLOQUEO
#bien
  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 3; despues que se realizo un pago total de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Multiservicio (Mall Proxim)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante1>
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante2>
    And se bloquea el pago a firma conjunta - Pendiente <firmante3>

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa     | servicio            | codigoDeudor | descripcion | firmante1 | firmante2 | firmante3 |
      | DEV      | Usuario01 | SI             | Ahorros Dólares | 100-7005765130 | MALL PROXIM | TODOS MIS SERVICIOS | EMP00000006  |             | Usuario03 | Usuario04 | Usuario05 |
