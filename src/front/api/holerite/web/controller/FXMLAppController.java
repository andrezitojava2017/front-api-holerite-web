/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 *
 * @author andre
 */
public class FXMLAppController implements Initializable {
    
    private Label label;
    @FXML
    private MenuBar barraMenu;
    @FXML
    private Menu menuUsuarios;
    @FXML
    private MenuItem menuUsuarioListarUsuarios;
    @FXML
    private MenuItem menuUsusarioNovoUsuario;
    @FXML
    private Menu menuFuncionario;
    @FXML
    private MenuItem menuFuncionarioCadFuncionario;
    @FXML
    private MenuItem menuFuncionarioImportFuncionario;
    @FXML
    private MenuItem menuFuncionarioListarFuncionario;
    @FXML
    private MenuItem menuFuncionarioBuscaPorCpf;
    @FXML
    private Menu menuOrgao;
    @FXML
    private MenuItem menuOrgaoCadastrarOrgao;
    @FXML
    private MenuItem menuOrgaoListar;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
