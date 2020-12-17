package model;

import java.util.List;

public class User {
    private String id;
    private String nome;
    private String email;
    private List<Integer> digitosUnicos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getDigitosUnicos() {
        return digitosUnicos;
    }

    public void setDigitosUnicos(List<Integer> digitosUnicos) {
        this.digitosUnicos = digitosUnicos;
    }
}
