package com.everis.utils.mainframe;

import net.serenitybdd.core.Serenity;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ImprimirDetalleInformacionNuevo {


    public static void datosValidos(ArrayList<String> informacion) throws IOException {
        ArrayList<String> nombrePuntoServicioArrays = Serenity.sessionVariableCalled("nombrePuntoServicioArrays");
        ArrayList<String> numeroPuntoServicioArrays = Serenity.sessionVariableCalled("numeroPuntoServicioArrays");
        ArrayList<String> tipoClienteArrays = Serenity.sessionVariableCalled("tipoClienteArrays");
        ArrayList<String> nivelServicioArrays = Serenity.sessionVariableCalled("nivelServicioArrays");
        ArrayList<String> cuentaCargoArrays = Serenity.sessionVariableCalled("cuentaCargoArrays");
        ArrayList<String> monedaCuentaArrays = Serenity.sessionVariableCalled("monedaCuentaArrays");
        ArrayList<String> tipoAccesoArrays = Serenity.sessionVariableCalled("tipoAccesoArrays");
        ArrayList<Integer> cantUsuarioActivoConfirmadoArrays = Serenity.sessionVariableCalled("cantUsuarioActivoConfirmadoArrays");
        ArrayList<Integer> cantUsuarioActivoArrays = Serenity.sessionVariableCalled("cantUsuarioActivoArrays");
        ArrayList<Integer> cantUsuarioArrays = Serenity.sessionVariableCalled("cantUsuarioArrays");

        for (int i = 0; i < nombrePuntoServicioArrays.size(); i++) {
            String[][] seccionNumeroTieArrays = Serenity.sessionVariableCalled("seccionNumeroTieArrays" + i);

            informacion.add("\n" + "_____________________________________________________________________________");
            System.out.println("\n" + "_____________________________________________________________________________");
            informacion.add("Punto de Servicio Nro: " + (i + 1));
            System.out.println("Punto de Servicio Nro: " + (i + 1));
            informacion.add("-------------------------" + "\n");
            System.out.println("-------------------------" + "\n");

            informacion.add("Nombre del Punto de Servicio:   " + nombrePuntoServicioArrays.get(i));
            System.out.println("Nombre del Punto de Servicio:   " + nombrePuntoServicioArrays.get(i));
            informacion.add("Numero del Punto de Servicio:   " + numeroPuntoServicioArrays.get(i + 1) + "\n");
            System.out.println("Numero del Punto de Servicio:   " + numeroPuntoServicioArrays.get(i + 1) + "\n");

            informacion.add("\n" + "_____________________________________________________________________________");
            System.out.println("\n" + "_____________________________________________________________________________");

            informacion.add("Información del punto de servicio:");
            System.out.println("Información del punto de servicio:");
            informacion.add("----------------------------------" + "\n");
            System.out.println("----------------------------------" + "\n");

            informacion.add("- Tipo de Cliente:     " + tipoClienteArrays.get(i));
            System.out.println("- Tipo de Cliente:     " + tipoClienteArrays.get(i));
            informacion.add("- Nivel de Servicio:   " + nivelServicioArrays.get(i));
            System.out.println("- Nivel de Servicio:   " + nivelServicioArrays.get(i));
            informacion.add("- Cuenta de Cargo:     " + cuentaCargoArrays.get(i));
            System.out.println("- Cuenta de Cargo:     " + cuentaCargoArrays.get(i));
            informacion.add("- Moneda:              " + monedaCuentaArrays.get(i));
            System.out.println("- Moneda:              " + monedaCuentaArrays.get(i));

            informacion.add("\n" + "_____________________________________________________________________________");
            System.out.println("\n" + "_____________________________________________________________________________");

            informacion.add("Usuarios Activos:");
            System.out.println("Usuarios Activos:");
            informacion.add("-----------------");
            System.out.println("-----------------");

            if (cantUsuarioActivoArrays.get(i) == null) {
                informacion.add("Aún no se ha creado ningún usuario para este punto de servicio.");
                System.out.println("Aún no se ha creado ningún usuario para este punto de servicio.");
            } else if (cantUsuarioActivoArrays.get(i) == 0) {
                informacion.add("No se encontro Usuario Activo para este punto de servicio.");
                System.out.println("No se encontro Usuario Activo para este punto de servicio.");
            } else {
                for (int j = 0; j < cantUsuarioActivoArrays.get(i); j++) {
                    informacion.add("  *Usuario Nro: " + (j + 1));
                    System.out.println("  *Usuario Nro: " + (j + 1));
                    informacion.add("  ---------------");
                    System.out.println("  ---------------");
                    informacion.add("   - Seccion TIE:    " + seccionNumeroTieArrays[j][0]);
                    System.out.println("   - Seccion TIE:    " + seccionNumeroTieArrays[j][0]);
                    informacion.add("   - Numero de TIE:  " + seccionNumeroTieArrays[j][1]);
                    System.out.println("   - Numero de TIE:  " + seccionNumeroTieArrays[j][1]);
                }
            }

            informacion.add("  ------------------------" + "\n");
            System.out.println("  ------------------------" + "\n");

            if (cantUsuarioActivoArrays.get(i) == null) {
                informacion.add("Cantidad de Usuarios: Ninguno");
                System.out.println("Cantidad de Usuarios: Ninguno");
            } else {
                informacion.add("Cantidad de Usuarios: " + cantUsuarioArrays.get(i));
                System.out.println("Cantidad de Usuarios: " + cantUsuarioArrays.get(i));
                informacion.add("Cantidad de Usuarios Activos: " + cantUsuarioActivoArrays.get(i));
                System.out.println("Cantidad de Usuarios Activos: " + cantUsuarioActivoArrays.get(i));
                informacion.add("Cantidad de Usuarios Activos Confirmados: " + cantUsuarioActivoConfirmadoArrays.get(i));
                System.out.println("Cantidad de Usuarios Activos Confirmados: " + cantUsuarioActivoConfirmadoArrays.get(i));
            }

            informacion.add("\n" + "_____________________________________________________________________________");
            System.out.println("\n" + "_____________________________________________________________________________");

            informacion.add("Tipo de Acceso:  " + tipoAccesoArrays.get(i));
            System.out.println("Tipo de Acceso:  " + tipoAccesoArrays.get(i));

            informacion.add("_____________________________________________________________________________" + "\n" + "\n");
            System.out.println("_____________________________________________________________________________" + "\n" + "\n");
        }

        String filepath = "./report/ReporteUsuariosValidos.txt";
        //CREACION DE REPORTE.TXT
        FileWriter f1 = new FileWriter(filepath);
        PrintWriter out = new PrintWriter(f1);

        for (int j = 0; j < informacion.size(); j++) {
            out.println(informacion.get(j));
        }
        f1.close();

        Path credentialsFile = Paths.get(filepath);
        Serenity.recordReportData().withTitle("Evidencia Nexbi").fromFile(credentialsFile);
    }


}

