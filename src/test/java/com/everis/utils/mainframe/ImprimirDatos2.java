package com.everis.utils.mainframe;

import net.serenitybdd.core.Serenity;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ImprimirDatos2 {

    public static void datosFinales(List<WebElement> punto){

        String tipoTarifa = Serenity.sessionVariableCalled("tipoTarifa");
        String montoTarifaNivel20 = Serenity.sessionVariableCalled("montoTarifaNivel20");
        String montoTarifaNivel21 = Serenity.sessionVariableCalled("montoTarifaNivel21");
        String montoTarifaNivel22 = Serenity.sessionVariableCalled("montoTarifaNivel22");
        String montoTarifaNivel40 = Serenity.sessionVariableCalled("montoTarifaNivel40");


        String tipoCambioDolares = Serenity.sessionVariableCalled("tipoCambioDolares");

        String respDescargaImCobrosMensual = Serenity.sessionVariableCalled("respDescargaImCobrosMensual");
        String respDescargaStCobrosMensual = Serenity.sessionVariableCalled("respDescargaStCobrosMensual");

        String[] montoCobrarArrays = Serenity.sessionVariableCalled("montoCobrarArrays");
        String[] coherenciaCuentaMonto = Serenity.sessionVariableCalled("coherenciaCuentaMonto");

        ArrayList<String> incoherenciasCuentaMonto = Serenity.sessionVariableCalled("incoherenciasCuentaMonto");


        System.out.println("------------------------------------------------");
        System.out.println("Tipo de Tarifa:                     "+tipoTarifa);
        System.out.println("Monto de Tarifa Negocio:            "+montoTarifaNivel20);
        System.out.println("Monto de Tarifa Empresa:            "+montoTarifaNivel21);
        System.out.println("Monto de Tarifa Corporativo:        "+montoTarifaNivel22);
        System.out.println("Monto de Tarifa Usuario Adicional:  "+montoTarifaNivel40);
        System.out.println("------------------------------------------------"+"\n");

        System.out.println("Tipo de Cambio a Dolares:  "+tipoCambioDolares);
        System.out.println("------------------------------------------------"+"\n");

        System.out.println(respDescargaImCobrosMensual);
        System.out.println(respDescargaStCobrosMensual);
        System.out.println("------------------------------------------------"+"\n");

        System.out.println("MONTOS POR COBRAR:");
        for(int i=0;i<punto.size();i++){
            System.out.println("Monto por cobrar del Punto de Servicio N°"+(i+1)+":  " + montoCobrarArrays[i]);
        }
        System.out.println("------------------------------------------------"+"\n");

        System.out.println("COBROS ENCONTRADOS EN EL TXT:");
        for(int i=0;i<punto.size();i++){
            System.out.println("Cobro del Punto de Servicio n°"+(i+1)+":   " + coherenciaCuentaMonto[i]);
        }
        System.out.println("------------------------------------------------"+"\n");

        System.out.println("INCOHERENCIAS ENCONTRADAS:");
        for(int i=0;i<incoherenciasCuentaMonto.size();i++){
            System.out.println(incoherenciasCuentaMonto.get(i));
        }
        System.out.println("------------------------------------------------"+"\n");

    }
}

