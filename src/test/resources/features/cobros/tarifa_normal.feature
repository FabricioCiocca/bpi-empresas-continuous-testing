@tarifa_normal

Feature: Tarifa normal

  #Se puede agregar mas Códigos Unicos

  @happy_tarifa_normal
  Scenario Outline: Consulta de Cliente por Codigo Unico

    Given que soy un usuario de Nexbi Intranet e ingreso en el DEV con las credenciales: cpaucarv@proveedor.intercorp.com.pe, Interbank22
    #When  consulto un cliente por <codigoUnico> y visualizo la Información General
    When  consulto un cliente por <codigoUnico> y visualizo
    And   se ingresa al CICS con 2 credencial
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
      #| 60575823    |
      #| 50000029    |
      #| 60652790    |
      #| 60613779    |
      #| 0060653623  |
      #| 60653572    |
       #| 0060615277  |
       #| 0060661473  |
           #| 60113056    |
            |60662124|


    #en la fila 10 entra como actor cristian paucar
  #en la fila 14 (api) entra como actor un ambiente. habra algun problema?
  #como se puede ver lo que se imprime con System.out.println en el reporte
  #cambiar el estepDefinition de algunos step


#  @unhappy_tarifa_normal
#  Scenario Outline: Consulta de Cliente por Codigo Unico que no tenga Usuarios
#
#    Given que soy un usuario de Nexbi Intranet e ingreso en el DEV con las credenciales: cpaucarv@proveedor.intercorp.com.pe, Interbank22
#    When  consulto un cliente por <codigoUnico> y visualizo la Información General sin Usuarios
#    And   se ingresa al CICS con 2 credencial
#    Then  consulto tarifarios por nivel de servicio
#    And   consulto el tipo de cambio de uat en el API
#    Then  valido el codigo respuesta
#    And   ejecuto el servicio Job fee charge de uat
#    Then  valido la respuesta Data del Job
#    And   descargo el archivo TXT
#    And   calculo el monto a cobrar
#    Then  valido monto en el txt
#
#    Examples: Listado de Codigo Únicos
#      | codigoUnico |
#      | 0060615277  |







