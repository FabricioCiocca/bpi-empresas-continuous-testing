package com.everis.questions.mainframe;

import mainframe.com.bdd.lib.EmulatorActions;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Question;

public class Validacion extends EmulatorActions {

    public static Question<Boolean> ingresoTarifa(int i, String monto, String[] montoTarifaNivelArray) {

        boolean resultado = false;
        if (monto.toLowerCase().contains("exo")) {
            if (montoTarifaNivelArray[i].equals("0.00")) {
                resultado = true;
                System.out.println("Se Ingreso Correctamente la Tarifa Preferencial");
                Serenity.recordReportData().withTitle("Validacion").andContents("Se Ingreso Correctamente la Tarifa Preferencial");
            }
        } else {
            if (montoTarifaNivelArray[i].equals(String.valueOf(String.format("%.2f", Double.parseDouble(monto))))) {
                resultado = true;
                System.out.println("Se Ingreso Correctamente la Tarifa Preferencial");
                Serenity.recordReportData().withTitle("Validacion").andContents("Se Ingreso Correctamente la Tarifa Preferencial");
            }
        }

        boolean finalResultado = resultado;
        return actor -> finalResultado;
    }

    public static Question<Boolean> modificoTarifa(int i, String monto, String[] montoTarifaNivelArray) {

        boolean resultado = false;
        if (monto.toLowerCase().contains("exo")) {
            if (montoTarifaNivelArray[i].equals("0.00")) {
                resultado = true;
                System.out.println("Se Ingreso la Modificacion de la Tarifa Preferencial Correctamente");
                Serenity.recordReportData().withTitle("Validacion").andContents("Se Ingreso la Modificacion de la Tarifa Preferencial Correctamente");
            }
        } else {
            if (montoTarifaNivelArray[i].equals(String.valueOf(String.format("%.2f", Double.parseDouble(monto))))) {
                resultado = true;
                System.out.println("Se Ingreso la Modificacion de la Tarifa Preferencial Correctamente");
                Serenity.recordReportData().withTitle("Validacion").andContents("Se Ingreso la Modificacion de la Tarifa Preferencial Correctamente");
            }
        }

        boolean finalResultado = resultado;
        return actor -> finalResultado;
    }
}
