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

public class ImprimirDetalleInformacionNuevo {


    public static void datosValidos(ArrayList<String> informacion) throws IOException {
        ArrayList<String> nombrePuntoServicioArrays = Serenity.sessionVariableCalled("nombrePuntoServicioArrays");
        ArrayList<String> numeroPuntoServicioArrays = Serenity.sessionVariableCalled("numeroPuntoServicioArrays");
        ArrayList<String> tipoClienteArrays = Serenity.sessionVariableCalled("tipoClienteArrays");
        ArrayList<String> nivelServicioArrays = Serenity.sessionVariableCalled("nivelServicioArrays");
        ArrayList<String> cuentaCargoArrays = Serenity.sessionVariableCalled("cuentaCargoArrays");
        ArrayList<String> monedaCuentaArrays = Serenity.sessionVariableCalled("monedaCuentaArrays");
        ArrayList<String> tipoAccesoArrays = Serenity.sessionVariableCalled("tipoAccesoArrays");
        ArrayList<Integer> cantUsuarioActivoArrays = Serenity.sessionVariableCalled("cantUsuarioActivoArrays");
        ArrayList<Integer> cantUsuarioArrays = Serenity.sessionVariableCalled("cantUsuarioArrays");

        for(int i=0;i<nombrePuntoServicioArrays.size();i++){
            String[][] seccionNumeroTieArrays= Serenity.sessionVariableCalled("seccionNumeroTieArrays"+i);

            informacion.add   ("\n"+"_____________________________________________________________________________");
            System.out.println("\n"+"_____________________________________________________________________________");
            informacion.add   ("Punto de Servicio Nro: " + (i + 1));
            System.out.println("Punto de Servicio Nro: " + (i + 1));
            informacion.add   ("-------------------------"+"\n");
            System.out.println("-------------------------"+"\n");

            informacion.add   ("Nombre del Punto del Servicio: " + nombrePuntoServicioArrays.get(i).trim());
            System.out.println("Nombre del Punto de Servicio:  " + nombrePuntoServicioArrays.get(i).trim());
            informacion.add   ("Numero del Punto de Servicio:  " + numeroPuntoServicioArrays.get(i+1).trim() + "\n");
            System.out.println("Numero del Punto de Servicio:  " + numeroPuntoServicioArrays.get(i+1).trim() + "\n");

            informacion.add   ("\n"+"_____________________________________________________________________________");
            System.out.println("\n"+"_____________________________________________________________________________");

            informacion.add   ("Información del punto de servicio:");
            System.out.println("Información del punto de servicio:");
            informacion.add   ("----------------------------------"+"\n");
            System.out.println("----------------------------------"+"\n");

            informacion.add   ("- Tipo de Cliente:     " + tipoClienteArrays.get(i).trim());
            System.out.println("- Tipo de Cliente:     " + tipoClienteArrays.get(i).trim());
            informacion.add   ("- Nivel de Servicio:   " + nivelServicioArrays.get(i).trim());
            System.out.println("- Nivel de Servicio:   " + nivelServicioArrays.get(i).trim());
            informacion.add   ("- Cuenta de Cargo:     " + cuentaCargoArrays.get(i).trim());
            System.out.println("- Cuenta de Cargo:     " + cuentaCargoArrays.get(i).trim());
            informacion.add   ("- Moneda:              " + monedaCuentaArrays.get(i).trim());
            System.out.println("- Moneda:              " + monedaCuentaArrays.get(i).trim());

            informacion.add   ("\n"+"_____________________________________________________________________________");
            System.out.println("\n"+"_____________________________________________________________________________");

            informacion.add   ("Usuarios Activos:");
            System.out.println("Usuarios Activos:");
            informacion.add   ("-----------------");
            System.out.println("-----------------");

            if (cantUsuarioArrays.get(i) > 0) {
                for (int j = 0; j < cantUsuarioArrays.get(i); j++) {
                    informacion.add   ("  *Usuario Nro: " + (j + 1));
                    System.out.println("  *Usuario Nro: " + (j + 1));
                    informacion.add   ("  ---------------");
                    System.out.println("  ---------------");
                    informacion.add   ("   - Seccion TIE:    " + seccionNumeroTieArrays[j][0]);
                    System.out.println("   - Seccion TIE:    " + seccionNumeroTieArrays[j][0]);
                    informacion.add   ("   - Numero de TIE:  " + seccionNumeroTieArrays[j][1]);
                    System.out.println("   - Numero de TIE:  " + seccionNumeroTieArrays[j][1]);
                }
            }else{
                informacion.add   ("Aún no se ha creado ningún usuario para este punto de servicio.");
                System.out.println("Aún no se ha creado ningún usuario para este punto de servicio.");
            }

            informacion.add   ("  ------------------------"+"\n");
            System.out.println("  ------------------------"+"\n");

            informacion.add   ("Cantidad de Usuarios Activos Confirmados: " + cantUsuarioActivoArrays.get(i));
            System.out.println("Cantidad de Usuarios Activos Confirmados: " + cantUsuarioActivoArrays.get(i));

            informacion.add   ("\n"+"_____________________________________________________________________________");
            System.out.println("\n"+"_____________________________________________________________________________");

            informacion.add   ("Tipo de Acceso:  " + tipoAccesoArrays.get(i));
            System.out.println("Tipo de Acceso:  " + tipoAccesoArrays.get(i));

            informacion.add   ("_____________________________________________________________________________"+"\n"+"\n");
            System.out.println("_____________________________________________________________________________"+"\n"+"\n");
        }

        //CREACION DE REPORTE.TXT
        FileWriter f1 =new FileWriter("C:/Users/x12091/Documents/TXT/ReporteUsuariosValidos.txt");
        PrintWriter out =new PrintWriter(f1);

        for (int j=0;j<informacion.size();j++) {
            out.println(informacion.get(j));
        }
        f1.close();

        Path credentialsFile = Paths.get("C:/Users/x12091/Documents/TXT/ReporteUsuariosValidos.txt");
        Serenity.recordReportData().withTitle("Evidencia Nexbi").fromFile(credentialsFile);
    }

