@consulto_tarifa

Feature: Consulto Tarifa

  @happy_consulto_tarifa
  Scenario Outline: Consulto una Tarifa para su validacion de cobro en el txt

    Given que soy un administrador de Nexbi Intranet e ingreso con las credenciales: cpaucarv@proveedor.intercorp.com.pe, Ss?p0jmP
    When  consulto un cliente por <codigoUnico> y visualizo la Información General
    And   se ingresa al CICS con mis credenciales
    Then  consulto tarifarios por nivel de servicio
    And   consulto el tipo de cambio en el API
    Then  valido el codigo respuesta
    And   ejecuto el servicio Job fee charge
    Then  valido la respuesta Data del Job
    And   descargo el archivo TXT
    And   calculo el monto a cobrar
    Then  valido monto en el txt

    Examples: Listado de Codigo Únicos
      | codigoUnico |
      #| 60575823    |
      #| 60081965    |
      #| 50000029    |
      | 60652790    |
      #| 60613779    |
      #| 60653623    |
      #| 60653572    |
      #| 60615277    |
      #| 60661473    |
      #| 60113056    |
      #| 60662124    |