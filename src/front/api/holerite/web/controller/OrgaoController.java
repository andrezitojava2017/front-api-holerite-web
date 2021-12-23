/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import front.api.holerite.web.config.TokenDefault;
import front.api.holerite.web.model.Orgao;
import front.api.holerite.web.services.EmpresaService;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Usuario
 */
public class OrgaoController implements Initializable {

    @FXML
    private TextField cpOrgao;
    @FXML
    private TextField cpCidade;
    @FXML
    private TextField cpUf;
    @FXML
    private TextField cpCnpj;

    private Orgao empresa;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnLimpar;
    @FXML
    private Button btnSair;
    
    /**
     * Atribui valores aos campos
     * e ainda, ao atributo empresa desta classe
     * @param empresa 
     */
    public void loadDataEmpresa(Orgao empresa){
        this.empresa = empresa;
        cpOrgao.setText(empresa.getNomeOrgao());
        cpCnpj.setText(empresa.getCnpj());
        cpCidade.setText(empresa.getCidade());
        cpUf.setText(empresa.getUf());
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void saveNewOrgao(ActionEvent event) {

        try {

            Orgao orgao = new Orgao();
            orgao.setNomeOrgao(cpOrgao.getText());
            orgao.setCnpj(cpCnpj.getText());
            orgao.setCidade(cpCidade.getText());
            orgao.setUf(cpUf.getText());

            EmpresaService service = new EmpresaService();
            service.postNewEmpresa(orgao, TokenDefault.getTOKEN());

        } catch (JsonProcessingException | UnsupportedEncodingException ex) {
            Logger.getLogger(OrgaoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