    public static void datosInválidos(List<WebElement> punto, ArrayList<String> informacion) throws IOException {
        String mensajeUsuario = Serenity.sessionVariableCalled("mensajeUsuarios");
        String[] nombrePuntoServicioArrays = Serenity.sessionVariableCalled("nombrePuntoServicioArrays");
        String[] puntoServicioArrays = Serenity.sessionVariableCalled("puntoServicioArrays");
        String[] tipoClienteArrays = Serenity.sessionVariableCalled("tipoClienteArrays");
        String[] nivelServicioArrays = Serenity.sessionVariableCalled("nivelServicioArrays");
        String[] cuentaCargoArrays = Serenity.sessionVariableCalled("cuentaCargoArrays");
        String[] monedaCuentaArrays = Serenity.sessionVariableCalled("monedaCuentaArrays");
        String[] tipoAccesoArrays = Serenity.sessionVariableCalled("tipoAccesoArrays");
        int[] cantUsuarioActivoArrays = Serenity.sessionVariableCalled("cantUsuarioActivoArrays");

        for (int i = 0; i < punto.size(); i++) {

            informacion.add   ("\n"+"_____________________________________________________________________________");
            System.out.println("\n"+"_____________________________________________________________________________");

            informacion.add   ("Punto de Servicio Nro: " + (i + 1));
            System.out.println("Punto de Servicio Nro: " + (i + 1));
            informacion.add   ("-------------------------------------"+"\n");
            System.out.println("-------------------------------------"+"\n");

            informacion.add   ("Nombre del Punto del Servicio: " + nombrePuntoServicioArrays[i].trim());
            System.out.println("Nombre del Punto de Servicio:  " + nombrePuntoServicioArrays[i].trim());
            informacion.add   ("Numero del Punto de Servicio:  " + puntoServicioArrays[i].trim());
            System.out.println("Numero del Punto de Servicio:  " + puntoServicioArrays[i].trim());

            informacion.add   ("\n"+"_____________________________________________________________________________");
            System.out.println("\n"+"_____________________________________________________________________________");
            informacion.add   ("Información del punto de servicio:");
            System.out.println("Información del punto de servicio:");
            informacion.add   ("----------------------------------"+"\n");
            System.out.println("----------------------------------"+"\n");

            informacion.add   ("- Tipo de Cliente:     " + tipoClienteArrays[i].trim());
            System.out.println("- Tipo de Cliente:     " + tipoClienteArrays[i].trim());
            informacion.add   ("- Nivel de Servicio:   " + nivelServicioArrays[i].trim());
            System.out.println("- Nivel de Servicio:   " + nivelServicioArrays[i].trim());
            informacion.add   ("- Cuenta de Cargo:     " + cuentaCargoArrays[i].trim());
            System.out.println("- Cuenta de Cargo:     " + cuentaCargoArrays[i].trim());
            informacion.add   ("- Moneda:              " + monedaCuentaArrays[i].trim() + "\n");
            System.out.println("- Moneda:              " + monedaCuentaArrays[i].trim() + "\n");

            informacion.add   ("\n"+"_____________________________________________________________________________");
            System.out.println("\n"+"_____________________________________________________________________________");

            informacion.add   ("Usuarios Activos:");
            System.out.println("Usuarios Activos:");
            informacion.add   ("-----------------"+"\n");
            System.out.println("-----------------"+"\n");

            informacion.add   ("Cantidad de Usuarios Activos Confirmados: " + cantUsuarioActivoArrays[i]);
            System.out.println("Cantidad de Usuarios Activos Confirmados: " + cantUsuarioActivoArrays[i]);

            informacion.add   ("\n"+"_____________________________________________________________________________");
            System.out.println("\n"+"_____________________________________________________________________________");

            informacion.add   ("Otros Mensajes:");
            System.out.println("Otros Mensajes:");
            informacion.add   ("---------------"+"\n");
            System.out.println("---------------"+"\n");

            informacion.add   ("Tipo de Acceso:  " + tipoAccesoArrays[i]);
            System.out.println("Tipo de Acceso:  " + tipoAccesoArrays[i]);
            informacion.add   ("Mensaje:         " + mensajeUsuario);
            System.out.println("Mensaje:         " + mensajeUsuario);

            informacion.add   ("-----------------------------------------------------------------------------------"+"\n"+"\n");
            System.out.println("-----------------------------------------------------------------------------------"+"\n"+"\n");

        }
        //CREACION DE REPORTE.TXT
        FileWriter f1 =new FileWriter("C:/Users/x12091/Documents/TXT/ReporteUsuariosInvalidos.txt");
        PrintWriter out =new PrintWriter(f1);

        for (int j=0;j<informacion.size();j++) {
            out.println(informacion.get(j));
        }
        f1.close();

        Path credentialsFile = Paths.get("C:/Users/x12091/Documents/TXT/ReporteUsuariosInvalidos.txt");
        Serenity.recordReportData().withTitle("Evidencia Nexbi").fromFile(credentialsFile);
    }

