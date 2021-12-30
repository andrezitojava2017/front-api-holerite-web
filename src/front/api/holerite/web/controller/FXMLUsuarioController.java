/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import front.api.holerite.web.config.TokenDefault;
import front.api.holerite.web.model.Funcionario;
import front.api.holerite.web.model.Orgao;
import front.api.holerite.web.model.Usuario;
import front.api.holerite.web.services.EmpresaService;
import front.api.holerite.web.services.UsuarioService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class FXMLUsuarioController implements Initializable {

    String dados;

    @FXML
    private TextField cpNomeUsuario;
    @FXML
    private TextField cpToken;
    @FXML
    private TextField cpCpf;
    @FXML
    private TextField cpContato;
    @FXML
    private Button btnSalvarUsuario;
    @FXML
    private Button btnSair;
    @FXML
    private Button btnLImpar;
    @FXML
    private ComboBox<Orgao> comboListEmpresa;
    @FXML
    private Button btnBuscarFuncionario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDataEmpresa();
    }

    /**
     * Carrega informaçõe sobre empresas cadastradas no combobox
     */
    private void loadDataEmpresa() {

        TokenDefault token = new TokenDefault();
        EmpresaService service = new EmpresaService();
        try {
            List<Orgao> listEmpresa = service.getListEmpresa(token);
            comboListEmpresa.setItems(FXCollections.observableList(listEmpresa));
        } catch (IOException ex) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Error");
            msg.setContentText("Ocorreu um erro ao carregar dados de empresas\n" + ex);
            msg.showAndWait();
        }

    }

    @FXML
    public void saveNewUser() {

        if (cpNomeUsuario.getText().isEmpty()
                || cpContato.getText().isEmpty()
                || cpCpf.getText().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Atenção");
            msg.setContentText("Preencha todos os campos");
            msg.showAndWait();
        } else {
            TokenDefault token = new TokenDefault();
            Usuario user = new Usuario();
            Orgao org = comboListEmpresa.getSelectionModel().getSelectedItem();
            user.setNome(cpNomeUsuario.getText());
            user.setCpf(cpCpf.getText());
            user.setTelefone(cpContato.getText());
            user.setOrgao(org);
            UsuarioService service = new UsuarioService();
            Usuario newUsuario;
            try {

                newUsuario = service.postNewUsuario(token, user);
                cpToken.setText(newUsuario.getToken());

            } catch (UnsupportedEncodingException ex) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Error");
                msg.setContentText("Ocorreu um erro de conexao com servidor\n" + ex);
                msg.showAndWait();
            } catch (IOException ex) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Error");
                msg.setContentText("Ocorreu um erro de conexao com servidor\n" + ex);
                msg.showAndWait();
            }
        }

    }

    @FXML
    private void searchFuncionario() {

        if (checkComboEmpresa() != null) {
            
            String cnpj = checkComboEmpresa();
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/front/api/holerite/web/view/ListaFuncionarios.fxml"));
                load.setController(new ListaFuncionariosController(cnpj));
                Parent root = load.load();

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.setTitle("Localizar funcionario");
                stage.showAndWait();

            } catch (IOException ex) {
                Logger.getLogger(FXMLUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * preenche campos com dados do funcionario selecionado na lista de busca
     *
     * @param funcionario
     */
    private void fillFieldsFuncionario(Funcionario funcionario) {

        cpNomeUsuario.setText(funcionario.getNomeFuncionario());
        cpCpf.setText(funcionario.getCpf());

    }

    @FXML
    private void exitView(ActionEvent event) {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clearFields(ActionEvent event) {

        cpNomeUsuario.setText(null);
        cpCpf.setText(null);
        cpContato.setText(null);
        cpToken.setText(null);

    }

    private String checkComboEmpresa() {

        if (comboListEmpresa.getSelectionModel().getSelectedItem() != null) {
            return comboListEmpresa.getSelectionModel().getSelectedItem().getCnpj();
        } else {
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Atenção");
            msg.setContentText("Selecione uma empresa para identificar CNPJ");
            msg.showAndWait();
        }
        return null;
    }

}
