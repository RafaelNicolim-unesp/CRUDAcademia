package com.template.validator;

public interface IAcademiaValidator {

    boolean validarNome(
            ValidadorNome validador
    );

    boolean validarEndereco(
            ValidadorEndereco validador
    );

    boolean validarQuantAlunos(
            ValidadorQuantAlunos validador
    );

    boolean validarTelefone(
            ValidadorTelefone validador
    );
}