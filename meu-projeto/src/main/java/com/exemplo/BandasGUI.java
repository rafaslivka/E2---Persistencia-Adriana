package com.exemplo;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BandasGUI extends JFrame {

    private static Clip clipAtual;

    public BandasGUI() {
        // Configurações iniciais da janela
        setTitle("LockFist669");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Adicionando o GIF da banda
        JLabel gifLabel = new JLabel(new ImageIcon("D:/MeusProjetos/musicas e gif's/banda.gif"));
        gifLabel.setBounds(50, 10, 400, 247);
        add(gifLabel);

        // Botão para tocar música
        JButton tocarMusicaButton = new JButton("Tocar música escolhida");
        tocarMusicaButton.setBounds(150, 300, 200, 30);
        add(tocarMusicaButton);

        // ComboBox com as músicas disponíveis
        String[] musicas = {"Wasted", "Home-Cide"};
        JComboBox<String> comboMusicas = new JComboBox<>(musicas);
        comboMusicas.setBounds(150, 260, 200, 30);
        add(comboMusicas);

        // Ação do botão para tocar a música selecionada
        tocarMusicaButton.addActionListener((ActionEvent e) -> {
            String musicaSelecionada = (String) comboMusicas.getSelectedItem();
            String caminho = "D:/MeusProjetos/musicas e gif's/" + musicaSelecionada + ".wav";
            System.out.println("Caminho do arquivo: " + caminho);

            // Se já houver uma música tocando, para e fecha a anterior
            if (clipAtual != null && clipAtual.isRunning()) {
                clipAtual.stop();
                clipAtual.close();
            }
            // Chama o método para tocar a música
            tocarMusica(caminho);
        });
    }

    // Método para tocar a música selecionada
    private static void tocarMusica(String caminho) {
        try {
            File musica = new File(caminho);
            if (musica.exists()) {
                // Cria o fluxo de áudio
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musica);
                clipAtual = AudioSystem.getClip();
                clipAtual.open(audioInputStream);
                clipAtual.start();
            } else {
                System.out.println("Arquivo não encontrado: " + caminho);
            }
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Criação da janela BandasGUI
        BandasGUI gui = new BandasGUI();
        gui.setVisible(true);
    }
}
