package com.everis.userinterfaces.web.nexbi;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {
    public static final Target BTN_INGRESAR = Target.the("Boton Ingresar").located(By.xpath("(//*[@type='button'])[2]"));
    public static final Target INP_USER = Target.the("Ingresamos Correo").located(By.xpath("//*[@type='email']"));
    public static final Target INP_PASSWORD = Target.the("Ingresamos Contraseña").located(By.xpath("//*[@type='password']"));
    public static final Target BTN_NEXT = Target.the("Boton Siguiente").located(By.xpath("//*[@type='submit']"));
    public static final Target BTN_INICIAR_SESION = Target.the("Boton de Iniciar Sesión").located(By.xpath("//*[@class='submit']"));


}
