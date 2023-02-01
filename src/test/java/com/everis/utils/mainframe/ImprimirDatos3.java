package com.everis.utils.mainframe;

import net.serenitybdd.core.Serenity;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ImprimirDatos3 {

    public static void datosGenerales(List<WebElement> punto, ArrayList<String> informacion) throws IOException {

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
        ArrayList<String> listaTarifaPreferencial = Serenity.sessionVariableCalled("listaTarifaPreferencial");

        informacion.add("____________________________________________________________________________________________");
        informacion.add("TIPO DE TARIFA");
        informacion.add("--------------"+"\n");
        if(listaTarifaPreferencial.size()==0){
            informacion.add("Tarifa Normal");
        }else{
            informacion.add("Tarifa Preferencial:");
            for(int i=0;i<listaTarifaPreferencial.size();i++){
                informacion.add("- "+listaTarifaPreferencial.get(i));
            }
        }

        informacion.add("\n"+"____________________________________________________________________________________________");
        informacion.add("MONTO DE TARIFA");
        informacion.add("---------------"+"\n");
        informacion.add("Negocio:            "+montoTarifaNivel20);
        informacion.add("Empresa:            "+montoTarifaNivel21);
        informacion.add("Corporativo:        "+montoTarifaNivel22);
        informacion.add("Usuario Adicional:  "+montoTarifaNivel40);


        informacion.add("\n"+"____________________________________________________________________________________________");
        informacion.add("TIPO DE CAMBIO");
        informacion.add("--------------"+"\n");
        informacion.add("A Dolares:  "+tipoCambioDolares);


        informacion.add("\n"+"____________________________________________________________________________________________");
        informacion.add("ARCHIVO TXT");
        informacion.add("-----------"+"\n");
        informacion.add(respDescargaImCobrosMensual);
        informacion.add(respDescargaStCobrosMensual);

        informacion.add("\n"+"____________________________________________________________________________________________");
        informacion.add("MONTOS POR COBRAR");
        informacion.add("-----------------"+"\n");
        for(int i=0;i<punto.size();i++){
            informacion.add("Punto de Servicio Nro "+(i+1)+":  " + montoCobrarArrays[i]);
        }

        informacion.add("\n"+"____________________________________________________________________________________________");
        informacion.add("COBROS EN EL TXT");
        informacion.add("----------------"+"\n");
        for(int i=0;i<punto.size();i++){
            informacion.add("Punto de Servicio Nro "+(i+1)+":   " + coherenciaCuentaMonto[i]);
        }

        informacion.add("\n"+"____________________________________________________________________________________________");
        informacion.add("INCOHERENCIAS ENCONTRADAS");
        informacion.add("-------------------------"+"\n");
        for(int i=0;i<incoherenciasCuentaMonto.size();i++){
            informacion.add(incoherenciasCuentaMonto.get(i));
        }

        //CREACION DE REPORTE.TXT
        FileWriter f1 =new FileWriter("C:/Users/x12090/Documents/TXT/Reporte.txt");
        PrintWriter out =new PrintWriter(f1);

        for (int i=0;i<informacion.size();i++) {
            out.println(informacion.get(i));
        }
        f1.close();

        Path credentialsFile = Paths.get("C:/Users/x12090/Documents/TXT/Reporte.txt");
        Serenity.recordReportData().withTitle("Mensaje de Serenity").fromFile(credentialsFile);
    }
}

