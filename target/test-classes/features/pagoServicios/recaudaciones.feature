@recaudaciones
Feature: Recaudaciones

  @happy_path  @ESC1RECAUDACIONES
  Scenario Outline: Validar que se realice un pago a sola firma y haya sido abonada en la Empresa Seleccionada
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago <tipoPago> de tipo Pagos - De servicios <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza con exito <monto>, <descripcion>, <password>
    And valida el Saldo y Movimiento
    And valido recaudaciones <datosService>, <buscarcod>
    Examples:
      | ambiente | usuario   | password    | tipoCuenta        | cuentaOrigen   | empresa       | servicio           | codigoDeudor | monto | descripcion | credencialesOK | tipoPago | datosService | buscarcod        |
 #     | DEV      | Usuario06 | Interbank*1 | Ahorros Dólares   | 100-7005763919 | SEMBRANDO     | DONACIONES DOLARES | 123123123    | 10    | CP1         | SI             | *        | DONACIONES   | Todos            |
      | DEV      | Usuario06 | Interbank*1 | Ahorros Dólares   | 100-7005763919 | SEMBRANDO     | DONACIONES DOLARES | 231231231    | 10    | CP2         | SI             | *        | DONACIONES   | Código de deudor |
  #    | DEV      | Usuario02 | Interbank*1 | Corriente Dólares | 100-7001097380 | SAN SILVESTRE | PENSIONES          | 0091002      | 20    | CP3         | SI             | Parcial  | PENSIONES    | Todos            |
  #    | DEV      | Usuario02 | Interbank*1 | Corriente Dólares | 100-7001097380 | SAN SILVESTRE | PENSIONES          | 0090074      | 20    | CP3         | SI             | Parcial  | PENSIONES    | Código de deudor |

  @happy_path @ESC2RECAUDACIONES
  Scenario Outline: Valida que se pueda realizar un pago total de servicio a firma conjunta, de Data Remota, de una (1) cuota, para un (1) codigo deudor, para la empresa Oslo (codigo deudor <= 800)
    Given que el usuario Especialista accede a la aplicacion BPI <ambiente> <firmante1>
    When intenta realizar un pago de tipo Pagos - De servicios - A firma conjunta - Remota <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago se realiza satisfactoriamente <descripcion>, <password>
    And se autoriza el pago a firma conjunta <firmante2>
    And se autoriza el pago a firma conjunta <firmante3>
    And validos los Saldos y Movimientos

    Examples:
      | ambiente | firmante1 | password    | tipoCuenta        | cuentaOrigen   | empresa | servicio      | codigoDeudor | descripcion | firmante2 | firmante3 |
      | DEV      | Usuario03 | Interbank*1 | Corriente Soles   | 100-7001097373 | OSLO    | VTA MERCADERI | 01234567     | test01      | Usuario04 | Usuario05 |
      | DEV      | Usuario03 | Interbank*1 | Corriente Dólares | 100-7001097380 | OSLO    | VTA MERCADERI | 01234567     |             | Usuario04 | Usuario05 |

