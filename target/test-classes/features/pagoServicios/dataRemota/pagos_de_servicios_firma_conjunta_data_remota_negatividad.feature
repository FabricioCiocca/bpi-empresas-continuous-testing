@pago_servicios_data_remota_a_firma_conjunta_negatividad
Feature: Firma conjunta negatividad - Data Remota
#terminado
  @unhappy_path @pagoServicio @ESC1REMOTANEGATIVIDADFC

  Scenario Outline: Valida que aparezca un error si el firmante 2 introduce una contraseña erronea al realizar una autorizacion de pago total de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se introduce la contraseña para autorizar el pago a Firma Conjunta - Remota <firmante2>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | firmante2 | password |
      | DEV      | Usuario01 | SI             | Ahorros Dólares | 100-7005760855 | OSLO    | VTA MERCADERI | 10086371     |             | Usuario02 | xxxxx    |

  @unhappy_path @pagoServicio @ESC2REMOTANEGATIVIDADFC

  Scenario Outline: Valida que aparezca un error si el monto a pagar es mayor al limite de firma combinada al realizar un pago total de servicio a sola firma,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta efectuar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then se muestra el mensaje de error 'Monto ingresado supera límites'

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion |
      | DEV      | Usuario01 | SI             | Corriente Dólares | 100-7001098298 | OSLO    | VTA MERCADERI | 10086371     |             |

  @unhappy_path @pagoServicio @ESC3REMOTANEGATIVIDADFC

  Scenario Outline: Valida que se pueda rechazar un pago si el saldo disponible es menor al monto de pago y menor igual a su limite permitido a firma conjunta;
  despues que se realizo un pago total de servicio a firma conjunta, de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Remota <firmante2>
    And el pago a firma conjunta se realiza sin exito
    And valida el saldo

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | firmante2 |
      | DEV      | Usuario01 | SI             | Ahorros Soles | 100-7005763928 | OSLO    | VTA MERCADERI | 10086371     | test01      | Usuario02 |

  @unhappy_path @pagoServicio @ESC4REMOTANEGATIVIDADFC

  Scenario Outline: Valida que aparezca un error si el firmante 2 introduce una contraseña erronea al realizar un bloqueo de pago total de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se introduce la contraseña para bloquear el pago a Firma Conjunta - Remota <firmante2>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | firmante2 | password |
      | DEV      | Usuario01 | SI             | Ahorros Soles | 100-7005763928 | OSLO    | VTA MERCADERI | 10086371     | test01      | Usuario02 | xxxxx    |

  @unhappy_path @pagoServicio @ESC5REMOTANEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el firmante 1 introduce una contraseña erronea al realizar un bloqueo de pago parcial de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And introduce la contraseña para bloquear el pago a Firma Conjunta - Remota <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto | descripcion | password |
      | DEV      | Usuario01 | SI             | Ahorros Dólares | 100-7005760855 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 150   |             | xxxxx    |

  @unhappy_path @pagoServicio @ESC6REMOTANEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el firmante 2 introduce una contraseña erronea al realizar un bloqueo de pago parcial de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se introduce la contraseña para bloquear el pago a Firma Conjunta - Remota <firmante2>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto | descripcion | firmante2 | password |
      | DEV      | Usuario01 | SI             | Ahorros Soles | 100-7005763928 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 139   | test01      | Usuario02 | xxxxx    |

  @unhappy_path @pagoServicio @ESC7REMOTANEGATIVIDADFC
#bien
  Scenario Outline: Valida que se pueda rechazar un pago si el saldo disponible es menor al monto de pago y menor igual a su limite permitido a firma conjunta;
  despues que se realizo un pago parcial de servicio a firma conjunta, de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Remota <firmante2>
    And el pago a firma conjunta se realiza sin exito
    And valida el saldo

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto | descripcion | firmante2 |
      | DEV      | Usuario01 | SI             | Ahorros Soles | 100-7005763928 | COSTAMAR | RECAUDO LIBRE | 0567297048   |  139  | test01      | Usuario02 |

