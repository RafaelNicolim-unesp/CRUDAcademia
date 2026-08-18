package com.template.controller;

import com.template.model.dto.AcademiaDTO;
import com.template.service.AcademiaService;
import com.template.util.AcademiaViewUtils;
import com.template.util.DialogUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {

    private static final Logger LOGGER =
            Logger.getLogger(MainController.class.getName());

    private final AcademiaService service =
            new AcademiaService();

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

            service.salvar(
                    txtNome.getText(),
                    txtEndereco.getText(),
                    txtTelefone.getText(),
                    txtQuantAlunos.getText()
            );

            carregarAcademias();

            AcademiaViewUtils.limparCampos(
                    txtNome,
                    txtEndereco,
                    txtTelefone,
                    txtQuantAlunos,
                    lblMensagem,
                    tblAcademia
            );

            AcademiaViewUtils.mostrarMensagem(
                    lblMensagem,
                    "Academia salva com sucesso!",
                    "green"
            );

        } catch (IllegalArgumentException e) {

            AcademiaViewUtils.mostrarMensagem(
                    lblMensagem,
                    e.getMessage(),
                    "red"
            );

            AcademiaViewUtils.focarCampoComErro(
                    e.getMessage(),
                    txtNome,
                    txtEndereco,
                    txtTelefone,
                    txtQuantAlunos
            );

        } catch (Exception e) {

            AcademiaViewUtils.mostrarMensagem(
                    lblMensagem,
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
                AcademiaViewUtils.obterSelecionado(
                        tblAcademia
                );

        if (selecionado == null) {
            return;
        }

        try {

            service.editar(
                    selecionado.getId(),
                    txtNome.getText(),
                    txtEndereco.getText(),
                    txtTelefone.getText(),
                    txtQuantAlunos.getText()
            );

            carregarAcademias();

            AcademiaViewUtils.limparCampos(
                    txtNome,
                    txtEndereco,
                    txtTelefone,
                    txtQuantAlunos,
                    lblMensagem,
                    tblAcademia
            );

            AcademiaViewUtils.mostrarMensagem(
                    lblMensagem,
                    "Academia editada com sucesso!",
                    "green"
            );

        } catch (IllegalArgumentException e) {

            AcademiaViewUtils.mostrarMensagem(
                    lblMensagem,
                    e.getMessage(),
                    "red"
            );

            AcademiaViewUtils.focarCampoComErro(
                    e.getMessage(),
                    txtNome,
                    txtEndereco,
                    txtTelefone,
                    txtQuantAlunos
            );

        } catch (Exception e) {

            AcademiaViewUtils.mostrarMensagem(
                    lblMensagem,
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
                AcademiaViewUtils.obterSelecionado(
                        tblAcademia
                );

        if (selecionado == null) {
            return;
        }

        boolean confirmou =
                DialogUtils.confirmar(
                        "Confirmar Exclusão",
                        "Deseja realmente excluir a academia \""
                                + selecionado.getNome()
                                + "\"?"
                );

        if (!confirmou) {
            return;
        }

        try {

            service.deletar(
                    selecionado.getId()
            );

            carregarAcademias();

            AcademiaViewUtils.limparCampos(
                    txtNome,
                    txtEndereco,
                    txtTelefone,
                    txtQuantAlunos,
                    lblMensagem,
                    tblAcademia
            );

            AcademiaViewUtils.mostrarMensagem(
                    lblMensagem,
                    "Academia deletada com sucesso.",
                    "blue"
            );

        } catch (Exception e) {

            AcademiaViewUtils.mostrarMensagem(
                    lblMensagem,
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

        AcademiaViewUtils.limparCampos(
                txtNome,
                txtEndereco,
                txtTelefone,
                txtQuantAlunos,
                lblMensagem,
                tblAcademia
        );
    }

    private void carregarAcademias() {

        AcademiaViewUtils.carregarTabela(
                tblAcademia,
                service.listar()
        );
    }

    @FXML
    private void initialize() {

        AcademiaViewUtils.configurarTabela(
                colId,
                colNome,
                colEndereco,
                colTelefone,
                colQuantAlunos
        );

        AcademiaViewUtils.configurarBotoes(
                btnSalvar,
                btnEditar,
                btnDeletar,
                tblAcademia
        );

        tblAcademia
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (obs, antigo, novo) ->
                                AcademiaViewUtils.preencherCampos(
                                        novo,
                                        txtNome,
                                        txtEndereco,
                                        txtTelefone,
                                        txtQuantAlunos,
                                        lblMensagem
                                )
                );

        carregarAcademias();

        LOGGER.info(
                "FXML carregado com sucesso!"
        );
    }
}