package com.everis.userinterfaces.web.bpi;


import com.everis.bpi.stepsdefinitions.web.bpi.login.LoginStepDefinitions;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * @author Nilo Carrion
 * @author Renato Paco
 */
public class LoginPage {

    public static final Target INP_CHOOSE_TIPO_ACCESO = Target.the("Tipo de acceso").located(By.xpath("(//*[@class='mat-select-arrow'])[1]"));
    public static final Target INP_CHOOSE_TIPO_DOC = Target.the("Tipo de acceso").located(By.xpath("//mat-option[@role='option']//span[text()=' Documento de identidad ']"));

    public static final Target INP_DOCUMENTO = Target.the("Usuario").located(By.xpath("//*[@data-test='txtNumDoc']"));
    public static final Target INP_PASSWORD = Target.the("Contraseña").located(By.xpath("//*[@data-test='txtPassword']"));
    public static final Target BTN_INICIAR_SESION = Target.the("Botón iniciar sesion").located(By.xpath("//*[@id='btnEnter']"));









    public static final Target POPUP_BTN_ENTENDIDO = Target.the("Entendido").located(By.xpath("//ibk-button[@class='d-none d-sm-block ibk-button--large ibk-button mt-0 mb-6']/button"));



    public static final Target BTN_NUEVO_PAGO = Target.the("Nuevo Pago de Servicio").located(By.xpath("//button[@data-test='btnPayAgain']"));







