package com.template.validator;

public class ValidadorNome implements Validador<String> {

    private final String nome;

    public ValidadorNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean validar(String valor) {

        if (valor == null || valor.trim().isEmpty()) {
            return false;
        }

        return valor.trim().matches("[\\p{L} ]+")
                && valor.trim().length() >= 2;
    }

    @Override
    public String getMensagemErro() {

        if (nome == null || nome.trim().isEmpty()) {
            return "O campo Nome é obrigatório.";
        }

        if (!nome.trim().matches("[\\p{L} ]+")) {
            return "O nome deve conter apenas letras e espaços.";
        }

        return "O nome deve possuir pelo menos 2 caracteres.";
    }

    @Override
    public String getValor() {
        return nome;
    }
}