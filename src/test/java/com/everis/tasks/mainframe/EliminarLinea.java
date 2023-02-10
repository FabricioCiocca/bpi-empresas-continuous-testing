package com.everis.tasks.mainframe;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;

import java.io.*;
import java.util.ArrayList;

public class EliminarLinea {
    public final int i;
    EnvironmentVariables environmentVariables;
    public final ArrayList<String> cuentaCargoArrays;
    public final ArrayList<Integer> cantUsuarioActivoConfirmadoArrays;
    public final String[] montoCobrarArrays;
    public final ArrayList<String> coherenciaCuentaMonto;

    public EliminarLinea(int i, EnvironmentVariables environmentVariables, ArrayList<String> cuentaCargoArrays, ArrayList<Integer> cantUsuarioActivoConfirmadoArrays, String[] montoCobrarArrays, ArrayList<String> coherenciaCuentaMonto) {
        this.i = i;
        this.environmentVariables = environmentVariables;
        this.cuentaCargoArrays = cuentaCargoArrays;
        this.cantUsuarioActivoConfirmadoArrays = cantUsuarioActivoConfirmadoArrays;
        this.montoCobrarArrays = montoCobrarArrays;
        this.coherenciaCuentaMonto = coherenciaCuentaMonto;
    }

    public static void Txt(int i, EnvironmentVariables environmentVariables, ArrayList<String> cuentaCargoArrays, ArrayList<Integer> cantUsuarioActivoConfirmadoArrays, String[] montoCobrarArrays, ArrayList<String> coherenciaCuentaMonto) throws IOException {

        String[] cuentaCargoArray = cuentaCargoArrays.get(i - 1).split(" ");
        String tipoCuenta = cuentaCargoArray[0] + " " + cuentaCargoArray[1];
        String numeroCuenta = cuentaCargoArray[2].substring(4);

        String linea = "";

        File file = new File("File Name");
        if (tipoCuenta.equals("Cuenta Corriente")) {
            file = new File(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("ruta.im"));
        } else if (tipoCuenta.equals("Cuenta Ahorros")) {
            file = new File(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("ruta.st"));
        }

        BufferedReader br = new BufferedReader(new FileReader(file));

        ArrayList<String> lineas = new ArrayList<>();
        int cantFilaRepetidas = 0;
        if (cantUsuarioActivoConfirmadoArrays.get(i - 1) != null) {
            if (cantUsuarioActivoConfirmadoArrays.get(i - 1) != 0) {
                while ((linea = br.readLine()) != null) {
                    if (cantFilaRepetidas == 0) {//String.format("%06.2f",Double.parseDouble(montoCobrarArrays[i - 1])
                        if (linea.contains(numeroCuenta) && linea.contains(String.format("%06.2f", Double.parseDouble(montoCobrarArrays[i - 1])))) {
                            cantFilaRepetidas++;
                            coherenciaCuentaMonto.add(linea);
                        } else {
                            lineas.add(linea);
                        }
                    } else {
                        lineas.add(linea);
                    }
                }
                FileWriter f1 = new FileWriter(file);
                PrintWriter out = new PrintWriter(f1);

                for (int j = 0; j < lineas.size(); j++) {
                    out.println(lineas.get(j));
                }
                f1.close();
            }
        }
        Serenity.setSessionVariable("coherenciaCuentaMonto").to(coherenciaCuentaMonto);
    }
}
