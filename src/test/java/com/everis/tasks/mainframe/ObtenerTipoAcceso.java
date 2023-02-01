package com.everis.tasks.mainframe;

import net.serenitybdd.core.Serenity;

public class ObtenerTipoAcceso {
    public final int i;
    public final String[] tipoAccesoArrays;

    public ObtenerTipoAcceso(int i, String[] tipoAccesoArrays) {
        this.i = i;
        this.tipoAccesoArrays = tipoAccesoArrays;
    }

    public static void ConUsuarios(int i, String[] tipoAccesoArrays) throws InterruptedException {

        Thread.sleep(1500);
        boolean isEnabled = Serenity.sessionVariableCalled("checkBoxTipoAcceso");
        int[] cantUsuarioActivoArrays = Serenity.sessionVariableCalled("cantUsuarioActivoArrays");
        int cantUsuarioStock = Serenity.sessionVariableCalled("cantUsuarioStock");
        int cantiUsuarioNuevo = Serenity.sessionVariableCalled("cantiUsuarioNuevo");

        if (!isEnabled && cantUsuarioStock==cantUsuarioActivoArrays[i-1]) {
            tipoAccesoArrays[i-1]= "Stock";
        } else if (isEnabled) {
            if (cantUsuarioStock == 0) {
                tipoAccesoArrays[i-1]= "Nuevo";
            } else if (cantiUsuarioNuevo == 0) {
                tipoAccesoArrays[i-1]= "Migrado";
            } else {
                tipoAccesoArrays[i-1]= "Migrado";
            }
        }
        Serenity.setSessionVariable("tipoAccesoArrays").to(tipoAccesoArrays);
    }

    public static void SinUsuarios(int i, String[] tipoAccesoArrays) throws InterruptedException {

        Thread.sleep(1500);
        boolean isEnabled = Serenity.sessionVariableCalled("checkBoxTipoAcceso");
        if (!isEnabled) {
            tipoAccesoArrays[i-1]= "Stock";
        } else {
            tipoAccesoArrays[i-1]= "Nuevo";
        }
        Serenity.setSessionVariable("tipoAccesoArrays").to(tipoAccesoArrays);
    }
}