#-----------------------------------------------------------------------------------------------------------------------

  @unhappy_path @pagoServicio @ESC8REMOTANEGATIVIDADFC

  Scenario Outline: Valida que aparezca un error si el firmante 2 introduce una contraseña erronea al realizar una autorizacion de pago total de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se introduce la contraseña para autorizar el pago a Firma Conjunta - Remota <firmante2>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | firmante2 | password |
      | DEV      | Usuario01 | SI             | Ahorros Soles | 100-7005763928 | OSLO    | VTA MERCADERI | 10086371     | test01      | Usuario02 | xxxxx    |

  @unhappy_path @pagoServicio @ESC9REMOTANEGATIVIDADFC

  Scenario Outline: Valida que aparezca un error si el firmante 3 introduce una contraseña erronea al realizar una autorizacion de pago total de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Remota <firmante2>
    And se introduce la contraseña para autorizar el pago a Firma Conjunta - Remota <firmante3>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | firmante2 | firmante3 | password |
      | DEV      | Usuario03 | SI             | Ahorros Dólares | 100-7005760855 | OSLO    | VTA MERCADERI | 10086371     |             | Usuario04 | Usuario05 | xxxxx    |

  @unhappy_path @pagoServicio @ESC10REMOTANEGATIVIDADFC

  Scenario Outline: Valida que se pueda rechazar un pago si el saldo disponible es menor al monto de pago y menor igual a su limite permitido a firma conjunta;
  despues que se realizo un pago total de servicio a firma conjunta, de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Remota <firmante2>
    And se autoriza el pago a Firma Conjunta - Remota <firmante3>
    And el pago a firma conjunta se realiza sin exito
    And valida el saldo

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | firmante2 | firmante3 |
      | DEV      | Usuario03 | SI             | Corriente Dólares | 100-7001098298 | OSLO    | VTA MERCADERI | 10086371     |             | Usuario04 | Usuario05 |

  @unhappy_path @pagoServicio @ESC11REMOTANEGATIVIDADFC

  Scenario Outline: Valida que aparezca un error si el firmante 2 introduce una contraseña erronea al realizar un bloqueo de pago total de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se introduce la contraseña para bloquear el pago a Firma Conjunta - Remota <firmante2>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen     | empresa | servicio      | codigoDeudor | descripcion | firmante2 | password |
      | DEV      | Usuario01 | SI             | Corriente Dólares | 100-7001098298 | OSLO    | VTA MERCADERI | 10086371     |             | Usuario02 | xxxxx    |

  @unhappy_path @pagoServicio @ESC12REMOTANEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el firmante 3 introduce una contraseña erronea al realizar un bloqueo de pago parcial de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And se autoriza el pago a Firma Conjunta - Remota <firmante2>
    And se introduce la contraseña para bloquear el pago a Firma Conjunta - Remota <firmante3>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto |descripcion | firmante2 | firmante3 | password |
      | DEV      | Usuario03 | SI             | Ahorros Dólares | 100-7005760855 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 900   |            | Usuario04 | Usuario05 | xxxxx    |

  @unhappy_path @pagoServicio @ESC13REMOTANEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el firmante 1 introduce una contraseña erronea al realizar un bloqueo de pago parcial de servicio a firma conjunta,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma conjunta se realiza con exito
    And introduce la contraseña para bloquear el pago a Firma Conjunta - Remota <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | firmante1 | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto | descripcion | password |
      | DEV      | Usuario01 | SI             | Corriente Soles | 100-7001095133 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 200   | test01      | xxxxx    |

