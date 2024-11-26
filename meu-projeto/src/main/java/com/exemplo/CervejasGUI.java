package com.exemplo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class CervejasGUI extends JFrame {
    @SuppressWarnings("FieldMayBeFinal")
    private JTextArea infoArea;
    @SuppressWarnings("FieldMayBeFinal")
    private JPanel menuPanel;
    private JPanel gifPanel; // Painel para o GIF
    private static Clip clipAtual;

    @SuppressWarnings("Convert2Lambda")
    public CervejasGUI() {
        setTitle("Catálogo de Cervejas");
        // Removido o "setExtendedState(JFrame.MAXIMIZED_BOTH)" para evitar que a janela abra maximizada
        setSize(800, 600); // Defina o tamanho desejado para a janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Catálogo de Cervejas", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        infoArea = new JTextArea();
        infoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setPreferredSize(new Dimension(400, 600));

        menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());

        JButton addButton = new JButton("Adicionar Cerveja");
        addButton.addActionListener(new ActionListener() {
            @SuppressWarnings("override")
            public void actionPerformed(ActionEvent e) {
                adicionarCerveja();
            }
        });

        menuPanel.add(addButton, BorderLayout.SOUTH);
        menuPanel.add(scrollPane, BorderLayout.CENTER);

        gifPanel = new JPanel();
        JLabel gifLabel = new JLabel(new ImageIcon("D:/MeusProjetos/musicas e gif's/Cervejas.GIF"));
        gifLabel.setHorizontalAlignment(JLabel.CENTER);
        gifPanel.add(gifLabel);
        add(gifPanel, BorderLayout.EAST);

        add(menuPanel, BorderLayout.WEST);
    }

    private void adicionarCerveja() {
        String marca = JOptionPane.showInputDialog("Marca da Cerveja:");
        String tipo = JOptionPane.showInputDialog("Tipo da Cerveja:");

        String[] tamanhos = {"350ml", "600ml"};
        String tamanho = (String) JOptionPane.showInputDialog(
                this,
                "Escolha o tamanho:",
                "Tamanho da Cerveja",
                JOptionPane.QUESTION_MESSAGE,
                null,
                tamanhos,
                tamanhos[0]
        );

        Cerveja novaCerveja = new Cerveja(marca, tipo, tamanho);
        infoArea.append(novaCerveja.mostrarInformacoes() + "\n");

        // Tocar música ao adicionar a cerveja
        tocarMusica("D:/MeusProjetos/musicas e gif's/cervejaMusica.wav");

        // Exibir um novo GIF
        JLabel newGifLabel = new JLabel(new ImageIcon("D:/MeusProjetos/musicas e gif's/cervejaNovo.gif"));
        newGifLabel.setHorizontalAlignment(JLabel.CENTER);
        gifPanel.removeAll();  // Remove o GIF anterior
        gifPanel.add(newGifLabel);  // Adiciona o novo GIF
        gifPanel.revalidate();  // Revalida o layout
        gifPanel.repaint();  // Atualiza a exibição
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private static void tocarMusica(String caminho) {
        try {
            File musica = new File(caminho);
            if (musica.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musica);
                clipAtual = AudioSystem.getClip();
                clipAtual.open(audioInputStream);
                clipAtual.start();
            } else {
                System.out.println("Arquivo de música não encontrado: " + caminho);
            }
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao tocar a música: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CervejasGUI frame = new CervejasGUI();
            frame.setVisible(true);
        });
    }

    public JPanel getGifPanel() {
        return gifPanel;
    }

    public void setGifPanel(JPanel gifPanel) {
        this.gifPanel = gifPanel;
    }
}

class Cerveja {
    private final String nome;
    private String tipo;
    @SuppressWarnings("FieldMayBeFinal")
    private String marca;

    public Cerveja(String marca, String tipo, String tamanho) {
        this.marca = marca;
        this.tipo = tipo;
        this.nome = tamanho;
    }

    public String mostrarInformacoes() {
        return "Marca: " + marca + ", Tipo: " + tipo + ", Tamanho: " + nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
