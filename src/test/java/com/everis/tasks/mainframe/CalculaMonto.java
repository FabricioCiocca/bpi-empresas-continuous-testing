package com.everis.tasks.mainframe;

import net.serenitybdd.core.Serenity;

import java.util.ArrayList;

public class CalculaMonto {
    public final int i;
    public final ArrayList<String> monedaCuentaArrays;
    public final ArrayList<Integer> cantUsuarioActivoConfirmadoArrays;
    public final double montoTarifaUsuario;
    public final double tipoCambioDolares;
    public final double montoTarifaNivel;
    public final String[] montoCobrarArrays;

    public CalculaMonto(int i, ArrayList<String> monedaCuentaArrays, ArrayList<Integer> cantUsuarioActivoConfirmadoArrays, double montoTarifaUsuario, double tipoCambioDolares, double montoTarifaNivel, String[] montoCobrarArrays) {
        this.i = i;
        this.monedaCuentaArrays = monedaCuentaArrays;
        this.cantUsuarioActivoConfirmadoArrays = cantUsuarioActivoConfirmadoArrays;
        this.montoTarifaUsuario = montoTarifaUsuario;
        this.tipoCambioDolares = tipoCambioDolares;
        this.montoTarifaNivel = montoTarifaNivel;
        this.montoCobrarArrays = montoCobrarArrays;
    }

    public static void SinUsuario(int i, String[] montoCobrarArrays) {

        montoCobrarArrays[i] = "No hay Monto";

        Serenity.setSessionVariable("montoCobrarArrays").to(montoCobrarArrays);
    }

    public static void TipoCambio(int i, ArrayList<String> monedaCuentaArrays, double tipoCambioDolares, double montoTarifaNivel, String[] montoCobrarArrays) {

        if (monedaCuentaArrays.get(i).equals("Soles")) {
            montoCobrarArrays[i] = String.valueOf(String.format("%.2f", montoTarifaNivel));
        } else if (monedaCuentaArrays.get(i).equals("Dólares")) {
            montoCobrarArrays[i] = String.valueOf(String.format("%.2f", montoTarifaNivel / tipoCambioDolares));
        }
        Serenity.setSessionVariable("montoCobrarArrays").to(montoCobrarArrays);
    }

    public static void TipoCambio2(int i, ArrayList<String> monedaCuentaArrays, ArrayList<Integer> cantUsuarioActivoConfirmadoArrays, double montoTarifaUsuario, double tipoCambioDolares, double montoTarifaNivel, String[] montoCobrarArrays) {

        if (monedaCuentaArrays.get(i).equals("Soles")) {
            montoCobrarArrays[i] = String.valueOf(String.format("%.2f", montoTarifaNivel + montoTarifaUsuario * (cantUsuarioActivoConfirmadoArrays.get(i) - 3)));
        } else if (monedaCuentaArrays.get(i).equals("Dólares")) {
            montoCobrarArrays[i] = String.valueOf(String.format("%.2f", (montoTarifaNivel + montoTarifaUsuario * (cantUsuarioActivoConfirmadoArrays.get(i) - 3)) / tipoCambioDolares));
        }
        Serenity.setSessionVariable("montoCobrarArrays").to(montoCobrarArrays);
    }
}
