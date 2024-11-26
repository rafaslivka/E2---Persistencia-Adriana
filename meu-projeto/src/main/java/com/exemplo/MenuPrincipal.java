package com.exemplo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Menu Principal");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        String gifPath = "D:\\MeusProjetos\\musicas e gif's\\gif_para_menu.gif";
        ImageIcon gifIcon = new ImageIcon(gifPath);
        JLabel gifLabel = new JLabel(gifIcon);
        gifLabel.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 1, 10, 10));  // Aumenta o espaçamento entre os botões
        panel.setOpaque(false);  // Deixa o fundo transparente para que o GIF apareça atrás

        JButton bandasBtn = createButton("Bandas");
        bandasBtn.addActionListener(e -> new BandasGUI().setVisible(true));

        JButton carrosBtn = createButton("Carros");
        carrosBtn.addActionListener(e -> new CarrosGUI().setVisible(true));

        JButton cervejasBtn = createButton("Cervejas");
        cervejasBtn.addActionListener(e -> new CervejasGUI().setVisible(true));

        JButton chocolatesBtn = createButton("Chocolates");
        chocolatesBtn.addActionListener(e -> new ChocolatesGUI().setVisible(true));

        JButton eletronicosBtn = createButton("Eletrônicos");
        eletronicosBtn.addActionListener(e -> {
            ArrayList<Eletronicos> dispositivos = new ArrayList<>();
            dispositivos.add(new Eletronicos("Smartphone", "Samsung", "Celular", 64, 80));
            dispositivos.add(new Eletronicos("Laptop", "Dell", "Computador", 512, 50));
            new EletronicosGUI(dispositivos).setVisible(true);
        });

        JButton frutasBtn = createButton("Frutas");
        frutasBtn.addActionListener(e -> {
            ArrayList<Frutas> frutas = new ArrayList<>();
            frutas.add(new Frutas("Kiwi", "Primavera", 0.5));
            frutas.add(new Frutas("Abacaxi", "Verão", 1.2));
            frutas.add(new Frutas("Uva", "Verão", 0.3));
            frutas.add(new Frutas("Romã", "Outono", 0.4));
            frutas.add(new Frutas("Banana", "Todo o ano", 1.1));
            frutas.add(new Frutas("Manga", "Verão", 1.0));
            frutas.add(new Frutas("Melancia", "Verão", 3.5));
            frutas.add(new Frutas("Laranja", "Inverno", 0.5));
            frutas.add(new Frutas("Pêra", "Outono", 0.8));
            frutas.add(new Frutas("Limão Siciliano", "Primavera", 0.4));
            new FrutasGUI(frutas);
        });

        JButton jogadoresBtn = createButton("Jogadores");
        jogadoresBtn.addActionListener(e -> new JogadoresGUI().setVisible(true));

        JButton redesSociaisBtn = createButton("Redes Sociais");
        redesSociaisBtn.addActionListener(e -> new RedesSociaisGUI().setVisible(true));

        JButton streamingsBtn = createButton("Streamings");
        streamingsBtn.addActionListener(e -> new StreamingsGUI().setVisible(true));

        JButton timesBtn = createButton("Times");
        timesBtn.addActionListener(e -> new TimesGUI().setVisible(true));

        panel.add(bandasBtn);
        panel.add(carrosBtn);
        panel.add(cervejasBtn);
        panel.add(chocolatesBtn);
        panel.add(eletronicosBtn);
        panel.add(frutasBtn);
        panel.add(jogadoresBtn);
        panel.add(redesSociaisBtn);
        panel.add(streamingsBtn);
        panel.add(timesBtn);

        mainPanel.add(panel, BorderLayout.WEST);
        mainPanel.add(gifLabel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
    }

    // Método para criar botões mais estilizados
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 50));
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuPrincipal().setVisible(true));
    }
}
