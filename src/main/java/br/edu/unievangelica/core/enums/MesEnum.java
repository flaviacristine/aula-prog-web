package br.edu.unievangelica.core.enums;

public enum MesEnum {
    /* JANEIRO */
    JANEIRO("mensagem.janeiro", 1),

    /* FEVEREIRO */
    FEVEREIRO("mensagem.fevereiro", 2),

    /* MARCO */
    MARCO("mensagem.marco", 3),

    /* ABRIL */
    ABRIL("mensagem.abril", 4),

    /* MAIO */
    MAIO("mensagem.maio", 5),

    /* JUNHO */
    JUNHO("mensagem.junho", 6),

    /* JULHO */
    JULHO("mensagem.julho", 7),

    /* AGOSTO */
    AGOSTO("mensagem.agosto", 8),

    /* SETEMBRO */
    SETEMBRO("mensagem.setembro", 9),

    /* OUTUBRO */
    OUTUBRO("mensagem.outubro", 10),

    /* NOVEMBRO */
    NOVEMBRO("mensagem.novembro", 11),

    /* DEZEMBRO */
    DEZEMBRO("mensagem.dezembro", 12);

    private String nome;

    private int numero;

    MesEnum(String nome, int numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return this.numero;
    }
}
