package br.com.fiap.model.DTO;

public class Jogo {
    //Aributos
    private int id_nota;
    private int id_pontuacao;
    private float nocao_medica;
    private float nota;

    //Construtor vazio
    public Jogo(){}

    //Getters & Setters
    public int getId_nota() {return id_nota;}

    public void setId_nota(int id_nota) {this.id_nota = id_nota;}

    public int getId_pontuacao() {return id_pontuacao;}

    public void setId_pontuacao(int id_pontuacao) {this.id_pontuacao = id_pontuacao;}

    public float getNocao_medica() {return nocao_medica;}

    public void setNocao_medica(float nocao_medica) {this.nocao_medica = nocao_medica;}

    public float getNota() {return nota;}

    public void setNota(float nota) {this.nota = nota;}
}