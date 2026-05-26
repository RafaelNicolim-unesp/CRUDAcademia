package com.template;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {

    private static final Logger LOGGER = Logger.getLogger(MainController.class.getName());
    private AcademiaDAO dao = new AcademiaDAO();

    @FXML private Button btnSalvar;
    @FXML private Button btnEditar;
    @FXML private Button btnDeletar;
    @FXML private TextField txtNome;
    @FXML private TextField txtEndereco;
    @FXML private TextField txtTelefone;
    @FXML private TextField txtQuantAlunos;
    @FXML private TableView<AcademiaDTO> tblAcademia;
    @FXML private TableColumn<AcademiaDTO, Integer> colId;
    @FXML private TableColumn<AcademiaDTO, String> colNome;
    @FXML private TableColumn<AcademiaDTO, String> colEndereco;
    @FXML private TableColumn<AcademiaDTO, String> colTelefone;
    @FXML private TableColumn<AcademiaDTO, Integer> colQuantAlunos;

    @FXML
    private void btnSalvarAction() {
        try {
            AcademiaDTO academia = new AcademiaDTO(
                    0,
                    txtNome.getText(),
                    txtEndereco.getText(),
                    txtTelefone.getText(),
                    Integer.parseInt(txtQuantAlunos.getText())
            );
            dao.inserir(academia);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao salvar", e);
        }
    }

    @FXML
    private void btnEditarAction() {
        AcademiaDTO selecionado = tblAcademia.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            try {
                AcademiaDTO academia = new AcademiaDTO(
                        selecionado.getId(),
                        txtNome.getText(),
                        txtEndereco.getText(),
                        txtTelefone.getText(),
                        Integer.parseInt(txtQuantAlunos.getText())
                );
                dao.atualizar(academia);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Erro ao editar", e);
            }
        } else {
            LOGGER.warning("Nenhuma academia selecionada para editar.");
        }
    }

    @FXML private void btnDeletarAction() {
        AcademiaDTO selecionado = tblAcademia.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            dao.deletar(selecionado.getId());
        } else {
            LOGGER.warning("Nenhuma academia selecionada para deletar.");
        }
    }

    @FXML
    private void initialize() {
        LOGGER.info("FXML carregado com sucesso!");
    }
}