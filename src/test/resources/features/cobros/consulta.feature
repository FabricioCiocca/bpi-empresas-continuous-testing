@consulta

Feature: Consulta

  @happy_cobros
  Scenario Outline: Consulta de Cliente por Codigo Unico

    Given que soy un usuario de Nexbi Intranet e ingreso en el ambiente DEV con las credenciales: cpaucarv@proveedor.intercorp.com.pe, Interbank22
    When  consulto un cliente por <codigoUnico> y visualizo la Información General
    And   se ingresa al CICS con 1 credencial
    Then  consulto tarifarios por nivel de servicio
    And   consulto el tipo de cambio de uat en el API
    Then  valido el codigo respuesta
    And   ejecuto el servicio Job fee charge de uat
    Then  valido la respuesta Data del Job
    And   descargo el archivo TXT
    And   calculo el monto a cobrar
    Then  valido monto en el txt

    Examples: Listado de Codigo Únicos
      | codigoUnico |
      | 0060575823  |



  @unhappy_cobros
  Scenario: Consulta de Cliente por Punto de Servicio que no tenga Usuarios

    Given que soy un usuario de Nexbi Intranet e ingreso en el ambiente DEV con las credenciales: cpaucarv@proveedor.intercorp.com.pe, Interbank22
    When  consulto un cliente por Punto de Servicio y visualizo la Información General sin Usuarios
          | punto      |
          | 0000205519 |
    And   ejecuto el servicio Job fee charge de uat
    Then  valido la respuesta Data del Job
    And   descargo el archivo TXT
    Then  valido que el monto no exista en el txt