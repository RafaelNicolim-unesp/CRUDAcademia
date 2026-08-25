package com.template.validator;

public class ValidadorQuantAlunos implements Validador<String> {

    private final String quantidadeAlunos;

    public ValidadorQuantAlunos(String quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
    }

    @Override
    public boolean validar(String valor) {

        if (valor == null || valor.trim().isEmpty()) {
            return false;
        }

        if (!valor.trim().matches("\\d+")) {
            return false;
        }

        try {
            int quantidade = Integer.parseInt(valor.trim());
            return quantidade >= 0;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String getMensagemErro() {

        if (quantidadeAlunos == null ||
                quantidadeAlunos.trim().isEmpty()) {

            return "O campo Quantidade de Alunos é obrigatório.";
        }

        if (!quantidadeAlunos.trim().matches("\\d+")) {
            return "A quantidade de alunos deve conter apenas números.";
        }

        try {
            int quantidade =
                    Integer.parseInt(quantidadeAlunos.trim());

            if (quantidade < 0) {
                return "A quantidade de alunos não pode ser negativa.";
            }

        } catch (NumberFormatException e) {
            return "A quantidade de alunos informada é inválida.";
        }

        return "A quantidade de alunos informada é inválida.";
    }

    @Override
    public String getValor() {
        return quantidadeAlunos;
    }
}