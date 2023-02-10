package com.everis.questions.mainframe;

import com.everis.bpi.utils.mainframe.ImprimirDetalleInformacion;
import mainframe.com.bdd.lib.EmulatorActions;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Question;
import net.thucydides.core.util.EnvironmentVariables;

import java.io.*;
import java.util.ArrayList;

public class ValidacionCobros extends EmulatorActions {


    public static Question<String> GetStringByPosition(int iFila, int iColumna, int ctdFilas, int ctdColumnas) {
        String Cadena = getInstancia().obtenerCadenaPorPosicion(iFila, iColumna, ctdFilas, ctdColumnas);
        return actor -> Cadena;
    }

    public static Question<Boolean> DetectorIncoherencias(int i, EnvironmentVariables environmentVariables, ArrayList<String> cuentaCargoArrays, ArrayList<String> incoherenciasCuentaMonto, ArrayList<String> informacion) throws IOException {

        ArrayList<String> coherenciaCuentaMonto = Serenity.sessionVariableCalled("coherenciaCuentaMonto");
        ArrayList<Integer> cantUsuarioActivoConfirmadoArrays = Serenity.sessionVariableCalled("cantUsuarioActivoConfirmadoArrays");

        String[] cuentaCargoArray = cuentaCargoArrays.get(i - 1).split(" ");
        String tipoCuenta = cuentaCargoArray[0] + " " + cuentaCargoArray[1];
        String numeroCuenta = cuentaCargoArray[2].substring(4);

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

        while ((linea = br.readLine()) != null) {
            if (linea.contains(numeroCuenta)) {
                incoherenciasCuentaMonto.add(linea);
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

        Serenity.setSessionVariable("incoherenciasCuentaMonto").to(incoherenciasCuentaMonto);

        if (i == 1) {
            if (incoherenciasCuentaMonto.size() > 0) {
                resultado = false;
            }
            int count = 0;
            for (int k = 0; k < cantUsuarioActivoConfirmadoArrays.size(); k++) {
                if (cantUsuarioActivoConfirmadoArrays.get(k) != null) {
                    if (cantUsuarioActivoConfirmadoArrays.get(k) != 0) {
                        count++;
                    }
                }
            }
            if (count != coherenciaCuentaMonto.size()) {
                resultado = false;
                coherenciaCuentaMonto.add("No se encontraron algunos cobros");
            }
            ImprimirDetalleInformacion.prueba(informacion);
        }
        boolean finalResultado = resultado;
        return actor -> finalResultado;
    }
}