/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import front.api.holerite.web.config.TokenDefault;
import front.api.holerite.web.model.Orgao;
import front.api.holerite.web.model.Usuario;
import front.api.holerite.web.services.UsuarioService;
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

/**
 * FXML Controller class
 *
 * @author andre
 */
public class FXMLUsuarioController implements Initializable {

    String dados;

    @FXML
    private TextField cpNomeUsuario;
    @FXML
    private TextField cpToken;
    @FXML
    private TextField cpCpf;
    @FXML
    private TextField cpContato;
    @FXML
    private TextField cpOrgao;
    @FXML
    private TextField cpCidade;
    @FXML
    private TextField cpUf;
    @FXML
    private TextField cpCnpj;
    @FXML
    private Button btnSalvarUsuario;
    @FXML
    private Button btnSair;
    @FXML
    private Button btnLImpar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void teste(ActionEvent event) {
        
        Stage stage = (Stage) btnSalvarUsuario.getScene().getWindow();
        String userData = (String) stage.getUserData();
        System.out.println(userData);

    }
    
    @FXML
    public void saveNewUser(){
        
        try {
            Usuario user = new Usuario();
            Orgao org = new Orgao();
            
            org.setNomeOrgao(cpOrgao.getText());
            org.setCnpj(cpCnpj.getText());
            org.setCidade(cpCidade.getText());
            org.setUf(cpUf.getText());
            
            user.setNome(cpNomeUsuario.getText());
            user.setCpf(cpCpf.getText());
            user.setTelefone(cpContato.getText());
            user.setOrgao(org);
            
            UsuarioService service = new UsuarioService();
            Usuario newUsuario = service.postNewUsuario(TokenDefault.getTOKEN(), user);
            
            
        } catch (JsonProcessingException | UnsupportedEncodingException ex) {
            Logger.getLogger(FXMLUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
