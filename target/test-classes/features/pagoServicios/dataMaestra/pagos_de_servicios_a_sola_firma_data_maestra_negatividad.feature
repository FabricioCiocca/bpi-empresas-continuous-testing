@pago_servicios_data_maestra_sola_firma_negatividad
Feature: A sola firma - Data Maestra - Negatividad

  @unhappy_path @pagoServicio @ESC1DATAMAESTRANEGATIVIDAD
  Scenario Outline: Valida que se pueda rechazar un pago si el monto es mayor que el saldo disponible
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Data Maestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago no se realiza satisfactoriamente <monto>, <descripcion>, <password>

    Examples:
      | ambiente | usuario | password    | tipoCuenta        | cuentaOrigen   | empresa | servicio        | codigoDeudor | monto | descripcion | credencialesOK |
      | DEV      | AUTO13  | Interbank1* | Corriente Dólares | 100-7001101400 | PERUSAT | PREPAGO - SOLES | 201077       | 30    |             | SI             |


  @unhappy_path @pagoServicio @ESC2DATAMAESTRANEGATIVIDAD
  Scenario Outline: Valida que el pago no se efectue si el monto es igual a 0
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Data Maestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago no se realiza satisfactoriamente <monto>, <descripcion>, <password>

    Examples:
      | ambiente | usuario | password    | tipoCuenta        | cuentaOrigen   | empresa | servicio        | codigoDeudor | monto | descripcion | credencialesOK |
      | DEV      | AUTO12  | Interbank1* | Ahorros Soles     | 100-7005769276 | PERUSAT | PREPAGO - SOLES | 10304034527  | 20    | DESC        | SI             |


  @unhappy_path @pagoServicio @ESC3DATAMAESTRANEGATIVIDAD
  Scenario Outline: Valida que no realice el pago cuando no seleccione las cuotas
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Data Maestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago no se realiza satisfactoriamente <monto>, <descripcion>, <password>

    Examples:
      | ambiente | usuario | password    | tipoCuenta        | cuentaOrigen   | empresa | servicio        | codigoDeudor | monto | descripcion | credencialesOK |
      | DEV      | AUTO14  | Interbank1* | Ahorros Dólares   | 100-7005769291 | PERUSAT | PREPAGO - SOLES | 244369       | 40    |             | SI             |


  @unhappy_path @pagoServicio @ESC4DATAMAESTRANEGATIVIDAD
  Scenario Outline: Valida mensaje de dupicidad de codigo
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Data Maestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago no se realiza satisfactoriamente <monto>, <descripcion>, <password>

    Examples:
      | ambiente | usuario | password    | tipoCuenta        | cuentaOrigen   | empresa | servicio        | codigoDeudor | monto | descripcion | credencialesOK |
      | DEV      | AUTO11  | Interbank1* | Corriente Soles   | 100-7001101389 | PERUSAT | PREPAGO - SOLES | 10440873800  | 10    |             | SI             |


  @unhappy_path @pagoServicio @ESC5DATAMAESTRANEGATIVIDAD
  Scenario Outline: Valida que al ingresar las credenciales incorrectas me aparezca el mensaje "No cumple con los parámetros de seguridad"
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Data Maestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago no se realiza satisfactoriamente <monto>, <descripcion>, <password>

    Examples:
      | ambiente | usuario | password    | tipoCuenta        | cuentaOrigen   | empresa | servicio        | codigoDeudor | monto | descripcion | credencialesOK |
      | DEV      | AUTO11  | Interbank1* | Corriente Soles   | 100-7001101389 | PERUSAT | PREPAGO - SOLES | 10440873800  | 10    | DESCP1      | SI             |


  @unhappy_path @pagoServicio @ESC6DATAMAESTRANEGATIVIDAD
  Scenario Outline: Valida que no pueda agregar mas 10 servicios
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Data Maestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago no se realiza satisfactoriamente <monto>, <descripcion>, <password>

    Examples:
      | ambiente | usuario | password    | tipoCuenta        | cuentaOrigen   | empresa | servicio        | codigoDeudor | monto | descripcion | credencialesOK |
      | DEV      | AUTO11  | Interbank1* | Corriente Soles   | 100-7001101389 | PERUSAT | PREPAGO - SOLES | 10440873800  | 10    |             | SI             |


  @unhappy_path @pagoServicio @ESC7DATAMAESTRANEGATIVIDAD
  Scenario Outline: Valida que al ingresar el codigo deudor incorrecto no se continua con el pago
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Data Maestra <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <codigoDeudor>
    Then el pago no se realiza satisfactoriamente <monto>, <descripcion>, <password>

    Examples:
      | ambiente | usuario | password    | tipoCuenta        | cuentaOrigen   | empresa | servicio        | codigoDeudor | monto | descripcion | credencialesOK |
      | DEV      | AUTO11  | Interbank1* | Corriente Soles   | 100-7001101389 | PERUSAT | PREPAGO - SOLES | 10440873800  | 10    |             | SI             |

