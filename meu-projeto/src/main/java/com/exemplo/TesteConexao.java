package com.exemplo;

public class TesteConexao {
    public static void main(String[] args) {
        if (ConexaoBD.getConnection() != null) {
            System.out.println("Conexão com o banco foi um sucesso!");
        } else {
            System.out.println("Erro ao conectar ao banco.");
        }
    }
}
