/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import front.api.holerite.web.config.UrlBase;
import java.io.IOException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import front.api.holerite.web.model.*;
import javafx.scene.control.Alert;
import org.apache.http.ParseException;

/**
 *
 * @author andre
 */
public class UsuarioService {

    private static UrlBase url = new UrlBase();

    /**
     * chamada ao end-point /usuario/{id} recupera os dados de um usuario pelo
     * ID
     *
     * @param token
     * @param idUser
     * @throws java.io.IOException
     */
    public Usuario getUsuarioPorId(String token, String idUser) throws IOException {

        Usuario usuario = new Usuario();
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet request = new HttpGet(url.getURL_BASE()
                + url.getEND_POINT_GET_USUARIO()
                + idUser
        );

        request.addHeader("token", token);
        CloseableHttpResponse response = client.execute(request);

        HttpEntity entity = response.getEntity();
        if (entity != null) {

            String result = EntityUtils.toString(entity);
            usuario = converterJsonUsuario(result);

        }
        return usuario;

    }

    /**
     * metodo que faz a conversão de da resosta recebida do end-point em objeto
     * tipo Usuario
     *
     * @throws IOException
     */
    private Usuario converterJsonUsuario(String dados) {
        ObjectMapper maper = new ObjectMapper();
        Usuario user = new Usuario();
        try {

            user = maper.readValue(dados, Usuario.class);

        } catch (JsonParseException | JsonMappingException e) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Error");
            msg.setContentText("Erro ao tentar fazer conversao da resposta do end-point\n" + e.getMessage());
            msg.showAndWait();
        } catch (IOException e) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Error");
            msg.setContentText("Erro ao tentar fazer conversao da resposta do end-point\n" + e.getMessage());
            msg.showAndWait();
        }

        return user;
    }

}