    public static void datosGenerales(List<WebElement> punto, ArrayList<String> informacion) throws IOException {

        int[] cantUsuarioActivoArrays = Serenity.sessionVariableCalled("cantUsuarioActivoArrays");
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

        informacion.add   ("_____________________________________________________________________________");
        System.out.println("_____________________________________________________________________________");
        informacion.add   ("TIPO DE TARIFA");
        System.out.println("TIPO DE TARIFA");
        informacion.add   ("--------------"+"\n");
        System.out.println("--------------"+"\n");
        if(listaTarifaPreferencial.size()==0){
            informacion.add   ("Tarifa Normal");
            System.out.println("Tarifa Normal");
        }else{
            informacion.add   ("Tarifa Preferencial:");
            System.out.println("Tarifa Preferencial:");
            for(int i=0;i<listaTarifaPreferencial.size();i++){
                informacion.add   (" - "+listaTarifaPreferencial.get(i));
                System.out.println(" - "+listaTarifaPreferencial.get(i));
            }
        }

        informacion.add   ("\n"+"_____________________________________________________________________________");
        System.out.println("\n"+"_____________________________________________________________________________");
        informacion.add   ("MONTO DE TARIFA");
        System.out.println("MONTO DE TARIFA");
        informacion.add   ("---------------"+"\n");
        System.out.println("---------------"+"\n");
        informacion.add   (" - Negocio:            "+montoTarifaNivel20);
        System.out.println(" - Negocio:            "+montoTarifaNivel20);
        informacion.add   (" - Empresa:            "+montoTarifaNivel21);
        System.out.println(" - Empresa:            "+montoTarifaNivel21);
        informacion.add   (" - Corporativo:        "+montoTarifaNivel22);
        System.out.println(" - Corporativo:        "+montoTarifaNivel22);
        informacion.add   (" - Usuario Adicional:  "+montoTarifaNivel40);
        System.out.println(" - Usuario Adicional:  "+montoTarifaNivel40);

        informacion.add   ("\n"+"_____________________________________________________________________________");
        System.out.println("\n"+"_____________________________________________________________________________");
        informacion.add   ("TIPO DE CAMBIO");
        System.out.println("TIPO DE CAMBIO");
        informacion.add   ("--------------"+"\n");
        System.out.println("--------------"+"\n");
        informacion.add   (" - A Dolares:  "+tipoCambioDolares);
        System.out.println(" - A Dolares:  "+tipoCambioDolares);

        informacion.add   ("\n"+"_____________________________________________________________________________");
        System.out.println("\n"+"_____________________________________________________________________________");
        informacion.add   ("ARCHIVO TXT");
        System.out.println("ARCHIVO TXT");
        informacion.add   ("-----------"+"\n");
        System.out.println("-----------"+"\n");
        informacion.add   (" - "+respDescargaImCobrosMensual);
        System.out.println(" - "+respDescargaImCobrosMensual);
        informacion.add   (" - "+respDescargaStCobrosMensual);
        System.out.println(" - "+respDescargaStCobrosMensual);

        informacion.add   ("\n"+"_____________________________________________________________________________");
        System.out.println("\n"+"_____________________________________________________________________________");
        informacion.add   ("MONTOS POR COBRAR");
        System.out.println("MONTOS POR COBRAR");
        informacion.add   ("-----------------"+"\n");
        System.out.println("-----------------"+"\n");
        for(int i=0;i<punto.size();i++){
            if(cantUsuarioActivoArrays[i]==0) {
                informacion.add   (" - Punto de Servicio Nro " + (i + 1) + ":   " + "0.00");
                System.out.println(" - Punto de Servicio Nro " + (i + 1) + ":   " + "0.00");
            }else{
                informacion.add   (" - Punto de Servicio Nro " + (i + 1) + ":   " + montoCobrarArrays[i]);
                System.out.println(" - Punto de Servicio Nro " + (i + 1) + ":   " + montoCobrarArrays[i]);
            }
        }

        informacion.add   ("\n"+"_____________________________________________________________________________");
        System.out.println("\n"+"_____________________________________________________________________________");
        informacion.add   ("COBROS EN EL TXT");
        System.out.println("COBROS EN EL TXT");
        informacion.add   ("----------------"+"\n");
        System.out.println("----------------"+"\n");
        for(int i=0;i<punto.size();i++){
            informacion.add   (" - Punto de Servicio Nro "+(i+1)+":   " + coherenciaCuentaMonto[i]);
            System.out.println(" - Punto de Servicio Nro "+(i+1)+":   " + coherenciaCuentaMonto[i]);
        }

        informacion.add   ("\n"+"_____________________________________________________________________________");
        System.out.println("\n"+"_____________________________________________________________________________");
        informacion.add   ("INCOHERENCIAS ENCONTRADAS");
        System.out.println("INCOHERENCIAS ENCONTRADAS");
        informacion.add   ("-------------------------"+"\n");
        System.out.println("-------------------------"+"\n");
        for(int i=0;i<incoherenciasCuentaMonto.size();i++){
            informacion.add   (" - "+incoherenciasCuentaMonto.get(i));
            System.out.println(" - "+incoherenciasCuentaMonto.get(i));
        }
        informacion.add   ("\n"+"_____________________________________________________________________________");
        System.out.println("\n"+"_____________________________________________________________________________");

        //CREACION DE REPORTE.TXT
        FileWriter f1 =new FileWriter("C:/Users/x12091/Documents/TXT/Reporte.txt");
        PrintWriter out =new PrintWriter(f1);

        for (int i=0;i<informacion.size();i++) {
            out.println(informacion.get(i));
        }
        f1.close();

        Path credentialsFile = Paths.get("C:/Users/x12091/Documents/TXT/Reporte.txt");
        Serenity.recordReportData().withTitle("Mensaje de Serenity").fromFile(credentialsFile);
    }
}

