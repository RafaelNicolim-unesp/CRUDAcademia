package com.template.service;

import com.template.model.dao.AcademiaDAO;
import com.template.model.dto.AcademiaDTO;
import com.template.validator.AcademiaValidator;

import java.util.List;

public class AcademiaService {

    private final AcademiaDAO dao;

    public AcademiaService() {
        this.dao = new AcademiaDAO();
    }

    public void salvar(
            String nome,
            String endereco,
            String telefone,
            String quantidadeAlunos
    ) {

        validar(
                nome,
                endereco,
                telefone,
                quantidadeAlunos
        );

        AcademiaDTO academia = new AcademiaDTO(
                0,
                nome.trim(),
                endereco.trim(),
                telefone.trim(),
                Integer.parseInt(quantidadeAlunos.trim())
        );

        dao.inserir(academia);
    }

    public void editar(
            int id,
            String nome,
            String endereco,
            String telefone,
            String quantidadeAlunos
    ) {

        validar(
                nome,
                endereco,
                telefone,
                quantidadeAlunos
        );

        AcademiaDTO academia = new AcademiaDTO(
                id,
                nome.trim(),
                endereco.trim(),
                telefone.trim(),
                Integer.parseInt(quantidadeAlunos.trim())
        );

        dao.atualizar(academia);
    }

    public void deletar(int id) {
        dao.deletar(id);
    }

    public List<AcademiaDTO> listar() {
        return dao.listar();
    }

    private void validar(
            String nome,
            String endereco,
            String telefone,
            String quantidadeAlunos
    ) {

        String erro = AcademiaValidator.validar(
                nome,
                endereco,
                telefone,
                quantidadeAlunos
        );

        if (erro != null) {
            throw new IllegalArgumentException(erro);
        }
    }
}