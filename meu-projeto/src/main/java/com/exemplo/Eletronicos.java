package com.exemplo;

public class Eletronicos {

    private String nome;
    private String marca;
    private String tipo;
    private int armazenamento;
    private int bateria;

    public Eletronicos(String nome, String marca, String tipo, int armazenamento, int bateria) {
        this.nome = nome;
        this.marca = marca;
        this.tipo = tipo;
        this.armazenamento = armazenamento;
        this.bateria = bateria;
    }

    public String getNome() {
        return nome;
    }

    public String getMarca() {
        return marca;
    }

    public String getTipo() {
        return tipo;
    }

    public int getArmazenamento() {
        return armazenamento;
    }

    public int getBateria() {
        return bateria;
    }

    public void ligar() {
        // Simulação de ligar o eletrônico
        System.out.println(nome + " está ligado.");
    }

    public void desligar() {
        // Simulação de desligar o eletrônico
        System.out.println(nome + " está desligado.");
    }
}
