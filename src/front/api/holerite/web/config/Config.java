/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.config;

import front.api.holerite.web.controller.FXMLAppController;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author andre
 */
public class Config {
    
    /**
     * le arquivo config.properties e retorna
     * propriedades 
     * @return 
     */
    public Properties lerPropriedades() {
        Properties prop = new Properties();
        try (InputStream input = FXMLAppController.class.getResourceAsStream("/resources/config.properties")) {

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            //System.out.println(prop.getProperty("URL.base"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }
    
    
}
