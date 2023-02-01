@pago_servicios_data_pendiente_a_sola_firma_negatividad
Feature: A sola firma negatividad - Data Pendiente

  @unhappy_path @pagoServicio @ESC1PENDIENTENEGATIVIDADSF
#bien
  Scenario Outline: Valida que se pueda rechazar un pago si el saldo disponible es menor al monto de pago y menor igual a su limite permitido a sola firma;
  despues que se realizo un pago total de servicio a sola firma, de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Luz del Sur)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>
    Then el pago a sola firma se realiza sin exito
    And valida el saldo

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa     | servicio | codigoDeudor | descripcion |
      | DEV      | Usuario03 | SI             | Ahorros Dólares | 100-7005760855 | LUZ DEL SUR | LUZ      | SX898051     |             |

  @unhappy_path @pagoServicio @ESC2PENDIENTENEGATIVIDADSF

  Scenario Outline: Valida que aparezca un error si se realiza un pago de servicio a sola firma,
  de Data Pendiente, para mas de diez (>10) codigo deudor, para una Empresa Uniservicio (Luz del Sur)
#bien
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta introducir los servicios para realizar un pago de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>
    Then se muestra el mensaje de error 'Limite de Servicios'

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta    | cuentaOrigen   | empresa     | servicio | codigoDeudor                                                                                       |
      | DEV      | Usuario04 | SI             | Ahorros Soles | 100-7005763928 | LUZ DEL SUR | LUZ      | SX898051-SX898054-SX898053-SX898049-SX898044-SX898052-SX898046-SX898043-SX898042-SX898050-11072220 |

  @unhappy_path @pagoServicio @ESC3PENDIENTENEGATIVIDADSF
#bien
  Scenario Outline: Valida que aparezca un error si se realiza un pago de servicio a sola firma,
  de Data Pendiente, para dos (2) codigo deudor iguales, para una Empresa Uniservicio (Luz del Sur)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta introducir los servicios para realizar un pago de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>
    Then se muestra el mensaje de error 'Servicio ya Agregado'

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa     | servicio | codigoDeudor      |
      | DEV      | Usuario01 | SI             | Ahorros Dólares | 100-7005760855 | LUZ DEL SUR | LUZ      | SX898051-SX898051 |

  @unhappy_path @pagoServicio @ESC4PENDIENTENEGATIVIDADSF
#bien
  Scenario Outline: Valida que aparezca un error si se introduce una contraseña erronea al realizar un pago total de servicio a sola firma,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (Luz del Sur)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta introducir la contraseña para realizar un pago total de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <descripcion>; <password>
    Then se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa     | servicio | codigoDeudor | descripcion | password |
      | DEV      | Usuario04 | SI             | Corriente Soles | 100-7001095133 | LUZ DEL SUR | LUZ      | SX898051     |             | xxxxx    |

  @unhappy_path @pagoServicio @ESC5PENDIENTENEGATIVIDADSF
#bien
  Scenario Outline: Valida que aparezca un error si se realiza un pago de servicio a sola firma,
  de Data Pendiente, para un (1) codigo deudor incorrecto, para una Empresa Uniservicio (Luz del Sur)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta introducir los servicios para realizar un pago de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>
    Then se muestra el mensaje de error 'No se encontraron cuotas'

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa     | servicio | codigoDeudor |
      | DEV      | Usuario04 | SI             | Corriente Dólares | 100-7001098298 | LUZ DEL SUR | LUZ      | SX89805x     |

  @unhappy_path @pagoServicio @ESC6PENDIENTENEGATIVIDADSF
#bien
  Scenario Outline: Valida que se pueda rechazar un pago si el saldo disponible es menor al monto de pago y menor igual a su limite permitido a sola firma;
  despues que se realizo un pago parcial de servicio a sola firma, de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (San Silvestre)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then el pago a sola firma se realiza sin exito
    And valida el saldo

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa       | servicio  | codigoDeudor | monto | descripcion |
      | DEV      | Usuario03 | SI             | Ahorros Dólares | 100-7005760855 | SAN SILVESTRE | PENSIONES | 0089002      | 328   |             |

  @unhappy_path @pagoServicio @ESC7PENDIENTENEGATIVIDADSF
#bien
  Scenario Outline: Valida que aparezca un error si el monto a pagar es 0 al realizar un pago parcial de servicio a sola firma,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (San Silvestre)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta introducir el monto de pago para realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then se muestra el mensaje de error 'Debes ingresar un monto válido'

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa       | servicio  | codigoDeudor | monto | descripcion |
      | DEV      | Usuario01 | SI             | Corriente Soles | 100-7001095133 | SAN SILVESTRE | PENSIONES | 0089002      | 0     | test01      |

  @unhappy_path @pagoServicio @ESC8PENDIENTENEGATIVIDADSF
