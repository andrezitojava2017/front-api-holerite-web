/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import front.api.holerite.web.config.UrlBase;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import front.api.holerite.web.model.*;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javax.xml.ws.Endpoint;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

/**
 *
 * @author andre
 */
public class UsuarioService {

    private static UrlBase url = new UrlBase();

    public List<Usuario> getListUsers(String token) throws IOException {

        List<Usuario> userData = new ArrayList<>();

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(url.getURL_BASE()
                + url.getEND_POINT_GET_LISTA_USUARIOS()
        );
        request.addHeader("token", token);
        CloseableHttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            String result = EntityUtils.toString(entity);

        }

        return userData;
    }

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
            usuario = convertJsonToUsuario(result);

        }
        return usuario;

    }

    public Usuario postNewUsuario(String token, Usuario dataUser) throws JsonProcessingException, UnsupportedEncodingException {

        // definição do end point que ira chamar
        String point = url.getURL_BASE() + url.getEND_POINT_POST_USUARIO();
        // conversão de objeto Usuario para JSON
        String jsonDataUser = convertObjectUsuarioToJson(dataUser);
        // instanciamento objetoo para retorno
        Usuario resultUsuario = new Usuario();

        // chamada ao end-point
        HttpPost post = new HttpPost(point);
        post.addHeader("token", token);
        post.addHeader("content-type", "application/json");

        post.setEntity(new StringEntity(jsonDataUser));
        CloseableHttpClient client = HttpClients.createDefault();
        try {

            CloseableHttpResponse response = client.execute(post);
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(">>>> " + result);
            // converte JSON para objeto USUARIO
            // temos todos os dados inclusive tokem gerado e id do usuario
            // resultUsuario = convertJsonToUsuario(result);

        } catch (IOException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultUsuario;
    }

    /**
     * metodo que faz a conversão da resosta recebida do end-point em objeto
     * tipo Usuario
     *
     * @throws IOException
     */
    private Usuario convertJsonToUsuario(String dados) {
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

    /**
     * metodo que ira converter um objeto Usuario para estrutura JSON
     *
     * @param user
     * @return String jsonUser
     * @throws JsonProcessingException
     */
    private String convertObjectUsuarioToJson(Usuario user) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String jsonUser = mapper.writeValueAsString(user);

        return jsonUser;

    }
}
