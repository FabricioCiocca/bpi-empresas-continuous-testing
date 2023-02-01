package com.everis.userinterfaces.web.nexbi;


import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class QueryPage {
    public static Target CHOOSE_SEARCH = Target.the("Elige Tipo de Busqueda").locatedBy("//mat-option//span[contains(text(),'Código único')]");
    public static Target CHOOSE_SEARCH_PUNTO = Target.the("Elige Tipo de Busqueda").locatedBy("//mat-option//span[contains(text(),'Punto de servicio')]");
    public static final Target INP_SEARCH_COD = Target.the("Ingresar el Codigo de Busqueda").located(By.xpath("(//*[@data-test='txtSearch'])[2]"));
    public static final Target ICON_SEARCH = Target.the("Icono de Buscar").located(By.xpath("(//*[@class='ibk-icon icon-search'])[2]"));
    public static final Target MENU_INFOGENERAL = Target.the("Menu Informacion General").located(By.xpath("//*[@data-test='lnkGeneralInformation"));
    public static final Target MENU_USUARIOS = Target.the("Menu Usuarios").located(By.xpath("//*[@data-test='lnkUsers']"));
    public static final Target TIPO_CLIENTE = Target.the("Tipo de Cliente").located(By.xpath("//*[@data-test='txtTypeClient']"));
    public static final Target CODIGO_UNICO = Target.the("Codigo Unico").located(By.xpath("(//*[@class='search-table--row my-1 ng-star-inserted'])[3]"));
    public static final Target NIVEL_SERVICIO = Target.the("Nivel de Servicio").located(By.xpath("(//span[@class='ng-star-inserted'])[3]"));
    public static final Target CUENTA_CARGO = Target.the("Cuenta a Cargo").located(By.xpath("//*[@data-test='txtAccount']"));
    public static final Target MONEDA_CUENTA = Target.the("Moneda de Cuenta").located(By.xpath("//*[@data-test='lblCurrentValue']"));
    public static final Target BTN_ACTIVO = Target.the("Boton Activo").located(By.xpath("(//*[@class='mat-button-toggle-label-content'])[2]"));
    public static final Target SELEC_ALL = Target.the("Seleccionar Todo").located(By.xpath("//*[@data-test='chkSelectAll']"));
    public static final Target CANT_USUARIOS = Target.the("Seleccionar Todo").located(By.xpath("//*[@class='c-black px-2']"));
    public static final Target SIN_USUARIO = Target.the("No tiene Usuario").located(By.xpath("//h2[contains(text(),'Aún no se ha creado ningún usuario para este punto')]"));
    public static final Target BTN_SEARCH = Target.the("Boton Buscar").locatedBy("(//ibk-icon[@icon='search'])[{0}]");
    public static final Target BTN_SEARCH1 = Target.the("Boton Buscar").located(By.xpath("(//ibk-icon[@icon='search'])[2]"));
    public static final Target BTN_REGRESAR = Target.the("Boton Regresar").located(By.xpath("//span[@class='hover-text-underline']/span[contains(text(),'Regresar')]"));
    public static final Target SECCION_TIE = Target.the("Seccion Tie").located(By.xpath("//*[@data-test='lblTIE']"));
    public static final Target NUMERO_TIE = Target.the("Numero Tie").located(By.xpath("//*[@data-test='lblCardNumberValue']"));

    //lo nuevo
    public static final Target SELECT_ARROW = Target.the("Selecionar Flecha").located(By.xpath("(//*[@data-test='cmbTypeSearch'])[2]"));
    public static final Target SELECT_CHOOSE_TYPE_SEARCH = Target.the("Selecionar Tipo de Busqueda").located(By.xpath("//mat-option//span[contains(text(),'Código único')]"));
    public static final Target TABLE_PUNTOSERVICIO_RECORRIDO = Target.the("Tabla de resultado punto de servicios").locatedBy("(//*[@class='ibk-table-row ng-star-inserted'])[{0}]");
    public static final Target TABLE_PUNTOSERVICIO_SIN_USUARIOS = Target.the("Tabla de resultado punto de servicios sin Usuarios").located(By.xpath("//ibk-table-body/ibk-table-row[1]"));
    public static final Target TABLE_PUNTOSERVICIO = Target.the("Tabla de resultado punto de servicios").locatedBy("(//*[@class='ibk-table-row ng-star-inserted'])");
    public static final Target SELECT_ICON_ISV = Target.the("Selecionar Icono ISV").located(By.xpath("//*[@data-test='btnisv']"));
    public static final Target NOMBRE_PUNTO_SERVICIO = Target.the("Nombre del Punto de Servicio").locatedBy("(//ibk-table-row[{0}]//ibk-table-cell//div[@class='search-table--row my-1 ng-star-inserted'])[1]");
    public static final Target PUNTO_SERVICIO = Target.the("Numero del Punto de Servicio").locatedBy("//ibk-table-row[{0}]//ibk-table-cell//div[@class='search-table--first search-table--row my-1 ng-star-inserted']");
    public static final Target CHECKBOX_TIPO_ACCESO = Target.the("Checkbox del Tipo de Acceso").located(By.xpath("(//*[@aria-checked='true'])[1]"));
    public static final Target INPUT_NOMBRE_USUARIO = Target.the("Ingresar Nombre de Usuario").located(By.xpath("//*[@data-placeholder='Nombre de usuario']"));
    public static final Target ULTIMO_PUNTO_SERVICIO = Target.the("Ultimo punto de servicios").located(By.xpath("(//*[@class='ibk-table-row ng-star-inserted'])[12]"));
    public static final Target NIVEL_SERVICIO_USUARIO = Target.the("Nivel de Servicio del Usuario").located(By.xpath("//*[@class='w-15']"));

}
