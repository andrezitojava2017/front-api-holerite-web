/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import front.api.holerite.web.config.TokenDefault;
import front.api.holerite.web.config.UrlBase;
import front.api.holerite.web.model.Funcionario;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Usuario
 */
public class FuncionarioService {

    private static UrlBase url = new UrlBase();

    public Funcionario postNewFuncionario(Funcionario funcionario, TokenDefault token) throws JsonProcessingException, IOException {

        String endPoint = url.getURL_BASE() + url.getEND_POINT_POST_FUNCIONARIO();
        String funcJson = convertFuncionarioToJson(funcionario);
        String result = FactoryConnection.createPostConnectionService(token, endPoint, funcJson);

        Funcionario func = convertJsonToObjectFuncionario(result);
        return func;
    }

    private String convertFuncionarioToJson(Funcionario funcionario) throws JsonProcessingException {

        DateFormat frm = new SimpleDateFormat("yyyy-MM-dd");

        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(frm);
        String jsonFuncionario = mapper.writeValueAsString(funcionario);

        return jsonFuncionario;

    }

    private Funcionario convertJsonToObjectFuncionario(String json) throws IOException {

        DateFormat frm = new SimpleDateFormat("yyyy-MM-dd");

        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(frm);
        Funcionario funcionario = mapper.readValue(json, Funcionario.class);

        return funcionario;

    }
}