#-----------------------------------------------------------------------------------------------------------------------

  @unhappy_path @pagoServicio @ESC14REMOTANEGATIVIDADFC

  Scenario Outline: Valida que aparezca un error si el firmante 1 introduce una contraseña erronea al realizar una autorizacion de pago total de servicio a firma combinada,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se introduce la contraseña para autorizar el pago a Firma Conjunta - Remota <firmante1>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | firmante1 | password |
      | DEV      | Usuario01 | SI             | Corriente Dólares | 100-7001098298 | OSLO    | VTA MERCADERI | 10086371     |             | Usuario03 | xxxxx    |

  @unhappy_path @pagoServicio @ESC15REMOTANEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el firmante 2 introduce una contraseña erronea al realizar una autorizacion de pago parcial de servicio a firma combinada,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se autoriza el pago a Firma Conjunta - Remota <firmante1>
    And se introduce la contraseña para autorizar el pago a Firma Conjunta - Remota <firmante2>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto | descripcion | firmante1 | firmante2 | password |
      | DEV      | Usuario01 | SI             | Ahorros Soles | 100-7005763928 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 300   | test01      | Usuario03 | Usuario04 | xxxxx    |

  @unhappy_path @pagoServicio @ESC16REMOTANEGATIVIDADFC

  Scenario Outline: Valida que aparezca un error si el firmante 3 introduce una contraseña erronea al realizar una autorizacion de pago total de servicio a firma combinada,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se autoriza el pago a Firma Conjunta - Remota <firmante1>
    And se autoriza el pago a Firma Conjunta - Remota <firmante2>
    And se introduce la contraseña para autorizar el pago a Firma Conjunta - Remota <firmante3>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | firmante1 | firmante2 | firmante3 | password |
      | DEV      | Usuario01 | SI             | Ahorros Dólares | 100-7005763928 | OSLO    | VTA MERCADERI | 10086371     |             | Usuario03 | Usuario04 | Usuario05 | xxxxx    |

  @unhappy_path @pagoServicio @ESC17REMOTANEGATIVIDADFC
#bien
  Scenario Outline: Valida que se pueda rechazar un pago si el saldo disponible es menor al monto de pago y menor igual a su limite permitido a firma combinada;
  despues que se realizo un pago parcial de servicio a firma conjunta, de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se autoriza el pago a Firma Conjunta - Remota <firmante1>
    And se autoriza el pago a Firma Conjunta - Remota <firmante2>
    And se autoriza el pago a Firma Conjunta - Remota <firmante3>
    And el pago a firma combinada se realiza sin exito
    And valida el saldo

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto | descripcion | firmante1 | firmante2 | firmante3 |
      | DEV      | Usuario01 | SI             | Corriente Soles | 100-7001095133 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 300   | test01      | Usuario03 | Usuario04 | Usuario05 |

  @unhappy_path @pagoServicio @ESC18REMOTANEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el firmante 1 introduce una contraseña erronea al realizar un bloqueo de pago parcial de servicio a firma combinada,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se introduce la contraseña para bloquear el pago a Firma Conjunta - Remota <firmante1>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto | descripcion | firmante1 | password |
      | DEV      | Usuario01 | SI             | Ahorros Soles | 100-7005763928 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 300   | test01      | Usuario03 | xxxxx    |

  @unhappy_path @pagoServicio @ESC19REMOTANEGATIVIDADFC

  Scenario Outline: Valida que aparezca un error si el firmante 2 introduce una contraseña erronea al realizar un bloqueo de pago total de servicio a firma combinada,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se autoriza el pago a Firma Conjunta - Remota <firmante1>
    And se introduce la contraseña para bloquear el pago a Firma Conjunta - Remota <firmante2>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | firmante1 | firmante2 | password |
      | DEV      | Usuario01 | SI             | Corriente Soles | 100-7001095133 | OSLO    | VTA MERCADERI | 10086371     | test01      | Usuario03 | Usuario04 | xxxxx    |

  @unhappy_path @pagoServicio @ESC20REMOTANEGATIVIDADFC
#bien
  Scenario Outline: Valida que aparezca un error si el firmante 3 introduce una contraseña erronea al realizar un bloqueo de pago parcial de servicio a firma combinada,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a firma combinada se realiza con exito
    And se autoriza el pago a Firma Conjunta - Remota <firmante1>
    And se autoriza el pago a Firma Conjunta - Remota <firmante2>
    And se introduce la contraseña para bloquear el pago a Firma Conjunta - Remota <firmante3>; <password>
    And se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | operador  | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto | descripcion | firmante1 | firmante2 | firmante3 | password |
      | DEV      | Usuario01 | SI             | Ahorros Soles | 100-7005763928 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 250   | test01      | Usuario03 | Usuario04 | Usuario05 | xxxxx    |