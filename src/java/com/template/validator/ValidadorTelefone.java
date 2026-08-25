package com.template.validator;

public class ValidadorTelefone implements Validador<String> {

    private final String telefone;

    public ValidadorTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean validar(String valor) {

        if (valor == null || valor.trim().isEmpty()) {
            return false;
        }

        String telefoneLimpo = valor.replaceAll("\\D", "");

        if (telefoneLimpo.length() != 10 &&
                telefoneLimpo.length() != 11) {
            return false;
        }

        return valor.matches(
                "\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}"
        );
    }

    @Override
    public String getMensagemErro() {

        if (telefone == null || telefone.trim().isEmpty()) {
            return "O campo Telefone é obrigatório.";
        }

        String telefoneLimpo = telefone.replaceAll("\\D", "");

        if (telefoneLimpo.length() != 10 &&
                telefoneLimpo.length() != 11) {
            return "O telefone deve possuir 10 ou 11 números.";
        }

        return "O telefone está em um formato inválido.";
    }

    @Override
    public String getValor() {
        return telefone;
    }
}