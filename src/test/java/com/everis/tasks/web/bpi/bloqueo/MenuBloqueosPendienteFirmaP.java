package com.everis.tasks.web.bpi.bloqueo;

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
public class MenuBloqueosPendienteFirmaP implements Task {

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                (WaitUntil.the(LoginPage.MENU_AUTORIZACIONES, isVisible()).forNoMoreThan(150).seconds()),
                Click.on(LoginPage.MENU_AUTORIZACIONES));

        actor.attemptsTo(
                (WaitUntil.the(LoginPage.INP_TIPO_SOLICITUD, isVisible()).forNoMoreThan(150).seconds()),
                Click.on(LoginPage.INP_TIPO_SOLICITUD));

        Serenity.getDriver().findElement(By.xpath("//span[contains(text(),'Pago de servicios')]")).click();

        actor.attemptsTo(
                WaitUntil.the(LoginPage.BTN_PENDIENTE_FIRMA, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.BTN_PENDIENTE_FIRMA));

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

        Serenity.getDriver().findElement(By.xpath("(//*[@data-test='ckhSelectRequest'])[" + cont + "]")).click();

        actor.attemptsTo(
                WaitUntil.the(LoginPage.BTN_BLOQUEAR_P, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.BTN_BLOQUEAR_P));

        actor.attemptsTo(
                WaitUntil.the(LoginPage.INP_CONTRASENA, isVisible()).forNoMoreThan(150).seconds(),
                Enter.theValue(LoginStepDefinitions.pagosServiciosData.getPassword()).into(LoginPage.INP_CONTRASENA));

        actor.attemptsTo(
                WaitUntil.the(LoginPage.INP_TOKEN, isVisible()).forNoMoreThan(150).seconds(),
                Enter.theValue("111111").into(LoginPage.INP_TOKEN));

        actor.attemptsTo(
                WaitUntil.the(LoginPage.BTN_FINALIZAR_PENDIENTE, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.BTN_FINALIZAR_PENDIENTE));

    }
}
