
@tarifa_prueba

Feature: Tarifa prueba

  #Se puede agregar mas Códigos Unicos

  @happy_tarifa_prueba
  Scenario Outline: Consulta de Cliente por Codigo Unico

    Given que soy un usuario de Nexbi Intranet e ingreso en el ambiente DEV con las credenciales: cpaucarv@proveedor.intercorp.com.pe, Interbank22
    When  consulto un cliente por <codigoUnico> y visualizo la Información General
    And   se ingresa al CICS con 1 credencial
    And   consulto tarifarios por nivel de servicio
    And   ingreso una <tarifa Preferencial>, <monto> y <fecha de Vencimiento>
    Then  consulto tarifa preferecial ingresado por nivel de servicio
    And   consulto el tipo de cambio de uat en el API
    Then  valido el codigo respuesta
    And   ejecuto el servicio Job fee charge de uat
    Then  valido la respuesta Data del Job
    And   descargo el archivo TXT
    And   calculo el monto a cobrar
    Then  valido monto en el txt

    Examples: Listado de Codigo Únicos
      | codigoUnico | tarifa Preferencial | monto     | fecha de Vencimiento |
      | 60652790    | Corporativo         | Exonerado | 10/05/2023           |
      | 60613779    | Empresa             | 50.50     | 04/01/2023           |
      #| 0060653623  |              |
      #| 60653572    |              |

#se colocara la fecha de inicio?



    #en la fila 10 entra como actor cristian paucar
  #en la fila 14 (api) entra como actor un ambiente. habra algun problema?
  #como se puede ver lo que se imprime con System.out.println en el reporte
  #cambiar el estepDefinition de algunos step

