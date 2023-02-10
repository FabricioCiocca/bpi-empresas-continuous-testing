package com.everis.userinterfaces.web.bpi;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PagoRealizadoPage {

    public static final Target INP_DESCRIPCION = Target.the("Descripción").located(By.xpath("//input[@data-test='txtDescription']"));

    public static final Target BTN_CONINUE = Target.the("Botón Continuar").located(By.xpath("//*[@id='btnContinue']"));
    public static final Target INP_CONTRASENA = Target.the("Contraseña").located(By.xpath("//*[@data-test='txtPassword']"));
    public static final Target INP_TOKEN = Target.the("Token").located(By.xpath("//*[@data-test='txtToken']"));
    public static final Target BTN_FINALIZAR = Target.the("Token").located(By.xpath("//*[@id='btnContinue']"));

    public static final Target REVISA_DETALLE_ESTADO = Target.the("Revisa su estado en el detalle").located(By.xpath("(//span[@class='hover-text-underline ng-star-inserted'])"));

    public static final Target REGRESAR_DETALLE_ESTADO = Target.the("Regresar estado").located(By.xpath("//span[@class='hover-text-underline']"));

}
