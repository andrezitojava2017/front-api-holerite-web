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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jederson André
 */
public class ListaFuncionariosController implements Initializable {

    private List<Funcionario> listaFuncionarios;
    private String cnpj;
    private Funcionario funcSelecionado;

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
    @FXML
    private TextField cpLocalizar;


    public ListaFuncionariosController() {

    }

    public Funcionario getFuncionarioSelected(){
        return this.funcSelecionado;
    }
    /**
     * preenche tabela com os dados recuperados da base
     */
    private void loadListFuncionarios() {

        try {
            TokenDefault token = new TokenDefault();
            FuncionarioService service = new FuncionarioService();
            this.listaFuncionarios = service.getListFuncionarios(token, cnpj);

        } catch (IOException ex) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Error");
            msg.setContentText("Ocorreu um erro ao tentar recuperar dados dos funcionarios\n" + ex);
            msg.showAndWait();
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

    private void setDataTableUsuarioSelecionado(List<Funcionario> lista) {
        tableListaFuncionarios.setItems(FXCollections.observableArrayList(lista));
        TableView.TableViewSelectionModel<Funcionario> model = tableListaFuncionarios.getSelectionModel();
        model.select(0);
        this.funcSelecionado = model.getSelectedItem();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // carregamos a lista de funcionarios 
        // de acordo com cnpj selecionado
        FXMLUsuarioController cont = new FXMLUsuarioController();
        this.cnpj = cont.getcnpj();

        setColunsTableUsuario();
        loadListFuncionarios();
        setDataTableUsuario();

    }

    @FXML
    private void searchFuncionario(KeyEvent event) {
        List<Funcionario> selecao = new ArrayList<>();

        listaFuncionarios.stream()
                .filter(e -> e.getNomeFuncionario().contains(cpLocalizar.getText()))
                .forEach(result -> {
                    Funcionario func = result;
                    selecao.add(func);
                });
        setDataTableUsuarioSelecionado(selecao);
    }

    @FXML
    private void selectFuncionario(ActionEvent event) {
        TableView.TableViewSelectionModel<Funcionario> model = tableListaFuncionarios.getSelectionModel();
        this.funcSelecionado = model.getSelectedItem();
        
        Stage stage = (Stage)btnSelecionar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void exitView(ActionEvent event) {
        Stage stage = (Stage)btnSair.getScene().getWindow();
        stage.close();
    }
}
