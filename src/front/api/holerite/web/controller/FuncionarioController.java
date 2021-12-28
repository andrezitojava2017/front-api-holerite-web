/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.api.holerite.web.controller;

import front.api.holerite.web.config.TokenDefault;
import front.api.holerite.web.model.Cargo;
import front.api.holerite.web.model.Funcionario;
import front.api.holerite.web.model.Orgao;
import front.api.holerite.web.services.EmpresaService;
import front.api.holerite.web.services.FuncionarioService;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class FuncionarioController implements Initializable {

    @FXML
    private TextField cpNomeFuncionario;
    @FXML
    private TextField cpCpf;
    @FXML
    private TextField cpCargo;
    @FXML
    private ComboBox<Orgao> comboListEmpresa;
    @FXML
    private Button btnSalvarFuncionario;
    @FXML
    private Button btnSair;
    @FXML
    private Button btnLImpar;

    @FXML
    private DatePicker cpDateAdmissao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDataEmpresa();
    }

    @FXML
    private void saveNewFuncionario() {

        if (cpNomeFuncionario.getText().isEmpty()
                || cpCpf.getText().isEmpty()
                || cpCargo.getText().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Atenção");
            msg.setContentText("Preencha todos os campos");
            msg.showAndWait();
        } else {

            Funcionario func = new Funcionario();
            func.setNomeFuncionario(cpNomeFuncionario.getText());
            func.setCpf(cpCpf.getText());

            String data = cpDateAdmissao.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
            try {

                Cargo cargo = new Cargo(cpCargo.getText(), data);
                func.setCargo(cargo);

                func.setOrgao(comboListEmpresa.getSelectionModel().getSelectedItem());

                FuncionarioService service = new FuncionarioService();

                Funcionario funcionario = service.postNewFuncionario(func, new TokenDefault());
                if (funcionario.getId() != null || funcionario.getId() != "") {
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Atenção");
                    msg.setContentText("Dados salvo com sucesso!!!");
                    msg.showAndWait();
                }

            } catch (IOException ex) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Error");
                msg.setContentText("Ocorreu um erro ao tentar acessar servidor\n" + ex);
                msg.showAndWait();
            } catch (ParseException ex) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Error");
                msg.setContentText("Ocorreu um erro ao fazer conversao de Admissao\n" + ex);
                msg.showAndWait();
            }
        }

    }

    @FXML
    private void cleanFields() {
        cpNomeFuncionario.setText(null);
        cpCpf.setText(null);
        cpCargo.setText(null);
        cpDateAdmissao.setValue(null);

    }

    @FXML
    private void exitView() {

        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }

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

    private LocalDate formatLocalDate(String adm) {
        DateTimeFormatter frm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dat = LocalDate.parse(adm, frm);
        return dat;
    }
}