#bien
  Scenario Outline: Valida que aparezca un error si el monto a pagar es mayor al monto total al realizar un pago parcial de servicio a sola firma,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (San Silvestre)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta introducir el monto de pago para realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then se muestra el mensaje de error 'Debes ingresar un monto válido'

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta        | cuentaOrigen   | empresa       | servicio  | codigoDeudor | monto | descripcion |
      | DEV      | Usuario01 | SI             | Corriente Dólares | 100-7001098298 | SAN SILVESTRE | PENSIONES | 0089002      | 5000  |             |

  @unhappy_path @pagoServicio @ESC9PENDIENTENEGATIVIDADSF
#bien
  Scenario Outline: Valida que aparezca un error si se realiza un pago parcial de servicio a sola firma,
  de Data Pendiente, de cero (0) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (San Silvestre)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta elegir las cuotas de pago para realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then se muestra el mensaje de error 'Debes seleccionar al menos una cuota para poder realizar el pago correspondiente.'

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa       | servicio  | codigoDeudor | monto | descripcion |
      | DEV      | Usuario01 | SI             | Ahorros Dólares | 100-7005760855 | SAN SILVESTRE | PENSIONES | 0089002      | 10    | test01      |

  @unhappy_path @pagoServicio @ESC10PENDIENTENEGATIVIDADSF
#bien
  Scenario Outline: Valida que se pueda rechazar un pago si el saldo disponible es menor al monto de pago y menor igual a su limite permitido a sola firma;
  despues que se realizo un pago parcial de servicio a sola firma, de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (San Silvestre)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto> ; <descripcion>
    Then el pago a sola firma se realiza sin exito
    And valida el saldo

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa       | servicio | codigoDeudor | monto | descripcion |
      | DEV      | Usuario02 | SI             | Ahorros Dólares | 100-7005760855 | SAN SILVESTRE | VARIOS   | 0000P7110003 | 143   |             |

  @unhappy_path @pagoServicio @ESC11PENDIENTENEGATIVIDADSF
#bien
  Scenario Outline: Valida que aparezca un error si el monto a pagar es mayor al monto total al realizar un pago parcial de servicio a sola firma,
  de Data Pendiente, de una (1) cuota, para un (1) codigo deudor, para una Empresa Uniservicio (San Silvestre)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta introducir el monto de pago para realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>
    Then se muestra el mensaje de error 'Debes ingresar un monto válido'

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa       | servicio | codigoDeudor | monto | descripcion |
      | DEV      | Usuario02 | SI             | Corriente Soles | 100-7001095133 | SAN SILVESTRE | VARIOS   | 0000P7110003 | 150   | test01      |

  @unhappy_path @pagoServicio @ESC12PENDIENTENEGATIVIDADSF

  Scenario Outline: Valida que se pueda rechazar un pago si el saldo disponible es menor al monto de pago y menor igual a su limite permitido a sola firma;
  despues que se realizo un pago parcial de servicio a sola firma, de Data Pendiente, de una (1) cuota, para mas de un (>1) codigo deudor, para una Empresa Multiservicio (Mall Proxim)
#bien
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto> ; <descripcion>
    Then el pago a sola firma se realiza sin exito
    And valida el saldo

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa     | servicio            | codigoDeudor            | monto | descripcion |
      | DEV      | Usuario03 | SI             | Ahorros Dólares | 100-7005760855 | MALL PROXIM | TODOS MIS SERVICIOS | EMP11111124-EMP11111126 | 200   |             |

  @unhappy_path @pagoServicio @ESC13PENDIENTENEGATIVIDADSF
#bien
  Scenario Outline: Valida que aparezca un error si se introduce una contraseña erronea al realizar un pago parcial de servicio a sola firma,
  de Data Pendiente, de una (1) cuota, para mas de un (>1) codigo deudor, para una Empresa Multiservicio (Mall Proxim)

    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta introducir la contraseña para realizar un pago parcial de tipo Pagos - De servicios - Pendiente <tipoCuenta>; <cuentaOrigen>; <empresa>; <servicio>; <codigoDeudor>; <monto>; <descripcion>; <password>
    Then se muestra el mensaje de error 'No cumple con los parámetros de seguridad'

    Examples:
      | ambiente | usuario   | credencialesOK | tipoCuenta      | cuentaOrigen   | empresa     | servicio            | codigoDeudor            | monto | descripcion | password |
      | DEV      | Usuario03 | SI             | Corriente Soles | 100-7001095133 | MALL PROXIM | TODOS MIS SERVICIOS | EMP11111124-EMP11111126 | 200   | test01      | xxxxx    |