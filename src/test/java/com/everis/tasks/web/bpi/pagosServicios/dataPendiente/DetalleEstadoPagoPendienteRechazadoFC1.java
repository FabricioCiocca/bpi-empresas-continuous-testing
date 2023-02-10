package com.everis.tasks.web.bpi.pagosServicios.dataPendiente;

import com.everis.questions.web.bpi.EstadoPagoQuestions;
import com.everis.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import com.everis.userinterfaces.web.bpi.LoginPage;
import lombok.SneakyThrows;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class DetalleEstadoPagoPendienteRechazadoFC1 implements Task {
    private final String Usuario;
    private final String tipoCuenta;
    private final String cuentaOrigen;

    public DetalleEstadoPagoPendienteRechazadoFC1(String Usuario, String tipoCuenta, String cuentaOrigen) {

        this.Usuario = Usuario;
        this.tipoCuenta = tipoCuenta;
        this.cuentaOrigen = cuentaOrigen;

    }

    public static Performable withData(String dniUsuario, String cuentaOrigen, String tipoCuenta) {

        return instrumented(DetalleEstadoPagoPendienteRechazadoFC1.class, dniUsuario, cuentaOrigen, tipoCuenta);

    }

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {

        LoginStepDefinitions.pagosServiciosData.setNombreusuario(Usuario);

        LoginStepDefinitions.pagosServiciosData.setDatosCargoDetalle(tipoCuenta + " " + cuentaOrigen);


        //CONFIRMACION DE PAGO------------------------------------------------------------------------------------------

        Target FECHA_HORA_PROCESS = Target.the("Fecha y hora de envío").located(By.xpath("//*[@data-test='lblDateValue']"));
        LoginStepDefinitions.pagosServiciosData.setFechaHora(String.valueOf(FECHA_HORA_PROCESS.resolveFor(actor).getText()));

        System.out.println("nombres de usuario: " + LoginStepDefinitions.pagosServiciosData.getNombreusuario());
        System.out.println("confirmacion - numero de solicitud: " + LoginStepDefinitions.pagosServiciosData.getNroSolicitudProcess());
        System.out.println("confirmacion - fecha y hora de envío: " + LoginStepDefinitions.pagosServiciosData.getFechaHora());
        System.out.println("confirmacion - fecha y hora de envío 2: " + LoginStepDefinitions.pagosServiciosData.getFechaHora2());
        System.out.println("confirmacion - monto: " + LoginStepDefinitions.pagosServiciosData.getMontoProcess());

        actor.attemptsTo(
                WaitUntil.the(LoginPage.VER_DETALLE, isVisible()).forNoMoreThan(4).seconds(),
                Click.on(LoginPage.VER_DETALLE));

        //DETALLE-------------------------------------------------------------------------------------------------------

        String[] fechaHoraAux = LoginStepDefinitions.pagosServiciosData.getFechaHora2().split("-");
        String[] nombreUsuarioAux = LoginStepDefinitions.pagosServiciosData.getNombreusuario().split("-");


        System.out.println("fecha: " + fechaHoraAux[0]);
        System.out.println("fecha: " + fechaHoraAux[1]);
        System.out.println("fecha: " + fechaHoraAux[2]);
        System.out.println("fecha: " + fechaHoraAux[3]);

        System.out.println("usuario: " + nombreUsuarioAux[0]);
        System.out.println("usuario: " + nombreUsuarioAux[1]);

        //ESTADO

        Target DETALLE_ESTADO_SOLICITUD = Target.the("Estado de Servicio - Detalle").located(By.xpath("(//div[@data-test='txtEstado'])"));
        LoginStepDefinitions.pagosServiciosData.setEstadoSolicitudDetalle(String.valueOf(DETALLE_ESTADO_SOLICITUD.resolveFor(actor).getText()));

        System.out.println("detalle - estado 1: " + LoginStepDefinitions.pagosServiciosData.getEstadoSolicitudDetalle());

        if (LoginStepDefinitions.pagosServiciosData.getEstadoSolicitudDetalle().equals("En proceso")) {

            while (!LoginStepDefinitions.pagosServiciosData.getEstadoSolicitudDetalle().substring(0,9).trim().equals("Rechazada")) {

                System.out.println("detalle: " + LoginStepDefinitions.pagosServiciosData.getEstadoSolicitudDetalle().substring(0,9));

                theActorInTheSpotlight().attemptsTo(
                        WaitUntil.the(LoginPage.REGRESAR_DETALLE_ESTADO, isVisible()).forNoMoreThan(150).seconds(),
                        Click.on(LoginPage.REGRESAR_DETALLE_ESTADO));

                theActorInTheSpotlight().attemptsTo(
                        WaitUntil.the(LoginPage.REVISA_DETALLE_ESTADO, isVisible()).forNoMoreThan(150).seconds(),
                        Click.on(LoginPage.REVISA_DETALLE_ESTADO));

                Target DETALLE_ESTADO_SOLICITUD2 = Target.the("Estado de Servicio - Detalle").located(By.xpath("(//div[@data-test='txtEstado'])"));
                LoginStepDefinitions.pagosServiciosData.setEstadoSolicitudDetalle(String.valueOf(DETALLE_ESTADO_SOLICITUD2.resolveFor(actor).getText()));

            }
        }

        theActorInTheSpotlight().should(seeThat(
                EstadoPagoQuestions.EstadoServicioDetalleRechazada(), equalTo(LoginStepDefinitions.pagosServiciosData.getEstadoSolicitudDetalle().substring(0,124))));

        System.out.println("detalle - estado 3: " + LoginStepDefinitions.pagosServiciosData.getEstadoSolicitudDetalle());

        //CREACION

        theActorInTheSpotlight().should(seeThat(
                EstadoPagoQuestions.CreaciónSolititudDetalle(), containsString(nombreUsuarioAux[0].trim() + " - " + fechaHoraAux[0].trim())));

        System.out.println("detalle - creacion: " + nombreUsuarioAux[0].trim() + " - " + fechaHoraAux[0].trim());

        //AUTORIZADORES

        System.out.println("cantidad de fechas " + fechaHoraAux.length);

        if(fechaHoraAux.length==4){

            theActorInTheSpotlight().should(seeThat(
                    EstadoPagoQuestions.AutorizadorSolicitudDetalle(), containsString(nombreUsuarioAux[0].trim() + " - " + fechaHoraAux[0].trim())));

            theActorInTheSpotlight().should(seeThat(
                    EstadoPagoQuestions.AutorizadorSolicitudDetalle2(), containsString(nombreUsuarioAux[1].trim() + " - " + fechaHoraAux[2].trim())));

            System.out.println("usuario1: " + nombreUsuarioAux[0].trim() + " - " + fechaHoraAux[0].trim());
            System.out.println("usuario1: " + nombreUsuarioAux[1].trim() + " - " + fechaHoraAux[2].trim());

        } else if (fechaHoraAux.length==6) {

            theActorInTheSpotlight().should(seeThat(
                    EstadoPagoQuestions.AutorizadorSolicitudDetalle(), containsString(nombreUsuarioAux[0].trim() + " - " + fechaHoraAux[0].trim())));

            theActorInTheSpotlight().should(seeThat(
                    EstadoPagoQuestions.AutorizadorSolicitudDetalle2(), containsString(nombreUsuarioAux[1].trim() + " - " + fechaHoraAux[2].trim())));

            theActorInTheSpotlight().should(seeThat(
                    EstadoPagoQuestions.AutorizadorSolicitudDetalle3(), containsString(nombreUsuarioAux[2].trim() + " - " + fechaHoraAux[4].trim())));

            System.out.println("usuario1: " + nombreUsuarioAux[0].trim() + " - " + fechaHoraAux[0].trim());
            System.out.println("usuario1: " + nombreUsuarioAux[1].trim() + " - " + fechaHoraAux[2].trim());
            System.out.println("usuario1: " + nombreUsuarioAux[2].trim() + " - " + fechaHoraAux[4].trim());

        }

        //NUMERO DE SOLICITUD

        theActorInTheSpotlight().should(seeThat(
                EstadoPagoQuestions.NumeroSolicitudDetalle(), equalTo(LoginStepDefinitions.pagosServiciosData.getNroSolicitudProcess())));

        System.out.println("detalle - numero solicitud: " + LoginStepDefinitions.pagosServiciosData.getNroSolicitudProcess());

        //DESCRIPCION

        if(!LoginStepDefinitions.pagosServiciosData.getDescripcion().equals("")){

            System.out.println("entre por que la descripcion esta llena");
            theActorInTheSpotlight().should(seeThat(
                    EstadoPagoQuestions.DetalleDescripcion(), equalTo(LoginStepDefinitions.pagosServiciosData.getDescripcion())));
        }

        System.out.println("detalle - descripcion: " + LoginStepDefinitions.pagosServiciosData.getDescripcion());

        //DATOS CARGO

        theActorInTheSpotlight().should(seeThat(
                EstadoPagoQuestions.DatosCargoDetalle(), containsString(LoginStepDefinitions.pagosServiciosData.getDatosCargoDetalle())));

        System.out.println("detalle - datos cargo: " + LoginStepDefinitions.pagosServiciosData.getDatosCargoDetalle());

        //MONTO

        theActorInTheSpotlight().should(seeThat(
                EstadoPagoQuestions.MontoDetalle(), equalTo(LoginStepDefinitions.pagosServiciosData.getMontoProcess())));

        System.out.println("detalle - monto" + LoginStepDefinitions.pagosServiciosData.getMontoProcess());

        //PROCESADOS

        theActorInTheSpotlight().should(seeThat(
                EstadoPagoQuestions.PagoProcesadasDetalle(), equalTo("0")));

        System.out.println("detalle - procesados: "+ EstadoPagoQuestions.PagoProcesadasDetalle());

        //RECHAZADOS

        theActorInTheSpotlight().should(seeThat(
                EstadoPagoQuestions.PagoRechazadasDetalle(), equalTo(LoginStepDefinitions.pagosServiciosData.getCuotasPagar())));

        System.out.println("detalle - rechazados: "+ LoginStepDefinitions.pagosServiciosData.getCuotasPagar());

        //INFORMACION DE LAS CUOTAS-------------------------------------------------------------------------------------

        LoginStepDefinitions.pagosServiciosData.setmontoPagado("");

        System.out.println("info - monto pagado: "+ LoginStepDefinitions.pagosServiciosData.getmontoPagado());

        int i= Integer.parseInt(LoginStepDefinitions.pagosServiciosData.getCantdni())+1;

        while(!LoginStepDefinitions.pagosServiciosData.getmontosPagados().equals(LoginStepDefinitions.pagosServiciosData.getmontoPagado())) {

            i--;

            //MONTO PAGADO

            Target MONTO_PAGADO = Target.the("Monto Pagado").located(By.xpath("(//*[@class='height-16 cursor-default hover-none ibk-table-row ng-star-inserted'])[" + i + "]//ibk-table-cell[7]"));
            LoginStepDefinitions.pagosServiciosData.setmontoPagado(String.valueOf(MONTO_PAGADO.resolveFor(actor).getText()));

        }

        //MODO DE PAGO

        Target MODO_DE_PAGO = Target.the("Modo de Pago").located(By.xpath("(//*[@class='height-16 cursor-default hover-none ibk-table-row ng-star-inserted'])[" + i + "]//ibk-table-cell[6]"));
        LoginStepDefinitions.pagosServiciosData.setmodoDePago(String.valueOf(MODO_DE_PAGO.resolveFor(actor).getText()));

        assertEquals(
                LoginStepDefinitions.pagosServiciosData.getmodoDePago(), LoginStepDefinitions.pagosServiciosData.getModoPago());

        //ESTADO

        Target ESTADO = Target.the("Estado").located(By.xpath("(//*[@class='height-16 cursor-default hover-none ibk-table-row ng-star-inserted'])[" + i + "]//ibk-table-cell[8]"));
        LoginStepDefinitions.pagosServiciosData.setEstado(String.valueOf(ESTADO.resolveFor(actor).getText()));

        assertEquals(
                LoginStepDefinitions.pagosServiciosData.getEstado(), "Rechazado");

        //TOTAL SOLES O DOLARES

        if(LoginStepDefinitions.pagosServiciosData.getSimbolo().equals("S/")) {

            Target TOTAL_SOLES = Target.the("Total Soles").located(By.xpath("(//span[@data-test='lblTotalPenValue'])[" + i + "]"));
            LoginStepDefinitions.pagosServiciosData.setmontoPagado(String.valueOf(TOTAL_SOLES.resolveFor(actor).getText()));

            assertEquals(
                    LoginStepDefinitions.pagosServiciosData.getmontoPagado(), LoginStepDefinitions.pagosServiciosData.getmontosPagados());

            System.out.println("monto pagado: "+ LoginStepDefinitions.pagosServiciosData.getmontoPagado());

            System.out.println("montos pagados: "+ LoginStepDefinitions.pagosServiciosData.getmontosPagados());

        } else if (LoginStepDefinitions.pagosServiciosData.getSimbolo().equals("$")) {

            Target TOTAL_DOLARES = Target.the("Total Dolares").located(By.xpath("(//span[@data-test='lblTotalDollarValue'])[" + i + "]"));
            LoginStepDefinitions.pagosServiciosData.setmontoPagado(String.valueOf(TOTAL_DOLARES.resolveFor(actor).getText()));

            assertEquals(
                    LoginStepDefinitions.pagosServiciosData.getmontoPagado(), LoginStepDefinitions.pagosServiciosData.getmontosPagados());

            System.out.println("monto pagado: "+ LoginStepDefinitions.pagosServiciosData.getmontoPagado());

            System.out.println("montos pagados: "+ LoginStepDefinitions.pagosServiciosData.getmontosPagados());

        }

        //--------------------------------------------------------------------------------------------------------------

        //REGRESAR

        actor.attemptsTo(
                WaitUntil.the(LoginPage.REGRESAR_DETALLE_ESTADO, isVisible()).forNoMoreThan(150).seconds(),
                Click.on(LoginPage.REGRESAR_DETALLE_ESTADO));

        System.out.println("regrese");

    }
}
