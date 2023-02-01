@pago_servicios_data_remota_a_firma_conjunta
Feature: Firma conjunta - Data Remota
#terminado
  @happy_path @pagoServicio @ESC1REMOTAFC

  Scenario Outline: Valida que se pueda realizar un pago total de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Remota <firmante2>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | firmante2 |
      | DEV      | Usuario01 | SI             | Corriente Dólares | 100-7001097380 | OSLO    | VTA MERCADERI | 10086371     |             | Usuario02 |
      | DEV      | Usuario01 | SI             | Ahorros Soles     | 100-7005764266 | OSLO    | VTA MERCADERI | 10086371     | test01      | Usuario02 |

  @happy_path @pagoServicio @ESC2REMOTAFC
#bien
  Scenario Outline: Valida que se pueda realizar un pago parcial de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Remota <firmante2>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto | descripcion | firmante2 |
      | DEV      | Usuario02 | SI             | Ahorros Dólares | 100-7005765130 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 400   |             | Usuario01 |
      | DEV      | Usuario02 | SI             | Ahorros Soles   | 100-7005764266 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 200   | test01      | Usuario01 |

  @happy_path @pagoServicio @ESC3REMOTAFC

  Scenario Outline: Valida que se pueda realizar un pago total de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Remota <firmante2>
    And se autoriza el pago a Firma Conjunta - Remota <firmante3>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | firmante2 | firmante3 |
      | DEV      | Usuario03 | SI             | Corriente Soles   | 100-7001097373 | OSLO    | VTA MERCADERI | 10086371     | test01      | Usuario04 | Usuario05 |
      | DEV      | Usuario03 | SI             | Corriente Dólares | 100-7001097380 | OSLO    | VTA MERCADERI | 10086371     |             | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC4REMOTAFC
#bien
  Scenario Outline: Valida que se pueda realizar un pago parcial de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Remota <firmante2>
    And se autoriza el pago a Firma Conjunta - Remota <firmante3>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto | descripcion | firmante2 | firmante3 |
      | DEV      | Usuario03 | SI             | Corriente Dólares | 100-7001097380 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 900   |             | Usuario04 | Usuario05 |
      | DEV      | Usuario03 | SI             | Ahorros Soles     | 100-7005764266 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 300   | test01      | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC5REMOTAFC

  Scenario Outline: Valida que se pueda realizar un pago total de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se autoriza el pago a Firma Conjunta - Remota <firmante1>
    And se autoriza el pago a Firma Conjunta - Remota <firmante2>
    And se autoriza el pago a Firma Conjunta - Remota <firmante3>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | firmante1 | firmante2 | firmante3 |
      | DEV      | Usuario01 | SI             | Corriente Soles | 100-7001097373 | OSLO    | VTA MERCADERI | 10086371     | test01      | Usuario03 | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC6REMOTAFC
#bien
  Scenario Outline: Valida que se pueda realizar un pago parcial de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se autoriza el pago a Firma Conjunta - Remota <firmante1>
    And se autoriza el pago a Firma Conjunta - Remota <firmante2>
    And se autoriza el pago a Firma Conjunta - Remota <firmante3>
    And valida el Saldo y Movimiento

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto | descripcion | firmante1 | firmante2 | firmante3 |
      | DEV      | Usuario01 | SI             | Corriente Soles   | 100-7001097373 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 250   | test01      | Usuario03 | Usuario04 | Usuario05 |
      | DEV      | Usuario01 | SI             | Corriente Dólares | 100-7001097380 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 1001  |             | Usuario03 | Usuario04 | Usuario05 |