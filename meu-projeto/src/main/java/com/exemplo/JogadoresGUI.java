package com.exemplo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JogadoresGUI extends JFrame {
    private JComboBox<String> comboBoxJogadores;
    private JTextArea infoArea;
    private JTextField pesoField;
    private JTextField numeroField;
    private Jogadores[] jogadores;

    public JogadoresGUI() {
        jogadores = new Jogadores[]{
                new Jogadores("Sócrates", 8, 81.0),
                new Jogadores("Rivelino", 10, 76.0),
                new Jogadores("Casagrande", 9, 85.0),
                new Jogadores("Tevez", 7, 74.0),
                new Jogadores("Ronaldo Fenômeno", 9, 83.0),
                new Jogadores("Cássio", 12, 89.0),
                new Jogadores("Jadson", 10, 75.0),
                new Jogadores("Gerson", 8, 73.0)
        };

        setTitle("Jogadores do Corinthians");
        setSize(800, 400); // Tamanho da janela ajustado para acomodar o GIF original
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        comboBoxJogadores = new JComboBox<>();
        for (Jogadores jogador : jogadores) {
            comboBoxJogadores.addItem(jogador.getNome());
        }
        comboBoxJogadores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarInformacoesJogador();
            }
        });
        topPanel.add(new JLabel("Selecione um jogador:"));
        topPanel.add(comboBoxJogadores);
        add(topPanel, BorderLayout.NORTH);

        infoArea = new JTextArea(8, 30);
        infoArea.setEditable(false);
        add(new JScrollPane(infoArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        bottomPanel.add(new JLabel("Novo Peso (kg):"));
        pesoField = new JTextField(5);
        bottomPanel.add(pesoField);

        bottomPanel.add(new JLabel("Novo Número:"));
        numeroField = new JTextField(5);
        bottomPanel.add(numeroField);

        JButton atualizarBtn = new JButton("Atualizar Informações");
        atualizarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizarJogador();
            }
        });
        bottomPanel.add(atualizarBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        // Carrega o GIF no tamanho original
        ImageIcon gifIcon = new ImageIcon("D:/Tarefa3/jogadores.gif");
        JLabel gifLabel = new JLabel(gifIcon);
        add(gifLabel, BorderLayout.WEST);

        mostrarInformacoesJogador();
    }

    private void mostrarInformacoesJogador() {
        int selectedIndex = comboBoxJogadores.getSelectedIndex();
        Jogadores jogador = jogadores[selectedIndex];
        infoArea.setText("Nome: " + jogador.getNome() + "\n"
                + "Número: " + jogador.getNumero() + "\n"
                + "Peso: " + jogador.getPeso() + " kg");
    }

    private void atualizarJogador() {
        int selectedIndex = comboBoxJogadores.getSelectedIndex();
        Jogadores jogador = jogadores[selectedIndex];
        try {
            double novoPeso = Double.parseDouble(pesoField.getText());
            jogador.atualizarPeso(novoPeso);

            int novoNumero = Integer.parseInt(numeroField.getText());
            jogador.atualizarNumero(novoNumero);

            mostrarInformacoesJogador();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    class Jogadores {
        private String nome;
        private int numero;
        private double peso;

        public Jogadores(String nome, int numero, double peso) {
            this.nome = nome;
            this.numero = numero;
            this.peso = peso;
        }

        public String getNome() {
            return nome;
        }

        public int getNumero() {
            return numero;
        }

        public double getPeso() {
            return peso;
        }

        public void atualizarPeso(double novoPeso) {
            this.peso = novoPeso;
        }

        public void atualizarNumero(int novoNumero) {
            this.numero = novoNumero;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JogadoresGUI().setVisible(true);
            }
        });
    }
}
