/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.controller;

import front.api.holerite.web.model.Usuario;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class ListaUsuariosController implements Initializable {

    private List<Usuario> listaUsuarios;
    @FXML
    private TableView<Usuario> tableListaUsuarios;
    @FXML
    private TableColumn<Usuario, String> colNomeUsuario;
    @FXML
    private TableColumn<Usuario, String> colOrgaoVinculado;
    @FXML
    private TableColumn<Usuario, String> colContato;
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

    /**
     * Set colunas com parametros do objeto Usuario
     */
    private void setColunsTableUsuario() {
        colNomeUsuario.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colOrgaoVinculado.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getOrgao().getNomeOrgao()));
        colContato.setCellValueFactory(new PropertyValueFactory<>("telefone"));
    }

    /**
     * Add valores na tabela lista de usuarios
     * @param listaUsuarios 
     */
    private void setDataTableUsuario(List<Usuario> listaUsuarios) {
        tableListaUsuarios.setItems(FXCollections.observableArrayList(listaUsuarios));
        TableView.TableViewSelectionModel<Usuario> model = tableListaUsuarios.getSelectionModel();
        model.select(0);
    }

    @FXML
    private void selecionarUsuario(ActionEvent event) {
    }

    @FXML
    private void sairView(ActionEvent event) {
    }

}
