package com.template.validator;

public class ValidadorEndereco implements Validador<String> {
    @Override
    public boolean validar(String valor) {
        return false;
    }

    @Override
    public String getMensagemErro() {
        return "";
    }

    @Override
    public String getValor() {
        return "";
    }
}
