@pago_servicios_sin_data_a_firma_conjunta
Feature: Firma Conjunta - Sin Data

  @happy_path @pagoServicio @ESC1FIRMACONJUNTASINDATA
  Scenario Outline: Valida que se pueda realizar un pago de servicio a firma conjunta, sin data, de una (1) cuota, para un (1) codigo deudor, para la empresa FE Y ALEGRIA
    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza de manera exitosa <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Sin data <firmante2>
    And validamos el Saldo y Movimiento

    Examples:
      | ambiente | firmante1 | password    | tipoCuenta        | cuentaOrigen   | empresa      | servicio   | codigoDeudor | monto | descripcion | firmante2 | credencialesOK |
      | DEV      | Usuario06 | Interbank*1 | Corriente Soles   | 100-7001095125 | FE Y ALEGRÍA | DONACIONES | 01234567     | 1000  | CP01        | Usuario07 | SI             |
   #   | DEV      | Usuario07 | Interbank*1 | Corriente Dólares | 100-7001097364 | FE Y ALEGRÍA | DONACIONES | 01234568     | 16000 | CP02        | Usuario06 | SI             |
   #   | DEV      | Usuario06 | Interbank*1 | Ahorros Soles     | 100-7005760848 | FE Y ALEGRÍA | DONACIONES | 01234569     | 900   | CP03        | Usuario07 | SI             |
   #   | DEV      | Usuario06 | Interbank*1 | Ahorros Dólares   | 100-7005763919 | FE Y ALEGRÍA | DONACIONES | 01234510     | 3400  | CP04        | Usuario07 | SI             |

  @happy_path @pagoServicio @ESC2FIRMACONJUNTASINDATA
  Scenario Outline: Valida que se pueda realizar un pago de servicio a firma conjunta, sin data, de una (1) cuota, para un (1) codigo deudor, para la empresa SEMBRANDO
    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza de manera exitosa <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Sin data <firmante2>
    And validamos el Saldo y Movimiento

    Examples:
      | ambiente | firmante1 | password    | tipoCuenta        | cuentaOrigen   | empresa   | servicio           | codigoDeudor | monto | descripcion | firmante2 | credencialesOK |
      | DEV      | Usuario06 | Interbank*1 | Corriente Soles   | 100-7001095125 | SEMBRANDO | DONACIONES - SOLES | 01234568     | 1000  | CP05        | Usuario07 | SI             |
      | DEV      | Usuario07 | Interbank*1 | Corriente Dólares | 100-7001097364 | SEMBRANDO | DONACIONES DOLARES | 01234569     | 3900  | CP06        | Usuario06 | SI             |
      | DEV      | Usuario06 | Interbank*1 | Ahorros Soles     | 100-7005760848 | SEMBRANDO | DONACIONES - SOLES | 01234510     | 900   | CP07        | Usuario07 | SI             |
      | DEV      | Usuario06 | Interbank*1 | Ahorros Dólares   | 100-7005763919 | SEMBRANDO | DONACIONES DOLARES | 01234511     | 3400  | CP08        | Usuario07 | SI             |

  @happy_path @pagoServicio @ESC3FIRMACONJUNTASINDATA
  Scenario Outline: Valida que se pueda realizar un pago de servicio a firma conjunta 2, de Sin Data, de una (1) cuota, para un (1) codigo deudor, para la empresa Fe y alegria (codigo deudor <= 800)
    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza de manera exitosa <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Sin data <firmante2>
    And se autoriza el pago a Firma Conjunta - Sin data <firmante3>
    And validamos el Saldo y Movimiento

    Examples:
      | ambiente | firmante1 | password    | tipoCuenta        | cuentaOrigen   | empresa      | servicio   | codigoDeudor | monto | descripcion | firmante2 | firmante3 | credencialesOK |
      | DEV      | Usuario08 | Interbank*1 | Corriente Soles   | 100-7001095125 | FE Y ALEGRÍA | DONACIONES | 01234567     | 8000  | CP01        | Usuario09 | Usuario10 | SI             |
      | DEV      | Usuario09 | Interbank*1 | Corriente Dólares | 100-7001097364 | FE Y ALEGRÍA | DONACIONES | 01234568     | 33000 |             | Usuario10 | Usuario08 | SI             |
      | DEV      | Usuario08 | Interbank*1 | Ahorros Soles     | 100-7005760848 | FE Y ALEGRÍA | DONACIONES | 01234568     | 7900  | CP03        | Usuario10 | Usuario09 | SI             |
      | DEV      | Usuario09 | Interbank*1 | Ahorros Dólares   | 100-7005763919 | FE Y ALEGRÍA | DONACIONES | 01234568     | 33000 |             | Usuario08 | Usuario10 | SI             |

  @happy_path @pagoServicio @ESC4FIRMACONJUNTASINDATA
  Scenario Outline: Valida que se pueda realizar un pago de servicio a firma conjunta 2, de Sin Data, de una (1) cuota, para un (1) codigo deudor, para la empresa SEMBRANDO (codigo deudor <= 800)
    Given que el usuario <firmante1> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza de manera exitosa <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Sin data <firmante2>
    And se autoriza el pago a Firma Conjunta - Sin data <firmante3>
    And validamos el Saldo y Movimiento

    Examples:
      | ambiente | firmante1 | password    | tipoCuenta        | cuentaOrigen   | empresa   | servicio           | codigoDeudor | monto | descripcion | firmante2 | firmante3 | credencialesOK |
      | DEV      | Usuario08 | Interbank*1 | Corriente Soles   | 100-7001095125 | SEMBRANDO | DONACIONES - SOLES | 01234567     | 8000  | CP01        | Usuario09 | Usuario10 | SI             |
      | DEV      | Usuario09 | Interbank*1 | Corriente Dólares | 100-7001097364 | SEMBRANDO | DONACIONES DOLARES | 01234568     | 7900  |             | Usuario10 | Usuario08 | SI             |
      | DEV      | Usuario08 | Interbank*1 | Ahorros Soles     | 100-7005760848 | SEMBRANDO | DONACIONES DOLARES | 01234568     | 1100  | CP03        | Usuario10 | Usuario09 | SI             |
      | DEV      | Usuario09 | Interbank*1 | Ahorros Dólares   | 100-7005763919 | SEMBRANDO | DONACIONES - SOLES | 01234568     | 32560 |             | Usuario08 | Usuario10 | SI             |


  @happy_path @pagoServicio @ESC5FIRMACONJUNTASINDATA
  Scenario Outline: Valida que se pueda realizar un pago de servicio a firma conjunta, de Sin Data, de una (1) cuota, para un (1) codigo deudor, para la empresa FE Y ALEGRIA (codigo deudor <= 800)
    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza de manera exitosa <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Sin data <firmante1>
    And se autoriza el pago a Firma Conjunta - Sin data <firmante2>
    And se autoriza el pago a Firma Conjunta - Sin data <firmante3>
    And validamos el Saldo y Movimiento

    Examples:
      | ambiente | operador  | password    | tipoCuenta        | cuentaOrigen   | empresa      | servicio   | codigoDeudor | monto | descripcion | firmante1 | firmante2 | firmante3 | credencialesOK |
      | DEV      | Usuario06 | Interbank*1 | Corriente Soles   | 100-7001095125 | FE Y ALEGRÍA | DONACIONES | 01234567     | 15100 | CP01        | Usuario08 | Usuario09 | Usuario10 | SI             |
      | DEV      | Usuario06 | Interbank*1 | Corriente Dólares | 100-7001097364 | FE Y ALEGRÍA | DONACIONES | 01234568     | 5000  |             | Usuario09 | Usuario08 | Usuario10 | SI             |
      | DEV      | Usuario06 | Interbank*1 | Ahorros Soles     | 100-7005760848 | FE Y ALEGRÍA | DONACIONES | 01234569     | 15100 | CP03        | Usuario10 | Usuario09 | Usuario08 | SI             |
      | DEV      | Usuario06 | Interbank*1 | Ahorros Dólares   | 100-7005763919 | FE Y ALEGRÍA | DONACIONES | 01234510     | 5000  |             | Usuario10 | Usuario08 | Usuario09 | SI             |


  @happy_path @pagoServicio @ESC6FIRMACONJUNTASINDATA
  Scenario Outline: Valida que se pueda realizar un pago de servicio a firma conjunta, de Sin Data, de una (1) cuota, para un (1) codigo deudor, para la empresa SEMBRANDO
    Given que el usuario <operador> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de estado pendiente <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza de manera exitosa <monto>, <descripcion>, <password>
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante1>
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante2>
    And se autoriza el pago a Firma Conjunta - Pendiente <firmante3>
    And validamos el Saldo y Movimiento

    Examples:
      | ambiente | operador  | password    | tipoCuenta        | cuentaOrigen   | empresa   | servicio           | codigoDeudor | monto | descripcion | firmante1 | firmante2 | firmante3 | credencialesOK |
      | DEV      | Usuario07 | Interbank*1 | Corriente Soles   | 100-7001095125 | SEMBRANDO | DONACIONES - SOLES | 01234567     | 15100 | CP01        | Usuario08 | Usuario09 | Usuario10 | SI             |
      | DEV      | Usuario07 | Interbank*1 | Corriente Dólares | 100-7001097364 | SEMBRANDO | DONACIONES DOLARES | 01234568     | 5000  |             | Usuario09 | Usuario08 | Usuario10 | SI             |
      | DEV      | Usuario07 | Interbank*1 | Ahorros Soles     | 100-7005760848 | SEMBRANDO | DONACIONES - SOLES | 01234569     | 15100 | CP03        | Usuario10 | Usuario09 | Usuario08 | SI             |
      | DEV      | Usuario07 | Interbank*1 | Ahorros Dólares   | 100-7005763919 | SEMBRANDO | DONACIONES DOLARES | 01234510     | 5000  |             | Usuario10 | Usuario08 | Usuario09 | SI             |

