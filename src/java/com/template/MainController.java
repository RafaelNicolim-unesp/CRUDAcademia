package com.template;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {

    private static final Logger LOGGER =
            Logger.getLogger(MainController.class.getName());

    private AcademiaDAO dao = new AcademiaDAO();

    @FXML private Button btnSalvar;
    @FXML private Button btnEditar;
    @FXML private Button btnDeletar;
    @FXML private Button btnLimpar;

    @FXML private TextField txtNome;
    @FXML private TextField txtEndereco;
    @FXML private TextField txtTelefone;
    @FXML private TextField txtQuantAlunos;

    @FXML private Label lblMensagem;

    @FXML private TableView<AcademiaDTO> tblAcademia;

    @FXML private TableColumn<AcademiaDTO, Integer> colId;
    @FXML private TableColumn<AcademiaDTO, String> colNome;
    @FXML private TableColumn<AcademiaDTO, String> colEndereco;
    @FXML private TableColumn<AcademiaDTO, String> colTelefone;
    @FXML private TableColumn<AcademiaDTO, Integer> colQuantAlunos;

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

            lblMensagem.setStyle("-fx-text-fill: green;");
            lblMensagem.setText("Academia salva com sucesso!");
            LOGGER.info("Academia salva com sucesso!");

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao salvar", e);
        }
    }

    @FXML
    private void btnEditarAction() {
        AcademiaDTO selecionado =
                tblAcademia.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            try {
                if (!validarCampos()) {
                    return;
                }

                AcademiaDTO academia = new AcademiaDTO(
                        selecionado.getId(),
                        txtNome.getText().trim(),
                        txtEndereco.getText().trim(),
                        txtTelefone.getText().trim(),
                        Integer.parseInt(txtQuantAlunos.getText().trim())
                );

                dao.atualizar(academia);

                carregarAcademias();
                limparCampos();

                lblMensagem.setStyle("-fx-text-fill: green;");
                lblMensagem.setText("Academia editada com sucesso!");
                LOGGER.info("Academia editada com sucesso!");

            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Erro ao editar", e);
            }
        } else {
            LOGGER.warning("Nenhuma academia selecionada para editar.");
        }
    }

    @FXML
    private void btnDeletarAction() {
        AcademiaDTO selecionado =
                tblAcademia.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Exclusão");
            alert.setHeaderText(null);
            alert.setContentText("Deseja realmente excluir a academia \"" + selecionado.getNome() + "\"?");

            Optional<ButtonType> resultado = alert.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                dao.deletar(selecionado.getId());

                carregarAcademias();
                limparCampos();

                lblMensagem.setStyle("-fx-text-fill: blue;");
                lblMensagem.setText("Academia deletada com sucesso.");
                LOGGER.info("Academia deletada.");
            }
        } else {
            LOGGER.warning("Nenhuma academia selecionada para deletar.");
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
        tblAcademia.getSelectionModel().clearSelection();
    }

    private void carregarAcademias() {
        tblAcademia.setItems(
                FXCollections.observableArrayList(
                        dao.listar()
                )
        );
    }

    private boolean validarCampos() {
        lblMensagem.setStyle("-fx-text-fill: red;");

        if (txtNome.getText() == null || txtNome.getText().trim().isEmpty()) {
            lblMensagem.setText("O campo Nome é obrigatório.");
            txtNome.requestFocus();
            return false;
        }
        if (txtEndereco.getText() == null || txtEndereco.getText().trim().isEmpty()) {
            lblMensagem.setText("O campo Endereço é obrigatório.");
            txtEndereco.requestFocus();
            return false;
        }
        if (txtTelefone.getText() == null || txtTelefone.getText().trim().isEmpty()) {
            lblMensagem.setText("O campo Telefone é obrigatório.");
            txtTelefone.requestFocus();
            return false;
        }
        if (txtQuantAlunos.getText() == null || txtQuantAlunos.getText().trim().isEmpty()) {
            lblMensagem.setText("O campo Quantidade de Alunos é obrigatório.");
            txtQuantAlunos.requestFocus();
            return false;
        }
        if (!txtQuantAlunos.getText().trim().matches("\\d+")) {
            lblMensagem.setText("A quantidade de alunos deve conter apenas números.");
            txtQuantAlunos.requestFocus();
            return false;
        }
        return true;
    }

    @FXML
    private void initialize() {
        colId.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        colNome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));

        colEndereco.setCellValueFactory(
                new PropertyValueFactory<>("endereco"));

        colTelefone.setCellValueFactory(
                new PropertyValueFactory<>("telefone"));

        colQuantAlunos.setCellValueFactory(
                new PropertyValueFactory<>("quantidadeAlunos"));

        btnEditar.disableProperty().bind(tblAcademia.getSelectionModel().selectedItemProperty().isNull());
        btnDeletar.disableProperty().bind(tblAcademia.getSelectionModel().selectedItemProperty().isNull());
        btnSalvar.disableProperty().bind(tblAcademia.getSelectionModel().selectedItemProperty().isNotNull());

        tblAcademia.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, novo) -> {
                    if (novo != null) {
                        txtNome.setText(novo.getNome());
                        txtEndereco.setText(novo.getEndereco());
                        txtTelefone.setText(novo.getTelefone());
                        txtQuantAlunos.setText(
                                String.valueOf(
                                        novo.getQuantidadeAlunos()
                                )
                        );
                    }
                });

        carregarAcademias();
        LOGGER.info("FXML carregado com sucesso!");
    }
}