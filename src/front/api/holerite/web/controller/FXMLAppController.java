/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.controller;

import front.api.holerite.web.config.TokenDefault;
import front.api.holerite.web.services.UsuarioService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    @FXML
    private Button btn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void service(ActionEvent event) {
        
        TokenDefault token = new TokenDefault();
        UsuarioService ser = new UsuarioService();
        
        try {
            
            ser.getUsuarioPorId(token.getTOKEN(), "6164c8b48abd1c30f02b4496");
            
        } catch (IOException ex) {

            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Error");
            msg.setContentText("Erro ao executar chamada ao endpoint /usuario");
            msg.showAndWait();
        }
    }

}
