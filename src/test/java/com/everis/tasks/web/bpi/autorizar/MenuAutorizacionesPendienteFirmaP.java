package com.everis.tasks.web.bpi.autorizar;

import com.everis.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.userinterfaces.web.bpi.LoginPage;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * @author Nilo Carrion
 */
public class MenuAutorizacionesPendienteFirmaP implements Task {

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {

        Thread.sleep(4000);

        actor.attemptsTo(
                (WaitUntil.the(LoginPage.MENU_AUTORIZACIONES, isVisible()).forNoMoreThan(150).seconds()),
                Click.on(LoginPage.MENU_AUTORIZACIONES));

        Thread.sleep(5000);

        actor.attemptsTo(
                (WaitUntil.the(LoginPage.INP_TIPO_SOLICITUD, isVisible()).forNoMoreThan(150).seconds()),
                Click.on(LoginPage.INP_TIPO_SOLICITUD));

        Thread.sleep(3000);

        Serenity.getDriver().findElement(By.xpath("//span[contains(text(),'Pago de servicios')]")).click();

        Thread.sleep(2000);

        actor.attemptsTo(
                WaitUntil.the(LoginPage.BTN_PENDIENTE_FIRMA, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.BTN_PENDIENTE_FIRMA));

        Thread.sleep(2000);

        LoginStepDefinitions.pagosServiciosData.setcodigo("");

        int cont=0;

        System.out.println("numero solicitud : " +LoginStepDefinitions.pagosServiciosData.getNroSolicitudProcess() );

        while(!LoginStepDefinitions.pagosServiciosData.getcodigo().contains(LoginStepDefinitions.pagosServiciosData.getNroSolicitudProcess())){

            cont++;

            Target CODIGO = Target.the("Codigo").located(By.xpath("(//*[@data-test='lblRequestName'])[" + cont + "]"));

            LoginStepDefinitions.pagosServiciosData.setcodigo(String.valueOf(CODIGO.resolveFor(actor).getText()));

            System.out.println("codigo : " +LoginStepDefinitions.pagosServiciosData.getcodigo() );
        }

        System.out.println("conto : " + cont );

        Thread.sleep(2000);

        Serenity.getDriver().findElement(By.xpath("(//*[@data-test='ckhSelectRequest'])[" + cont + "]")).click();

        Thread.sleep(2000);

        actor.attemptsTo(
                WaitUntil.the(LoginPage.BTN_AUTORIZAR_P, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.BTN_AUTORIZAR_P));

        Thread.sleep(2000);

        actor.attemptsTo(
                WaitUntil.the(LoginPage.INP_CONTRASENA, isVisible()).forNoMoreThan(150).seconds(),
                Enter.theValue(LoginStepDefinitions.pagosServiciosData.getPassword()).into(LoginPage.INP_CONTRASENA));

        Thread.sleep(2000);

        actor.attemptsTo(
                WaitUntil.the(LoginPage.INP_TOKEN, isVisible()).forNoMoreThan(150).seconds(),
                Enter.theValue("111111").into(LoginPage.INP_TOKEN));

        Thread.sleep(2000);

        actor.attemptsTo(
                WaitUntil.the(LoginPage.BTN_FINALIZAR_PENDIENTE, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.BTN_FINALIZAR_PENDIENTE));

        if(LoginStepDefinitions.pagosServiciosData.getcredencialIncorrecto().equals("NO")) {

            Thread.sleep(2000);

            Target FECHA_HORA_PROCESS = Target.the("Fecha y hora de envío").located(By.xpath("//*[@data-test='lblDateValue']"));
            LoginStepDefinitions.pagosServiciosData.setFechaHora(String.valueOf(FECHA_HORA_PROCESS.resolveFor(actor).getText()));
        }
    }
}
