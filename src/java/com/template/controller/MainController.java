package com.template.controller;

import com.template.model.dao.AcademiaDAO;
import com.template.model.dto.AcademiaDTO;
import com.template.validator.AcademiaValidator;
import com.template.util.DialogUtils;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {

    private static final Logger LOGGER =
            Logger.getLogger(MainController.class.getName());

    private final AcademiaDAO dao = new AcademiaDAO();

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnLimpar;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtQuantAlunos;

    @FXML
    private Label lblMensagem;

    @FXML
    private TableView<AcademiaDTO> tblAcademia;

    @FXML
    private TableColumn<AcademiaDTO, Integer> colId;

    @FXML
    private TableColumn<AcademiaDTO, String> colNome;

    @FXML
    private TableColumn<AcademiaDTO, String> colEndereco;

    @FXML
    private TableColumn<AcademiaDTO, String> colTelefone;

    @FXML
    private TableColumn<AcademiaDTO, Integer> colQuantAlunos;

    @FXML
    private void btnSalvarAction() {

        try {

            if (!validarCampos()) {
                return;
            }

            AcademiaDTO academia = new AcademiaDTO(
                    0,
                    txtNome.getText().trim(),
                    txtEndereco.getText().trim(),
                    txtTelefone.getText().trim(),
                    Integer.parseInt(txtQuantAlunos.getText().trim())
            );

            dao.inserir(academia);

            carregarAcademias();
            limparCampos();

            mostrarMensagem(
                    "Academia salva com sucesso!",
                    "green"
            );

            LOGGER.info("Academia salva com sucesso.");

        } catch (Exception e) {

            mostrarMensagem(
                    "Erro ao salvar a academia.",
                    "red"
            );

            LOGGER.log(
                    Level.SEVERE,
                    "Erro ao salvar academia.",
                    e
            );
        }
    }

    @FXML
    private void btnEditarAction() {

        AcademiaDTO selecionado =
                tblAcademia.getSelectionModel().getSelectedItem();

        if (selecionado == null) {

            LOGGER.warning(
                    "Nenhuma academia selecionada para editar."
            );

            return;
        }

        try {

            if (!validarCampos()) {
                return;
            }

            AcademiaDTO academia = new AcademiaDTO(
                    selecionado.getId(),
                    txtNome.getText().trim(),
                    txtEndereco.getText().trim(),
                    txtTelefone.getText().trim(),
                    Integer.parseInt(
                            txtQuantAlunos.getText().trim()
                    )
            );

            dao.atualizar(academia);

            carregarAcademias();
            limparCampos();

            mostrarMensagem(
                    "Academia editada com sucesso!",
                    "green"
            );

            LOGGER.info("Academia editada com sucesso.");

        } catch (Exception e) {

            mostrarMensagem(
                    "Erro ao editar a academia.",
                    "red"
            );

            LOGGER.log(
                    Level.SEVERE,
                    "Erro ao editar academia.",
                    e
            );
        }
    }

    @FXML
    private void btnDeletarAction() {

        AcademiaDTO selecionado =
                tblAcademia.getSelectionModel().getSelectedItem();

        if (selecionado == null) {

            LOGGER.warning(
                    "Nenhuma academia selecionada para deletar."
            );

            return;
        }

        String mensagem =
                "Deseja realmente excluir a academia \""
                        + selecionado.getNome()
                        + "\"?";

        boolean confirmou =
                DialogUtils.confirmar(
                        "Confirmar Exclusão",
                        mensagem
                );

        if (!confirmou) {
            return;
        }

        try {

            dao.deletar(selecionado.getId());

            carregarAcademias();
            limparCampos();

            mostrarMensagem(
                    "Academia deletada com sucesso.",
                    "blue"
            );

            LOGGER.info("Academia deletada com sucesso.");

        } catch (Exception e) {

            mostrarMensagem(
                    "Erro ao deletar a academia.",
                    "red"
            );

            LOGGER.log(
                    Level.SEVERE,
                    "Erro ao deletar academia.",
                    e
            );
        }
    }

    @FXML
    private void btnLimparAction() {

        limparCampos();

        LOGGER.info("Campos limpos.");
    }

    private void limparCampos() {

        txtNome.clear();
        txtEndereco.clear();
        txtTelefone.clear();
        txtQuantAlunos.clear();

        lblMensagem.setText("");

        tblAcademia
                .getSelectionModel()
                .clearSelection();
    }

    private void carregarAcademias() {

        tblAcademia.setItems(
                FXCollections.observableArrayList(
                        dao.listar()
                )
        );
    }

    private boolean validarCampos() {

        String erro = AcademiaValidator.validar(
                txtNome.getText(),
                txtEndereco.getText(),
                txtTelefone.getText(),
                txtQuantAlunos.getText()
        );

        if (erro != null) {

            mostrarMensagem(
                    erro,
                    "red"
            );

            focarCampoComErro(erro);

            return false;
        }

        lblMensagem.setText("");

        return true;
    }

    private void focarCampoComErro(String erro) {

        if (erro.contains("Nome")) {

            txtNome.requestFocus();

        } else if (erro.contains("Endereço")) {

            txtEndereco.requestFocus();

        } else if (erro.contains("Telefone")) {

            txtTelefone.requestFocus();

        } else if (erro.contains("Quantidade")) {

            txtQuantAlunos.requestFocus();
        }
    }

    private void mostrarMensagem(
            String mensagem,
            String cor
    ) {

        lblMensagem.setStyle(
                "-fx-text-fill: " + cor + ";"
        );

        lblMensagem.setText(mensagem);
    }

    @FXML
    private void initialize() {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        colNome.setCellValueFactory(
                new PropertyValueFactory<>("nome")
        );

        colEndereco.setCellValueFactory(
                new PropertyValueFactory<>("endereco")
        );

        colTelefone.setCellValueFactory(
                new PropertyValueFactory<>("telefone")
        );

        colQuantAlunos.setCellValueFactory(
                new PropertyValueFactory<>("quantidadeAlunos")
        );

        btnEditar.disableProperty().bind(
                tblAcademia
                        .getSelectionModel()
                        .selectedItemProperty()
                        .isNull()
        );

        btnDeletar.disableProperty().bind(
                tblAcademia
                        .getSelectionModel()
                        .selectedItemProperty()
                        .isNull()
        );

        btnSalvar.disableProperty().bind(
                tblAcademia
                        .getSelectionModel()
                        .selectedItemProperty()
                        .isNotNull()
        );

        tblAcademia
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (obs, antigo, novo) -> {

                            if (novo != null) {

                                txtNome.setText(
                                        novo.getNome()
                                );

                                txtEndereco.setText(
                                        novo.getEndereco()
                                );

                                txtTelefone.setText(
                                        novo.getTelefone()
                                );

                                txtQuantAlunos.setText(
                                        String.valueOf(
                                                novo.getQuantidadeAlunos()
                                        )
                                );

                                lblMensagem.setText("");
                            }
                        }
                );

        carregarAcademias();

        LOGGER.info(
                "FXML carregado com sucesso!"
        );
    }

}
