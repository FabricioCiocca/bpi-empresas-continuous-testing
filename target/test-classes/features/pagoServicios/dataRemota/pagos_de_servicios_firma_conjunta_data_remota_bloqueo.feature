@pago_servicios_data_remota_a_firma_conjunta_bloqueo
Feature: Firma conjunta bloqueo - Data Remota
#terminado
  @happy_path @pagoServicio @ESC1REMOTABLOQUEO

  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 1; despues que se realizo un pago total de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And bloquea el pago a fima conjunta - Remota
    And se valida que no se visualice el pago a firma conjunta <firmante2>

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | firmante2 |
      | DEV      | Usuario01 | SI             | Ahorros Dólares | 100-7005765130 | OSLO    | VTA MERCADERI | 10086371     |             | Usuario02 |

  @happy_path @pagoServicio @ESC2REMOTABLOQUEO

  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 2; despues que se realizo un pago total de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se bloquea el pago a firma conjunta - Remota <firmante2>

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | firmante2 |
      | DEV      | Usuario01 | SI             | Corriente Dólares | 100-7001097380 | OSLO    | VTA MERCADERI | 10086371     |             | Usuario02 |

  @happy_path @pagoServicio @ESC3REMOTABLOQUEO
#bien
  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 2; despues que se realizo un pago parcial de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se bloquea el pago a firma conjunta - Remota <firmante2>

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa | servicio       | codigoDeudor | monto | descripcion | firmante2 |
      | DEV      | Usuario01 | SI             | Corriente Soles | 100-7001097373 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 200   | test01      | Usuario02 |

  @happy_path @pagoServicio @ESC4REMOTABLOQUEO

  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 1; despues que se realizo un pago total de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And bloquea el pago a fima conjunta - Remota
    And se valida que no se visualice el pago a firma conjunta <firmante2>
    And se valida que no se visualice el pago a firma conjunta <firmante3>

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | firmante2 | firmante3 |
      | DEV      | Usuario03 | SI             | Ahorros Soles | 100-7005764266 | OSLO    | VTA MERCADERI | 10086371     | test01      | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC5REMOTABLOQUEO

  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 2; despues que se realizo un pago total de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se bloquea el pago a firma conjunta - Remota <firmante2>
    And se valida que no se visualice el pago a firma conjunta <firmante3>

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | firmante2 | firmante3 |
      | DEV      | Usuario03 | SI             | Corriente Soles | 100-7001097373 | OSLO    | VTA MERCADERI | 10086371     | test01      | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC6REMOTABLOQUEO

  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 3; despues que se realizo un pago total de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Remota <firmante2>
    And se bloquea el pago a firma conjunta - Remota <firmante3>

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | firmante2 | firmante3 |
      | DEV      | Usuario03 | SI             | Ahorros Soles | 100-7005764266 | OSLO    | VTA MERCADERI | 10086371     | test01      | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC7REMOTABLOQUEO
#bien
  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 2; despues que se realizo un pago parcial de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se bloquea el pago a firma conjunta - Remota <firmante2>
    And se valida que no se visualice el pago a firma conjunta <firmante3>

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto | descripcion | firmante2 | firmante3 |
      | DEV      | Usuario03 | SI             | Corriente Soles | 100-7001097373 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 250   | test01      | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC8REMOTABLOQUEO
#bien
  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 1; despues que se realizo un pago parcial de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And bloquea el pago a fima conjunta - Remota
    And se valida que no se visualice el pago a firma conjunta <firmante2>
    And se valida que no se visualice el pago a firma conjunta <firmante3>

    Examples:
      | ambiente | firmante1 | credencialesOK |  tipoCuenta      | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto | descripcion | firmante2 | firmante3 |
      | DEV      | Usuario03 | SI             |  Ahorros Dólares | 100-7005765130 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 900   |             | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC9REMOTABLOQUEO

  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 1; despues que se realizo un pago total de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se bloquea el pago a firma conjunta - Remota <firmante1>
    And se valida que no se visualice el pago a firma conjunta <firmante2>
    And se valida que no se visualice el pago a firma conjunta <firmante3>

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | firmante1 | firmante2 | firmante3 |
      | DEV      | Usuario01 | SI             | Corriente Dólares | 100-7001097380 | OSLO    | VTA MERCADERI | 10086371     |             | Usuario03 | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC10REMOTABLOQUEO

  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 2; despues que se realizo un pago total de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se autoriza el pago a Firma Conjunta - Remota <firmante1>
    And se bloquea el pago a firma conjunta - Remota <firmante2>
    And se valida que no se visualice el pago a firma conjunta <firmante3>

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | firmante1 | firmante2 | firmante3 |
      | DEV      | Usuario01 | SI             | Ahorros Dólares | 100-7005765130 | OSLO    | VTA MERCADERI | 10086371     |             | Usuario03 | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC11REMOTABLOQUEO

  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 3; despues que se realizo un pago total de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se autoriza el pago a Firma Conjunta - Remota <firmante1>
    And se autoriza el pago a Firma Conjunta - Remota <firmante2>
    And se bloquea el pago a firma conjunta - Remota <firmante3>

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | firmante1 | firmante2 | firmante3 |
      | DEV      | Usuario01 | SI             | Corriente Dólares | 100-7001097380 | OSLO    | VTA MERCADERI | 10086371     |             | Usuario03 | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC12REMOTABLOQUEO
#bien
  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 2; despues que se realizo un pago parcial de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se autoriza el pago a Firma Conjunta - Remota <firmante1>
    And se bloquea el pago a firma conjunta - Remota <firmante2>
    And se valida que no se visualice el pago a firma conjunta <firmante3>

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto | descripcion | firmante1 | firmante2 | firmante3 |
      | DEV      | Usuario01 | SI             | Corriente Soles | 100-7001097373 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 250   | test01      | Usuario03 | Usuario04 | Usuario05 |

  @happy_path @pagoServicio @ESC13REMOTABLOQUEO
#bien
  Scenario Outline: Valida que se pueda realizar un bloqueo por el firmante 1; despues que se realizo un pago parcial de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se bloquea el pago a firma conjunta - Remota <firmante1>
    And se valida que no se visualice el pago a firma conjunta <firmante2>
    And se valida que no se visualice el pago a firma conjunta <firmante3>

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto | descripcion | firmante1 | firmante2 | firmante3 |
      | DEV      | Usuario01 | SI             | Ahorros Dólares | 100-7005765130 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 1001  |             | Usuario03 | Usuario04 | Usuario05 |
