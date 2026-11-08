package tiposEnumeradosEnum;

public enum DiaSemana {
    SEGUNDA("Segunda-feira"),
    TERCA("Terça-feira"),
    QUARTA("Quarta-feira"),
    QUINTA("Quinta-feira"),
    SEXTA("Sexta-feira"),
    SABADO("Sábado"),
    DOMINGO("Domingo");

    private String nome;

    DiaSemana(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
