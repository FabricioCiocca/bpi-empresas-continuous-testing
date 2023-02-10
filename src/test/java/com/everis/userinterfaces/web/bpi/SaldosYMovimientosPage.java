package com.everis.userinterfaces.web.bpi;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class SaldosYMovimientosPage {

    public static final Target MENU_CONSULTAS = Target.the("Consultas").located(By.xpath("//span[contains(text(), 'Consultas')]"));

    public static final Target MENU_SALDOS = Target.the("Saldos").located(By.xpath("(//li[@class='sidenav-submenu__item ng-star-inserted']/a)[1]"));
    public static final Target INP_BUSCAR_CUENTA = Target.the("Ingresar Cuenta").located(By.xpath("//input[@data-test='txtSearchAccount']"));

    public static final Target BTN_BUSCAR_CUENTA = Target.the("Buscar Cuenta").located(By.xpath("//*[@data-test='iconSearch']"));

    public static final Target INFO_CUENTA_FILA = Target.the("Informacion Fila Cuenta").located(By.xpath("//*[@class='ibk-table-row ng-star-inserted'][1]"));

    public static final Target MENU_MOVIMIENTOS = Target.the("Movimientos").located(By.xpath("(//li[@class='sidenav-submenu__item ng-star-inserted']/a)[2]"));

    public static final Target CMB_ELEGIR_CUENTA = Target.the("Elegir Cuenta").located(By.xpath("(//div[@class='mat-select-arrow'])[2]"));

    public static final Target BTN_BUSCAR_MOVIMIENTOS = Target.the("Buscar Movimientos").located(By.xpath("//*[@id='btnSearch']"));

}
