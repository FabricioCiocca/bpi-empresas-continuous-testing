package com.everis.actions.mainframe;

import mainframe.com.bdd.lib.EmulatorActions;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetStringByPosition extends EmulatorActions implements Interaction {
    public final int iFila;
    public final int iColumna;
    public final int ctdFilas;
    public final int ctdColumnas;

    public GetStringByPosition(int iFila, int iColumna, int ctdFilas, int ctdColumnas) {
        this.iFila = iFila;
        this.iColumna = iColumna;
        this.ctdFilas = ctdFilas;
        this.ctdColumnas = ctdColumnas;
    }
    public static String theValue(int iFila, int iColumna, int ctdFilas, int ctdColumnas){
        return String.valueOf(instrumented(GetStringByPosition.class,iFila, iColumna, ctdFilas, ctdColumnas));
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
         getInstancia().obtenerCadenaPorPosicion(iFila, iColumna, ctdFilas, ctdColumnas);
    }
}
