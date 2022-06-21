package br.com.academia.pessoaapi.model;

public enum TipoPessoa {

    ALUNO("Aluno"),
    PERSONAL("Personal Trainer"),
    RECEPCIONISTA("Recepcionista");

    private String descricao;

    TipoPessoa(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoPessoa() {
        return descricao;
    }
}
