package com.template.util;

import com.template.model.dto.AcademiaDTO;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AcademiaViewUtils {

    private AcademiaViewUtils() {
    }

    public static void configurarTabela(
            TableColumn<AcademiaDTO, Integer> colId,
            TableColumn<AcademiaDTO, String> colNome,
            TableColumn<AcademiaDTO, String> colEndereco,
            TableColumn<AcademiaDTO, String> colTelefone,
            TableColumn<AcademiaDTO, Integer> colQuantAlunos
    ) {

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
    }

    public static void configurarBotoes(
            Button btnSalvar,
            Button btnEditar,
            Button btnDeletar,
            TableView<AcademiaDTO> tabela
    ) {

        btnEditar.disableProperty().bind(
                tabela.getSelectionModel()
                        .selectedItemProperty()
                        .isNull()
        );

        btnDeletar.disableProperty().bind(
                tabela.getSelectionModel()
                        .selectedItemProperty()
                        .isNull()
        );

        btnSalvar.disableProperty().bind(
                tabela.getSelectionModel()
                        .selectedItemProperty()
                        .isNotNull()
        );
    }

    public static void carregarTabela(
            TableView<AcademiaDTO> tabela,
            java.util.List<AcademiaDTO> academias
    ) {

        tabela.setItems(
                FXCollections.observableArrayList(
                        academias
                )
        );
    }

    public static AcademiaDTO obterSelecionado(
            TableView<AcademiaDTO> tabela
    ) {

        return tabela
                .getSelectionModel()
                .getSelectedItem();
    }

    public static void preencherCampos(
            AcademiaDTO academia,
            TextField txtNome,
            TextField txtEndereco,
            TextField txtTelefone,
            TextField txtQuantAlunos,
            Label lblMensagem
    ) {

        if (academia == null) {
            return;
        }

        txtNome.setText(academia.getNome());

        txtEndereco.setText(
                academia.getEndereco()
        );

        txtTelefone.setText(
                academia.getTelefone()
        );

        txtQuantAlunos.setText(
                String.valueOf(
                        academia.getQuantidadeAlunos()
                )
        );

        limparMensagem(lblMensagem);
    }

    public static void limparCampos(
            TextField txtNome,
            TextField txtEndereco,
            TextField txtTelefone,
            TextField txtQuantAlunos,
            Label lblMensagem,
            TableView<AcademiaDTO> tabela
    ) {

        txtNome.clear();
        txtEndereco.clear();
        txtTelefone.clear();
        txtQuantAlunos.clear();

        limparMensagem(lblMensagem);

        tabela.getSelectionModel()
                .clearSelection();
    }

    public static void mostrarMensagem(
            Label label,
            String mensagem,
            String cor
    ) {

        label.setStyle(
                "-fx-text-fill: " + cor + ";"
        );

        label.setText(mensagem);
    }

    public static void limparMensagem(Label label) {
        label.setText("");
    }

    public static void focarCampoComErro(
            String erro,
            TextField txtNome,
            TextField txtEndereco,
            TextField txtTelefone,
            TextField txtQuantAlunos
    ) {

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
}