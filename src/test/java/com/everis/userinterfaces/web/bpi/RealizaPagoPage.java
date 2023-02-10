package com.everis.userinterfaces.web.bpi;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class RealizaPagoPage {

    public static final Target MENU_PAGOS_Y_TRANSFERENCIAS = Target.the("Menú Pagos y transferencias").located(By.xpath("//span[contains(text(), 'Pagos y Transferencias')]"));

    public static final Target MENU_DE_SERVICIOS = Target.the("De servicios").located(By.xpath("(//li[@class='sidenav-submenu__item ng-star-inserted']/a)[6]"));

    public static final Target INP_CHOOSE_ACCOUNT = Target.the("Eleccion de cuenta").located(By.xpath("(//div[@class='mat-select-arrow'])[2]"));

    public static final Target INP_TYPE_COMPANY = Target.the("Empresa").located(By.xpath("//input[@data-test='txtServiceCompany']"));

    public static final Target INP_CHOOSE_COMPANY = Target.the("Eleccion de empresa").located(By.xpath("(//span[@class='mat-option-text'])[1]"));

    public static final Target CMB_CHOOSE_SERVICE = Target.the("Seleccionar Servicio").located(By.xpath("(//div[@class='mat-select-arrow'])[3]"));

    public static final Target INP_CODDEUDOR = Target.the("Codigo deudor").located(By.xpath("//input[@data-test='txtDebtorCode']"));

    public static final Target BTN_AGREGAR = Target.the("Agregar").located(By.id("btnAdd"));// XPATH://*[@id='btnAdd']

}
