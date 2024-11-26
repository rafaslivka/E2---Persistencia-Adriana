package com.exemplo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class ChocolatesGUI extends JFrame {
    private JTextArea infoArea;
    @SuppressWarnings("FieldMayBeFinal")
    private JList<ChocolateInfo> chocolateList;
    @SuppressWarnings("FieldMayBeFinal")
    private DefaultListModel<ChocolateInfo> chocolateListModel;
    private ArrayList<ChocolateInfo> chocolates;

    public ChocolatesGUI() {
        setTitle("Catálogo de Chocolates");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Estilos de fontes
        Font labelFont = new Font("Arial", Font.BOLD, 24);
        Font buttonFont = new Font("Arial", Font.PLAIN, 16);

        chocolates = new ArrayList<>();
        chocolateListModel = new DefaultListModel<>();

        // Cabeçalho
        JLabel titleLabel = new JLabel("Catálogo de Chocolates", JLabel.CENTER);
        titleLabel.setFont(labelFont);
        add(titleLabel, BorderLayout.NORTH);

        // Lista de Chocolates
        chocolateList = new JList<>(chocolateListModel);
        chocolateList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        chocolateList.addListSelectionListener(e -> mostrarInformacoes());

        JScrollPane listScrollPane = new JScrollPane(chocolateList);
        listScrollPane.setPreferredSize(new Dimension(300, 600));

        // Área de informações
        infoArea = new JTextArea();
        infoArea.setEditable(false);
        JScrollPane infoScrollPane = new JScrollPane(infoArea);
        infoScrollPane.setPreferredSize(new Dimension(300, 600));

        // Botão de adicionar
        JButton addButton = new JButton("Adicionar Chocolate");
        addButton.setFont(buttonFont);
        addButton.setBackground(new Color(51, 153, 255)); // Azul mais suave
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setPreferredSize(new Dimension(250, 40));
        addButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        addButton.addActionListener(e -> adicionarChocolate());

        // Painel esquerdo com a lista, informações e o botão
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));  // Usando BoxLayout para acomodar os componentes verticalmente
        leftPanel.add(listScrollPane);
        leftPanel.add(infoScrollPane);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  // Centraliza o botão
        buttonPanel.add(addButton);
        leftPanel.add(buttonPanel);

        // Painel com o GIF
        JLabel gifLabel = new JLabel(new ImageIcon("D:/Tarefa3/chocolates.gif"));
        gifLabel.setHorizontalAlignment(JLabel.CENTER);
        gifLabel.setPreferredSize(new Dimension(500, 600));

        // Adicionando painéis à janela principal
        add(leftPanel, BorderLayout.WEST);
        add(gifLabel, BorderLayout.CENTER);
    }

    private void adicionarChocolate() {
        String nome = JOptionPane.showInputDialog(this, "Nome do Chocolate:");
        String tipo = JOptionPane.showInputDialog(this, "Tipo do Chocolate:");

        if (nome != null && tipo != null && !nome.trim().isEmpty() && !tipo.trim().isEmpty()) {
            ChocolateInfo novoChocolate = new ChocolateInfo(nome, tipo);
            chocolates.add(novoChocolate);
            chocolateListModel.addElement(novoChocolate);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
        }
    }

    private void mostrarInformacoes() {
        ChocolateInfo selectedChocolate = chocolateList.getSelectedValue();
        if (selectedChocolate != null) {
            infoArea.setText(selectedChocolate.mostrarInformacoes());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChocolatesGUI frame = new ChocolatesGUI();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);  // Centraliza a janela
        });
    }

    public JTextArea getInfoArea() {
        return infoArea;
    }

    public void setInfoArea(JTextArea infoArea) {
        this.infoArea = infoArea;
    }

    public ArrayList<ChocolateInfo> getChocolates() {
        return chocolates;
    }

    public void setChocolates(ArrayList<ChocolateInfo> chocolates) {
        this.chocolates = chocolates;
    }
}

class ChocolateInfo {
    private final String nome;
    private String tipo;
    @SuppressWarnings("FieldMayBeFinal")
    private String marca;

    public ChocolateInfo(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
        this.marca = "Nestlé"; // Pode ser alterado para ser dinâmico
    }

    public String mostrarInformacoes() {
        return "Nome: " + nome + ", Tipo: " + tipo + ", Marca: " + marca;
    }

    // Modificação no método toString
    @Override
    public String toString() {
        return nome + " - " + tipo; // Exibe o nome e o tipo na lista
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }
}
