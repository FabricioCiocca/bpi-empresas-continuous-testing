@pago_servicios_data_remota_a_sola_firma_negatividad
Feature: A sola firma negatividad - Data Remota
#terminado
  @unhappy_path @pagoServicio @ESC1REMOTANEGATIVIDADSF

  Scenario Outline: Valida que se pueda rechazar un pago si el saldo disponible es menor al monto de pago y menor igual a su limite permitido a sola firma;
  despues que se realizo un pago total de servicio a sola firma, de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a sola firma se realiza sin exito
    And valida el saldo

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion |
      | DEV      | Usuario04 | SI             | Ahorros Dólares | 100-7005760855 | OSLO    | VTA MERCADERI | 10086371     |             |

  @unhappy_path @pagoServicio @ESC2REMOTANEGATIVIDADSF

  Scenario Outline: Valida que aparezca un error si se realiza un pago de servicio a sola firma,
  de Data Remota, para mas de diez (>10) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta introducir los servicios para realizar un pago de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>
    Then se muestra el mensaje de error 'Limite de Servicios'

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa | servicio      | codigoDeudor                                             |
      | DEV      | Usuario04 | SI             | Ahorros Soles | 100-7005763928 | OSLO    | VTA MERCADERI | 10086371-43417134-46975907-46485500-77250601-6-7-8-9-0-1 |

  @unhappy_path @pagoServicio @ESC3REMOTANEGATIVIDADSF

  Scenario Outline: Valida que aparezca un error si se realiza un pago de servicio a sola firma,
  de Data Remota, para dos (2) codigo deudor iguales, para una Empresa Uniservicio (Oslo)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta introducir los servicios para realizar un pago de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>
    Then se muestra el mensaje de error 'Servicio ya Agregado'

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa | servicio      | codigoDeudor      |
      | DEV      | Usuario01 | SI             | Ahorros Dólares | 100-7005760855 | OSLO    | VTA MERCADERI | 10086371-43417134 |

  @unhappy_path @pagoServicio @ESC4REMOTANEGATIVIDADSF

  Scenario Outline: Valida que aparezca un error si se introduce una contraseña erronea al realizar un pago total de servicio a sola firma,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Oslo)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta introducir la contraseña para realizar un pago total de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>; <password>
    Then se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | password |
      | DEV      | Usuario04 | SI             | Corriente Soles | 100-7001095133 | OSLO    | VTA MERCADERI | 10086371     |             | xxxxx    |

  @unhappy_path @pagoServicio @ESC5REMOTANEGATIVIDADSF

  Scenario Outline: Valida que aparezca un error si se realiza un pago de servicio a sola firma,
  de Data Remota, para un (1) codigo deudor incorrecto, para una Empresa Uniservicio (Oslo)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta introducir los servicios para realizar un pago de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>
    Then se muestra el mensaje de error 'No se encontraron cuotas'

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa | servicio      | codigoDeudor |
      | DEV      | Usuario04 | SI             | Corriente Dólares | 100-7001098298 | OSLO    | VTA MERCADERI | 10086371     |

  @unhappy_path @pagoServicio @ESC6REMOTANEGATIVIDADSF
#bien
  Scenario Outline: Valida que se pueda rechazar un pago si el saldo disponible es menor al monto de pago y menor igual a su limite permitido a sola firma;
  despues que se realizo un pago parcial de servicio a sola firma, de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto> ; <descripcion>
    Then el pago a sola firma se realiza sin exito
    And valida el saldo

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto | descripcion |
      | DEV      | Usuario03 | SI             | Ahorros Dólares | 100-7005760855 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 191   |             |

  @unhappy_path @pagoServicio @ESC7REMOTANEGATIVIDADSF
#bien
  Scenario Outline: Valida que aparezca un error si el monto a pagar es mayor al monto total al realizar un pago parcial de servicio a sola firma,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta introducir el monto de pago para realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then se muestra el mensaje de error 'Debes ingresar un monto válido'

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto | descripcion |
      | DEV      | Usuario03 | SI             | Corriente Dólares | 100-7001098298 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 5000  |             |

  @unhappy_path @pagoServicio @ESC8REMOTANEGATIVIDADSF
#bien
  Scenario Outline: Valida que aparezca un error si el monto a pagar es 0 al realizar un pago parcial de servicio a sola firma,
  de Data Remota, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta introducir el monto de pago para realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then se muestra el mensaje de error 'Debes ingresar un monto válido'

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto | descripcion |
      | DEV      | Usuario03 | SI             | Corriente Soles | 100-7001095133 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 0     | test01      |

  @unhappy_path @pagoServicio @ESC9REMOTANEGATIVIDADSF
#bien
  Scenario Outline: Valida que aparezca un error si se realiza un pago parcial de servicio a sola firma,
  de Data Remota, de cero (0) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta elegir las cuotas de pago para realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then se muestra el mensaje de error 'Debes seleccionar al menos una cuota para poder realizar el pago correspondiente.'

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto | descripcion |
      | DEV      | Usuario03 | SI             | Ahorros Soles | 100-7005763928 | COSTAMAR | RECAUDO LIBRE | 0567297048   | 10    | test01      |

  @unhappy_path @pagoServicio @ESC10REMOTANEGATIVIDADSF
#bien
  Scenario Outline: Valida que aparezca un error si se introduce una contraseña erronea al realizar un pago parcial de servicio a sola firma,
  de Data Remota, de una (1) cuota, para mas de un (>1) codigo deudor, para una Empresa Uniservicio (Costamar)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta introducir la contraseña para realizar un pago parcial de tipo Pagos - De servicios - Remota <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>; <password>
    Then se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa  | servicio      | codigoDeudor | monto | descripcion | password |
      | DEV      | Usuario03 | SI             | Corriente Soles | 100-7001095133 | COSTAMAR | RECAUDO LIBRE | 0567297048-2 | 191   | test01      | xxxxx    |
