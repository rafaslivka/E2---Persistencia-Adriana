package com.exemplo;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class TimesGUI extends JFrame {
    private JButton tocarHinoButton;
    private Times corinthians;

    public TimesGUI() {
        corinthians = new Times("Corinthians", 30, 30);

        setTitle("CORINTHIANS O MAIOR DE TODOS!");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        tocarHinoButton = new JButton("Tocar o hino mais lindo do mundo");
        tocarHinoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> corinthians.tocarHino()).start();
            }
        });

        add(tocarHinoButton);

        JLabel gifLabel = new JLabel();
        gifLabel.setIcon(new ImageIcon("D:/Tarefa3/Times.gif"));
        add(gifLabel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TimesGUI gui = new TimesGUI();
            gui.setVisible(true);
        });
    }
}

class Times {
    private String nome;
    private int titulos;
    private int quantidadeMembros;

    public Times(String nome, int titulos, int quantidadeMembros) {
        this.nome = nome;
        this.titulos = titulos;
        this.quantidadeMembros = quantidadeMembros;
    }

    public void mostrarInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Títulos: " + titulos);
        System.out.println("Quantidade de Membros: " + quantidadeMembros);
    }

    public void tocarHino() {
        try {
            File hinoFile = new File("D:/Tarefa3Adriana/hino_corinthians.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(hinoFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            System.out.println("Tocando o hino do " + nome + "...");
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });
            Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
