package com.everis.utils.mainframe;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class Utils {

    public static String getTemplate(String templatePath) {
        try{
            return IOUtils.toString(new ClassPathResource(templatePath).getInputStream(), StandardCharsets.UTF_8);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

/*    public static Performable leerTxt(String tipoCuenta, String numeroCuenta, String montoCobrar, EnvironmentVariables environmentVariables) throws IOException {

        File file = new File("File Name");
        if (tipoCuenta.equals("Cuenta Corriente")) {
            file = new File(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("ruta.st"));
        } else if (tipoCuenta.equals("Cuenta de Ahorros")) {
            file = new File(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("ruta.im"));
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
            String linea = "";
            while ((linea = br.readLine()) != null) {
                if (linea.contains(numeroCuenta) && linea.contains(montoCobrar)) {
                System.out.println(linea);
                }
            }


        return null;
    }*/

/*    @Override
    public <T extends Actor> void performAs(T actor) {

    }*/
}
