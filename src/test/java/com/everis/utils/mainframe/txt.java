package com.everis.utils.mainframe;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;

import java.io.*;

public class txt {
    public final int i;
    EnvironmentVariables environmentVariables;
    public final String[] cuentaCargoArrays;
    public final String[] montoCobrarArrays;
    public final String[] coherenciaCuentaMonto;

    public txt(int i, EnvironmentVariables environmentVariables, String[] cuentaCargoArrays, String[] montoCobrarArrays, String[] coherenciaCuentaMonto) {
        this.i = i;
        this.environmentVariables = environmentVariables;
        this.cuentaCargoArrays = cuentaCargoArrays;
        this.montoCobrarArrays = montoCobrarArrays;
        this.coherenciaCuentaMonto = coherenciaCuentaMonto;
    }

    public static void valideRowDeletion(int i, EnvironmentVariables environmentVariables, String[] cuentaCargoArrays, String[] montoCobrarArrays, String[] coherenciaCuentaMonto) throws IOException {
        String[] cuentaCargoArray = cuentaCargoArrays[i].split(" ");
        String tipoCuenta = cuentaCargoArray[1] + " " + cuentaCargoArray[2];
        String numeroCuenta = cuentaCargoArray[3].substring(4);

        String texto;
        int count=0;
        int cantFilaRepetidas = 0;
        String lineaCuentaMonto = "";

        File file = new File("File Name");
        if (tipoCuenta.equals("Cuenta Corriente")) {
            file = new File(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("ruta.im"));
        } else if (tipoCuenta.equals("Cuenta Ahorros")) {
            file = new File(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("ruta.st"));
        }

        BufferedReader brc = new BufferedReader(new FileReader(file));
        while ((brc.readLine()) != null) {
            count++;
        }

        BufferedReader brl = new BufferedReader(new FileReader(file));

        String[] lineas=new String[count];
        count=0;

        while ((texto = brl.readLine()) != null) {
            if (texto.contains(numeroCuenta) && texto.contains(montoCobrarArrays[i])) {
                cantFilaRepetidas++;
                lineaCuentaMonto = texto;
                if(cantFilaRepetidas==1){
                    count--;
                }
            }
            if (!(texto.contains(numeroCuenta) && texto.contains(montoCobrarArrays[i])) || cantFilaRepetidas > 1) {
                lineas[count] = texto;
            }
            count++;
        }

        if(cantFilaRepetidas==0) {
            coherenciaCuentaMonto[i]="No se encontro el cobro correspondiente";

        }else{
            coherenciaCuentaMonto[i]=lineaCuentaMonto;
        }

        FileWriter f1 =new FileWriter(file);
        PrintWriter out =new PrintWriter(f1);

        for (int j=0;j<count;j++) {
            out.println(lineas[j]);
        }
        f1.close();

        Serenity.setSessionVariable("coherenciaCuentaMonto").to(coherenciaCuentaMonto);
    }
    public static void valideRowDeletionSinUser(int i, EnvironmentVariables environmentVariables, String[] cuentaCargoArrays, String[] coherenciaCuentaMonto) throws IOException {
        String[] cuentaCargoArray = cuentaCargoArrays[i].split(" ");
        String tipoCuenta = cuentaCargoArray[1] + " " + cuentaCargoArray[2];
        String numeroCuenta = cuentaCargoArray[3].substring(4);

        String texto;
        int count=0;
        int cantFilaRepetidas = 0;
        String lineaCuentaMonto = "";

        File file = new File("File Name");
        if (tipoCuenta.equals("Cuenta Corriente")) {
            file = new File(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("ruta.im"));
        } else if (tipoCuenta.equals("Cuenta Ahorros")) {
            file = new File(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("ruta.st"));
        }

        BufferedReader brc = new BufferedReader(new FileReader(file));
        while ((brc.readLine()) != null) {
            count++;
        }

        BufferedReader brl = new BufferedReader(new FileReader(file));
        String[] lineas=new String[count];
        count=0;

        while ((texto = brl.readLine()) != null) {
            if (texto.contains(numeroCuenta) && texto.contains("000.00")) {
                cantFilaRepetidas++;
                lineaCuentaMonto = texto;
                if(cantFilaRepetidas==1){
                    count--;
                }
            }
            if (!(texto.contains(numeroCuenta) && texto.contains("000.00")|| cantFilaRepetidas > 1)) {
                lineas[count] = texto;
            }
            count++;
        }

        if(cantFilaRepetidas==0) {
            coherenciaCuentaMonto[i]="No se encontro el cobro correspondiente";

        }else{
            coherenciaCuentaMonto[i]=lineaCuentaMonto;
        }

        FileWriter f1 =new FileWriter(file);
        PrintWriter out =new PrintWriter(f1);

        for (int j=0;j<count;j++) {
            out.println(lineas[j]);
        }
        f1.close();

        Serenity.setSessionVariable("coherenciaCuentaMonto").to(coherenciaCuentaMonto);
    }
}
