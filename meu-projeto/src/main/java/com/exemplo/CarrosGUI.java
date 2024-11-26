package com.exemplo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarrosGUI extends JFrame {
    private final JTextArea infoArea;
    private final JPanel menuPanel;
    @SuppressWarnings("FieldMayBeFinal")
    private JPanel gifPanel; // Painel para o GIF

    @SuppressWarnings("Convert2Lambda")
    public CarrosGUI() {
        setTitle("Cadastro de Carros");
        setSize(800, 600); // Defina o tamanho desejado para a janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Cadastro de Carros", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        infoArea = new JTextArea();
        infoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setPreferredSize(new Dimension(400, 600));

        menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());

        JButton addButton = new JButton("Cadastrar Carro");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCarro();
            }
        });

        menuPanel.add(addButton, BorderLayout.SOUTH);
        menuPanel.add(scrollPane, BorderLayout.CENTER);

        gifPanel = new JPanel();
        JLabel gifLabel = new JLabel(new ImageIcon("D:/MeusProjetos/musicas e gif's/carros.gif"));
        gifLabel.setHorizontalAlignment(JLabel.CENTER);
        gifPanel.add(gifLabel);
        add(gifPanel, BorderLayout.EAST);

        add(menuPanel, BorderLayout.WEST);
    }

    private void cadastrarCarro() {
        String nome = JOptionPane.showInputDialog(this, "Nome do Carro:");
        String modelo = JOptionPane.showInputDialog(this, "Modelo do Carro:");
        String anoString = JOptionPane.showInputDialog(this, "Ano do Carro:");
        int ano = Integer.parseInt(anoString);

        Carros novoCarro = new Carros(nome, modelo, ano);
        infoArea.append(novoCarro.mostrarInformacoes() + "\n");

        // Exibir um novo GIF
        JLabel newGifLabel = new JLabel(new ImageIcon("D:/MeusProjetos/musicas e gif's/carroNovo.gif"));
        newGifLabel.setHorizontalAlignment(JLabel.CENTER);
        gifPanel.removeAll();  // Remove o GIF anterior
        gifPanel.add(newGifLabel);  // Adiciona o novo GIF
        gifPanel.revalidate();  // Revalida o layout
        gifPanel.repaint();  // Atualiza a exibição
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CarrosGUI frame = new CarrosGUI();
            frame.setVisible(true);
        });
    }
}

class Carros {
    private final String nome;
    private String modelo;
    @SuppressWarnings("FieldMayBeFinal")
    private int ano;

    public Carros(String nome, String modelo, int ano) {
        this.nome = nome;
        this.modelo = modelo;
        this.ano = ano;
    }

    public String mostrarInformacoes() {
        return "Carro: " + nome + "\nModelo: " + modelo + "\nAno: " + ano;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
