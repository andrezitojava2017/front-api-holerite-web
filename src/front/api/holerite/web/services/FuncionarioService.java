/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import front.api.holerite.web.config.TokenDefault;
import front.api.holerite.web.config.UrlBase;
import front.api.holerite.web.model.Funcionario;
import front.api.holerite.web.model.Usuario;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Usuario
 */
public class FuncionarioService {

    private static UrlBase url = new UrlBase();

    public List<Funcionario> getListFuncionarios(TokenDefault token, String cnpj) throws IOException {

        String endPoint = url.getURL_BASE() + url.getEND_POINT_GET_LIST_FUNCIONARIOS() + cnpj;
        List<Funcionario> listFuncionarios = new ArrayList<>();

        String result = FactoryConnection.createGetConnectionService(token, endPoint);
        listFuncionarios = convertJsonToListFuncionario(result);

        return listFuncionarios;
    }

    public Funcionario postNewFuncionario(Funcionario funcionario, TokenDefault token) throws JsonProcessingException, IOException {

        String endPoint = url.getURL_BASE() + url.getEND_POINT_POST_FUNCIONARIO();
        String funcJson = convertFuncionarioToJson(funcionario);
        String result = FactoryConnection.createPostConnectionService(token, endPoint, funcJson);

        Funcionario func = convertJsonToObjectFuncionario(result);
        return func;
    }

    /**
     * Metodo que faz upload do arquivo com dados dos funcionarios
     *
     * @param token
     * @param cnpj
     * @param anexo
     */
    public void uploadDataAnexoFuncionarios(TokenDefault token, String cnpj, File anexo) {
        String endPoint = url.getURL_BASE() + url.getEND_POINT_POST_UPLOAD_ARQ_FUNCIONARIOS();
        String result = FactoryConnection.sendUploadFileAnexo(token, endPoint, anexo, cnpj);

        System.out.println("list funcionarios: \n" + result);
    }

    /**
     * Converte objecto Funcionario em JSON
     *
     * @param funcionario
     * @return String
     * @throws JsonProcessingException
     */
    private String convertFuncionarioToJson(Funcionario funcionario) throws JsonProcessingException {

        DateFormat frm = new SimpleDateFormat("yyyy-MM-dd");

        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(frm);
        String jsonFuncionario = mapper.writeValueAsString(funcionario);

        return jsonFuncionario;

    }

    /**
     * Converte JSON em objeto Funcionario
     *
     * @param json
     * @return Funcionario
     * @throws IOException
     */
    private Funcionario convertJsonToObjectFuncionario(String json) throws IOException {

        DateFormat frm = new SimpleDateFormat("yyyy-MM-dd");

        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(frm);
        Funcionario funcionario = mapper.readValue(json, Funcionario.class);

        return funcionario;

    }

    private List<Funcionario> convertJsonToListFuncionario(String json) throws IOException {

        List<Funcionario> funcionario = new ArrayList();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);
        node.forEach(ls -> {
            try {
                Funcionario func;

                func = convertJsonToObjectFuncionario(ls.toString());

                funcionario.add(func);
            } catch (IOException ex) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Error");
                msg.setContentText("Erro ao tentar fazer conversao da resposta do end-point\n" + ex.getMessage());
                msg.showAndWait();
            }
        });

        return funcionario;
    }
}
