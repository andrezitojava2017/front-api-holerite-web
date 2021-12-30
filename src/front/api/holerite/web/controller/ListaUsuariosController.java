/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.controller;

import front.api.holerite.web.config.TokenDefault;
import front.api.holerite.web.model.Funcionario;
import front.api.holerite.web.model.Orgao;
import front.api.holerite.web.model.Usuario;
import front.api.holerite.web.services.UsuarioService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private TableColumn<Usuario, Orgao> colOrgaoVinculado;
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
        loadListUsuarios();
        setColunsTableUsuario();
        setDataTableUsuario();
    }

    private void loadListUsuarios() {

        try {
            TokenDefault token = new TokenDefault();
            UsuarioService service = new UsuarioService();
            listaUsuarios = service.getListUsers(token);
        } catch (IOException ex) {
            Logger.getLogger(ListaUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
     *
     * @param listaUsuarios
     */
    private void setDataTableUsuario() {
        tableListaUsuarios.setItems(FXCollections.observableArrayList(listaUsuarios));
        TableView.TableViewSelectionModel<Usuario> model = tableListaUsuarios.getSelectionModel();
        model.select(0);
    }

    @FXML
    private void selecionarUsuario(ActionEvent event) {

        try {
            FXMLLoader load = FXMLLoader.load(getClass().getResource("/front/api/holerite/web/view/ListaUsuarios.fxml"));
            Parent root = load.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Usuarios cadastrados");
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ListaUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void sairView(ActionEvent event) {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }

}
