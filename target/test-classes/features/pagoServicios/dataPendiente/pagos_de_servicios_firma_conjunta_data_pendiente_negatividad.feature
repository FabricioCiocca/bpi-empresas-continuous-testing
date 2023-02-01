@pago_servicios_data_pendiente_a_firma_conjunta_negatividad
Feature: Firma conjunta negatividad - Data Pendiente

  @unhappy_path @pagoServicio @ESC1PENDIENTENEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el firmante 2 introduce una contraseña erronea al realizar una autorizacion de pago total de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (San Silvestre)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se introduce la contraseña para autorizar el pago a Firma Conjunta - Pendiente <firmante2>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa       | servicio  | codigoDeudor | descripcion | firmante2 | password |
      | DEV      | Usuario01 | SI             | Ahorros Soles | 100-7005763928 | SAN SILVESTRE | PENSIONES | 0089002      | test01      | Usuario02 | xxxxx    |

  @unhappy_path @pagoServicio @ESC2PENDIENTENEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el firmante 2 introduce una contraseña erronea al realizar un bloqueo de pago parcial de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (San Silvestre)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se introduce la contraseña para bloquear el pago a Firma Conjunta - Pendiente <firmante2>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa       | servicio | codigoDeudor | monto | descripcion | firmante2 | password |
      | DEV      | Usuario01 | SI             | Ahorros Soles | 100-7005763928 | SAN SILVESTRE | VARIOS   | 0000P7110009 | 390   | test01      | Usuario02 | xxxxx    |

  @unhappy_path @pagoServicio @ESC3PENDIENTENEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el monto a pagar es mayor al limite de firma combinada al realizar un pago total de servicio a sola firma,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Multiservicio (Mall Proxim)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta efectuar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then se muestra el mensaje de error 'Monto ingresado supera límites'

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa     | servicio            | codigoDeudor | descripcion |
      | DEV      | Usuario01 | SI             | Corriente Dólares | 100-7001098298 | MALL PROXIM | TODOS MIS SERVICIOS | EMP00000006  |             |

  @unhappy_path @pagoServicio @ESC4PENDIENTENEGATIVIDADFC
#bien
  Scenario Outline: Valida que se pueda rechazar un pago si el saldo disponible es menor al monto de pago y menor igual a su limite permitido a firma conjunta;
  despues que se realizo un pago total de servicio a firma conjunta, de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Multiservicio (Mall Proxim)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante2>
    And el pago a firma conjunta se realiza sin exito
    And valida el saldo

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa     | servicio            | codigoDeudor | descripcion | firmante2 |
      | DEV      | Usuario01 | SI             | Ahorros Soles | 100-7005763928 | MALL PROXIM | TODOS MIS SERVICIOS | SX898022     |  test01     | Usuario02 |

  @unhappy_path @pagoServicio @ESC5PENDIENTENEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el firmante 1 introduce una contraseña erronea al realizar un bloqueo de pago parcial de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Multiservicio (Mall Proxim)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And introduce la contraseña para bloquear el pago a Firma Conjunta - Pendiente <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa     | servicio            | codigoDeudor | monto | descripcion | password |
      | DEV      | Usuario01 | SI             | Ahorros Dólares | 100-7005760855 | MALL PROXIM | TODOS MIS SERVICIOS | EMP00000006  | 3300  |             | xxxxx    |