    public static final Target SALDO_TIPO_CUENTA = Target.the("Tipo de Cuenta").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][1]//div)[1]"));
    public static final Target SALDO_TIPO_MONEDA = Target.the("Tipo de Moneda").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][1]//div)[2]"));
    public static final Target SALDO_NUMERO_CUENTA = Target.the("Numero de Cuenta").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][1]//div)[3]"));
    public static final Target SALDO_DISPONIBLE = Target.the("Saldo Disponible").located(By.xpath("//*[@data-test='lblAccountNumber']"));

    public static final Target SALDO_EMPRESA_TOTAL = Target.the("Saldo Total").located(By.xpath("(//span[@class='label-balance__amount ng-star-inserted'])[1]"));
    public static final Target SALDO_CONTABLE = Target.the("Saldo Contable").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][1]//span)[2]"));

    public static final Target SALDO_DISPONIBLE_LINEA = Target.the("Saldo Disponible").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][1]//span)[4]"));

    ///sacar xpath
    public static final Target SALDO_FINAL_TOTAL = Target.the("Saldo Final Disponible").located(By.xpath("(//span[@class='label-balance__amount ng-star-inserted'])[1]"));

    //ingresar a saldo
    public static final Target SALDO_TIPO_DE_CUENTA = Target.the("Tipo de cuenta").located(By.xpath("//*[@data-test='lblAccountType']"));

    public static final Target SALDO_SALDO_DISPONIBLE = Target.the("Saldo Disponible").located(By.xpath("//*[@data-test='lblAvailableBalanceAmount']"));


    // VALIDAR MOVIMIENTOS





    public static final Target MOVIMIENTOS_FECHA_OPERACION = Target.the("Fecha Operacion").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][1]//div)[1]"));
    public static final Target MOVIMIENTOS_FECHA_PROCESO = Target.the("Fecha Proceso").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][1]//div)[2]"));
    public static final Target MOVIMIENTOS_NUMERO_OPERACION = Target.the("Numero de Operacion").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][1]//div)[3]/text()"));
    public static final Target MOVIMIENTOS_MOVIMIENTOS = Target.the("Movimientos").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][1]//div)[4]"));
    public static final Target MOVIMIENTOS_DESCRIPCION = Target.the("Descripcion").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][1]//div)[5]"));
    public static final Target MOVIMIENTOS_CANAL = Target.the("Canal").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][1]//div)[6]"));
    public static final Target MOVIMIENTOS_IMPORTE = Target.the("Importe").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][1]//span)[2]"));
    public static final Target MOVIMIENTOS_SALDO_CONTABLE = Target.the("Saldo Contable").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][1]//span)[4]"));

    //CUANDO REALIZA EL PAGO Y SE ESTA PROCESANDO


    //             DETALLE DEL ESTADO - INFORMACIÓN DE SOLICITUD
    public static final Target DETALLE_ESTADO_SOLICITUD = Target.the("Estado de Servicio - Detalle").located(By.xpath("(//div[@data-test='txtEstado'])"));
    public static final Target DETALLE_CREACION_SOLICITUD = Target.the("Creación de Pago - Detalle").located(By.xpath("(//div[@data-test='txtCreacion'])"));
    public static final Target DETALLE_AUTORIZADOR_SOLICITUD = Target.the("Autorizador de A sola firma - Detalle").located(By.xpath("(//ul[@data-test='txtAutoriza']/li)"));
    public static final Target DETALLE_AUTORIZADOR_SOLICITUD2 = Target.the("Autorizador de A sola firma - Detalle").located(By.xpath("(//ul[@data-test='txtAutoriza']/li)[2]"));
    public static final Target DETALLE_AUTORIZADOR_SOLICITUD3 = Target.the("Autorizador de A sola firma - Detalle").located(By.xpath("(//ul[@data-test='txtAutoriza']/li)[3]"));
    public static final Target DETALLE_NUMERO_SOLICITUD = Target.the("Numero de Solicitud - Detalle").located(By.xpath("//ibk-card-description[@data-test='lblApplicationNumValue']"));
    //             DETALLE DEL ESTADO - INFORMACIÓN DE CARGO
    public static final Target DETALLE_DATOS_CARGO = Target.the("Datos del Cargo - Detalle").located(By.xpath("(//*[@testid='lblChargeDataValue'])"));
    //             DETALLE DEL ESTADO - INFORMACIÓN DE PAGO
    public static final Target DETALLE_MONTO_PAGO = Target.the("Monto de Pago - Detalle").located(By.xpath("(//*[@data-test='lblAmountValue'])"));
    public static final Target DETALLE_PROCESADOS_PAGO = Target.the("Procesados Pago - Detalle").located(By.xpath("(//*[@testid='lblProcessedFeesValue'])"));
    public static final Target DETALLE_RECHAZADOS_PAGO = Target.the("Rechazados Pago - Detalle").located(By.xpath("(//*[@testid='lblRejectedFeesValue'])"));
    //             DETALLE DEL ESTADO - INFORMACIÓN DE LAS CUOTAS
    public static final Target DETALLE_MODO_PAGO_CUOTAS = Target.the("Modo de Pago- Detalle").located(By.xpath("(//ibk-table-cell[@class='text-center ibk-table-cell']/span)[2]"));
    public static final Target DETALLE_MONTO_PAGADO_CUOTAS = Target.the("Monto Pagado- Detalle").located(By.xpath("(//*[@class='text-right ibk-table-cell']/span)"));

    public static final Target DETALLE_MONTO_PAGADO_CUOTAS_P = Target.the("Monto Pagado- Detalle").located(By.xpath("(//*[@class='text-right ibk-table-cell']/span)[" + LoginStepDefinitions.pagosServiciosData.getCuotasPagar() + "]"));//CRISTIAN

    public static final Target DETALLE_ESTADO_CUOTAS = Target.the("Estado de Cuotas- Detalle").located(By.xpath("(//*[@class='fsize-14'])"));
    public static final Target DETALLE_TOTAL_SOLES_CUOTAS = Target.the("Total Soles Cuotas - Detalle").located(By.xpath("(//span[@data-test='lblTotalPenValue'])"));

    public static final Target DETALLE_TOTAL_DOLARES_CUOTAS = Target.the("Total Dolares Cuotas - Detalle").located(By.xpath("(//span[@data-test='lblTotalDollarValue'])"));

    public static final Target DETALLE_TOTAL_DOLARES_CUOTAS_P = Target.the("Total Dolares Cuotas - Detalle").located(By.xpath("(//span[@data-test='lblTotalDollarValue'])[" + LoginStepDefinitions.pagosServiciosData.getCuotasPagar() + "]"));//CRISTIAN
    public static final Target DETALLE_DESCRIPCION = Target.the("Descripcion").located(By.xpath("//ibk-card-description[@data-test='lblDescriptionValue']"));

    public static final Target CUOTAS_PAGAR = Target.the("Cuotas Seleccionadas por Pagar").located(By.xpath("//span[@data-test='lblSelectedFeesValue']"));
    public static final Target BUSCAR_CERRAR_SESION = Target.the("Buscar Cerrar Sesion").located(By.xpath("//*[@data-test='icoMenu']"));
    public static final Target BTN_CERRAR_SESION = Target.the("Boton Cerrar Sesion").located(By.xpath("(//a[contains(text(), 'Cerrar sesión')])[2]"));
    public static final Target BTN_CONT_CERRAR_SESION = Target.the("Boton Continuar Cerrar Sesion").located(By.xpath("//*[@data-test='btnContinuarAlert']"));
    public static final Target MENU_AUTORIZACIONES = Target.the("Menú Autorizaciones").located(By.xpath("//span[contains(text(), 'Autorizaciones')]"));
    public static final Target INP_TIPO_SOLICITUD = Target.the("Eleccion de cuenta").located(By.xpath("(//div[@class='mat-select-arrow'])[2]"));
    public static final Target BTN_PENDIENTE_FIRMA = Target.the("Menú Autorizaciones").located(By.xpath("//div[contains(text(), 'Pendientes de firma')]"));
    public static final Target BTN_ENVIADAS = Target.the("Menú Autorizaciones").located(By.xpath("//div[contains(text(), 'Enviadas por mí')]"));
    public static final Target BTN_AUTORIZAR_P = Target.the("Boton Autorizar Pago").located(By.xpath("//*[@data-test='btnAutorize']"));

    public static final Target BTN_BLOQUEAR_P = Target.the("Boton Autorizar Pago").located(By.xpath("//*[@data-test='btnBlock']"));
    public static final Target BTN_FINALIZAR_PENDIENTE = Target.the("Token").located(By.xpath("//*[@id='btnFinish']"));

    public static final Target SALDO_DOLARES_FINAL_TOTAL = Target.the("Saldo Final Dolares Disponible").located(By.xpath("//*[@data-test='lblAccountUsdAmount']"));

    public static final Target IMPORTE = Target.the("IMPORTE").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'][{0}]//span)[2]"));

    public static final Target LIMITE_SERVICIO = Target.the("Limite de servicio").located(By.xpath("//*[@data-test='lblTitleAlert']"));
    public static final Target SERVICIO_AGREGADO = Target.the("Servicio ya agregado").located(By.xpath("//*[@data-test='lblTitleAlert']"));

    public static final Target PARAMETROS_SEGURIDAD = Target.the("No cumple con los parámetros de seguridad").located(By.xpath("//*[@data-test='txtErrorPassword']"));

    public static final Target NO_CUOTAS = Target.the("No se encontraron cuotas").located(By.xpath("//*[@data-test='lblTitleAlert']"));

    public static final Target MONTO_INVALIDO = Target.the("Debes ingresar un monto válido").located(By.xpath("//*[@data-test='lblErrorMessage']"));

    public static final Target MONTO_INVALIDO_SUPERADO = Target.the("Monto Superado").located(By.xpath("//*[@data-test='lblTitleAlert']"));
    public static final Target NINGUNA_CUOTA = Target.the("Debes seleccionar al menos una cuota para poder realizar el pago correspondiente").located(By.xpath("//*[@class='c-error d-none d-md-block ng-star-inserted']"));

    public static final Target VER_DETALLE = Target.the("Ver detalle").located(By.xpath("//*[@class='isize-24 ibk-icon icon-search ng-star-inserted']"));

    public static final Target MONTO_SUPERA = Target.the("Monto ingresado supera límites").located(By.xpath("//*[@data-test='lblTitleAlert']"));

    //RECAUDACIONES
    public static final Target RECAUDACIONES = Target.the("RecaudacionesQuestions").located(By.xpath("//a[@data-test='lnkrecaudaciones']"));
    public static final Target CHOOSE_SERVICE = Target.the("Seleccionar Servicio").located(By.xpath("(//div[@class='mat-select-arrow'])[2]"));
    public static final Target CHOOSE_COD_DEUDOR = Target.the("Seleccionar Todos o  por Codigo Deudor").located(By.xpath("(//div[@class='mat-select-arrow'])[3]"));
    public static final Target INP_BUSCAR_COD_DEUDOR = Target.the("Buscar por Codigo Deudor").located(By.xpath("//input[@data-test='txtSearchDebtor']"));

    public static final Target BTN_SEARCH = Target.the("Boton Buscar").located(By.xpath("//button[@data-test='btnSearch']"));
    public static final Target VER_PAGO = Target.the("Ver pagos").located(By.xpath("//div[@data-test='lnkSeePay']//span[text()='Ver pagos']"));
    public static final Target EMPRESA_RECAUDACION = Target.the("Saldo Contable").located(By.xpath("//*[@data-test='lblCompanyValue']"));
    public static final Target SERVICIO_RECAUDACION = Target.the("Servicio Recaudacion").located(By.xpath("//*[@data-test='lblServiceValue']"));

    public static final Target CODIGO_RECAUDACION = Target.the("Codigo Recaudacion").located(By.xpath("//*[@data-test='lblCodeValue']"));
    public static final Target FECHA_RECAUDACION = Target.the("Fecha Recaudación").located(By.xpath("//*[@data-test='lblDateHourPayValue']"));
    public static final Target MONTO_RECAUDACION = Target.the("Monto Recaudación").located(By.xpath("//*[@data-test='lblAmountPaidValue']"));
    public static final Target NUMERO_OPERACION_RECAUDACIONES = Target.the("Numero de la Operación Recaudación").located(By.xpath("//*[@data-test='lblNumberOperationValue']"));

}



