/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.controller;

import front.api.holerite.web.config.TokenDefault;
import front.api.holerite.web.model.Orgao;
import front.api.holerite.web.services.EmpresaService;
import front.api.holerite.web.services.FuncionarioService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class UploadArquivoAnexoController implements Initializable {

    private File fileSelect;

    @FXML
    private TextField cpCaminhoArquivo;
    @FXML
    private DatePicker dataPickerCompetencia;
    @FXML
    private ComboBox<Orgao> comboEmpresa;
    @FXML
    private Button btnLocalizar;
    @FXML
    private Button btnImportar;
    @FXML
    private Button btnSair;
    @FXML
    private CheckBox checkDadosFuncionarios;
    @FXML
    private CheckBox checkDadosProventosDescontos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDataEmpresa();
    }

    @FXML
    private void searchFileAnexo(ActionEvent event) {
        FileChooser chosser = new FileChooser();
        chosser.setTitle("Buscar ANEXO");
        chosser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TextPlain", "*.txt"));
        try {

            fileSelect = chosser.showOpenDialog(new Stage());
            if (fileSelect.exists() && fileSelect != null) {
                cpCaminhoArquivo.setText(fileSelect.getAbsolutePath());
            }

        } catch (NullPointerException e) {
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Mensagem");
            msg.setContentText("Nenhum arquivo foi selecionado");
            msg.showAndWait();
        }

    }

    @FXML
    private void sendPostFileAnexo(ActionEvent event) {

        Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("Mensagem");
        msg.setContentText("CONFIRMA A IMPORTAÇÃO DE DADOS?");
        Optional<ButtonType> view = msg.showAndWait();
        
        view.ifPresent(btn -> {
            if (btn.getButtonData().isDefaultButton()) {
                
                if (checkDadosFuncionarios.isSelected()) {
                    
                    if(checkComponentsAndDataFile()){
                        
                        TokenDefault token = new TokenDefault();
                        Orgao selectedCnpj = comboEmpresa.getSelectionModel().getSelectedItem();
                        
                        FuncionarioService service = new FuncionarioService();
                        service.uploadDataAnexoFuncionarios(token, selectedCnpj.getCnpj(), fileSelect);
                        
                    }
                

                }

                if (checkDadosProventosDescontos.isSelected()) {

                }
            }
        });

    }

    @FXML
    private void exitView(ActionEvent event) {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }

    private void loadDataEmpresa() {
        TokenDefault token = new TokenDefault();
        EmpresaService service = new EmpresaService();
        try {
            List<Orgao> listEmpresa = service.getListEmpresa(token);
            comboEmpresa.setItems(FXCollections.observableList(listEmpresa));
        } catch (IOException ex) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Error");
            msg.setContentText("Ocorreu um erro ao carregar dados de empresas\n" + ex);
            msg.showAndWait();
        }

    }

    @FXML
    private void checkUrlServiceFuncionarios(ActionEvent event) {

        if (checkDadosFuncionarios.isSelected()) {
            checkDadosProventosDescontos.setSelected(false);
        }

    }

    @FXML
    private void checkUrlServiceProvDesc(ActionEvent event) {

        if (checkDadosProventosDescontos.isSelected()) {
            checkDadosFuncionarios.setSelected(false);
        }
    }
    
    /**
     * verifica se o file foi carregado e o CNPJ foi selecionado
     * @return boolean
     */
    private boolean checkComponentsAndDataFile(){
        if(this.fileSelect !=null && comboEmpresa.getSelectionModel().getSelectedItem() != null){
            return true;
        }
        return false;
    }

}