#-----------------------------------------------------------------------------------------------------------------------

  @unhappy_path @pagoServicio @ESC6PENDIENTENEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el firmante 2 introduce una contraseña erronea al realizar una autorizacion de pago total de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Luz de Sur)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se introduce la contraseña para autorizar el pago a Firma Conjunta - Pendiente <firmante2>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa     | servicio | codigoDeudor | descripcion | firmante2 | password |
      | DEV      | Usuario01 | SI             | Corriente Dólares | 100-7001098298 | LUZ DEL SUR | LUZ      | SX898053     |             | Usuario02 | xxxxx    |

  @unhappy_path @pagoServicio @ESC7PENDIENTENEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el firmante 3 introduce una contraseña erronea al realizar una autorizacion de pago total de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (San Silvestre)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante2>
    And se introduce la contraseña para autorizar el pago a Firma Conjunta - Pendiente <firmante3>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa       | servicio  | codigoDeudor | descripcion | firmante2 | firmante3 | password |
      | DEV      | Usuario03 | SI             | Ahorros Soles | 100-7005763928 | SAN SILVESTRE | PENSIONES | 0089002      | test01      | Usuario04 | Usuario05 | xxxxx    |

  @unhappy_path @pagoServicio @ESC8PENDIENTENEGATIVIDADFC
#bien
  Scenario Outline: Valida que se pueda rechazar un pago si el saldo disponible es menor al monto de pago y menor igual a su limite permitido a firma conjunta;
  despues que se realizo un pago parcial de servicio a firma conjunta, de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Luz del sur)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante2>
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante3>
    And el pago a firma conjunta se realiza sin exito
    And valida el saldo

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa     | servicio | codigoDeudor | monto | descripcion | firmante2 | firmante3 |
      | DEV      | Usuario03 | SI             | Ahorros Dólares | 100-7005760855 | LUZ DEL SUR | LUZ      | SX898053     | 3300  |             | Usuario04 | Usuario05 |

  @unhappy_path @pagoServicio @ESC9PENDIENTENEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el firmante 1 introduce una contraseña erronea al realizar un bloqueo de pago parcial de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Multiservicio (San Silvestre)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And introduce la contraseña para bloquear el pago a Firma Conjunta - Pendiente <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa       | servicio | codigoDeudor | monto | descripcion | password |
      | DEV      | Usuario01 | SI             | Corriente Soles | 100-7001095133 | SAN SILVESTRE | VARIOS   | 0000P7110009 | 390   | test01      | xxxxx    |

  @unhappy_path @pagoServicio @ESC10PENDIENTENEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el firmante 2 introduce una contraseña erronea al realizar un bloqueo de pago total de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Luz del Sur)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se introduce la contraseña para bloquear el pago a Firma Conjunta - Pendiente <firmante2>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa     | servicio | codigoDeudor | descripcion | firmante2 | password |
      | DEV      | Usuario01 | SI             | Corriente Soles | 100-7001095133 | LUZ DEL SUR | LUZ      | SX898053     | test01      | Usuario02 | xxxxx    |

  @unhappy_path @pagoServicio @ESC11PENDIENTENEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el firmante 3 introduce una contraseña erronea al realizar un bloqueo de pago total de servicio a firma conjunta,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Luz del Sur)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante2>
    And se introduce la contraseña para bloquear el pago a Firma Conjunta - Pendiente <firmante3>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa     | servicio | codigoDeudor | descripcion | firmante2 | firmante3 | password |
      | DEV      | Usuario03 | SI             | Corriente Dólares | 100-7001098298 | LUZ DEL SUR | LUZ      | SX898053     | test01      | Usuario04 | Usuario05 | xxxxx    |

