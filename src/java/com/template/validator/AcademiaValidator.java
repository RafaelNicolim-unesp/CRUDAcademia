package com.template.validator;

import java.util.ArrayList;
import java.util.List;

public class AcademiaValidator {

    private AcademiaValidator() {
    }

    public static String validar(
            String nome,
            String endereco,
            String telefone,
            String quantidadeAlunos
    ) {

        List<Validador<String>> validadores = new ArrayList<>();

        validadores.add(
                new CampoObrigatorioValidador("Nome", nome)
        );

        validadores.add(
                new ValidadorNome(nome)
        );

        validadores.add(
                new CampoObrigatorioValidador("Endereço", endereco)
        );

        validadores.add(
                new ValidadorEndereco(endereco)
        );

        validadores.add(
                new CampoObrigatorioValidador("Telefone", telefone)
        );

        validadores.add(
                new ValidadorTelefone(telefone)
        );

        validadores.add(
                new CampoObrigatorioValidador("Quantidade de Alunos", quantidadeAlunos)
        );

        validadores.add(
                new ValidadorQuantAlunos(quantidadeAlunos)
        );

        for (Validador<String> validador : validadores) {

            if (!validador.validar(validador.getValor())) {
                return validador.getMensagemErro();
            }
        }

        return null;
    }
}