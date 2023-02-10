package com.everis.questions.web.nexbi;

import com.everis.userinterfaces.web.nexbi.QueryPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.questions.TextContent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NexbiQuestions {
    public static Question<String> tipoCliente() {
        return actor -> TextContent.of(QueryPage.TIPO_CLIENTE).answeredBy(actor);
    }

    public static Question<String> nivelServicio() {
        return actor -> TextContent.of(QueryPage.NIVEL_SERVICIO).answeredBy(actor);
    }

    public static Question<String> cuentaCargo() {
        return actor -> TextContent.of(QueryPage.CUENTA_CARGO).answeredBy(actor);
    }

    public static Question<String> monedaCuenta() {
        return actor -> TextContent.of(QueryPage.MONEDA_CUENTA).answeredBy(actor);
    }
    public static Question<String> cantUsuarios() {
        return actor -> TextContent.of(QueryPage.CANT_USUARIOS).answeredBy(actor);
    }
    public static Question<String> cantTotalUsuarios() {
        return actor -> TextContent.of(QueryPage.TOTAL_USUARIOS).answeredBy(actor);
    }
    public static Question<String> numeroPagina() {
        return actor -> TextContent.of(QueryPage.NUMERO_PAGINA).answeredBy(actor);
    }
    public static Question<String> siguientePagina() {
        return actor -> TextContent.of(QueryPage.SIGUIENTE_PAGINA).answeredBy(actor);
    }
    public static Question<String> sinUsuario() {
        return actor -> TextContent.of(QueryPage.SIN_USUARIO).answeredBy(actor);
    }

    public static Question<String> numeroTie() {
        return actor -> TextContent.of(QueryPage.NUMERO_TIE).answeredBy(actor);
    }

    public static Question<List<WebElement>> listaPuntoServicio() {
        return actor -> (List<WebElement>) BrowseTheWeb.as(actor).getDriver().findElements(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'])"));
    }
    public static Question<String> nivelServicioUsuario() {
        return actor -> TextContent.of(QueryPage.NIVEL_SERVICIO_USUARIO).answeredBy(actor);
    }
    public static Question<String> nombreUsuario() {
        return actor -> TextContent.of(QueryPage.NOMBRE_USUARIO).answeredBy(actor);
    }
}
