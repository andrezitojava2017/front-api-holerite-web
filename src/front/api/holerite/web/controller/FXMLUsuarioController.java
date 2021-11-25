/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class FXMLUsuarioController implements Initializable {

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
    
}
