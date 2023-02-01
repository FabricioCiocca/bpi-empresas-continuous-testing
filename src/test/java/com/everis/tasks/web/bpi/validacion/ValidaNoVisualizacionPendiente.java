package com.everis.tasks.web.bpi.validacion;

import com.everis.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.userinterfaces.web.bpi.LoginPage;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * @author Nilo Carrion
 */
public class ValidaNoVisualizacionPendiente implements Task {

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {

        Thread.sleep(3000);

        actor.attemptsTo(
                (WaitUntil.the(LoginPage.MENU_AUTORIZACIONES, isVisible()).forNoMoreThan(150).seconds()),
                Click.on(LoginPage.MENU_AUTORIZACIONES));

        Thread.sleep(2000);

        actor.attemptsTo(
                (WaitUntil.the(LoginPage.INP_TIPO_SOLICITUD, isVisible()).forNoMoreThan(150).seconds()),
                Click.on(LoginPage.INP_TIPO_SOLICITUD));

        Thread.sleep(2000);

        Serenity.getDriver().findElement(By.xpath("//span[contains(text(),'Pago de servicios')]")).click();

        Thread.sleep(2000);

        actor.attemptsTo(
                WaitUntil.the(LoginPage.BTN_PENDIENTE_FIRMA, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.BTN_PENDIENTE_FIRMA));

        Thread.sleep(2000);

        LoginStepDefinitions.pagosServiciosData.setcodigo("");



        Target CANTIDAD_AUTORIZACION = Target.the("Cantidad de Autorizaciones").located(By.xpath("//*[@class='mat-paginator-range-label']"));
        LoginStepDefinitions.pagosServiciosData.setcantAutorizacion(String.valueOf(CANTIDAD_AUTORIZACION.resolveFor(actor).getText()));



        if(LoginStepDefinitions.pagosServiciosData.getcantAutorizacion().length()==12){

            LoginStepDefinitions.pagosServiciosData.setcantAutorizacion(LoginStepDefinitions.pagosServiciosData.getcantAutorizacion().substring(LoginStepDefinitions.pagosServiciosData.getcantAutorizacion().length()-2));

        }else{
            LoginStepDefinitions.pagosServiciosData.setcantAutorizacion(LoginStepDefinitions.pagosServiciosData.getcantAutorizacion().substring(LoginStepDefinitions.pagosServiciosData.getcantAutorizacion().length()-1));
        }

        System.out.println("numero solicitud : " +LoginStepDefinitions.pagosServiciosData.getcantAutorizacion() );
        System.out.println("numero solicitud : " +LoginStepDefinitions.pagosServiciosData.getNroSolicitudProcess() );

        int cont= Integer.parseInt(LoginStepDefinitions.pagosServiciosData.getcantAutorizacion());

        while(!LoginStepDefinitions.pagosServiciosData.getcodigo().contains(LoginStepDefinitions.pagosServiciosData.getNroSolicitudProcess())){


            if(cont==0) {

                LoginStepDefinitions.pagosServiciosData.setcodigo(LoginStepDefinitions.pagosServiciosData.getNroSolicitudProcess());

            }else {
                Target CODIGO = Target.the("Codigo").located(By.xpath("(//*[@data-test='lblRequestName'])[" + cont + "]"));
                LoginStepDefinitions.pagosServiciosData.setcodigo(String.valueOf(CODIGO.resolveFor(actor).getText()));
            }

            cont--;
            System.out.println("codigo : " +LoginStepDefinitions.pagosServiciosData.getcodigo() );

        }

        System.out.println("conto : " + cont + "pase");

    }
}
