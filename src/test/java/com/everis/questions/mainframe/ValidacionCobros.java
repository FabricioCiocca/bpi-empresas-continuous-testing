package com.everis.questions.mainframe;

import com.everis.utils.mainframe.ImprimirDetalleInformacion;
import mainframe.com.bdd.lib.EmulatorActions;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Question;
import net.thucydides.core.util.EnvironmentVariables;

import java.io.*;
import java.util.ArrayList;

public class ValidacionCobros extends EmulatorActions {

    public static Question<Boolean> valideText(int i, EnvironmentVariables environmentVariables, ArrayList<String> cuentaCargoArrays, ArrayList<Integer> cantUsuarioActivoArrays, String[] montoCobrarArrays, String[] coherenciaCuentaMonto, String[] incoherenciasCuentaMonto, ArrayList<String> informacion) throws IOException {

        String[] cuentaCargoArray = cuentaCargoArrays.get(i-1).split(" ");
        String tipoCuenta = cuentaCargoArray[1] + " " + cuentaCargoArray[2];
        String numeroCuenta = cuentaCargoArray[3].substring(4);

        String linea = "";
        boolean resultado = true;

        File file = new File("File Name");
        if (tipoCuenta.equals("Cuenta Corriente")) {
            file = new File(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("ruta.im"));
        } else if (tipoCuenta.equals("Cuenta Ahorros")) {
            file = new File(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("ruta.st"));
        }

        BufferedReader br = new BufferedReader(new FileReader(file));

        ArrayList<String> lineas = new ArrayList<>();

        boolean cuentaMontoEncontados = false;

        while ((linea = br.readLine()) != null) {
            if (linea.contains(numeroCuenta) && !cuentaMontoEncontados) {
                cuentaMontoEncontados = true;
                if (cantUsuarioActivoArrays.get(i - 1) > 0) {
                    if (linea.contains(montoCobrarArrays[i - 1])) {
                        coherenciaCuentaMonto[i - 1] = linea;
                        incoherenciasCuentaMonto[i - 1] = "No se encontro Incoherencia";

                    } else {
                        coherenciaCuentaMonto[i - 1] = "No se encontro el cobro correspondiente";
                        incoherenciasCuentaMonto[i - 1] = linea;
                    }
                } else {
                    coherenciaCuentaMonto[i - 1] = "No se encontro el cobro correspondiente";
                    incoherenciasCuentaMonto[i - 1] = linea;
                }
            } else {
                lineas.add(linea);
            }
        }

        if (!cuentaMontoEncontados) {
            coherenciaCuentaMonto[i - 1] = "No se encontro el cobro correspondiente";
            incoherenciasCuentaMonto[i - 1] = "No se encontro Incoherencia";
        }

        if (i==1){
            for (int j = 0; j < cuentaCargoArrays.size(); j++) {
                if (!incoherenciasCuentaMonto[j].equals("No se encontro Incoherencia")) {
                    resultado=false;
                    break;
                }
            }
        }

        FileWriter f1 =new FileWriter(file);
        PrintWriter out =new PrintWriter(f1);

        for (int j=0;j<lineas.size();j++) {
            out.println(lineas.get(j));
        }
        f1.close();

        if(i==1) {
            ImprimirDetalleInformacion.prueba(informacion);
        }

        Serenity.setSessionVariable("coherenciaCuentaMonto").to(coherenciaCuentaMonto);
        Serenity.setSessionVariable("incoherenciasCuentaMonto").to(incoherenciasCuentaMonto);

        boolean finalResultado = resultado;
        return actor -> finalResultado;
    }

    public static Question<String> GetStringByPosition(int iFila, int iColumna, int ctdFilas, int ctdColumnas){
        String Cadena = getInstancia().obtenerCadenaPorPosicion(iFila,iColumna,ctdFilas,ctdColumnas);
        return actor -> Cadena;
    }
}
