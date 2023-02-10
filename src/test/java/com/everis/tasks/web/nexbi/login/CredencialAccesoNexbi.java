package com.everis.tasks.web.nexbi.login;

import com.everis.bpi.userinterface.web.nexbi.LoginPage;
import lombok.SneakyThrows;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Switch;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.util.EnvironmentVariables;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * @author Nilo Carrion
 */
public class CredencialAccesoNexbi implements Task {
    private final String correo;
    private final String password;
    EnvironmentVariables environmentVariables;

    public CredencialAccesoNexbi(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }

    public static Performable withData(String correo, String password) {
        return instrumented(CredencialAccesoNexbi.class, correo, password);
    }

    @SneakyThrows
    @Override
    @Step("{0} {0} Ingresamos datos de usuario")
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(LoginPage.BTN_INGRESAR, isVisible()).forNoMoreThan(40).seconds(),
                Click.on(LoginPage.BTN_INGRESAR));
        Set<String> currentHandle = BrowseTheWeb.as(actor).getDriver().getWindowHandles();
        List<String> handles = new ArrayList<>(currentHandle);

        actor.attemptsTo(
                Switch.toWindow(handles.get(1)),
                WaitUntil.the(LoginPage.INP_USER, isVisible()).forNoMoreThan(20).seconds(),
                Enter.theValue(correo).into(LoginPage.INP_USER),
                Click.on(LoginPage.BTN_NEXT),
                WaitUntil.the(LoginPage.INP_PASSWORD, isVisible()).forNoMoreThan(20).seconds(),
                Enter.theValue(password).into(LoginPage.INP_PASSWORD),
                Click.on(LoginPage.BTN_INICIAR_SESION),
                Click.on(LoginPage.BTN_NEXT),
                Switch.toWindow(handles.get(0)));

        String environment = System.getProperty("environment");
        System.out.println("Current environment: " + environment);

       if (environment.trim().equals("dev")) {
            Screen s = new Screen();
            try{
                String pathYourSystem = System.getProperty("user.dir") + "/";
                s.wait(pathYourSystem + EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("ruta.imagcredencial"),20);
                s.click(new Pattern( pathYourSystem + EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("ruta.imagcredencial")));
            }
            catch (FindFailed e){
                e.printStackTrace();
            }
        }


    }
}
