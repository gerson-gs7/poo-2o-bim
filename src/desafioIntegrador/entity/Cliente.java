package desafioIntegrador.entity;

import desafioIntegrador.exceptions.CadastroInvalidoException;

public class Cliente {
    private final int codigo;
    private final String nome;
    private final String email;

    public Cliente(int codigo, String nome, String email) throws CadastroInvalidoException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new CadastroInvalidoException("O nome do cliente é obrigatório.");
        }
        if (email == null || !email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new CadastroInvalidoException("Informe um e-mail válido.");
        }
        this.codigo = codigo;
        this.nome = nome.trim();
        this.email = email.trim();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return codigo + " - " + nome;
    }
}
