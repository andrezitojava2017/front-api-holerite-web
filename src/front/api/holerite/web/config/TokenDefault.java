/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.config;

import java.util.Properties;

/**
 *
 * @author andre
 */
public class TokenDefault {
    
    static String TOKEN;

    public TokenDefault() {
        Config cnfg = new Config();
        Properties props = cnfg.lerPropriedades();
        this.TOKEN = props.getProperty("admin.token");
    }

    public static String getTOKEN() {
        return TOKEN;
    }
    
    
    
    
    
}
