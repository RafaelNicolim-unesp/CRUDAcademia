package com.template.validator;

public class ValidadorEndereco implements Validador<String> {

    private final String endereco;

    public ValidadorEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean validar(String valor) {

        if (valor == null || valor.trim().isEmpty()) {
            return false;
        }

        return valor.trim().matches("[\\p{L}\\d .,'ºª/-]+")
                && valor.trim().length() >= 5;
    }

    @Override
    public String getMensagemErro() {

        if (endereco == null || endereco.trim().isEmpty()) {
            return "O campo Endereço é obrigatório.";
        }

        if (!endereco.trim().matches("[\\p{L}\\d .,'ºª/-]+")) {
            return "O endereço possui caracteres inválidos.";
        }

        return "O endereço deve possuir pelo menos 5 caracteres.";
    }

    @Override
    public String getValor() {
        return endereco;
    }
}