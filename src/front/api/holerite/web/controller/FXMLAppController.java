/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import front.api.holerite.web.config.TokenDefault;
import front.api.holerite.web.model.Orgao;
import front.api.holerite.web.model.Usuario;
import front.api.holerite.web.services.EmpresaService;
import front.api.holerite.web.services.UsuarioService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

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
        // inicializa o token
        TokenDefault token = new TokenDefault();
    }

    private void service(ActionEvent event) {

        try {

            TokenDefault token = new TokenDefault();
            EmpresaService ser = new EmpresaService();
            List<Orgao> Empresas = ser.getListEmpresa(token);

        } catch (IOException ex) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Error");
            msg.setContentText("Ocorreu um erro na requisição!\n" + ex.getMessage());
            msg.showAndWait();
        }

    }

    @FXML
    private void viewDadosUsuario(ActionEvent event) {

        try {

            Stage stage = openXmlViews("Usuario", "Dados de Usuarios");
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(FXMLAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Chamada padrao para abertura das view xml
     *
     * @param nameXmlView
     * @throws IOException
     */
    public Stage openXmlViews(String nameXmlView, String titleView) throws IOException {

        String path = "/front/api/holerite/web/view/";
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource(path + nameXmlView + ".fxml"));

        Parent root = load.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle(titleView);
        stage.setScene(scene);

        return stage;

    }

    @FXML
    private void viewCadastratoOrgao(ActionEvent event) {

        try {
            Stage stage = openXmlViews("Orgao", "Cadastro de Empresas");
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Error");
            msg.setContentText("Erro ao chamar tela de empresas!!");
            msg.showAndWait();
        }

    }

    @FXML
    private void viewListarEmpresas(ActionEvent event) {

        try {
            Stage stage = openXmlViews("ListaOrgaos", "Lista de empresas");
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Error");
            msg.setContentText("Erro ao chamar tela de empresas!!");
            msg.showAndWait();
        }

    }

    @FXML
    private void listarUsuarios(ActionEvent event) {
        try {
            Stage stage = openXmlViews("ListaUsuarios", "Usuarios cadastrados");
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException ex) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Error");
            msg.setContentText("Erro ao chamar tela de Usuarios!!");
            msg.showAndWait();
        }
    }

    @FXML
    private void viewNewFuncionario(ActionEvent event) {
        try {

            Stage stage = openXmlViews("Funcionario", "Cadastro de novo funcionario");
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException ex) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Error");
            msg.setContentText("Erro ao chamar tela de Funcionarios!!");
            msg.showAndWait();
        }
    }

    @FXML
    private void viewImportarListaFuncionarios(ActionEvent event) {
        try {
            Stage stage = openXmlViews("UploadArquivoAnexoIII", "Importar Funcionarios");
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException ex) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Error");
            msg.setContentText("Erro ao chamar tela de Importação!!");
            msg.showAndWait();
        }
    }
}
