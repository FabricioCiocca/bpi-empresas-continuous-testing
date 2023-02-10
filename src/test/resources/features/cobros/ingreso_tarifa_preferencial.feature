@ingreso_tarifa_preferencial

Feature: Ingreso Tarifa Preferencial

  @happy_ingreso_tarifa_preferencial
  Scenario Outline: Ingreso una Tarifa Preferencial por Tipo de Concepto

    Given que soy un usuario e ingreso al CICS
    When  se ingresa la tarifa preferencial: <concepto>, <codigo unico>, <monto>; desde el <fecha de inicio> hasta el <fecha de fin>, <referencia>, <autorizador>
    Then valido que la tarifa preferencial se haya ingresado correctamente

    Examples: Listado de Codigo Únicos
      | codigo unico | concepto          | monto     | fecha de inicio | fecha de fin | referencia | autorizador |
      | 60662124     | Negocio           | 20        | 09/01/2023      | 10/02/2023   | prueba1    | prueba01    |
      | 60081965     | negocio           | exonerado | 09/01/2023      | 11/02/2023   | prueba2    | prueba02    |
      | 60662124     | Empresa           | 70        | 09/01/2023      | 12/02/2023   | prueba3    | prueba03    |
      | 60081965     | empresa           | exonerado | 09/01/2023      | 12/02/2023   | prueba4    | prueba04    |
      | 60662124     | Corporativo       | 191       | 09/01/2023      | 13/02/2023   | prueba5    | prueba05    |
      | 60081965     | corporativo       | exonerado | 09/01/2023      | 14/02/2023   | prueba6    | prueba06    |
      | 60662124     | Usuario Adicional | 6.5       | 09/01/2023      | 15/02/2023   | prueba7    | prueba07    |
      | 60081965     | usuario adicional | exonerado | 09/01/2023      | 16/02/2023   | prueba8    | prueba08    |

  #When  consulto la tarifa de: <concepto>, <codigo unico>
  #And   se ingresa una tarifa preferencial: <monto>; desde el <fecha de inicio> hasta el <fecha de fin>, <referencia>, <autorizador>


  #When ingreso una tarifa preferencial por concepto <concepto> con codigo unico <codigo unico>, por el monto <monto>; desde el <fecha de inicio> hasta el <fecha de fin>, con referencia <referencia> y autorizado por <autorizador>
  #When ingreso una tarifa preferencial con: <concepto>, <codigo unico>, <monto>; desde el <fecha de inicio> al <fecha de fin>, en <referencia> y autorizado por <autorizador>