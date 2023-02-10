@modifico_tarifa_preferencial

Feature: Modifico Tarifa Preferencial

  @happy_modifico_tarifa_preferencial
  Scenario Outline: Modifico una Tarifa Preferencial por Tipo de Concepto

    Given que soy un usuario e ingreso al CICS
    When  se modifica la tarifa preferencial: <concepto>, <codigo unico>, <monto>; desde el <fecha de inicio> hasta el <fecha de fin>, <referencia>, <autorizador>
    Then valido que la tarifa preferencial se haya modificado correctamente

    Examples: Listado de Codigo Únicos
      | codigo unico | concepto          | monto     | fecha de inicio | fecha de fin | referencia | autorizador |
      | 60662124     | Negocio           | 15        | 09/01/2023      | 10/02/2023   | prueba10   | prueba010   |
      | 60662124     | negocio           | exonerado | 10/01/2023      | 12/02/2023   | prueba20   | prueba020   |
      | 60662124     | Empresa           | 60        | 09/01/2023      | 12/02/2023   | prueba30   | prueba030   |
      | 60662124     | empresa           | exonerado | 11/01/2023      | 14/02/2023   | prueba40   | prueba040   |
      | 60662124     | corporativo       | exonerado | 09/01/2023      | 14/02/2023   | prueba60   | prueba060   |
      | 60662124     | Corporativo       | 180       | 12/01/2023      | 16/02/2023   | prueba50   | prueba050   |
      | 60662124     | usuario adicional | exonerado | 09/01/2023      | 16/02/2023   | prueba80   | prueba080   |
      | 60662124     | Usuario Adicional | 5.5       | 13/01/2023      | 20/02/2023   | prueba70   | prueba070   |

  #Then se valida que la modificacion fue satisfactoria


  #When modifico una tarifa preferencial por concepto <concepto> con codigo unico <codigo unico>, por el monto <monto>; desde el <fecha de inicio> hasta el <fecha de fin>, con referencia <referencia> y autorizado por <autorizador>
  #When modifico una tarifa preferencial con: <concepto>, <codigo unico>, <monto>; desde el <fecha de inicio> al <fecha de fin>, en <referencia> y autorizado por <autorizador>