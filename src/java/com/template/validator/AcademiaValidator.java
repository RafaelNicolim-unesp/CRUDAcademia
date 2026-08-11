package com.template.validator;

public class AcademiaValidator {

    private AcademiaValidator() {
    }

    public static String validar(
            String nome,
            String endereco,
            String telefone,
            String quantidadeAlunos
    ) {

        if (nome == null || nome.trim().isEmpty()) {
            return "O campo Nome é obrigatório.";
        }

        if (!nome.trim().matches("[\\p{L} ]+")) {
            return "O nome deve conter apenas letras e espaços.";
        }

        if (nome.trim().length() < 2) {
            return "O nome deve possuir pelo menos 2 caracteres.";
        }

        if (endereco == null || endereco.trim().isEmpty()) {
            return "O campo Endereço é obrigatório.";
        }

        if (!endereco.trim().matches("[\\p{L}\\d .,'ºª/-]+")) {
            return "O endereço possui caracteres inválidos.";
        }

        if (endereco.trim().length() < 5) {
            return "O endereço deve possuir pelo menos 5 caracteres.";
        }

        if (telefone == null || telefone.trim().isEmpty()) {
            return "O campo Telefone é obrigatório.";
        }

        String telefoneLimpo = telefone.replaceAll("\\D", "");

        if (telefoneLimpo.length() != 10 &&
                telefoneLimpo.length() != 11) {
            return "O telefone deve possuir 10 ou 11 números.";
        }

        if (!telefone.matches(
                "\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}"
        )) {
            return "O telefone está em um formato inválido.";
        }

        if (quantidadeAlunos == null ||
                quantidadeAlunos.trim().isEmpty()) {

            return "O campo Quantidade de Alunos é obrigatório.";
        }

        if (!quantidadeAlunos.trim().matches("\\d+")) {
            return "A quantidade de alunos deve conter apenas números.";
        }

        try {
            int quantidade = Integer.parseInt(
                    quantidadeAlunos.trim()
            );

            if (quantidade < 0) {
                return "A quantidade de alunos não pode ser negativa.";
            }

        } catch (NumberFormatException e) {
            return "A quantidade de alunos informada é inválida.";
        }

        return null;
    }
}