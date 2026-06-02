package com.template;

public class AcademiaDTO {

    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private int quantidadeAlunos;

    public AcademiaDTO(
            int id,
            String nome,
            String endereco,
            String telefone,
            int quantidadeAlunos) {

        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.quantidadeAlunos = quantidadeAlunos;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getQuantidadeAlunos() {
        return quantidadeAlunos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setQuantidadeAlunos(int quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
    }

    @Override
    public String toString() {
        return nome;
    }
}