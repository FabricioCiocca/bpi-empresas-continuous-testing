package com.everis.utils.mainframe;

import net.serenitybdd.core.Serenity;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ImprimirDetalleInformacion {


    public static void prueba(ArrayList<String> informacion) throws IOException {

        String[] montoTarifaNivelArray = Serenity.sessionVariableCalled("montoTarifaNivelArray");
        String[] nivelConceptoArray = Serenity.sessionVariableCalled("nivelConceptoArray");
        String[] numeroConceptoArray = Serenity.sessionVariableCalled("numeroConceptoArray");

        String tipoCambioDolares = Serenity.sessionVariableCalled("tipoCambioDolares");

        String respDescargaImCobrosMensual = Serenity.sessionVariableCalled("respDescargaImCobrosMensual");
        String respDescargaStCobrosMensual = Serenity.sessionVariableCalled("respDescargaStCobrosMensual");

        String[] montoCobrarArrays = Serenity.sessionVariableCalled("montoCobrarArrays");
        ArrayList<String> coherenciaCuentaMonto = Serenity.sessionVariableCalled("coherenciaCuentaMonto");
        ArrayList<String> incoherenciasCuentaMonto = Serenity.sessionVariableCalled("incoherenciasCuentaMonto");

        String[] listaTarifaPreferencial = Serenity.sessionVariableCalled("listaTarifaPreferencial");

        informacion.add("___________________________");
        System.out.println("___________________________");
        informacion.add("TIPO DE TARIFA");
        System.out.println("TIPO DE TARIFA");
        informacion.add("--------------" + "\n");
        System.out.println("--------------" + "\n");

        boolean condicion = false;
        for (int i = 0; i < listaTarifaPreferencial.length; i++) {
            if (listaTarifaPreferencial[i] != null) {
                condicion = true;
                break;
            }
        }

        if (!condicion) {
            informacion.add("Tarifa Normal");
            System.out.println("Tarifa Normal");
        } else {
            informacion.add("Tarifa Preferencial:");
            System.out.println("Tarifa Preferencial:");
            for (int i = 0; i < listaTarifaPreferencial.length; i++) {
                if (listaTarifaPreferencial[i] != null) {
                    informacion.add(" - " + listaTarifaPreferencial[i]);
                    System.out.println(" - " + listaTarifaPreferencial[i]);
                }
            }
        }

        informacion.add("\n" + "___________________________");
        System.out.println("\n" + "___________________________");
        informacion.add("MONTO DE TARIFA");
        System.out.println("MONTO DE TARIFA");
        informacion.add("---------------" + "\n");
        System.out.println("---------------" + "\n");
        for (int i = 0; i < nivelConceptoArray.length; i++) {
            if (numeroConceptoArray[i] != null) {
                informacion.add((" - " + nivelConceptoArray[i] + ":               ").substring(0, 25) + "S/ " + montoTarifaNivelArray[i]);
                System.out.println((" - " + nivelConceptoArray[i] + ":               ").substring(0, 25) + "S/ " + montoTarifaNivelArray[i]);
            }
        }

        informacion.add("\n" + "___________________________");
        System.out.println("\n" + "___________________________");
        informacion.add("TIPO DE CAMBIO");
        System.out.println("TIPO DE CAMBIO");
        informacion.add("--------------" + "\n");
        System.out.println("--------------" + "\n");
        informacion.add(" - A Dolares:  " + tipoCambioDolares);
        System.out.println(" - A Dolares:  " + tipoCambioDolares);

        informacion.add("\n" + "___________________________");
        System.out.println("\n" + "___________________________");
        informacion.add("ARCHIVO TXT");
        System.out.println("ARCHIVO TXT");
        informacion.add("-----------" + "\n");
        System.out.println("-----------" + "\n");
        informacion.add(" - " + respDescargaImCobrosMensual);
        System.out.println(" - " + respDescargaImCobrosMensual);
        informacion.add(" - " + respDescargaStCobrosMensual);
        System.out.println(" - " + respDescargaStCobrosMensual);

        informacion.add("\n" + "___________________________");
        System.out.println("\n" + "___________________________");
        informacion.add("MONTOS POR COBRAR");
        System.out.println("MONTOS POR COBRAR");
        informacion.add("-----------------" + "\n");
        System.out.println("-----------------" + "\n");
        for (int i = 0; i < montoCobrarArrays.length; i++) {
            informacion.add(" - Punto de Servicio Nro " + (i + 1) + ":   " + montoCobrarArrays[i]);
            System.out.println(" - Punto de Servicio Nro " + (i + 1) + ":   " + montoCobrarArrays[i]);
        }

        informacion.add("\n" + "___________________________");
        System.out.println("\n" + "___________________________");
        informacion.add("COBROS EN EL TXT");
        System.out.println("COBROS EN EL TXT");
        informacion.add("----------------" + "\n");
        System.out.println("----------------" + "\n");
        for (int i = 0; i < coherenciaCuentaMonto.size(); i++) {
            informacion.add(" - " + coherenciaCuentaMonto.get(i));
            System.out.println(" - " + coherenciaCuentaMonto.get(i));
        }

        informacion.add("\n" + "___________________________");
        System.out.println("\n" + "___________________________");
        informacion.add("INCOHERENCIAS ENCONTRADAS");
        System.out.println("INCOHERENCIAS ENCONTRADAS");
        informacion.add("-------------------------" + "\n");
        System.out.println("-------------------------" + "\n");

        if (incoherenciasCuentaMonto.size() == 0) {
            informacion.add(" - No se encontraron Incoherencias");
            System.out.println(" - No se encontraron Incoherencias");
        } else {
            for (int i = 0; i < incoherenciasCuentaMonto.size(); i++) {
                informacion.add(" - incoherencia Nro " + (i + 1) + ":   " + incoherenciasCuentaMonto.get(i));
                System.out.println(" - incoherencia Nro " + (i + 1) + ":   " + incoherenciasCuentaMonto.get(i));
            }
        }

        informacion.add("\n" + "___________________________");
        System.out.println("\n" + "___________________________");

        String filepath = "./report/Reporte.txt";
        //CREACION DE REPORTE.TXT
        FileWriter f1 = new FileWriter(filepath);
        PrintWriter out = new PrintWriter(f1);

        for (int i = 0; i < informacion.size(); i++) {
            out.println(informacion.get(i));
        }
        f1.close();

        Path credentialsFile = Paths.get(filepath);
        Serenity.recordReportData().withTitle("Mensaje de Serenity").fromFile(credentialsFile);
    }
}