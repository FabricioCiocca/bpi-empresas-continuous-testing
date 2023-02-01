package com.everis.tasks.mainframe;

import io.cucumber.java.PendingException;
import net.serenitybdd.core.Serenity;

import java.util.ArrayList;

public class ObtenerTipoAccesoNuevo {
    public final ArrayList<String> nombrePuntoServicioArrays;
    public final ArrayList<String> tipoAccesoArrays;

    public ObtenerTipoAccesoNuevo(ArrayList<String> nombrePuntoServicioArrays, ArrayList<String> tipoAccesoArrays) {
        this.nombrePuntoServicioArrays = nombrePuntoServicioArrays;
        this.tipoAccesoArrays = tipoAccesoArrays;
    }

    public static void ConUsuarios(ArrayList<String> nombrePuntoServicioArrays, ArrayList<String> tipoAccesoArrays) throws InterruptedException {

        Thread.sleep(1500);
        boolean isEnabled = Serenity.sessionVariableCalled("checkBoxTipoAcceso");
        ArrayList<Integer> cantUsuarioActivoArrays = Serenity.sessionVariableCalled("cantUsuarioActivoArrays");
        int cantUsuarioStock = Serenity.sessionVariableCalled("cantUsuarioStock");
        int cantiUsuarioNuevo = Serenity.sessionVariableCalled("cantiUsuarioNuevo");

        if (!isEnabled && cantUsuarioStock==cantUsuarioActivoArrays.get(nombrePuntoServicioArrays.size()-1)) {
            tipoAccesoArrays.add("Stock");
        } else if (isEnabled) {
            if (cantUsuarioStock == 0) {
                tipoAccesoArrays.add("Nuevo");
            } else if (cantiUsuarioNuevo == 0) {
                tipoAccesoArrays.add("Migrado");
            } else {
                tipoAccesoArrays.add("Migrado");
            }
        }else{
            throw new PendingException("Error en los Usuarios del Punto de servicio Nro "+nombrePuntoServicioArrays.size());
        }
        Serenity.setSessionVariable("tipoAccesoArrays").to(tipoAccesoArrays);
    }

    public static void SinUsuarios(ArrayList<String> tipoAccesoArrays) throws InterruptedException {

        Thread.sleep(1500);
        boolean isEnabled = Serenity.sessionVariableCalled("checkBoxTipoAcceso");
        if (!isEnabled) {
            tipoAccesoArrays.add("Stock");
        } else {
            tipoAccesoArrays.add("Nuevo");
        }
        Serenity.setSessionVariable("tipoAccesoArrays").to(tipoAccesoArrays);
    }
}
