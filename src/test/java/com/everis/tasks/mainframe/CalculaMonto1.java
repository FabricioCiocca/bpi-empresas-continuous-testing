package com.everis.tasks.mainframe;

import net.serenitybdd.core.Serenity;

public class CalculaMonto1 {
    public final int i;
    public final String[] monedaCuentaArrays;
    public final int[] cantUsuarioActivoArrays;
    public final double montoTarifaUsuario;
    public final double tipoCambioDolares;
    public final double montoTarifaNivel;
    public final String[] montoCobrarArrays;

    public CalculaMonto1(int i, String[] monedaCuentaArrays, int[] cantUsuarioActivoArrays, double montoTarifaUsuario, double tipoCambioDolares, double montoTarifaNivel, String[] montoCobrarArrays) {
        this.i = i;
        this.monedaCuentaArrays = monedaCuentaArrays;
        this.cantUsuarioActivoArrays = cantUsuarioActivoArrays;
        this.montoTarifaUsuario = montoTarifaUsuario;
        this.tipoCambioDolares = tipoCambioDolares;
        this.montoTarifaNivel = montoTarifaNivel;
        this.montoCobrarArrays = montoCobrarArrays;
    }

    public static void TipoCambio(int i, String[] monedaCuentaArrays, double tipoCambioDolares, double montoTarifaNivel, String[] montoCobrarArrays) {

        if (monedaCuentaArrays[i].trim().equals("Soles")) {
            montoCobrarArrays[i] = String.valueOf(String.format("%.2f", montoTarifaNivel));
        } else if (monedaCuentaArrays[i].trim().equals("Dólares")) {
            montoCobrarArrays[i]=String.valueOf(String.format("%.2f",montoTarifaNivel/tipoCambioDolares));
        }

        Serenity.setSessionVariable("montoCobrarArrays").to(montoCobrarArrays);
    }

    public static void TipoCambio2(int i, String[] monedaCuentaArrays, int[] cantUsuarioActivoArrays, double montoTarifaUsuario, double tipoCambioDolares, double montoTarifaNivel, String[] montoCobrarArrays) {

        if (monedaCuentaArrays[i].trim().equals("Soles")) {
            montoCobrarArrays[i] = String.valueOf(String.format("%.2f", montoTarifaNivel + montoTarifaUsuario * (cantUsuarioActivoArrays[i] - 3)));
        } else if (monedaCuentaArrays[i].trim().equals("Dólares")) {
            montoCobrarArrays[i]=String.valueOf(String.format("%.2f",(montoTarifaNivel+ montoTarifaUsuario*(cantUsuarioActivoArrays[i]-3))/tipoCambioDolares));
        }

        Serenity.setSessionVariable("montoCobrarArrays").to(montoCobrarArrays);
    }
}
