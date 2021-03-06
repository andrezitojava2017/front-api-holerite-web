/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import front.api.holerite.web.config.TokenDefault;
import front.api.holerite.web.config.UrlBase;
import front.api.holerite.web.model.Orgao;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Jederson Andre
 */
public class EmpresaService {

    private static UrlBase url = new UrlBase();

    /**
     * Recupera lista de empresas cadastradas
     *
     * @param token
     * @return List<Orgao> - lista de empresas
     * @throws IOException
     */
    public List<Orgao> getListEmpresa(TokenDefault token) throws IOException {

        List<Orgao> lstOrgao = new ArrayList<>();
        String endPoint = url.getURL_BASE() + url.getEND_POINT_GET_LISTA_ORGAOS();

        String result = FactoryConnection.createGetConnectionService(token, endPoint);
        lstOrgao = convertJsonToListOrgao(result);
        return lstOrgao;
    }

    public void postNewEmpresa(Orgao empresa, TokenDefault token) throws JsonProcessingException, UnsupportedEncodingException, IOException {

        String edpoint = url.getURL_BASE() + url.getEND_POINT_POST_ORGAO();
        String jsonOrgao = convertObjectOrgaoToJson(empresa);

        String res = FactoryConnection.createPostConnectionService(token, edpoint, jsonOrgao);
        //System.out.println(">>>>> " + res);

    }

    public void putEmpresa(Orgao empresa, TokenDefault token) throws JsonProcessingException, IOException{
        
        String endPoint = url.getURL_BASE() + url.getEND_POINT_PUT_ORGAO() + empresa.getId();
        String jsonOrgao = convertObjectOrgaoToJson(empresa);
        
        String result = FactoryConnection.createPutConnectionService(token, endPoint,jsonOrgao);
        
    }
    /**
     * Metodo de conversao JSON para objeto ORGAO
     *
     * @param dados
     * @return Orgao
     */
    private Orgao convertJsonToOrgao(String dados) {
        ObjectMapper maper = new ObjectMapper();
        Orgao orgao = new Orgao();
        try {

            orgao = maper.readValue(dados, Orgao.class);

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

        return orgao;
    }

    /**
     * Metodo que converte dados de um json em lista de orgaos
     *
     * @param jsonList
     * @return List<Orgao>
     * @throws IOException
     */
    private List<Orgao> convertJsonToListOrgao(String jsonList) throws IOException {

        List<Orgao> listaOrgaos = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonList);

        node.forEach(ls -> {
            Orgao org = convertJsonToOrgao(ls.toString());
            listaOrgaos.add(org);
        });

        return listaOrgaos;
    }

    /**
     * metodo de conversao objeto ORGAO para estrutura JSON
     *
     * @param empresa
     * @return String jsonUser
     * @throws JsonProcessingException
     */
    private String convertObjectOrgaoToJson(Orgao empresa) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String jsonEmpresa = mapper.writeValueAsString(empresa);

        return jsonEmpresa;

    }
}