#-----------------------------------------------------------------------------------------------------------------------

  @unhappy_path @pagoServicio @ESC12PENDIENTENEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el firmante 3 introduce una contraseña erronea al realizar una autorizacion de pago parcial de servicio a firma combinada,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Luz del Sur)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante1>
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante2>
    And se introduce la contraseña para autorizar el pago a Firma Conjunta - Pendiente <firmante3>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa       | servicio | codigoDeudor | monto | descripcion | firmante1 | firmante2 | firmante3 | password |
      | DEV      | Usuario01 | SI             | Ahorros Soles | 100-7005763928 | SAN SILVESTRE | VARIOS   | 0000P7110004 | 1039  | test01      | Usuario03 | Usuario04 | Usuario05 | xxxxx    |

  @unhappy_path @pagoServicio @ESC13PENDIENTENEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el firmante 1 introduce una contraseña erronea al realizar una autorizacion de pago parcial de servicio a firma combinada,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Multiservicio (Mall Proxim)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se introduce la contraseña para autorizar el pago a Firma Conjunta - Pendiente <firmante1>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa     | servicio            | codigoDeudor | monto | descripcion | firmante1 | password |
      | DEV      | Usuario01 | SI             | Corriente Dólares | 100-7001098298 | MALL PROXIM | TODOS MIS SERVICIOS | EMP00000006  | 5000  |             | Usuario03 | xxxxx    |

  @unhappy_path @pagoServicio @ESC14PENDIENTENEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el firmante 2 introduce una contraseña erronea al realizar una autorizacion de pago total de servicio a firma combinada,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Multiservicio (Mall Proxim)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante1>
    And se introduce la contraseña para autorizar el pago a Firma Conjunta - Pendiente <firmante2>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa     | servicio            | codigoDeudor | descripcion | firmante1 | firmante2 | password |
      | DEV      | Usuario01 | SI             | Ahorros Soles | 100-7005763928 | MALL PROXIM | TODOS MIS SERVICIOS | EMP11111125  | test01      | Usuario03 | Usuario04 | xxxxx    |

  @unhappy_path @pagoServicio @ESC15PENDIENTENEGATIVIDADFC
#bien
  Scenario Outline: Valida que se pueda rechazar un pago si el saldo disponible es menor al monto de pago y menor igual a su limite permitido a firma combinada;
  despues que se realizo un pago total de servicio a firma conjunta, de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Luz del Sur)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante1>
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante2>
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante3>
    And el pago a firma combinada se realiza sin exito
    And valida el saldo

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa     | servicio | codigoDeudor | descripcion | firmante1 | firmante2 | firmante3 |
      | DEV      | Usuario01 | SI             | Corriente Soles | 100-7001095133 | LUZ DEL SUR | LUZ      | SX898053     |  test01     | Usuario03 | Usuario04 | Usuario05 |

  @unhappy_path @pagoServicio @ESC16PENDIENTENEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el firmante 1 introduce una contraseña erronea al realizar un bloqueo de pago parcial de servicio a firma combinada,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Luz del Sur)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se introduce la contraseña para bloquear el pago a Firma Conjunta - Pendiente <firmante1>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa     | servicio | codigoDeudor | monto | descripcion | firmante1 | password |
      | DEV      | Usuario01 | SI             | Corriente Dólares | 100-7001098298 | LUZ DEL SUR | LUZ      | SX898053     | 3300  |             | Usuario03 | xxxxx    |

  @unhappy_path @pagoServicio @ESC17PENDIENTENEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el firmante 2 introduce una contraseña erronea al realizar un bloqueo de pago parcial de servicio a firma combinada,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Luz del Sur)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante1>
    And se introduce la contraseña para bloquear el pago a Firma Conjunta - Pendiente <firmante2>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa     | servicio | codigoDeudor | monto | descripcion | firmante1 | firmante2 | password |
      | DEV      | Usuario01 | SI             | Ahorros Soles | 100-7005763928 | LUZ DEL SUR | LUZ      | SX898053     | 3300  | test01      | Usuario03 | Usuario04 | xxxxx    |

  @unhappy_path @pagoServicio @ESC18PENDIENTENEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el firmante 3 introduce una contraseña erronea al realizar un bloqueo de pago parcial de servicio a firma combinada,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Luz del Sur)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante1>
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante2>
    And se introduce la contraseña para bloquear el pago a Firma Conjunta - Pendiente <firmante3>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa     | servicio | codigoDeudor | monto | descripcion | firmante1 | firmante2 | firmante3 | password |
      | DEV      | Usuario01 | SI             | Ahorros Dólares | 100-7005760855 | LUZ DEL SUR | LUZ      | SX898053     | 3300  |             | Usuario03 | Usuario04 | Usuario05 | xxxxx    |