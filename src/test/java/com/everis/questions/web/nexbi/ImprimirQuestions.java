package com.everis.questions.web.nexbi;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Question;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ImprimirQuestions {

    public static Question<String> Imprimir(List<WebElement> punto) {
        String[] nombrePuntoServicioArrays = Serenity.sessionVariableCalled("nombrePuntoServicioArrays");
        String[] puntoServicioArrays = Serenity.sessionVariableCalled("puntoServicioArrays");
        String[] tipoClienteArrays = Serenity.sessionVariableCalled("tipoClienteArrays");
        String[] nivelServicioArrays = Serenity.sessionVariableCalled("nivelServicioArrays");
        String[] cuentaCargoArrays = Serenity.sessionVariableCalled("cuentaCargoArrays");
        String[] monedaCuentaArrays = Serenity.sessionVariableCalled("monedaCuentaArrays");
        String[] tipoAccesoArrays = Serenity.sessionVariableCalled("tipoAccesoArrays");
        int[] cantUsuarioActivoArrays = Serenity.sessionVariableCalled("cantUsuarioActivoArrays");
        int[] cantUsuarioArrays = Serenity.sessionVariableCalled("cantUsuarioArrays");

        for (int i = 0; i < punto.size(); i++) {
            String[][] seccionNumeroTieArrays = Serenity.sessionVariableCalled("seccionNumeroTieArrays" + i);
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.println("PUNTO DE SERVICIO N°" + (i + 1) + ":");
            System.out.println("Nombre del Punto de Servicio:  " + nombrePuntoServicioArrays[i].trim());
            System.out.println("Numero del Punto de Servicio:  " + puntoServicioArrays[i].trim() + "\n");
            System.out.println("INFORMACION DEL PUNTO DE SERVICIO:");
            System.out.println("- Tipo de Cliente:     " + tipoClienteArrays[i].trim());
            System.out.println("- Nivel de Servicio:   " + nivelServicioArrays[i].trim());
            System.out.println("- Cuenta de Cargo:     " + cuentaCargoArrays[i].trim());
            System.out.println("- Moneda:              " + monedaCuentaArrays[i].trim() + "\n");
            System.out.println("USUARIOS ACTIVOS:");
            System.out.println("--------------------------");
            for (int j = 0; j < cantUsuarioArrays[i]; j++) {
                System.out.println("Usuario N°" + (j + 1) + ":");
                System.out.println("- Seccion TIE:    " + seccionNumeroTieArrays[j][0]);
                System.out.println("- Numero de TIE:  " + seccionNumeroTieArrays[j][1]);
                System.out.println("--------------------------");
            }
            System.out.println("Tipo de Acceso:  " + tipoAccesoArrays[i]);
            System.out.println("Cantidad de Usuarios Activos Confirmados: " + cantUsuarioActivoArrays[i]);
            System.out.println("----------------------------------------------------------------------------------------");

        }
        return actor -> String.valueOf(Imprimir(punto));
    }
    public static Question<String> Imprimir2(List<WebElement> punto) {
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
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.println("PUNTO DE SERVICIO N°" + (i + 1) + ":");
            System.out.println("Nombre del Punto de Servicio:  " + nombrePuntoServicioArrays[i].trim());
            System.out.println("Numero del Punto de Servicio:  " + puntoServicioArrays[i].trim() + "\n");
            System.out.println("INFORMACION DEL PUNTO DE SERVICIO:");
            System.out.println("- Tipo de Cliente:     " + tipoClienteArrays[i].trim());
            System.out.println("- Nivel de Servicio:   " + nivelServicioArrays[i].trim());
            System.out.println("- Cuenta de Cargo:     " + cuentaCargoArrays[i].trim());
            System.out.println("- Moneda:              " + monedaCuentaArrays[i].trim() + "\n");
            System.out.println("USUARIOS ACTIVOS:");
            System.out.println("--------------------------");
            System.out.println("Tipo de Acceso:  " + tipoAccesoArrays[i]);
            System.out.println("Mensaje:   " + mensajeUsuario);
            System.out.println("Cantidad de Usuarios Activos Confirmados: " + cantUsuarioActivoArrays[i]);
            System.out.println("----------------------------------------------------------------------------------------" + "\n" + "\n");
        }
        return actor -> String.valueOf(Imprimir2(punto));
    }
}
