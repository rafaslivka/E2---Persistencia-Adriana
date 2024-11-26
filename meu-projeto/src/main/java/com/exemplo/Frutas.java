package com.exemplo;

import java.util.ArrayList;

public class Frutas {
    private String nome;
    private String epoca;
    private double peso;
    private ArrayList<String> historico;

    public Frutas(String nome, String epoca, double peso) {
        this.nome = nome;
        this.epoca = epoca;
        this.peso = peso;
        this.historico = new ArrayList<>();
        this.historico.add("Criada fruta: " + nome + " com peso " + peso + "kg.");
    }

    public String getNome() {
        return nome;
    }

    public String getEpoca() {
        return epoca;
    }

    public double getPeso() {
        return peso;
    }

    public void atualizarPeso(double novoPeso) {
        this.historico.add("Peso atualizado: " + this.peso + "kg -> " + novoPeso + "kg.");
        this.peso = novoPeso;
    }

    public ArrayList<String> getHistorico() {
        return historico;
    }

    @Override
    public String toString() {
        return nome + " - Época: " + epoca + " - Peso: " + peso + "kg";
    }
}
