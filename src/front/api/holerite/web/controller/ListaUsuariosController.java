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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class ListaUsuariosController implements Initializable {

    @FXML
    private TableView<?> tableListaUsuarios;
    @FXML
    private TableColumn<?, ?> colNomeUsuario;
    @FXML
    private TableColumn<?, ?> colOrgaoVinculado;
    @FXML
    private TableColumn<?, ?> colContato;
    @FXML
    private Button btnSelecionar;
    @FXML
    private Button btnSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void selecionarUsuario(ActionEvent event) {
    }

    @FXML
    private void sairView(ActionEvent event) {
    }
    
}
