package com.exemplo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Streamings {
    private String nome;
    private int quantidadeUsuarios;
    private int quantidadeFilmes;

    public Streamings(String nome, int quantidadeUsuarios, int quantidadeFilmes) {
        this.nome = nome;
        this.quantidadeUsuarios = quantidadeUsuarios;
        this.quantidadeFilmes = quantidadeFilmes;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadeUsuarios() {
        return quantidadeUsuarios;
    }

    public int getQuantidadeFilmes() {
        return quantidadeFilmes;
    }

    public void atualizarQuantidadeFilmes(int novaQuantidade) {
        this.quantidadeFilmes = novaQuantidade;
        System.out.println("Quantidade de filmes atualizada para: " + quantidadeFilmes);
    }

    public void atualizarQuantidadeUsuarios(int novaQuantidade) {
        this.quantidadeUsuarios = novaQuantidade;
        System.out.println("Quantidade de usuários atualizada para: " + quantidadeUsuarios);
    }
}

public class StreamingsGUI extends JFrame {
    private JComboBox<String> comboBoxStreamings;
    private JTextArea infoArea;
    private JTextField filmesField, usuariosField;
    private Streamings[] streamings;

    public StreamingsGUI() {
        streamings = new Streamings[]{
                new Streamings("Netflix", 231000000, 4000),
                new Streamings("Prime Video", 200000000, 5000),
                new Streamings("Disney+", 160000000, 3000),
                new Streamings("Globoplay", 15000000, 2000),
                new Streamings("Star+", 15000000, 1500),
                new Streamings("HBO Max", 76000000, 2500)
        };

        setTitle("Gerenciamento de Streamings");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        comboBoxStreamings = new JComboBox<>();
        for (Streamings streaming : streamings) {
            comboBoxStreamings.addItem(streaming.getNome());
        }
        comboBoxStreamings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInformacoesStreaming();
            }
        });
        topPanel.add(new JLabel("Selecione um serviço de streaming:"));
        topPanel.add(comboBoxStreamings);
        add(topPanel, BorderLayout.NORTH);

        infoArea = new JTextArea(8, 30);
        infoArea.setEditable(false);
        add(new JScrollPane(infoArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(3, 2));

        bottomPanel.add(new JLabel("Nova Quantidade de Filmes:"));
        filmesField = new JTextField(10);
        bottomPanel.add(filmesField);

        bottomPanel.add(new JLabel("Nova Quantidade de Usuários:"));
        usuariosField = new JTextField(10);
        bottomPanel.add(usuariosField);

        JButton atualizarBtn = new JButton("Atualizar");
        atualizarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarStreaming();
            }
        });
        bottomPanel.add(atualizarBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        JLabel gifLabel = new JLabel();
        gifLabel.setIcon(new ImageIcon("D:/Tarefa3/Streamings.gif"));
        add(gifLabel, BorderLayout.EAST);

        mostrarInformacoesStreaming();
    }

    private void mostrarInformacoesStreaming() {
        int selectedIndex = comboBoxStreamings.getSelectedIndex();
        Streamings streaming = streamings[selectedIndex];
        infoArea.setText("Nome: " + streaming.getNome() + "\n"
                + "Quantidade de Usuários: " + streaming.getQuantidadeUsuarios() + "\n"
                + "Quantidade de Filmes: " + streaming.getQuantidadeFilmes());
    }

    private void atualizarStreaming() {
        int selectedIndex = comboBoxStreamings.getSelectedIndex();
        Streamings streaming = streamings[selectedIndex];
        try {
            int novaQuantidadeFilmes = Integer.parseInt(filmesField.getText());
            int novaQuantidadeUsuarios = Integer.parseInt(usuariosField.getText());
            streaming.atualizarQuantidadeFilmes(novaQuantidadeFilmes);
            streaming.atualizarQuantidadeUsuarios(novaQuantidadeUsuarios);
            mostrarInformacoesStreaming();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira números válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StreamingsGUI().setVisible(true);
            }
        });
    }
}
