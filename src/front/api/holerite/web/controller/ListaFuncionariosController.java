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
import front.api.holerite.web.services.FuncionarioService;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jederson André
 */
public class ListaFuncionariosController implements Initializable {

    private List<Funcionario> listaFuncionarios;
    private String cnpj;

    @FXML
    private TableView<Funcionario> tableListaFuncionarios;
    @FXML
    private TableColumn<Funcionario, String> colNomeFuncionario;
    @FXML
    private TableColumn<Funcionario, Orgao> colOrgaoVinculado;
    @FXML
    private Button btnSelecionar;
    @FXML
    private Button btnSair;

    public ListaFuncionariosController(String cnpj) {
        this.cnpj = cnpj;
    }

    private void loadListFuncionarios() {

        try {
            TokenDefault token = new TokenDefault();
            FuncionarioService service = new FuncionarioService();
            this.listaFuncionarios = service.getListFuncionarios(token, cnpj);

        } catch (IOException ex) {
            Logger.getLogger(ListaFuncionariosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Set colunas com parametros do objeto Usuario
     */
    private void setColunsTableUsuario() {
        colNomeFuncionario.setCellValueFactory(new PropertyValueFactory<>("nomeFuncionario"));
        colOrgaoVinculado.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getOrgao().getNomeOrgao()));

    }

    /**
     * Add valores na tabela lista de usuarios
     *
     * @param listaUsuarios
     */
    private void setDataTableUsuario() {
        tableListaFuncionarios.setItems(FXCollections.observableArrayList(listaFuncionarios));
        TableView.TableViewSelectionModel<Funcionario> model = tableListaFuncionarios.getSelectionModel();
        model.select(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setColunsTableUsuario();
        loadListFuncionarios();
        setDataTableUsuario();
    }
}
