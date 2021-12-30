/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.services;

import front.api.holerite.web.config.TokenDefault;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Jederson Andre
 */
public class FactoryConnection {

    /**
     * Fabrica de conexao com webService
     *
     * @param token
     * @param urlService
     * @return String result - dados retornado da API
     * @throws IOException
     */
    public static String createGetConnectionService(TokenDefault token, String urlService) throws IOException {
        String result = null;

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(urlService);

        request.addHeader("token", token.getTOKEN());
        CloseableHttpResponse response;

        try {

            response = client.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (IOException ex) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Erro de conexao");
            msg.setContentText("Ocorreu um erro ao tentar fazer conexao com endpoint.\n" + ex.getMessage());
            msg.showAndWait();
        } finally {
            client.close();
        }

        return result;
    }

    /**
     * Faz a conexao com servico que retorna uma lista de funcionarios
     * @param token
     * @param urlService
     * @param cnpj
     * @return
     * @throws IOException 
     */
    public String createGetConnectionServiceFuncionarios(TokenDefault token, String urlService, String cnpj) throws IOException{
        
        String result = null;

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(urlService);

        request.addHeader("token", token.getTOKEN());
        CloseableHttpResponse response;

        try {

            response = client.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (IOException ex) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Erro de conexao");
            msg.setContentText("Ocorreu um erro ao tentar fazer conexao com endpoint.\n" + ex.getMessage());
            msg.showAndWait();
        } finally {
            client.close();
        }

        return result;
        
    }
    /**
     * Metodo POST, insercao de novos dados na API
     *
     * @param token
     * @param urlService
     * @param dataJson
     * @return String - estrutura JSON de retorno
     * @throws UnsupportedEncodingException
     */
    public static String createPostConnectionService(TokenDefault token, String urlService, String dataJson) throws IOException {

        String result = null;
        CloseableHttpClient client = HttpClients.createDefault();
        try {

            // chamada ao end-point
            HttpPost post = new HttpPost(urlService);
            post.addHeader("token", token.getTOKEN());
            post.addHeader("content-type", "application/json");

            post.setEntity(new StringEntity(dataJson));

            CloseableHttpResponse response = client.execute(post);

            result = EntityUtils.toString(response.getEntity());

        } catch (UnsupportedEncodingException ex) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Erro de conexao");
            msg.setContentText("Ocorreu um erro ao tentar fazer conexao com endpoint.\n" + ex.getMessage());
            msg.showAndWait();
        } finally {
            client.close();
        }
        return result;
    }

    public static String sendUploadFileAnexo(TokenDefault token, String urlService, File anexo, String cnpj) {

        String result = null;

        FileBody file = new FileBody(anexo, ContentType.DEFAULT_TEXT);
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(urlService);
            post.setHeader("token", token.getTOKEN());
            post.setHeader("cnpj", cnpj);
            
            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    .addPart("anexo", file)
                    .build();
            post.setEntity(reqEntity);

            try (CloseableHttpResponse response = client.execute(post)) {
                HttpEntity entity = response.getEntity();

                System.out.println(entity);
                System.out.println(entity.toString());

            }catch(ClientProtocolException ex){
                System.out.println("erro response\n" + ex);
            }

        } catch (IOException ex) {
            System.out.println("erro>>>>\n" + ex);
        }

        return result;
    }

}
