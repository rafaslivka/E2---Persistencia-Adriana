package com.exemplo;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class FrutasGUI {
    private JFrame frame;
    private JTextArea infoArea;
    private JTextArea historicoArea;
    private JComboBox<String> frutaMenu;
    private ArrayList<Frutas> frutas;

    public FrutasGUI(ArrayList<Frutas> frutas) {
        this.frutas = frutas;
        criarInterface();
    }

    public void criarInterface() {
        frame = new JFrame("Controle de Frutas");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        infoArea = new JTextArea(5, 30);
        infoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(infoArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        historicoArea = new JTextArea(5, 30);
        historicoArea.setEditable(false);
        JScrollPane historicoScrollPane = new JScrollPane(historicoArea);
        panel.add(historicoScrollPane, BorderLayout.SOUTH);

        frutaMenu = new JComboBox<>();
        for (Frutas fruta : frutas) {
            frutaMenu.addItem(fruta.getNome());
        }

        frutaMenu.addActionListener(e -> {
            atualizarInfo();
            atualizarHistorico();
        });

        JPanel buttonPanel = new JPanel();
        JButton mostrarInfoButton = new JButton("Mostrar Informações");
        JButton atualizarPesoButton = new JButton("Atualizar Peso");
        JButton exibirEpocaButton = new JButton("Exibir Época do Ano");

        mostrarInfoButton.addActionListener(e -> atualizarInfo());
        atualizarPesoButton.addActionListener(e -> {
            Frutas frutaSelecionada = getFrutaSelecionada();
            if (frutaSelecionada != null) {
                String novoPeso = JOptionPane.showInputDialog("Digite o novo peso:");
                if (novoPeso != null && !novoPeso.isEmpty()) {
                    try {
                        frutaSelecionada.atualizarPeso(Double.parseDouble(novoPeso));
                        atualizarHistorico();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Peso inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        exibirEpocaButton.addActionListener(e -> {
            Frutas frutaSelecionada = getFrutaSelecionada();
            if (frutaSelecionada != null) {
                JOptionPane.showMessageDialog(frame, "Época do ano: " + frutaSelecionada.getEpoca());
            }
        });

        buttonPanel.add(frutaMenu);
        buttonPanel.add(mostrarInfoButton);
        buttonPanel.add(atualizarPesoButton);
        buttonPanel.add(exibirEpocaButton);

        panel.add(buttonPanel, BorderLayout.NORTH);

        JLabel gifLabel = new JLabel(new ImageIcon("D:/Tarefa3/frutas.gif"));
        panel.add(gifLabel, BorderLayout.WEST);

        frame.add(panel);
        frame.setVisible(true);
    }

    private Frutas getFrutaSelecionada() {
        String nomeSelecionado = (String) frutaMenu.getSelectedItem();
        for (Frutas fruta : frutas) {
            if (fruta.getNome().equals(nomeSelecionado)) {
                return fruta;
            }
        }
        return null;
    }

    private void atualizarInfo() {
        Frutas frutaSelecionada = getFrutaSelecionada();
        if (frutaSelecionada != null) {
            infoArea.setText("");
            infoArea.append("Nome: " + frutaSelecionada.getNome() + "\n");
            infoArea.append("Época: " + frutaSelecionada.getEpoca() + "\n");
            infoArea.append("Peso: " + frutaSelecionada.getPeso() + " kg\n");
        }
    }

    private void atualizarHistorico() {
        Frutas frutaSelecionada = getFrutaSelecionada();
        if (frutaSelecionada != null) {
            historicoArea.setText("");
            ArrayList<String> historico = frutaSelecionada.getHistorico();
            for (String evento : historico) {
                historicoArea.append(evento + "\n");
            }
        }
    }
}
