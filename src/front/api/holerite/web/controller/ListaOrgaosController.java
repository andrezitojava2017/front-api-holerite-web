/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.controller;

import front.api.holerite.web.config.TokenDefault;
import front.api.holerite.web.model.Orgao;
import front.api.holerite.web.services.EmpresaService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
public class ListaOrgaosController implements Initializable {

    @FXML
    private TableView<Orgao> tableViewListaOrgaos;
    @FXML
    private TableColumn<Orgao, String> colCnpj;
    @FXML
    private TableColumn<Orgao, String> colNomeEmpresa;
    @FXML
    private Button btnAlterarRegistro;
    @FXML
    private Button btnSair;

    
    private List<Orgao> listaOrgaos;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadListEmpresa();
        setColunsTableEmpresas();
        fillDataTableEmpresa();
    }    
    
    /**
     * Define as colunas da tabela
     */
    private void setColunsTableEmpresas(){
        colCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        colNomeEmpresa.setCellValueFactory(new PropertyValueFactory<>("nomeOrgao"));
    }
    /**
     * Preenche tabela com dados das empresas cadastrado
     */
    private void fillDataTableEmpresa(){
        tableViewListaOrgaos.setItems(FXCollections.observableArrayList(listaOrgaos));
        TableView.TableViewSelectionModel<Orgao> model = tableViewListaOrgaos.getSelectionModel();
        model.select(0);
    }

    
    /**
     * Recupera lista de empresas cadastradas
     */
    private void loadListEmpresa(){
         try {
            
            TokenDefault token = new TokenDefault();
            EmpresaService ser = new EmpresaService();
            this.listaOrgaos = ser.getListEmpresa(token);            
            
        } catch (IOException ex) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Error");
            msg.setContentText("Ocorreu um erro na requisição!\n" + ex.getMessage());
            msg.showAndWait();
        }
    }

    @FXML
    private void alterDataEmpresa(ActionEvent event) {
    }

    @FXML
    private void actionExitView(ActionEvent event) {
        
        Stage stage = (Stage)btnSair.getScene().getWindow();
        stage.close();
    }
}
