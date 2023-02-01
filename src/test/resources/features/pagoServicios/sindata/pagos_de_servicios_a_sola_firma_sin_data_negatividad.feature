@pago_servicios_sin_data_sola_firma_negatividad
Feature: A sola firma - Sin Data - Negatividad

  @unhappy_path @pagoServicio @ESC1SINDATANEGATIVIDAD
  Scenario Outline: Valida que se pueda rechazar un pago si el saldo disponible es igual a 0 a sola firma sin data
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Sin data <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <dniPagador>
    Then el pago no se realiza satisfactoriamente <monto>; <descripcion>; <password>

    Examples:

      | ambiente | usuario   | password    | tipoCuenta    | cuentaOrigen   | empresa      | servicio   | dniPagador | monto | descripcion | credencialesOK |
      | DEV      | Usuario07 | Interbank*1 | Ahorros Soles | 100-7005764274 | FE Y ALEGRÍA | DONACIONES | 00000001   | 50    | CP01        | SI             |


  @unhappy_path @pagoServicio @ESC2SINDATANEGATIVIDAD
  Scenario Outline: Valida que se pueda rechazar un pago si el monto es mayor que el saldo disponible
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Sin data <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <dniPagador>
    Then el pago no se realiza satisfactoriamente <monto>; <descripcion>; <password>


    Examples:
      | ambiente | usuario   | password    | tipoCuenta        | cuentaOrigen   | empresa   | servicio           | dniPagador | monto | descripcion | credencialesOK |
      | DEV      | Usuario07 | Interbank*1 | Corriente Dólares | 100-7001098328 | SEMBRANDO | DONACIONES DOLARES | 00000002   | 20    | CP02        | SI             |

  @unhappy_path @pagoServicio @ESC3SINDATANEGATIVIDAD
  Scenario Outline: Valida mensaje de dupicidad de codigo
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Sin data <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <dniPagador>
    Then valida que el servicio ya existe

    Examples:
      | ambiente | usuario   | tipoCuenta      | cuentaOrigen   | empresa      | servicio   | dniPagador          | credencialesOK |
      | DEV      | Usuario07 | Corriente Soles | 100-7001095125 | FE Y ALEGRÍA | DONACIONES | 00000003 - 00000003 | SI             |

  @unhappy_path @pagoServicio @ESC4SINDATANEGATIVIDAD
  Scenario Outline: Valida que al ingresar las credenciales incorrectas me aparezca el mensaje "No cumple con los parámetros de seguridad"
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Sin data <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <dniPagador>
    Then ingreso contraseña incorrecta <monto>; <descripcion>; <password>
    And valido que la contraseña es incorrecta

    Examples:
      | ambiente | usuario   | password | tipoCuenta    | cuentaOrigen   | empresa   | servicio           | dniPagador | monto | descripcion | credencialesOK |
      | DEV      | Usuario07 | asdf     | Ahorros Soles | 100-7005760848 | SEMBRANDO | DONACIONES DOLARES | 00000004   | 19    | test 01     | SI             |

  @unhappy_path @pagoServicio @ESC5SINDATANEGATIVIDAD
  Scenario Outline: Valida que no pueda agregar mas 10 servicios
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Sin data <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <dniPagador>
    Then valida que no se agregue mas de 10 servicios


    Examples:
      | ambiente | usuario   | tipoCuenta        | cuentaOrigen   | empresa   | servicio           | dniPagador                       | credencialesOK |
      | DEV      | Usuario07 | Corriente Dólares | 100-7001097364 | SEMBRANDO | DONACIONES - SOLES | 01-02-03-04-05-06-07-08-09-10-11 | SI             |


  @unhappy_path @pagoServicio @ESC6SINDATANEGATIVIDAD
  Scenario Outline: Valida que no realice el pago cuando no seleccione las cuotas
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Sin data <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <dniPagador>
    Then no seleccionamos la cuota para pagar <monto>, <descripcion>
    And valida que debes seleccionar la cuota del servicio


    Examples:
      | ambiente | usuario   | tipoCuenta      | cuentaOrigen   | empresa   | servicio           | dniPagador | monto | descripcion | credencialesOK |
      | DEV      | Usuario07 | Ahorros Dólares | 100-7005763919 | SEMBRANDO | DONACIONES DOLARES | 00000006   | 80    | test01      | SI             |

  @unhappy_path @pagoServicio @ESC7SINDATANEGATIVIDAD
  Scenario Outline: Valida que el pago no se efectue si el monto es mayor a la mayor combinación de firma
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Sin data <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <dniPagador>
    Then el pago supera a la mayor combinación de firma <monto>
    And valida que el monto supera los limites

    Examples:
      | ambiente | usuario   | tipoCuenta    | cuentaOrigen   | empresa      | servicio   | dniPagador | monto | credencialesOK |
      | DEV      | Usuario06 | Ahorros Soles | 100-7005760848 | FE Y ALEGRÍA | DONACIONES | 00000007   | 60000 | SI             |

  @unhappy_path @pagoServicio @ESC8SINDATANEGATIVIDAD
  Scenario Outline: Valida que el pago no se efectue si el monto es igual a 0
    Given que el usuario <usuario> accede a la aplicacion BPI <ambiente> <credencialesOK>
    When intenta realizar un pago de tipo Pagos - De servicios - A sola firma - Sin data <tipoCuenta>, <cuentaOrigen>, <empresa>, <servicio>, <dniPagador>
    Then el pago supera a la mayor combinación de firma <monto>
    And valida que el monto sea mayor a 0

    Examples:
      | ambiente | usuario   | tipoCuenta    | cuentaOrigen   | empresa      | servicio   | dniPagador | monto | credencialesOK |
      | DEV      | Usuario07 | Ahorros Soles | 100-7005764274 | FE Y ALEGRÍA | DONACIONES | 00000001   | 0     | SI             |















